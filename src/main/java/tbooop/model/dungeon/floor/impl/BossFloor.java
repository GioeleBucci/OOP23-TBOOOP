package tbooop.model.dungeon.floor.impl;

import tbooop.model.dungeon.floor.api.BaseFloor;
import tbooop.model.enemy.api.EnemyFactory;

public class BossFloor extends BaseFloor {
    public BossFloor(final EnemyFactory enemyFactory) {
        super(4, enemyFactory, () -> 1);
    }
}
