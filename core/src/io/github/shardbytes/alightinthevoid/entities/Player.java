package io.github.shardbytes.alightinthevoid.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.shardbytes.alightinthevoid.GameScreen;
import io.github.shardbytes.alightinthevoid.interfaces.ITickable;

public class Player implements ITickable{
	
	private Sprite playerSprite;
	private Vector2 position;
	
	private float movementSpeed = 3.0f;
	private float rotationSpeed = 2.5f;
	
	public enum Team{
		AQUAMARINE,
		AMBER
	}
	
	public Player(Team team){
		if(team == Team.AMBER){
			playerSprite = new Sprite(new Texture(Gdx.files.internal("players/amberplayer.png")));
		}else if(team == Team.AQUAMARINE){
			playerSprite = new Sprite(new Texture(Gdx.files.internal("players/aquamarineplayer.png")));
		}else{
			System.err.println("Player " + this.toString() + "has no team specified!");
		}
		
		position = new Vector2();
		GameScreen.tickableGameObjects.add(this);
	
	}
	
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
