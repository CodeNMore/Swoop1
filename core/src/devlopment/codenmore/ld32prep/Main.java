package devlopment.codenmore.ld32prep;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import devlopment.codenmore.ld32prep.assets.Assets;
import devlopment.codenmore.ld32prep.state.GameState;

public class Main extends ApplicationAdapter {
	
	//GLOBALS
	public static final int WIDTH = 200, HEIGHT = 300;
	public static final int DESKWIDTH = WIDTH * 2, DESKHEIGHT = HEIGHT * 2;
	public static final String TITLE = "Swoop";
	//Rendering
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Viewport viewport;
	//State
	private GameState gameState;
	
	@Override
	public void create(){
		//Rendering
		batch = new SpriteBatch();
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.position.set(WIDTH / 2, HEIGHT / 2, 0);
		viewport = new FitViewport(WIDTH, HEIGHT, cam);
		Gdx.gl.glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
		//Assets
		Assets.init();
		Assets.resumeUpdate();
		Assets.postInit();
		//Input
		Input.init(cam, viewport);
		//State
		gameState = new GameState();
	}
	
	@Override
	public void render(){
		//RESET
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//RENDER
		gameState.tick(Gdx.graphics.getDeltaTime());
		gameState.render(batch);
	}
	
	@Override
	public void dispose(){
		//Rendering
		batch.dispose();
		//Assets
		Assets.dispose();
	}
	
	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
	}
	
	@Override
	public void pause(){
		gameState.pause();
	}
	
	@Override
	public void resume(){
		Assets.resumeUpdate();
		gameState.resume();
	}
	
}
