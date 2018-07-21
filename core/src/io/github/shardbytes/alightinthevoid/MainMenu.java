package io.github.shardbytes.alightinthevoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenu implements Screen{

	private final VoidLight game;
	private OrthographicCamera cam;
	
	MainMenu(final VoidLight game){
		this.game = game;

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		cam = new OrthographicCamera(30, 30 * (h / w));
		cam.position.set(cam.viewportWidth / 2.0f, cam.viewportHeight / 2.0f, 0.0f);
		cam.zoom = 10.0f;
		cam.update();
		
	}

	@Override
	public void render(float delta){
		cam.update();
		game.batch.setProjectionMatrix(cam.combined);
		
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.font.draw(game.batch, "HENLO BOI\nCLICC TO KONTINUE", 15.0f, 15.0f);
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			game.setScreen(new GameScreen(game));
			dispose();
		}
		
	}

	@Override
	public void dispose(){

	}

	@Override
	public void show(){

	}

	@Override
	public void hide(){

	}

	@Override
	public void pause(){

	}

	@Override
	public void resume(){

	}

	@Override
	public void resize(int width, int height){

	}
	
}
