package io.github.shardbytes.alightinthevoid.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.shardbytes.alightinthevoid.interfaces.ILockable;
import io.github.shardbytes.alightinthevoid.interfaces.ITickable;

/**
 * Simple ortographic camera that can be locked onto an object and will follow it.
 */
public class Camera implements ITickable, ILockable{
	
	private OrthographicCamera camera;
	private Vector2 cameraPosition;
	private ILockable lockObject = this;
	
	private float width = Gdx.graphics.getWidth();
	private float height = Gdx.graphics.getHeight();
	
	/**
	 * Camera constructor
	 */
	public Camera(){
		camera = new OrthographicCamera(30, 30 * (height / width));
		camera.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f, 0.0f);
		camera.zoom = 10.0f;
		camera.update();
	}
	
	/**
	 * Sets camera's lockObject to any object implementing ILockable interface
	 * @param object Object to lock the camera on
	 */
	public void lockOn(ILockable object){
		lockObject = object;
	}
	
	/**
	 * Unlocks camera from any potential locked object and sets its position manually.
	 * @param pos Camera position
	 */
	public void setPosition(Vector2 pos){
		lockObject = this;
		cameraPosition = pos;
	}
	
	@Override
	public Vector2 getPosition(){
		return cameraPosition;
	}
	
	@Override
	public void tick(SpriteBatch batch, float delta){
		if(lockObject.equals(this)){
			camera.position.set(cameraPosition, 0.0f);
			camera.update();
		}else{
		
		}
		
	}
	
}
