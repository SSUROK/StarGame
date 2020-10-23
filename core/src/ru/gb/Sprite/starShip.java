package ru.gb.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class starShip extends Sprite {

    private Vector2 v, touch, tmp;


    public starShip(TextureRegion region){
        super(region);
        v = new Vector2(1, 1);
        tmp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight()/3);
        touch = new Vector2(worldBounds.pos).sub(0.0f, 0.4f);
        pos.set(worldBounds.pos).sub(0.0f, 0.4f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v = this.touch.cpy().sub(pos).setLength(0.02f);
        return false;
    }

    @Override
    public void update(float delta){
        tmp.set(touch);
        if (tmp.sub(pos).len() <= v.len()){
            pos.set(touch);
        } else {
            pos.add(v);
        }
    }

}
