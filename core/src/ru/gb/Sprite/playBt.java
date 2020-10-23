package ru.gb.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.Screen.menuScreen;
import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class playBt extends Sprite {

    public playBt(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight()/12);
        pos.set(worldBounds.pos);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        menuScreen.inGame = true;
        return false;
    }

}
