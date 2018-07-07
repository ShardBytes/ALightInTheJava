package io.github.shardbytes.alightinthevoid.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import io.github.shardbytes.alightinthevoid.interfaces.ILockable;

/**
 * Simple ortographic camera that can be locked onto an object and will follow it.
 */
public class Camera implements ILockable{
	
	private OrthographicCamera camera;
	private Vector2 cameraPosition;
	private ResizeStrategy resizeStrategy;
	private ILockable lockObject = this;
	
	private float width = Gdx.graphics.getWidth();
	private float height = Gdx.graphics.getHeight();
	
	/**
	 * Enum defining what should camera do on window resize.
	 */
	public enum ResizeStrategy{
		KEEP_ZOOM,
		CHANGE_ZOOM
	}
	
	/**
	 * Constructs a camera on [0, 0] position.
	 * @param strategy What should camera do when window is resized
	 */
	public Camera(ResizeStrategy strategy){
		resizeStrategy = strategy;
		cameraPosition = new Vector2();
		construct();
	}
	
	/**
	 * Constructs a camera on a given position.
	 * @param strategy What should camera do when window is resized
	 * @param position Camera's position
	 */
	public Camera(ResizeStrategy strategy, Vector2 position){
		resizeStrategy = strategy;
		cameraPosition = position;
		construct();
	}
	
	/**
	 * Constructs a camera that follows the given object.
	 * @param strategy What should camera do when window is resized
	 * @param object Object to lock the camera on
	 */
	public Camera(ResizeStrategy strategy, ILockable object){
		resizeStrategy = strategy;
		cameraPosition = object.getPosition();
		lockObject = object;
		construct();
	}
	
	private void construct(){
		camera = new OrthographicCamera(30, 30 * (height / width));
		camera.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f, 0.0f);
		camera.zoom = 10.0f;
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
		lockObject = this;
		cameraPosition = pos;
	}
	
	/**
	 * Method that must be called whenever the window has resized to change camera's zoom
	 * or viewport size accordingly.
	 * @param width New window width
	 * @param height New window height
	 */
	public void windowResized(int width, int height){
		if(resizeStrategy == ResizeStrategy.KEEP_ZOOM){
			keepZoom(width, height);
		}else{
			changeZoom(width, height);
		}
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
		if(lockObject.equals(this)){
			camera.position.set(cameraPosition, 0.0f);
			camera.update();
		}else{
			camera.position.set(lockObject.getPosition(), 0.0f);
			camera.update();
		}
		
	}
	
	private void changeZoom(int w, int h){
		camera.viewportWidth = 30.0f;
		camera.viewportHeight = 30.0f * h / w;
	}
	
	private void keepZoom(int w, int h){
		camera.viewportWidth = w / 30.0f;
		camera.viewportHeight = camera.viewportWidth * h / w;
	}
	
}
