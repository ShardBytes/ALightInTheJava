package io.github.shardbytes.alightinthevoid.internal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.shardbytes.alightinthevoid.GameScreen;

public class ParallaxBackground extends Actor{
	
	private float scrollX;
	private float scrollY;
	private int speed;
	private Texture[] layers;
	private final float SPEED_DIFFERENCE = 0.05f;
	
	float x;
	float y;
	float width;
	float height;
	float scaleX;
	float scaleY;
	
	int originX;
	int originY;
	int rotation;
	int sourceX;
	int sourceY;
	
	boolean flipX;
	boolean flipY;
	
	public ParallaxBackground(Texture[] textures){
		layers = textures;
		
		for(Texture texture : textures){
			texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		}
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		scaleX = 1;
		scaleY = 1;
		flipX = false;
		flipY = false;
		
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
		
		scrollX = GameScreen.player.getPosition().x / 20.0f;
		scrollY = GameScreen.player.getPosition().y / 20.0f;
		
		for(int i = 0; i < layers.length; i++){
			sourceX = Math.round(scrollX + i * SPEED_DIFFERENCE * scrollX);
			sourceY = Math.round(scrollY + i * SPEED_DIFFERENCE * scrollY);
			//batch.draw(layers[i], x, y, originX, originY, width, height, scaleX, scaleY, rotation, sourceX, sourceY, layers[i].getWidth(), layers[i].getHeight(), flipX, flipY);
			batch.draw(layers[i], x, y, layers[i].getWidth(), layers[i].getHeight(), sourceX, sourceY, layers[i].getWidth(), layers[i].getHeight(), flipX, flipY);
		}
		
	}
	
}
