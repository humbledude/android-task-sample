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

    private Runnable mRunnable;
    private Consumer<O> mOnFinishAction;

    public TaskBase(final Callable<O> callable) {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    O result = callable.call();
                    if (mOnFinishAction != null) {
                        mOnFinishAction.accept(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void runOn(Looper looper) {
        new Handler(looper).post(mRunnable);
    }

    public TaskBase onFinish(Consumer<O> consumer) {
        mOnFinishAction = consumer;
        return this;
    }

    public interface Consumer<T> {
        void accept(T t);
    }

}
