package us.sigsegv.android.eruditedroid.lib;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * The Erudite file system is responsible for saving files, creating files, deleting files
 * writing to files, etc...
 *
 * The filesystem class needs to be a singleton as it behaves something like a DAO.  Also,
 * just for good measure ( and security ) Erudite can only exist on the system's main filesystem
 * it will not allow itself to be located on the extSdCard or the sdCard mount points.
 *
 * The filesystem will create files based on the most significant bit will refer to the server,
 * unused in this implementation, the next 5 hex values in the hash will point to the file
 * result.  The row will be allowed to have 64kb, with the last bit indicating the next file to read.
 * Staying under 64kb for rows will prove to have an advantage, if it goes over, the next file
 * will be labelled with the hash key followed by the bit index 0, 1, 2, etc... up through 7.
 * Therefore the largest row will be 576kb.  Did I say this was a text database ;-p.
 *
 * If you need more content, you should shard your data into multiple rows.  This will keep
 * the database responsive.
 *
 * When dealing with the journal, it will work as expected, from the oldest entry to the most
 * recent.  In a perfect situation, the database will have minimal lag between commit and the
 * data actually being present in the db file.  When busy, the journal will grow and grow.
 *
 * Created by 0x8badbeef<0x8badbeef@sigsegv.us> on 4/17/2014.
 */
public class FileSystem {
    /**
     * Successful file creation
     */
    public static final int FILE_CREATE_SUCCESS = 1;
    /**
     * File creation failure
     */
    public static final int FILE_CREATE_FAILURE = 2;
    /**
     * File seek failure
     */
    public static final int FILE_SEEK_FAILURE = 3;
    /**
     * File seek success
     */
    public static final int FILE_SEEK_SUCCESS = 4;
    /**
     * File vacuum success
     */
    public static final int FILE_VACUUM_SUCCESS = 5;
    /**
     * File vacuum failure
     */
    public static final int FILE_VACUUM_FAILURE = 6;
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
    private File mDatabaseFolder;
    /**
     * The android context, we have to be careful here since we need to make sure to
     * release this properly relative to the android lifecycle
     */
    private Context mContext;
    /**
     * Get the filesystem instance
     * @return
     */

    public synchronized static FileSystem getFileSystem(Context ctx){
        if (sFs == null) {
            sFs = new FileSystem(ctx);
            Log.d(TAG, "Created singleton instance");
        }
        return sFs;
    }

    /**
     * Since this is a singleton, we will need to return only myself
     */

    protected FileSystem(Context ctx){
        this.mContext = ctx;
        mDatabaseFolder= new File(ctx.getFilesDir(), "eruditedb");
    }

    /**
     * Will create a new file with a given file name
     * @param name The file name with which to create the file
     * @return The process status
     */

    public int createNewFile(String name){
        File f = new File(mDatabaseFolder.getAbsolutePath(), name);
        try {
            boolean created = f.createNewFile();
            if(created){
                return FILE_CREATE_SUCCESS;
            }else{
                return FILE_CREATE_FAILURE;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "There was a problem creating the file: " + e.getLocalizedMessage());
        }
        return FILE_CREATE_FAILURE;
    }

    /**
     * Will release the erudite filesystem's reference to the android context and
     * close any file descriptors that are currently open.
     */
    public void closeFileSystem(){
        this.mContext = null;
    }
}
