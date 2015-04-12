package devlopment.codenmore.ld32prep.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;

import devlopment.codenmore.ld32prep.Main;
import devlopment.codenmore.ld32prep.assets.Assets;

public class Platform implements Poolable {
	
	private static TextureRegion texture = Assets.getRegion("platform");
	private PlatformSpawner spawner;
	private static float leftBounds = Main.WIDTH - 16;
	private float x, y;
	private float width = 16, height = 16;
	private boolean active = true, right;
	private int amount;
	private Rectangle bounds;
	
	public Platform(PlatformSpawner spawner, int amount, boolean right){
		this.spawner = spawner;
		this.amount = amount;
		this.right = right;
		bounds = new Rectangle();
		y = -16;
		
		if(right)
			x = leftBounds;
		else
			x = 16;
	}
	public void init(int amount, boolean right){
		this.amount = amount;
		active = true;
		this.right = right;
		y = -16;
		
		if(right)
			x = leftBounds;
		else
			x = 16;
	}
	@Override
	public void reset(){}
	
	public void tick(float delta, float wallPos){
//		y += spawner.getLevel().getSpeed() * delta;
		y += wallPos;
		if(y > Main.HEIGHT){
			active = false;
		}
		//COLLISIONS
		if(spawner.getLevel().getPlayer().getBounds().overlaps(getBounds())){
			spawner.getLevel().lose();
		}
	}
	
	public void render(SpriteBatch batch){
		for(int i = 0;i < amount;++i){
			if(!right){
				batch.draw(texture, x + i * width, y, width, height);
			}else{
				batch.draw(texture, x + i * width - (amount * width), y, width, height);
			}
		}
	}
	
	public Rectangle getBounds(){
		if(right)
			bounds.x = x - amount * width;
		else
			bounds.x = x;
		bounds.y = y;
		bounds.width = width * amount;
		bounds.height = height;
		return bounds;
	}
	
	//GETTERS SETTERS

	public PlatformSpawner getSpawner() {
		return spawner;
	}

	public void setSpawner(PlatformSpawner spawner) {
		this.spawner = spawner;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
