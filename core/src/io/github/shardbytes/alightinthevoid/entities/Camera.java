package io.github.shardbytes.alightinthevoid.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.*;
import io.github.shardbytes.alightinthevoid.interfaces.ILockable;

/**
 * Simple ortographic camera that can be locked onto an object and will follow it.
 */
public class Camera implements ILockable{
	
	private OrthographicCamera camera;
	private Vector2 cameraPosition;
	private ResizeStrategy resizeStrategy;
	private ILockable lockObject = null;
	private Viewport viewport;
	
	private float screenWidth = Gdx.graphics.getWidth();
	private float screenHeight = Gdx.graphics.getHeight();
	private float viewportWidth;
	private float viewportHeight;
	
	/**
	 * Enum defining what should camera do on window resize.
	 */
	public enum ResizeStrategy{
		KEEP_ZOOM,
		CHANGE_ZOOM

	}

	/**
	 * Constructs a camera on [0, 0] world position.
	 * @param strategy What should camera do when window is resized
	 * @param width Width of camera's viewport
	 * @param height Height of camera's viewport
	 */
	public Camera(ResizeStrategy strategy, float width, float height){
		resizeStrategy = strategy;
		cameraPosition = new Vector2();
		viewportWidth = width;
		viewportHeight = height;
		construct();

	}
	
	/**
	 * Constructs a camera on a given world position.
	 * @param strategy What should camera do when window is resized
	 * @param position Camera's position
	 * @param width Width of camera's viewport
	 * @param height Height of camera's viewport
	 */
	public Camera(ResizeStrategy strategy, Vector2 position, float width, float height){
		resizeStrategy = strategy;
		cameraPosition = position;
		viewportWidth = width;
		viewportHeight = height;
		construct();

	}
	
	/**
	 * Constructs a camera that follows the given object.
	 * @param strategy What should camera do when window is resized
	 * @param object Object to lock the camera on
	 * @param width Width of camera's viewport
	 * @param height Height of camera's viewport
	 */
	public Camera(ResizeStrategy strategy, ILockable object, float width, float height){
		resizeStrategy = strategy;
		cameraPosition = object.getPosition();
		lockObject = object;
		viewportWidth = width;
		viewportHeight = height;
		construct();

	}
	
	private void construct(){
		if(resizeStrategy == ResizeStrategy.CHANGE_ZOOM){
			camera = new OrthographicCamera(viewportWidth, viewportHeight);
			viewport = new FitViewport(viewportWidth, viewportHeight, camera);
		}else{
			camera = new OrthographicCamera();
			viewport = new ScreenViewport(camera);
		}

		camera.update();

	}
	
	/**
	 * Sets camera's lockObject to any object implementing ILockable interface.
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
		lockObject = null;
		cameraPosition = pos;

	}
	
	/**
	 * Method that must be called whenever the window has resized to change camera's zoom
	 * or viewport size accordingly.
	 * @param width New window screenWidth
	 * @param height New window screenHeight
	 */
	public void windowResized(int width, int height){
		viewport.update(width, height);
		update();

	}
	
	/**
	 * Use with caution.
	 * @return Wrapped orthographic camera object for further customization.
	 */
	public OrthographicCamera getInnerCamera(){
		return camera;
	}
	
	@Override
	public Vector2 getPosition(){
		return cameraPosition;
	}
	
	/**
	 * Recalculates what is camera rendering. Should be called each render tick outside
	 * the begin()/end() block or after changing any camera attributes.
	 * @see OrthographicCamera#update()
	 */
	public void update(){
		if(lockObject == null){
			camera.position.set(cameraPosition, 0.0f);
		}else{
			camera.position.set(lockObject.getPosition(), 0.0f);
		}

		camera.update();
		
	}
	
}
