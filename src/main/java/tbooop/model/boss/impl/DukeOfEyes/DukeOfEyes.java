package tbooop.model.boss.impl.DukeOfEyes;

import tbooop.commons.impl.HealthImpl;
import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.player.api.Player;

public class DukeOfEyes extends AbstractBoss<DoESM.State> {

    public DukeOfEyes(Player p) {
        super(new HealthImpl(20), 0.035, 30);
        setStateMachine(new DoESM(this, p));
    }
}