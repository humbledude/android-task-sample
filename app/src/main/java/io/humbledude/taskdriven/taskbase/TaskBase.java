package io.humbledude.taskdriven.taskbase;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;

/**
 * Created by keunhui.park on 2018. 4. 26..
 */

public class TaskBase<O> {

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
