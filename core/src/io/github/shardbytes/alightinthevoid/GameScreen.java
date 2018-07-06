package io.github.shardbytes.alightinthevoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.shardbytes.alightinthevoid.entities.Player;
import io.github.shardbytes.alightinthevoid.interfaces.ITickable;

import java.util.ArrayList;

public class GameScreen implements Screen{
	
	private OrthographicCamera cam;
	private Sprite mapSprite;
	private Music music;
	
	private final VoidLight game;
	
	/*
	 * Array that holds all game objects, that are tickable
	 */
	public static ArrayList<ITickable> tickableGameObjects = new ArrayList<>();

	GameScreen(final VoidLight game){
		this.game = game;
		
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);

		mapSprite = new Sprite(new Texture(Gdx.files.internal("starBgPmxyEdition.png")));
		mapSprite.setPosition(0, 0);
		mapSprite.setSize(128, 128);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		/*
		 * Set up camera and move it to [0, 0]
		 */
		cam = new OrthographicCamera(30, 30 * (h / w));
		cam.position.set(cam.viewportWidth / 2.0f, cam.viewportHeight / 2.0f, 0.0f);
		cam.zoom = 10.0f;
		cam.update();
		
		Player p = new Player(Player.Team.AMBER);

	}

	@Override
	public void render(float delta){
		cam.update();
		game.batch.setProjectionMatrix(cam.combined);

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		mapSprite.draw(game.batch);
		
		for(ITickable tickableEntity : tickableGameObjects){
			tickableEntity.tick(game.batch, delta);
		}
		
		game.batch.end();

	}

	@Override
	public void dispose(){
		mapSprite.getTexture().dispose();
	}

	@Override
	public void resize(int width, int height){
		changeResize(width, height);
		cam.update();
	}

	private void keepResize(int width, int height){
		cam.viewportWidth = 30.0f;
		cam.viewportHeight = 30.0f * height / width;
	}

	private void changeResize(int width, int height){
		cam.viewportWidth = width / 30.0f;
		cam.viewportHeight = cam.viewportWidth * height / width;
	}
	
	@Override
	public void show(){
		//music.play();
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
	
}
