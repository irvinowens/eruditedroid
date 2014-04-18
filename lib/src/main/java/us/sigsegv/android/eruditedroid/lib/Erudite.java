package us.sigsegv.android.eruditedroid.lib;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Erudite is a mobile version of the NoSQL database
 * It is intended to be used for storing moderate amounts of data
 * Created by irvin_000 on 4/17/2014.
 */
public class Erudite {
    /**
     * We need an instance of the singleton to write through
     */
    private FileSystem mFs = FileSystem.getFileSystem();
    /**
     * We only want to run a single thread per proc if possible, let the android scheduler do its
     * job to figure out where to put us
     */
    ExecutorService mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

}
