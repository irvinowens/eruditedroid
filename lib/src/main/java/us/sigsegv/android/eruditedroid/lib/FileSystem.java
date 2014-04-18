package us.sigsegv.android.eruditedroid.lib;

import android.util.Log;

/**
 * The Erudite file system is responsible for saving files, creating files, deleting files
 * writing to files, etc...
 *
 * The filesystem class needs to be a singleton as it behaves something like a DAO
 * Created by irvin_000 on 4/17/2014.
 */
public class FileSystem {
    /**
     * Singleton instance
     */
    public static FileSystem sFs;
    /**
     * Class tag for the logger
     */
    public static final String TAG = FileSystem.class.getSimpleName();
    /**
     * Now we need to set the path for the database to go where we can write
     */
    private 
    /**
     * Get the filesystem instance
     * @return
     */

    public synchronized static FileSystem getFileSystem(){
        if (sFs == null) {
            sFs = new FileSystem();
            Log.d(TAG, "Created singleton instance");
        }
        return sFs;
    }

    /**
     * Since this is a singleton, we will need to return only myself
     */

    protected FileSystem(){
        // does nothing, exists to prevent instantiation
    }
}
