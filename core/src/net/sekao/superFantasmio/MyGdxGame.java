package net.sekao.superFantasmio;

import com.badlogic.gdx.Game;
import net.sekao.superFantasmio.MainScreen;

public class MyGdxGame extends Game {
	public void create() {
		this.setScreen(new MenuScreen(this));
	}
}
