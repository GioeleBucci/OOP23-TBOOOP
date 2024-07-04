package tbooop.view.sound_manager;

import java.util.List;

public enum Sound {
    SHOOT("sfx/shoot.wav"),
    HIT("sfx/dmg1.wav", "sfx/dmg2.wav", "sfx/dmg3.wav"),
    KEY_PICKUP("sfx/key.wav"),
    ITEM_PICKUP("sfx/item.wav"),
    HEART_PICKUP("sfx/heart1.wav", "sfx/heart2.wav"),
    COIN_PICKUP("sfx/coin.wav"),
    DOOR_OPEN("sfx/door_open1.wav", "sfx/door_open2.wav"),
    BOSS_DEATH("sfx/gore_explosion.wav");

    private final List<String> urls;

    Sound(String... urls) {
        this.urls = List.of(urls);
    }

    public String getUrl() {
        return urls.get((int) (Math.random() * urls.size()));
    }
}
