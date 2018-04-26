package io.humbledude.taskdriven.tasks;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.concurrent.Callable;

/**
 * Created by keunhui.park on 2018. 4. 26..
 */

public class TaskBase<O> {

    public static final Looper MAIN_LOOPER = Looper.getMainLooper();
    public static Looper WORKER_LOOPER;

    static {
        HandlerThread worker = new HandlerThread("worker");
        worker.start();
        WORKER_LOOPER = worker.getLooper();
    }

    private Handler mHandler;
    private Runnable mRunnable;
    private Callback<O> mOnFinishCallback;

    public TaskBase(final Callable<O> callable) {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    O result = callable.call();
                    if (mOnFinishCallback != null) {
                        mOnFinishCallback.onFinish(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public TaskBase<O> runOn(Looper looper) {
        mHandler = new Handler(looper);
        return this;
    }

    public void runNow() {
        mHandler.post(mRunnable);
    }

    public TaskBase onFinish(Callback<O> callback) {
        mOnFinishCallback = callback;
        return this;
    }

    public interface Callback<T> {
        void onFinish(T t);
    }

}
