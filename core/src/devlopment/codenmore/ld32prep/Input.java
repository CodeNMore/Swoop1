package devlopment.codenmore.ld32prep;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Input {
	
	private static OrthographicCamera cam;
	private static Viewport viewport;
	private static Vector3 tmp;
	
	public Input(){}
	
	public static void init(OrthographicCamera cam, Viewport viewport){
		Input.cam = cam;
		Input.viewport = viewport;
		tmp = new Vector3();
	}

	public static boolean justTapped(){
		if(Gdx.input.isKeyJustPressed(Keys.SPACE))
			return true;
		return Gdx.input.justTouched();
	}
	
	public static int getX(int id){
		tmp.x = Gdx.input.getX(id);
		cam.unproject(tmp, viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
		return (int) tmp.x;
	}
	
	public static int getY(int id){
		tmp.y = Gdx.input.getY(id);
		cam.unproject(tmp, viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
		return (int) tmp.y;
	}
	
}
