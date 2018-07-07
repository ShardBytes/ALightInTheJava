package io.github.shardbytes.alightinthevoid;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VoidLight extends Game{
	
	public SpriteBatch batch;
	public SpriteBatch HUDBatch;
	public BitmapFont font;
	
	@Override
	public void create(){
		batch = new SpriteBatch();
		HUDBatch = new SpriteBatch();
		font = new BitmapFont();
		
		this.setScreen(new MainMenu(this));
	
	}
	
	@Override
	public void render(){
		super.render();
	}
	
	@Override
	public void dispose(){
		//super.dispose();
		batch.dispose();
		HUDBatch.dispose();
		font.dispose();
		
	}
}
