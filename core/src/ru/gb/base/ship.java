package ru.gb.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.pool.BulletPool;
import ru.gb.Sprite.Bullet;

public abstract class ship extends Sprite {

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Sound bulletSound;
    protected float bulletHeight;
    protected int damage;

    protected final Vector2 v;
    protected final Vector2 v0;
    protected final Vector2 bulletV;
    protected final Vector2 bulletPos;

    protected float reloadTimer;
    protected float reloadInterval;

    protected int hp;

    public ship() {
        v = new Vector2();
        v0 = new Vector2();
        bulletV = new Vector2();
        bulletPos = new Vector2();
    }

    public ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        v = new Vector2();
        v0 = new Vector2();
        bulletV = new Vector2();
        bulletPos = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if (getTop() < worldBounds.getTop()) {
            if (reloadTimer >= reloadInterval) {
                reloadTimer = 0;
                shoot();
            }
            reloadTimer += delta;
        }
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, damage, bulletHeight);
        bulletSound.play();
    }
}
