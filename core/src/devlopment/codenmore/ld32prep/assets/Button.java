package devlopment.codenmore.ld32prep.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import devlopment.codenmore.ld32prep.Input;

public class Button {
	
	private TextureRegion texture;
	private Rectangle bounds;
	private float x, y, width, height;
	private float opacity = 1.0f;
	
	public Button(TextureRegion texture, float x, float y, float width, float height){
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(x, y, width, height);
	}
	public Button(TextureRegion texture, float x, float y){
		this(texture, x, y, texture.getRegionWidth(), texture.getRegionHeight());
	}
	
	public boolean clicked(){
		if(Input.justTapped()){
			if(getBounds().contains(Input.getX(0), Input.getY(0))){
				Assets.select.play(1.0f);
				return true;
			}
		}
		return false;
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(1.0f, 1.0f, 1.0f, opacity);
		batch.draw(texture, x, y, width, height);
		batch.setColor(Color.WHITE);
	}
	
	public Rectangle getBounds(){
		bounds.x = x;
		bounds.y = y;
		bounds.width = width;
		bounds.height = height;
		return bounds;
	}
	
	//GETTERS SETTERS
	
	public void incOpacity(float amt){
		opacity += amt;
		if(opacity > 1.0f){
			opacity = 1.0f;
		}
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	public TextureRegion getTexture() {
		return texture;
	}
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
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
	public float getOpacity() {
		return opacity;
	}
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}

}
