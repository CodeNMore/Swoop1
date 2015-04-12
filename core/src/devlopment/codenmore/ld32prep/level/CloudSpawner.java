package devlopment.codenmore.ld32prep.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import devlopment.codenmore.ld32prep.Main;
import devlopment.codenmore.ld32prep.assets.Assets;

public class CloudSpawner {
	
	private static TextureRegion texture = Assets.getRegion("cloud");
	private static final float SPEED1 = 20.0f, SPEED2 = 30.0f;
	private float speed;
	private float x, y;
	private float width, height;
	
	public CloudSpawner(){
		reset();
		width = 64;
		height = 32;
	}
	
	public void tick(float delta){
		y += speed * delta;
		if(y > Main.HEIGHT){
			reset();
		}
	}
	
	public void reset(){
		x = MathUtils.random(-16.0f, Main.WIDTH + 16.0f);
		y = -texture.getRegionHeight() - MathUtils.random(5.0f, 40.0f);
		speed = (MathUtils.randomBoolean()) ? SPEED1 : SPEED2;
	}
	
	public void render(SpriteBatch batch){
		batch.draw(texture, x, y, width, height);
	}

}
