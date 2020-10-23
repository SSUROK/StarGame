package ru.gb.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class exitBt extends Sprite {

    public exitBt(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight()/12);
        pos.set(worldBounds.getRight() - this.getHalfWidth(), worldBounds.getHeight()/2 - this.getHalfHeight());
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        Gdx.app.exit();
        return false;
    }}
