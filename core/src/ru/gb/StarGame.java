package ru.gb;

import com.badlogic.gdx.Game;

import ru.gb.Screen.menuScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new menuScreen(this));
	}
}
