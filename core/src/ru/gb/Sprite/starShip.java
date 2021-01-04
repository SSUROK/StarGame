package ru.gb.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.Sprite;
import ru.gb.base.ship;
import ru.gb.math.Rect;
import ru.gb.pool.BulletPool;

public class starShip extends ship {

    private static final float SHIP_HEIGHT = 0.15f;
    private static final float MARGIN = 0.05f;
    private static final float RELOAD_INTERVAL = 0.2f;
    private static final int HP = 100;

    private static final int INVALID_POINTER = -1;

    private boolean pressedLeft;
    private boolean pressedRight;
    private boolean autoFire;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;
    private static int timer = 0;

    public starShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        this.bulletPool = bulletPool;
        this.bulletHeight = 0.01f;
        this.damage = 1;
        this.v0.set(0.5f, 0);
        this.bulletV.set(0, 0.5f);
        this.reloadInterval = RELOAD_INTERVAL;
        this.hp = HP;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SHIP_HEIGHT);
        setBottom(worldBounds.getBottom() + MARGIN);
    }

    @Override
    public void update(float delta) {

        bulletPos.set(pos.x, getTop());
        super.update(delta);
//        if (getRight() > worldBounds.getRight()) {
//            setRight(worldBounds.getRight());
//            stop();
//        } else if (getLeft() < worldBounds.getLeft()) {
//            setLeft(worldBounds.getLeft());
//            stop();
//        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        } else if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (autoFire){
            if (timer == 12) {
                shoot();
                timer = 0;
            } else {
                timer++;
            }
        }
    }

    public void dispose() {
        bulletSound.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x <  worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                if(!autoFire)
                    shoot();
                break;
            case Input.Keys.SPACE:
                if (autoFire){
                    System.out.println("false");
                    autoFire = false;
                } else {
                    System.out.println("true");
                    autoFire = true;
                }
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

}
