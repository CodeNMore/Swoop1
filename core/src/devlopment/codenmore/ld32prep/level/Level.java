package devlopment.codenmore.ld32prep.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import devlopment.codenmore.ld32prep.Main;
import devlopment.codenmore.ld32prep.assets.Assets;
import devlopment.codenmore.ld32prep.state.GameState;

public class Level {
	
	private TextureRegion wallTexture;
	public static final float STARTSPEED = 60.0f, MAX_SPEED = 120.0f, SPEEDINC = 0.03f;
	private float scorePerSecond = 1.25f;
	private float speed = STARTSPEED;
	private float wallPos = 0.0f;
	
	private GameState state;
	private Player player;
	private PlatformSpawner spawner;
	
	private float score = 0;
	
	public Level(GameState state){
		this.state = state;
		wallTexture = Assets.getRegion("wall");
		spawner = new PlatformSpawner(this);
		
		player = new Player(this);
	}
	
	public void tick(float delta){
		//SPEED
		speed += SPEEDINC;
		if(speed > MAX_SPEED){
			speed = MAX_SPEED;
		}
		
		//PLAYER
		player.tick(delta);
		//WALLS
		float oldWallPos = wallPos, oldWallPos2 = 0;
		boolean changed = false;
		wallPos += speed * delta;
		if(wallPos > 16){
			oldWallPos2 = wallPos;
			wallPos = 0;
			changed = true;
		}
		//PLATFOMRS
		if(!changed)
			spawner.tick(delta, wallPos - oldWallPos);
		else
			spawner.tick(delta, oldWallPos2 - oldWallPos);
		//SCORE
		score += scorePerSecond * delta;
	}
	
	public void render(SpriteBatch batch){
		//PLAYER
		player.render(batch);
		//WALLS
		for(int x = 0;x < 2;++x){
			for(int y = 0;y < 20;++y){
				batch.draw(wallTexture, x * (Main.WIDTH - 16), y * 16 + wallPos - 16, 16, 16);
			}
		}
		//PLATFORMS
		spawner.render(batch);
		//Score Display
		Assets.setFontSmall();
		Assets.setFontColor(Color.BLUE);
		Assets.drawString(batch, "SCORE: " + getScore(), 18, Main.HEIGHT);
	}
	
	public void reset(){
		spawner.reset();
		player.reset();
		score = 0;
		speed = STARTSPEED;
	}
	
	public void lose(){
		state.lose();
		Assets.hit.play(1.0f);
	}
	
	//GETTERS SETTERS
	
	public void incScore(float amt){
		score += amt;
	}
	
	public int getScore(){
		return (int) score;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getWallPos() {
		return wallPos;
	}

	public void setWallPos(float wallPos) {
		this.wallPos = wallPos;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
