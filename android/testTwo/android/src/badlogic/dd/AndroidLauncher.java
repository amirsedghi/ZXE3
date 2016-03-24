package badlogic.dd;

import android.os.Bundle;
import com.badlogic.dd.DD;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * Created by evolerup on 3/16/16.
 */
public class AndroidLauncher extends AndroidApplication {
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        initialize(new DD(), config);
    }
}
