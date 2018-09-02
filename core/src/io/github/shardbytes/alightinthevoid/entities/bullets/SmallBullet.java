package io.github.shardbytes.alightinthevoid.entities.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.shardbytes.alightinthevoid.GameScreen;

public class SmallBullet extends Bullet{
	
	public SmallBullet(Vector2 position, float rotation, boolean fake){
		this.position = position;
		this.rotation = rotation;
		this.fake = fake;
		
		setProperties();
		this.bulletSprite = new Sprite(new Texture(Gdx.files.internal("bullets/bluelaser.png")));
	}
	
	@Override
	public void tick(SpriteBatch batch, float delta){
		if(currentLifeTime > maxLifeTime){
			GameScreen.tickableObjects.remove(this);
			
		}else{
			incrementLifeTime(delta);
		}
		
		addToBatch(batch);
		move();
	}
	
	private void setProperties(){
		speed = 0.05f;
		damage = 5.0f;
		maxLifeTime = 1.5f;
	}
	
	private void move(){
		float xAmount = -speed * MathUtils.sinDeg(rotation);
		float yAmount = speed * MathUtils.cosDeg(rotation);
		position.add(xAmount, yAmount);
		bulletSprite.translate(xAmount, yAmount);
	}
	
	private void incrementLifeTime(float delta){
		currentLifeTime += 1 * delta;
	}
	
}
