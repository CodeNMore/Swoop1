package devlopment.codenmore.ld32prep.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

public class Assets {
	
	private static AssetManager manager;
	private static BitmapFont font;
	private static TextureAtlas atlas;
	private static ObjectMap<String, TextureRegion> regions;
	public static Sound bloop, hit, select;
	
	private Assets(){}
	
	public static void init(){
		manager = new AssetManager();
		regions = new ObjectMap<String, TextureRegion>();
		manager.load("textures/atlas.pack", TextureAtlas.class);
		manager.load("font/CodeNFontI.fnt", BitmapFont.class);
		
		Texture.setAssetManager(manager);
		
		bloop = Gdx.audio.newSound(Gdx.files.internal("sounds/bloop.wav"));
		hit = Gdx.audio.newSound(Gdx.files.internal("sounds/hit.wav"));
		select = Gdx.audio.newSound(Gdx.files.internal("sounds/select.wav"));
	}
	
	public static void postInit(){
		font = manager.get("font/CodeNFontI.fnt", BitmapFont.class);
		atlas = manager.get("textures/atlas.pack", TextureAtlas.class);
	}
	
	public static TextureRegion getRegion(String name){
		if(regions.containsKey(name)){
			return regions.get(name);
		}
		regions.put(name, atlas.findRegion(name));
		return regions.get(name);
	}
	
	public static void setFontSmall(){
		font.setScale(1.0f);
	}
	
	public static void setFontMed(){
		font.setScale(2.0f);
	}
	
	public static void setFontBig(){
		font.setScale(3.0f);
	}
	
	public static void setFontColor(Color color){
		font.setColor(color);
	}
	
	public static void drawString(SpriteBatch batch, String str, float x, float y){
		font.draw(batch, str, x, y);
	}
	
	public static BitmapFont getFont(){
		return font;
	}
	
	public static void resumeUpdate(){
		manager.finishLoading();
	}
	
	public static void dispose(){
		manager.dispose();
		bloop.dispose();
		hit.dispose();
		select.dispose();
	}

}
