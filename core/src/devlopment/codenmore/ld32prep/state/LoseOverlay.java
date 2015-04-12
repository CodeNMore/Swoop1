package devlopment.codenmore.ld32prep.state;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import devlopment.codenmore.ld32prep.Main;
import devlopment.codenmore.ld32prep.assets.Assets;
import devlopment.codenmore.ld32prep.assets.Button;

public class LoseOverlay {
	
	private GameState state;
	private int score = 0;
	public static final float BUTTONWAIT = 1.1f;
	private float buttonShowTimer = 0;
	private boolean showButton;
	
	private Button restartButton;
	private int highscore;
	
	public LoseOverlay(GameState state){
		this.state = state;
		restartButton = new Button(Assets.getRegion("restart"), Main.WIDTH / 2 - 32, 134);
		enter(0);
	}
	
	public void tick(float delta){
		buttonShowTimer += delta;
		if(!showButton && buttonShowTimer >= BUTTONWAIT){
			showButton = true;
		}else if(showButton){
			restartButton.incOpacity(1.0f * delta);
			if(restartButton.clicked()){
				state.resetAndStart();
			}
		}
	}
	
	public void render(SpriteBatch batch){
		//Box
		batch.setColor(Color.LIGHT_GRAY);
		batch.draw(GameState.COLORTEX, 16, 108, Main.WIDTH - 32, 110);
		batch.setColor(Color.WHITE);
		//Text
		Assets.setFontBig();
		Assets.setFontColor(Color.RED);
		if(score != -10){
			Assets.drawString(batch, "YOU SCORED", 17, 220);
			Assets.setFontColor(Color.RED);
			Assets.setFontMed();
			Assets.drawString(batch, score + "", Main.WIDTH / 2 - Assets.getFont().getBounds(score + "").width / 2, 190);
		}else{
			Assets.setFontMed();
		}
		Assets.setFontColor(Color.BLUE);
		Assets.drawString(batch, "HIGHSCORE: " + highscore, 17, 130);
		
		if(showButton){
			restartButton.render(batch);
		}
	}
	
	public void enter(int score){
		this.score = score;
		buttonShowTimer = 0;
		showButton = false;
		restartButton.setOpacity(0.0f);
		state.setHighScorePreferenceAndCheck(score);
		highscore = state.getHighScorePreference();
	}
	
	public void setTimer(float value){
		buttonShowTimer = value;
	}

}
