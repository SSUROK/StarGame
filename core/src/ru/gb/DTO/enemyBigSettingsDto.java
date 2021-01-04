package ru.gb.DTO;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.enemySettingsDto;
import ru.gb.utils.Regions;

public class enemyBigSettingsDto extends enemySettingsDto {

    private static final Vector2 startSpeed = new Vector2(0f, -0.4f);
    private static final Vector2 normalSpeed = new Vector2(0f, -0.005f);
    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final int ENEMY_BIG_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_BIG_HP = 10;

    public enemyBigSettingsDto(TextureAtlas atlas, Sound bulletSound) {
        TextureRegion enemy0 = atlas.findRegion("enemy2");
        setRegions(Regions.split(enemy0, 1, 2, 2));
        setV(startSpeed);
        setV0(normalSpeed);
        setBulletRegion(atlas.findRegion("bulletEnemy"));
        setBulletHeight(ENEMY_BIG_BULLET_HEIGHT);
        setBulletV(new Vector2(0f, -0.25f));
        setBulletSound(bulletSound);
        setDamage(ENEMY_BIG_DAMAGE);
        setReloadInterval(ENEMY_BIG_RELOAD_INTERVAL);
        setHeight(ENEMY_BIG_HEIGHT);
        setHp(ENEMY_BIG_HP);
    }
}
