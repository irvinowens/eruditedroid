package us.sigsegv.android.eruditedroid.lib;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * After tons of debate, I decided to make the DB an Android service.
 * It really only makes sense to do this, there is no real other way
 * to go.
 */

public class EruditeService extends Service {
    public EruditeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
