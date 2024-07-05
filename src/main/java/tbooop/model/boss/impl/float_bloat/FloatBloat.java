package tbooop.model.boss.impl.float_bloat;

import tbooop.commons.impl.HealthImpl;
import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.enemy.api.EnemyType;
import tbooop.model.player.api.Player;

public class FloatBloat extends AbstractBoss<FBSM.State> {

    public FloatBloat(Player p) {
        super(new HealthImpl(40), 0.04, 14);
        setStateMachine(new FBSM(this, p));
    }

    @Override
    public EnemyType getEnemyType() {
        return EnemyType.FLOATBLOAT;
    }

}
