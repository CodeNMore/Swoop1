package devlopment.codenmore.ld32prep.level;

import com.badlogic.gdx.math.MathUtils;


public class PlatformIntervaler {
	
	private PlatformSpawner spawner;
	private float timer;
	
	private static final float MIN_TIME_BETWEEN = 0.55f, MAX_TIME_BETWEEN = 2.05f;
	private static final float MEDIAN_3_1_CHANGE = 0.638f;
	private static final float MEDIAN_SWAY = 0.35f;
	private static final float START_WAIT_TIME = 0.1f;
	private float maxMinSub = 0.0f;
	private float betweenMedian = MAX_TIME_BETWEEN - MEDIAN_SWAY;
	private float nextInterval = START_WAIT_TIME;
	private boolean lastSide;
	private int sideCounts = 0;
	
	public PlatformIntervaler(PlatformSpawner spawner){
		this.spawner = spawner;
		reset();
	}
	
	public void tick(float delta){
		//Update Timer
		updateTimer(delta);
		//Logic
		if(timer >= nextInterval){
			//SPAWN THIS PLATFORM
			//Check Side Booleans
			boolean side = MathUtils.randomBoolean();
			if(lastSide == side){
				sideCounts++;
				if(sideCounts > 2){//2 really 3
					side = !side;
					lastSide = side;
					sideCounts = 0;
				}
			}else{
				sideCounts = 0;
				lastSide = side;
			}
			//Spawn Platform
			if(betweenMedian < MEDIAN_3_1_CHANGE){
				spawner.addPlatform(MathUtils.random(1, 3), side);
			}else{
				spawner.addPlatform(MathUtils.random(3, 5), side);
			}
			//MaxMinSub setting
			maxMinSub = spawner.getLevel().getSpeed() / Level.MAX_SPEED + 0.1f;
			
			//SETUP NEXT INTERVAL
			if(maxMinSub > 0.5f){
				nextInterval = MathUtils.random(betweenMedian - MEDIAN_SWAY + 0.15f, betweenMedian + MEDIAN_SWAY - 0.15f);
			}else{
				nextInterval = MathUtils.random(betweenMedian - MEDIAN_SWAY, betweenMedian + MEDIAN_SWAY);
			}
//			betweenMedian = MAX_TIME_BETWEEN - MEDIAN_SWAY - spawner.getLevel().getSpeed() - Level.STARTSPEED;
			betweenMedian = MAX_TIME_BETWEEN - maxMinSub - MEDIAN_SWAY;
			//CHECK TIME BOUNDS OF EVERYTHING
			if(betweenMedian < MIN_TIME_BETWEEN - maxMinSub + MEDIAN_SWAY){
				betweenMedian = MIN_TIME_BETWEEN - maxMinSub + MEDIAN_SWAY;
			}
			//Reset Everything
			resetTimer();
		}
	}
	
	public void reset(){
		resetTimer();
		betweenMedian = MAX_TIME_BETWEEN - MEDIAN_SWAY;
		nextInterval = START_WAIT_TIME;
		sideCounts = 0;
		maxMinSub = 0.0f;
	}
	
	public void resetTimer(){
		timer = 0;
	}
	
	private void updateTimer(float delta){
		timer += delta;
	}

}
