package ru.gb.DTO;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.enemySettingsDto;
import ru.gb.utils.Regions;

public class enemySmallSettingsDto extends enemySettingsDto {

    private static final Vector2 startSpeed = new Vector2(0f, -0.4f);
    private static final Vector2 normalSpeed = new Vector2(0f, -0.2f);
    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final int ENEMY_SMALL_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 1.5f;
    private static final int ENEMY_SMALL_HP = 1;

    public enemySmallSettingsDto(TextureAtlas atlas, Sound bulletSound) {
        TextureRegion enemy0 = atlas.findRegion("enemy0");
        setRegions(Regions.split(enemy0, 1, 2, 2));
        setV(startSpeed);
        setV0(normalSpeed);
        setBulletRegion(atlas.findRegion("bulletEnemy"));
        setBulletHeight(ENEMY_SMALL_BULLET_HEIGHT);
        setBulletV(new Vector2(0f, -0.3f));
        setBulletSound(bulletSound);
        setDamage(ENEMY_SMALL_DAMAGE);
        setReloadInterval(ENEMY_SMALL_RELOAD_INTERVAL);
        setHeight(ENEMY_SMALL_HEIGHT);
        setHp(ENEMY_SMALL_HP);
    }
}
