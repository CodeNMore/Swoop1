package devlopment.codenmore.ld32prep.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import devlopment.codenmore.ld32prep.Main;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Main.DESKWIDTH, Main.DESKHEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Main();
        }
}