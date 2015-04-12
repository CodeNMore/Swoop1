package devlopment.codenmore.ld32prep.level;

import com.badlogic.gdx.utils.Pool;

public class PlatformPool extends Pool<Platform> {

	private PlatformSpawner spawner;
	
	public PlatformPool(PlatformSpawner spawner){
		this.spawner = spawner;
	}
	
	@Override
	protected Platform newObject() {
		return new Platform(spawner, 1, false);
	}

}
