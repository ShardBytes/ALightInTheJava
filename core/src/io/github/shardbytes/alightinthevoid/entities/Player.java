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

/**
 * Player wrapper class. Contains constants, input processing, rendering, ...
 */
public class Player implements ITickable, ILockable{
	
	private Sprite playerSprite;
	private Vector2 position;
	
	private float movementSpeed = 3.0f;
	private float rotationSpeed = 2.5f;
	
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
	
	}
	
	@Override
	public Vector2 getPosition(){
		return position;
	}
	
	@Override
	public void tick(SpriteBatch batch, float delta){
		handleInput();
		addToBatch(batch);
	}
	
	private void handleInput(){
		float rotation = playerSprite.getRotation();
		float xAmount = -movementSpeed * MathUtils.sinDeg(rotation);
		float yAmount = movementSpeed * MathUtils.cosDeg(rotation);
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			position.add(xAmount, yAmount);
			playerSprite.translate(xAmount, yAmount);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			position.sub(xAmount, yAmount);
			playerSprite.translate(-xAmount, -yAmount);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			playerSprite.rotate(rotationSpeed);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			playerSprite.rotate(-rotationSpeed);
		}
		
	}
	
	private void addToBatch(SpriteBatch batch){
		playerSprite.draw(batch);
	}
	
}
