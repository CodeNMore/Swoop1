package devlopment.codenmore.ld32prep.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import devlopment.codenmore.ld32prep.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = Main.DESKWIDTH;
		config.height = Main.DESKHEIGHT;
		config.title = Main.TITLE;
		
		new LwjglApplication(new Main(), config);
	}
}
