package tbooop.model.boss.impl.DukeOfEyes;

import tbooop.commons.impl.HealthImpl;
import tbooop.model.boss.api.AbstractBoss;

public class DukeOfEyes extends AbstractBoss<DoESM.State> {

    public DukeOfEyes() {
        super(new HealthImpl(20), 0.3, 20); // TODO tweak
        setStateMachine(new DoESM(this));
    }
}
