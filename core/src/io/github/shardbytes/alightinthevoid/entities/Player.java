package io.github.shardbytes.alightinthevoid.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.shardbytes.alightinthevoid.interfaces.ILockable;
import io.github.shardbytes.alightinthevoid.interfaces.ITickable;
import io.github.shardbytes.alightinthevoid.internal.Tween;

/**
 * Player wrapper class. Contains constants, input processing, rendering, ...
 */
public class Player implements ITickable, ILockable{
	
	public static final float MAX_SPEED = 3.0f;
	public static final float ROTATION_SPEED = 2.5f;
	
	private Sprite playerSprite;
	private Vector2 position;
	private Tween interpolatedSpeed;
	
	/**
	 * Enum defining what team the player is in.
	 */
	public enum Team{
		AMBER,
		AQUAMARINE
	}
	
	/**
	 * Constructs player with a texture of given team.
	 * @param team Team to use
	 */
	public Player(Team team){
		if(team == Team.AMBER){
			playerSprite = new Sprite(new Texture(Gdx.files.internal("players/amberplayer.png")));
		}else if(team == Team.AQUAMARINE){
			playerSprite = new Sprite(new Texture(Gdx.files.internal("players/aquamarineplayer.png")));
		}else{
			System.err.println("Player " + this.toString() + "has no team specified!");
		}
		
		position = new Vector2();
		interpolatedSpeed = new Tween(0.0d, 6.0d, true, false);
		
	}
	
	@Override
	public Vector2 getPosition(){
		return position;
	}
	
	@Override
	public void tick(SpriteBatch batch, float delta){
		interpolatedSpeed.step(delta);
		handleInput();
		addToBatch(batch);
	}
	
	private void handleInput(){
		float rotation = playerSprite.getRotation();
		float xAmount = -interpolatedSpeed.getFloat() * MathUtils.sinDeg(rotation);
		float yAmount = interpolatedSpeed.getFloat() * MathUtils.cosDeg(rotation);
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			interpolatedSpeed.setTarget(MAX_SPEED);
		}else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			interpolatedSpeed.setTarget(-MAX_SPEED / 2.0d);
		}else{
			interpolatedSpeed.setTarget(0.0d);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			playerSprite.rotate(ROTATION_SPEED);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			playerSprite.rotate(-ROTATION_SPEED);
		}
		
		position.add(xAmount, yAmount);
		playerSprite.translate(xAmount, yAmount);
		
	}
	
	private void addToBatch(SpriteBatch batch){
		playerSprite.draw(batch);
	}
	
}
