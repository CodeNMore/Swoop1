package devlopment.codenmore.ld32prep.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import devlopment.codenmore.ld32prep.Main;
import devlopment.codenmore.ld32prep.assets.Assets;
import devlopment.codenmore.ld32prep.level.CloudSpawner;
import devlopment.codenmore.ld32prep.level.Level;

public class GameState {
	
	private Preferences preferences;
	public static final TextureRegion COLORTEX = Assets.getRegion("color");
	private CloudSpawner[] clouds;
	private LoseOverlay loseOverlay;
	
	private Level level;
	private boolean stopLevelTicks = false;
	
	public GameState(){
		level = new Level(this);
		clouds = new CloudSpawner[]{
				new CloudSpawner(), new CloudSpawner(), new CloudSpawner(), new CloudSpawner(), new CloudSpawner(), new CloudSpawner()
		};
		preferences = Gdx.app.getPreferences("scoreprefsswoop");
		loseOverlay = new LoseOverlay(this);
		
		lose();
		loseOverlay.enter(-10);
		loseOverlay.setTimer(LoseOverlay.BUTTONWAIT);
	}
	
	public void tick(float delta){
		//BACKGROUND
		for(CloudSpawner c : clouds){
			c.tick(delta);
		}
		//Other
		if(!stopLevelTicks){
			level.tick(delta);
		}else{//LOSING SCREEN
			loseOverlay.tick(delta);
		}
	}
	
	public void render(SpriteBatch batch){
		batch.begin();
		{
			//BACKGROUND
			batch.setColor(Color.CYAN);
			batch.draw(COLORTEX, 0, 0, Main.WIDTH, Main.HEIGHT);
			batch.setColor(Color.WHITE);
			for(CloudSpawner c : clouds){
				c.render(batch);
			}
			//Level
			level.render(batch);
			//LOSING SCREEN
			if(stopLevelTicks){
				loseOverlay.render(batch);
			}
		}
		batch.end();
	}
	
	public void resetAndStart(){
		level.reset();
		stopLevelTicks = false;
	}
	
	public void lose(){
		stopLevelTicks = true;
		loseOverlay.enter(level.getScore());
	}
	
	public int getHighScorePreference(){
		return preferences.getInteger("highscore", 0);
	}
	
	public void setHighScorePreferenceAndCheck(int score){
		if(getHighScorePreference() < score){
			preferences.putInteger("highscore", score);
			preferences.flush();
		}
	}
	
	public void pause(){
		
	}
	
	public void resume(){
		
	}

}
