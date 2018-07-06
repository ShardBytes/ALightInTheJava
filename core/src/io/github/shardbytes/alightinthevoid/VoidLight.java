package io.github.shardbytes.alightinthevoid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class VoidLight extends Game{
	
	public SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create(){
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		this.setScreen(new MainMenu(this));
	
	}
	
	@Override
	public void render(){
		super.render();
	}
	
	@Override
	public void dispose(){
		batch.dispose();
		font.dispose();
		//super.dispose();
	}
}
