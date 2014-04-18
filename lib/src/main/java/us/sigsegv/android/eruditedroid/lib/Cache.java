package us.sigsegv.android.eruditedroid.lib;

import android.content.Context;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * We're a RAM cache for Erudite, we keep stuff, when android tells us to dump, we
 * will need to dump our cache data to the caches folder.
 *
 * Once the coast is clear and we start back up again, we will load ourselves back
 * into RAM...
 *
 * For simplicity's sake this will be a simple map of maps that tries to keep rough track
 * of how big the objects are, once it gets bigger than 5% of the device RAM, it will start
 * to implement a LIFO method of cleaning.  It will log cache hit stats for fun.
 *
 * Created by 0x8badbeef<0x8badbeef@sigsegv.us> on 4/17/2014.
 */
public class Cache {
    /**
     * The android application context
     */
    Context mContext;

    /**
     * Class tag for the logger
     */
    public static final String TAG = FileSystem.class.getSimpleName();
    private File mCacheFolder = new File(mContext.getCacheDir(), "eruditedbcache");
    private ConcurrentHashMap<String,String> mCacheMap = new java.util.concurrent.ConcurrentHashMap<String, String>();

    /**
     * Will create a new cache object
     * @param ctx The android context for the cache
     */
    public Cache(Context ctx){
        mContext = ctx;
    }

    /**
     * Should flush the memory cache to disk
     */

    public void onAppShutdown(){
        mContext = null;
    }

    /**
     * Should restore the cache from disk to RAM
     */

    public void onAppLaunch(){

    }
}
