package io.github.shardbytes.alightinthevoid.interfaces;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface that defines if a camera can lock onto an object. Objects implementing this
 * interface must implement getPosition() method returning object's position.
 */
public interface ILockable{
	
	/**
	 * Required for camera to lock onto an object.
	 * @return Object's position
	 */
	Vector2 getPosition();
	
}
