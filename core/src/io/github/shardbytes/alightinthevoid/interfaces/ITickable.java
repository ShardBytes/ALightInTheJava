package io.github.shardbytes.alightinthevoid.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Interface that defines if an object is tickable and so can be rendered.
 */
public interface ITickable{
	
	/**
	 * This method should be called each render tick, so that the object implementing this interface
	 * can be rendered properly.
	 * @param batch Current render batch
	 * @param delta Time difference between ticks
	 */
	void tick(SpriteBatch batch, float delta);
	
}
