package ru.gb.pool;

import ru.gb.base.SpritesPool;
import ru.gb.math.Rect;
import ru.gb.Sprite.enemyShip;

public class enemyShipPool extends SpritesPool<enemyShip> {

    private BulletPool bulletPool;
    private Rect worldBounds;

    public enemyShipPool(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected enemyShip newObject() {
        return new enemyShip(bulletPool, worldBounds);
    }
}