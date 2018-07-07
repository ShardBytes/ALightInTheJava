package io.github.shardbytes.alightinthevoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.shardbytes.alightinthevoid.entities.Camera;
import io.github.shardbytes.alightinthevoid.entities.Player;
import io.github.shardbytes.alightinthevoid.interfaces.ITickable;
import io.github.shardbytes.alightinthevoid.overlay.FPSCounter;

import java.util.ArrayList;

public class GameScreen implements Screen{
	
	private Camera cam;
	private Camera hudCam;
	private Sprite mapSprite;
	private Music music;
	private Player player;
	
	private final VoidLight game;
	
	/**
	 * ArrayList that holds all game objects that are tickable.
	 */
	private static ArrayList<ITickable> tickableObjects = new ArrayList<>();
	
	/**
	 * ArrayList that holds all game HUD's that are tickable.
	 */
	private static ArrayList<ITickable> tickableHUDs = new ArrayList<>();

	GameScreen(final VoidLight game){
		this.game = game;
		
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);

		mapSprite = new Sprite(new Texture(Gdx.files.internal("starBgPmxyEdition.png")));
		mapSprite.setPosition(0, 0);
		mapSprite.setSize(128, 128);
		
		player = new Player(Player.Team.AMBER);
		tickableObjects.add(player);
		
		FPSCounter counter = new FPSCounter();
		tickableHUDs.add(counter);
		
		cam = new Camera(Camera.ResizeStrategy.KEEP_ZOOM, player);
		hudCam = new Camera(Camera.ResizeStrategy.KEEP_ZOOM);

	}

	@Override
	public void render(float delta){
		cam.update();
		game.batch.setProjectionMatrix(cam.getInnerCamera().combined);
		game.HUDBatch.setProjectionMatrix(hudCam.getInnerCamera().combined);

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		mapSprite.draw(game.batch);
		for(ITickable tickableEntity : tickableObjects){
			tickableEntity.tick(game.batch, delta);
		}
		game.batch.end();
		
		game.HUDBatch.begin();
		for(ITickable hud : tickableHUDs){
			hud.tick(game.HUDBatch, delta);
		}
		game.HUDBatch.end();

	}
	
	/*
	 * TODO: Disposable stuff?
	 */
	@Override
	public void dispose(){
		mapSprite.getTexture().dispose();
	}

	@Override
	public void resize(int width, int height){
		cam.windowResized(width, height);
		hudCam.windowResized(width, height);
	}
	
	@Override
	public void show(){
		//music.play();
	}
	
	@Override
	public void hide(){}
	
	@Override
	public void pause(){}
	
	@Override
	public void resume(){}
	
}
