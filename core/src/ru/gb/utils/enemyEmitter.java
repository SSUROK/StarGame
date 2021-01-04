package ru.gb.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.gb.base.enemySettingsDto;
import ru.gb.DTO.enemyBigSettingsDto;
import ru.gb.DTO.enemyMediumSettingsDto;
import ru.gb.DTO.enemySmallSettingsDto;
import ru.gb.math.Rect;
import ru.gb.math.Rnd;
import ru.gb.pool.enemyShipPool;
import ru.gb.Sprite.enemyShip;

public class enemyEmitter {

    private static final float GENERATE_INTERVAL = 4f;

    private Rect worldBounds;
    private enemyShipPool enemyShipPool;
    private float generateTimer;

    private enemySettingsDto enemySmallSettingsDto;
    private enemySettingsDto enemyMediumSettingsDto;
    private enemySettingsDto enemyBigSettingsDto;

    public enemyEmitter(Rect worldBounds, enemyShipPool enemyShipPool, Sound bulletSound, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.enemyShipPool = enemyShipPool;
        enemySmallSettingsDto = new enemySmallSettingsDto(atlas, bulletSound);
        enemyMediumSettingsDto = new enemyMediumSettingsDto(atlas, bulletSound);
        enemyBigSettingsDto = new enemyBigSettingsDto(atlas, bulletSound);
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0;
            enemyShip enemyShip = enemyShipPool.obtain();
            float type = (float) Math.random();
            if (type < 0.5f) {
                enemyShip.set(enemySmallSettingsDto);
            } else if (type < 0.8f) {
                enemyShip.set(enemyMediumSettingsDto);
            } else {
                enemyShip.set(enemyBigSettingsDto);
            }
            enemyShip.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }
}
