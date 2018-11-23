package io.github.shardbytes.alightinthevoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import io.github.shardbytes.alightinthevoid.entities.Camera;
import io.github.shardbytes.alightinthevoid.entities.Player;
import io.github.shardbytes.alightinthevoid.interfaces.ITickable;
import io.github.shardbytes.alightinthevoid.internal.ParallaxBackground;
import io.github.shardbytes.alightinthevoid.overlay.FPSCounter;

import java.util.ArrayList;

public class GameScreen implements Screen{
	
	private Camera cam;
	private Camera hudCam;
	private Sprite mapSprite;
	private Music music;
	public static Player player;
	private ParallaxBackground background;
	
	private final VoidLight game;
	
	/**
	 * ArrayList that holds all game objects that are tickable.
	 */
	public static ArrayList<ITickable> tickableObjects = new ArrayList<>();
	
	/**
	 * ArrayList that holds all game HUD's that are tickable.
	 */
	public static ArrayList<ITickable> tickableHUDs = new ArrayList<>();

	GameScreen(final VoidLight game){
		this.game = game;

		cam = new Camera(Camera.ResizeStrategy.KEEP_ZOOM, 150.0f, 150.0f);
		hudCam = new Camera(Camera.ResizeStrategy.CHANGE_ZOOM, 100.0f, 100.0f);
		
		music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/music.mp3"));
		music.setLooping(true);

		mapSprite = new Sprite(new Texture(Gdx.files.internal("core/assets/starBgPmxyEdition.png")));
		//mapSprite.setPosition(0, 0);
		//mapSprite.setSize(128, 128);
		
		Texture[] backgroundTextures = new Texture[1];
		backgroundTextures[0] = mapSprite.getTexture();
		//backgroundTextures[1] = mapSprite.getTexture();
		
		background = new ParallaxBackground(backgroundTextures);
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.setSpeed(1);
		
		player = new Player(Player.Team.AMBER);
		tickableObjects.add(player);
		cam.lockOn(player);

		FPSCounter counter = new FPSCounter(new Vector2(-50.0f, 50.0f));
		tickableHUDs.add(counter);

	}

	@Override
	public void render(float delta){
		cam.update();
		hudCam.update();

		game.batch.setProjectionMatrix(cam.getInnerCamera().combined);
		game.hudBatch.setProjectionMatrix(hudCam.getInnerCamera().combined);

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		background.draw(game.batch, 1.0f);
		for(int i = 0; i < tickableObjects.size(); i++){
			tickableObjects.get(i).tick(game.batch, delta);
		}
		game.batch.end();

		game.hudBatch.begin();
		for(int i = 0; i < tickableHUDs.size(); i++){
			tickableHUDs.get(i).tick(game.hudBatch, delta);
		}
		game.hudBatch.end();

		Vector2 viewportCam = new Vector2(cam.getInnerCamera().viewportWidth, cam.getInnerCamera().viewportHeight);
		Vector2 viewportHud = new Vector2(hudCam.getInnerCamera().viewportWidth, hudCam.getInnerCamera().viewportHeight);
		System.out.println("viewportCam = " + viewportCam);
		System.out.println("viewportHud = " + viewportHud);

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
