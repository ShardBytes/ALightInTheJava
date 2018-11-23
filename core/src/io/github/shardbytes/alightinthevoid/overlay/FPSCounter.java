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
	private Texture backgroundTexture;
	private BitmapFont text;
	
	/**
	 * Constructs FPS counter in an upper left corner of a screen.
	 */
	public FPSCounter(){
		counterPosition = new Vector2(0.0f, 0.0f);
		backgroundTexture = new Texture(Gdx.files.internal("core/assets/hud/fpsbackground.png"));
		text = new BitmapFont();
	}
	
	/**
	 * Constructs FPS counter on a given position
	 * @param position FPS counter's position
	 */
	public FPSCounter(Vector2 position){
		counterPosition = position;
		backgroundTexture = new Texture(Gdx.files.internal("core/assets/hud/fpsbackground.png"));
		text = new BitmapFont();
	}
	
	@Override
	public void tick(SpriteBatch batch, float delta){
		batch.draw(backgroundTexture, counterPosition.x, counterPosition.y);
		text.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), counterPosition.x, counterPosition.y);
	
	}
	
}
