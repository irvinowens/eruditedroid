package us.sigsegv.android.eruditedroid.lib;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Erudite is a mobile version of the NoSQL database
 * It is intended to be a fully featured NoSQL database utilizing pattern matching
 * as its method of query.
 *
 * Created by 0x8badbeef<0x8badbeef@sigsegv.us> on 4/17/2014.
 */
public class Erudite {
    private static Erudite sErudite;
    /**
     * We need an instance of the singleton to write through
     */
    private FileSystem mFs;
    /**
     * We need a handle to the context too
     */
    private Context mContext;
    /**
     * We only want to run a single thread per proc if possible, let the android scheduler do its
     * job to figure out where to put us
     */
    ExecutorService mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static synchronized Erudite getErudite(Context ctx){
        if(sErudite == null){
            sErudite = new Erudite(ctx);
        }
        return sErudite;
    }

    /**
     * Private singleton method
     * @param ctx The android context for this session
     */

    private Erudite(Context ctx){
        mContext = ctx;
        mFs = FileSystem.getFileSystem(ctx);
    }

    public void closeDatabase(){
        mContext = null;
    }
}
