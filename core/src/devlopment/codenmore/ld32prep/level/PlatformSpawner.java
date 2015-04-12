package devlopment.codenmore.ld32prep.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class PlatformSpawner {
	
	private Level level;
	private PlatformPool pool;
	private Array<Platform> platforms;
	private PlatformIntervaler intervaler;
	
	public PlatformSpawner(Level level){
		this.level = level;
		pool = new PlatformPool(this);
		platforms = new Array<Platform>();
		
		intervaler = new PlatformIntervaler(this);
	}
	
	public void tick(float delta, float wallPos){
		intervaler.tick(delta);
		
		for(Platform p : platforms){
			p.tick(delta, wallPos);
			if(!p.isActive()){
				pool.free(p);
				platforms.removeValue(p, true);
				level.incScore(2);
			}
		}
	}
	
	public void render(SpriteBatch batch){
		for(Platform p : platforms){
			p.render(batch);
		}
	}
	
	public void reset(){
		intervaler.reset();
		for(Platform p : platforms){
			pool.free(p);
		}
		platforms.clear();
	}
	
	public void addPlatform(int amount, boolean right){
		Platform p = pool.obtain();
		p.init(amount, right);
		platforms.add(p);
	}
	
	public Level getLevel(){
		return level;
	}

}
