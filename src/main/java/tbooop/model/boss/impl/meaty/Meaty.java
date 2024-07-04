package tbooop.model.boss.impl.meaty;

import tbooop.commons.impl.HealthImpl;
import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.enemy.api.EnemyType;
import tbooop.model.player.api.Player;

public class Meaty extends AbstractBoss<MeatySM.State> {

    public Meaty(Player p) {
        super(new HealthImpl(45), 0, 14);
        setStateMachine(new MeatySM(this, p));
    }

    @Override
    public EnemyType getEnemyType() {
        return EnemyType.MEATY;
    }
}
