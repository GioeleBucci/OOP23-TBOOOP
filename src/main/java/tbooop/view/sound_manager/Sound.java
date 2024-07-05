package tbooop.view.sound_manager;

import java.util.List;

public enum Sound {
    SHOOT("sfx/shoot.wav"),
    HURT("sfx/hurt.wav"),
    ENEMY_DEATH("sfx/enemy_death1.wav", "sfx/enemy_death2.wav", "sfx/enemy_death3.wav"),
    KEY_PICKUP("sfx/key.wav"),
    ITEM_PICKUP("sfx/item.wav"),
    HEART_PICKUP("sfx/heart1.wav", "sfx/heart2.wav"),
    COIN_PICKUP("sfx/coin.wav"),
    DOOR_UNLOCK("sfx/door_unlock.wav"),
    BOSS_DEATH("sfx/boss_death.wav");

    private final List<String> urls;

    Sound(String... urls) {
        this.urls = List.of(urls);
    }

    public String getUrl() {
        return urls.get((int) (Math.random() * urls.size()));
    }
}
