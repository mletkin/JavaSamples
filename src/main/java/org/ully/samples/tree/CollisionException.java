package org.ully.samples.tree;

public class CollisionException extends TreeException {
    final int level;

    public CollisionException(int level) {
        super("Collision on level " + level);
        this.level = level;
    }

    public int level() {
        return level;
    }
}