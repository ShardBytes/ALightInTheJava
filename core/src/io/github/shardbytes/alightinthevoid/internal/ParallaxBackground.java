package io.github.shardbytes.alightinthevoid.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParallaxBackground extends Actor{
	
	private int scroll;
	private int speed;
	private Texture[] layers;
	private final int SPEED_DIFFERENCE = 2;
	
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
		
		scroll += speed;
		
		for(int i = 0; i < layers.length; i++){
			sourceX = scroll + i * SPEED_DIFFERENCE * scroll;
			batch.draw(layers[i], x, y, originX, originY, width, height, scaleX, scaleY, rotation, sourceX, sourceY, layers[i].getWidth(), layers[i].getHeight(), flipX, flipY);
		}
		
	}
	
}
