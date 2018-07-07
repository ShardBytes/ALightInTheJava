package io.github.shardbytes.alightinthevoid.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.shardbytes.alightinthevoid.interfaces.ITickable;

/**
 * Simple frames per second-showing object that can be rendered on screen.
 */
public class FPSCounter implements ITickable{
	
	private Vector2 counterPosition;
	private Sprite backgroundSprite;
	private BitmapFont text;
	
	/**
	 * Constructs FPS counter in an upper left corner of a screen.
	 */
	public FPSCounter(){
		counterPosition = new Vector2(-30.0f, 30.0f);
		backgroundSprite = new Sprite(new Texture(Gdx.files.internal("hud/fpsbackground.png")));
		backgroundSprite.setPosition(counterPosition.x, counterPosition.y);
		text = new BitmapFont();
	}
	
	/**
	 * Constructs FPS counter on a given position
	 * @param position FPS counter's position
	 */
	public FPSCounter(Vector2 position){
		counterPosition = position;
		backgroundSprite = new Sprite(new Texture(Gdx.files.internal("hud/fpsbackground.png")));
		backgroundSprite.setPosition(counterPosition.x, counterPosition.y);
		text = new BitmapFont();
	}
	
	@Override
	public void tick(SpriteBatch batch, float delta){
		backgroundSprite.draw(batch);
		text.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), counterPosition.x, counterPosition.y);
	
	}
	
}
