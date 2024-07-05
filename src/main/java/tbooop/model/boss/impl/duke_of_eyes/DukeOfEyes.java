package tbooop.model.boss.impl.duke_of_eyes;

import tbooop.commons.impl.HealthImpl;
import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.enemy.api.EnemyType;
import tbooop.model.player.api.Player;

public class DukeOfEyes extends AbstractBoss<DoESM.State> {

    public DukeOfEyes(Player p) {
        super(new HealthImpl(35), 0.035, 14);
        setStateMachine(new DoESM(this, p));
    }

    @Override
    public EnemyType getEnemyType() {
        return EnemyType.DUKE_OF_EYES;
    }
}
