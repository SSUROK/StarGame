package ru.gb.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


import ru.gb.Sprite.Background;
import ru.gb.Sprite.exitBt;
import ru.gb.Sprite.playBt;
import ru.gb.Sprite.starShip;
import ru.gb.base.baseScreen;
import ru.gb.math.Rect;

public class menuScreen extends baseScreen{

    private Texture bg, starShip, exit, play;
    private Background background;
    private starShip ship;
    private exitBt exitBt;
    private playBt playBt;
    public static boolean inGame = false;

    @Override
    public void show() {
        super.show();
        bg = new Texture("bg.jpg");
        play = new Texture("playbutton.png");
        exit = new Texture("exitbutton.png");
        starShip = new Texture("starship.png");
        playBt = new playBt(new TextureRegion(play));
        exitBt = new exitBt(new TextureRegion(exit));
        ship = new starShip(new TextureRegion(starShip));
        background = new Background(new TextureRegion(bg));
        background.setAngle(90);
        background.setScale(0.6f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        exitBt.draw(batch);
        ship.update(delta);
        isPlay();
        batch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        exit.dispose();
        starShip.dispose();
        play.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        ship.resize(worldBounds);
        exitBt.resize(worldBounds);
        playBt.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        boolean exitX = (touch.x >= exitBt.pos.x - exitBt.getHalfWidth()) && touch.x <= exitBt.pos.x + exitBt.getHalfWidth();
        boolean exitY = (touch.y >= exitBt.pos.y - exitBt.getHalfHeight()) && touch.y <= exitBt.pos.y + exitBt.getHalfHeight();

        boolean playX = (touch.x >= playBt.pos.x - playBt.getHalfWidth()) && touch.x <= playBt.pos.x + playBt.getHalfWidth();
        boolean playY = (touch.y >= playBt.pos.y - playBt.getHalfHeight()) && touch.y <= playBt.pos.y + playBt.getHalfHeight();

        if (exitX && exitY){
            exitBt.touchDown(touch, pointer, button);
        } else if (playX && playY && !inGame) {
            playBt.touchDown(touch, pointer, button);
        } else if (inGame) {
            ship.touchDown(touch, pointer, button);
        }
        return false;
    }

    public void isPlay(){
        if (inGame){
            ship.draw(batch);
        } else {
            playBt.draw(batch);
        }
    }

}
