package at.droidcon.vienna2016.core.dagger;

import at.droidcon.vienna2016.InternalDroidconApp;

public interface InternalAppGraph extends AppGraph {

    void inject(InternalDroidconApp app);
}
