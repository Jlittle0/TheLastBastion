package entities;

import static utilz.Constants.EnemyConstants.BADGER;
import static utilz.Constants.EnemyConstants.Badgers.*;

public class Badger extends Enemy {
    public Badger() {
        super(0, 0, BADGER_WIDTH, BADGER_HEIGHT, BADGER);
    }
}
