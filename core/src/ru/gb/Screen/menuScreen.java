package ru.gb.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


import ru.gb.base.baseScreen;

public class menuScreen extends baseScreen{

    private Texture img;
    private Vector2 pos, dest;
    private float way;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2(1, 1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, pos.x, pos.y, 100, 100);
        batch.end();
        if (dest != null)
            move();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        dest = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
        return super.touchUp(screenX, screenY, pointer, button);
    }

    public void move() {
        if (pos.x != dest.x){
            if (pos.x < dest.x)
                pos.x++;
            else
                pos.x--;
        }
        if (pos.y != dest.y){
            if (pos.y < dest.y)
                pos.y++;
            else
                pos.y--;
        }
    }

}
