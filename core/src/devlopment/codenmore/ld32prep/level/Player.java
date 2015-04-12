package devlopment.codenmore.ld32prep.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import devlopment.codenmore.ld32prep.Input;
import devlopment.codenmore.ld32prep.Main;
import devlopment.codenmore.ld32prep.assets.Assets;

public class Player {
	
	private static TextureRegion playerNormal = Assets.getRegion("slime1.s"),
								playerLeft = Assets.getRegion("slime1.l"),
								playerRight = Assets.getRegion("slime1.r");
	private static float leftWallBounds = 16, rightWallBounds = Main.WIDTH - 16 - 20;
	public static final float STARTSPEED = 180.0f;
	
	private Level level;
	private float x, y;
	private float width, height;
	private float speed = STARTSPEED;
	private float velocity = 0.0f;
	private Rectangle bounds;
	
	public Player(Level level){
		this.level = level;
		//sizing
		x = leftWallBounds;
		y = Main.HEIGHT - 64;
		width = 20;
		height = 20;
		//bounds
		bounds = new Rectangle(x, y, width, height);
	}
	
	public void tick(float delta){
		x += velocity * speed * delta;
		
		if(Input.justTapped()){
			if(toggleWall()){
				x += velocity * speed * delta;
				Assets.bloop.play(1.0f);
			}
		}
		
		if(velocity > 0){
			if(x > rightWallBounds){
				x = rightWallBounds;
				velocity = 0;
			}
		}else if(velocity < 0){
			if(x < leftWallBounds){
				x = leftWallBounds;
				velocity = 0;
			}
		}
		//Speed Update
		speed = STARTSPEED + level.getSpeed() - Level.STARTSPEED + 10.0f;
	}
	
	public boolean toggleWall(){
		if(x <= leftWallBounds){
			velocity = 1.0f;
			return true;
		}else if(x >= rightWallBounds){
			velocity = -1.0f;
			return true;
		}
		return false;
	}
	
	public void render(SpriteBatch batch){
		if(x == leftWallBounds){
			batch.draw(playerLeft, x, y, width, height);
		}else if(x == rightWallBounds){
			batch.draw(playerRight, x, y, width, height);
		}else{
			batch.draw(playerNormal, x, y, width, height);
		}
	}
	
	public void reset(){
		speed = STARTSPEED;
	}
	
	public Rectangle getBounds(){
		bounds.x = x;
		bounds.y = y;
		bounds.width = width;
		bounds.height = height;
		return bounds;
	}

}
