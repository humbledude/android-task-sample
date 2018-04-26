package io.humbledude.taskdriven.taskutil;

import android.os.HandlerThread;
import android.os.Looper;

/**
 * Created by keunhui.park on 2018. 4. 26..
 */

public class Threads {

    public static final Looper MAIN_LOOPER = Looper.getMainLooper();
    public static Looper WORKER_LOOPER;

    static {
        HandlerThread worker = new HandlerThread("worker");
        worker.start();
        WORKER_LOOPER = worker.getLooper();
    }
}
