package ru.gb.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.gb.Sprite.starShip;
import ru.gb.base.baseScreen;
import ru.gb.math.Rect;
import ru.gb.Sprite.Background;
import ru.gb.Sprite.Star;
import ru.gb.pool.BulletPool;
import ru.gb.pool.enemyShipPool;
import ru.gb.utils.enemyEmitter;

public class GameScreen extends baseScreen {

    private static final int STAR_COUNT = 64;

    private TextureAtlas atlas;
    private Texture bg;

    private Background background;
    private Star[] stars;
    private starShip starShip;
    private BulletPool bulletPool;
    private enemyShipPool enemyShipPool;
    private enemyEmitter enemyEmitter;

    protected Music music;
    private Sound enemyBulletSound;


    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        bg = new Texture("textures/bg.jpg");
        enemyBulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));

        background = new Background(bg);
        bulletPool = new BulletPool();
        enemyShipPool = new enemyShipPool(bulletPool, worldBounds);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
        starShip = new starShip(atlas, bulletPool);
        enemyEmitter = new enemyEmitter(worldBounds, enemyShipPool, enemyBulletSound, atlas);

        music.setLooping(true);
        music.play();
        music.play();
        music.setLooping(true);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollision();
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        starShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyShipPool.dispose();
        music.dispose();
        enemyBulletSound.dispose();
        starShip.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        starShip.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        starShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        starShip.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        starShip.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        enemyShipPool.updateActiveSprites(delta);
        bulletPool.updateActiveSprites(delta);
        starShip.update(delta);
        enemyEmitter.generate(delta);
    }

    private void checkCollision() {

    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyShipPool.freeAllDestroyedActiveSprites();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        enemyShipPool.drawActiveSprites(batch);
        starShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        batch.end();
    }
}
