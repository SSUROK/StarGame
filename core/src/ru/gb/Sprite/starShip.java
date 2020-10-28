package ru.gb.Sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class starShip extends Sprite {

    private Vector2 v, touch, tmp;
    private static final float V_LEN = 0.02f;
    private Rect worldBounds;


    public starShip(Texture region){
        super(new TextureRegion(region));
        setHeightProportion(0.4f);
        v = new Vector2(1, 1);
        tmp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        touch = new Vector2(worldBounds.pos).sub(0.0f, 0.4f);
        pos.set(worldBounds.pos).sub(0.0f, 0.4f);
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case 29:
            case 21:
                v.set(0.01f, 0.0f).setLength(V_LEN);
                touch.set(pos.cpy().sub(v));
                break;
            case 32:
            case 22:
                v.set(0.01f, 0.0f).setLength(V_LEN);
                touch.set(pos.cpy().add(v));
                break;
            case 51:
            case 19:
                v.set(0.0f, 0.01f).setLength(V_LEN);
                touch.set(pos.cpy().add(v));
                break;
            case 47:
            case 20:
                v.set(0.0f, 0.01f).setLength(V_LEN);
                touch.set(pos.cpy().sub(v));
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v = this.touch.cpy().sub(pos).setLength(V_LEN);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return false;
    }

    @Override
    public void update(float delta){
        tmp.set(touch);
        if (tmp.sub(pos).len() <= v.len()) {
            pos.set(touch);
        } else {
            pos.add(v);
        }
        if (pos.x < worldBounds.getLeft()) {
            v.set(1,1);
            pos.x = (worldBounds.getRight()) - 0.001f;
        }
        if (pos.x > worldBounds.getRight()) {
            v.set(1,1);
            pos.x = (worldBounds.getLeft()) + 0.001f;
        }
    }

}
