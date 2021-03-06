package io.github.shardbytes.alightinthevoid.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.shardbytes.alightinthevoid.GameScreen;
import io.github.shardbytes.alightinthevoid.entities.bullets.Bullet;
import io.github.shardbytes.alightinthevoid.entities.bullets.SmallBullet;
import io.github.shardbytes.alightinthevoid.interfaces.ILockable;
import io.github.shardbytes.alightinthevoid.interfaces.ITickable;
import io.github.shardbytes.alightinthevoid.internal.Tween;

import java.security.*;

/**
 * Player wrapper class. Contains constants, input processing, rendering, ...
 */
public class Player implements ITickable, ILockable{
	
	private float maxSpeed = 3.0f;
	private float rotationSpeed = 2.5f;
	
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
			playerSprite = new Sprite(new Texture(Gdx.files.internal("core/assets/players/amberplayer.png")));
		}else if(team == Team.AQUAMARINE){
			playerSprite = new Sprite(new Texture(Gdx.files.internal("core/assets/players/aquamarineplayer.png")));
		}else{
			throw new InvalidParameterException("Player " + this.toString() + " has no team specified!");
		}

		playerSprite.scale(10.0f);
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
		/*
		 * Movement
		 */
		float rotation = playerSprite.getRotation();
		float xAmount = -interpolatedSpeed.getFloat() * MathUtils.sinDeg(rotation);
		float yAmount = interpolatedSpeed.getFloat() * MathUtils.cosDeg(rotation);
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			interpolatedSpeed.setTarget(maxSpeed);
		}else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			interpolatedSpeed.setTarget(-maxSpeed / 2.0d);
		}else{
			interpolatedSpeed.setTarget(0.0d);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			playerSprite.rotate(rotationSpeed);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			playerSprite.rotate(-rotationSpeed);
		}
		
		position.add(xAmount, yAmount);
		playerSprite.translate(xAmount, yAmount);
		
		/*
		 * Shooting
		 */
		if(Gdx.input.isKeyPressed(Input.Keys.Q)){
			shoot();
		}
		
	}
	
	private void addToBatch(SpriteBatch batch){
		playerSprite.draw(batch);
	}
	
	private void shoot(){
		Bullet b = new SmallBullet(this.position, this.playerSprite.getRotation(), false);
		GameScreen.tickableObjects.add(b);
	}

	@Override
	public String toString(){
		return "playerName"; //TODO: nicknames
	}

}
