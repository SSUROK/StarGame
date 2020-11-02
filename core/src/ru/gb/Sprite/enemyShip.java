package ru.gb.Sprite;

import ru.gb.base.ship;
import ru.gb.base.enemySettingsDto;
import ru.gb.math.Rect;
import ru.gb.pool.BulletPool;

public class enemyShip extends ship {

    public enemyShip(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        bulletPos.set(pos.x, getBottom());
        super.update(delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
        if (getTop() < worldBounds.getTop()){
            v.set(v0);
            System.out.println(v);
        }
    }

    public void set(enemySettingsDto settings) {
        this.reloadTimer = settings.getReloadInterval();
        this.regions = settings.getRegions();
        this.v.set(settings.getV());
        this.v0.set(settings.getV0());
        this.bulletRegion = settings.getBulletRegion();
        this.bulletHeight = settings.getBulletHeight();
        this.bulletV.set(settings.getBulletV());
        this.bulletSound = settings.getBulletSound();
        this.damage = settings.getDamage();
        this.reloadInterval = settings.getReloadInterval();
        setHeightProportion(settings.getHeight());
        this.hp = settings.getHp();
    }

}
