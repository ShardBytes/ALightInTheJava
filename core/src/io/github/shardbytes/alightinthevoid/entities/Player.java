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
import io.github.shardbytes.alightinthevoid.internal.ValueAnimator;

/**
 * Player wrapper class. Contains constants, input processing, rendering, ...
 */
public class Player implements ITickable, ILockable{
	
	private Sprite playerSprite;
	private Vector2 position;
	private ValueAnimator speedAnimator;
	
	private float maxMovementSpeed = 3.0f;
	private float rotationSpeed = 2.5f;
	private Double speed = 0.0d;
	
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
		speedAnimator = new ValueAnimator(speed, 6.0d, this::setSpeed, false);
		speedAnimator.setActive(true);
		
	}
	
	@Override
	public Vector2 getPosition(){
		return position;
	}
	
	@Override
	public void tick(SpriteBatch batch, float delta){
		speedAnimator.step(delta);
		handleInput();
		addToBatch(batch);
	}
	
	private void handleInput(){
		float rotation = playerSprite.getRotation();
		float xAmount = (float)(-speed * MathUtils.sinDeg(rotation));
		float yAmount = (float)(speed * MathUtils.cosDeg(rotation));
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			speedAnimator.setTargetValue(maxMovementSpeed);
		}else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			speedAnimator.setTargetValue(-maxMovementSpeed / 2.0d);
		}else{
			speedAnimator.setTargetValue(0.0d);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			playerSprite.rotate(rotationSpeed);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			playerSprite.rotate(-rotationSpeed);
		}
		
		position.add(xAmount, yAmount);
		playerSprite.translate(xAmount, yAmount);
		
	}
	
	private void addToBatch(SpriteBatch batch){
		playerSprite.draw(batch);
	}
	
	private void setSpeed(double speed){
		this.speed = speed;
	}
	
}
