package io.github.shardbytes.alightinthevoid.entities.bullets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.shardbytes.alightinthevoid.interfaces.ILockable;
import io.github.shardbytes.alightinthevoid.interfaces.ITickable;

public abstract class Bullet implements ITickable, ILockable{
	
	protected Sprite bulletSprite;
	protected Vector2 position;
	protected float rotation;
	protected float speed;
	protected float damage;
	protected float maxLifeTime;
	protected float currentLifeTime;
	protected boolean fake;
	
	@Override
	public Vector2 getPosition(){
		return position;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public float getSpeed(){
		return speed;
	}
	
	protected void addToBatch(SpriteBatch batch){
		batch.draw(bulletSprite.getTexture(), position.x, position.y, 0, 0, bulletSprite.getTexture().getWidth(), bulletSprite.getTexture().getHeight(), 1, 1, rotation, 0, 0, bulletSprite.getTexture().getWidth(), bulletSprite.getTexture().getHeight(), false, false);
	}
	
}
