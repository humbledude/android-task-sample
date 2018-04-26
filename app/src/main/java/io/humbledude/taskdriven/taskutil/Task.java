package io.humbledude.taskdriven.taskutil;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;

/**
 * Created by keunhui.park on 2018. 4. 26..
 */

public class Task<O> {

    private Handler mHandler;
    private Callable<O> mCallable;
    private Consumer<O> mOnFinishAction;

    public Task(Callable<O> callable, Looper looper) {
        mHandler = new Handler(looper);
        mCallable = callable;
    }

    public Task run() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    O result = mCallable.call();
                    if (mOnFinishAction != null) {
                       mOnFinishAction.accept(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return this;
    }

    public Task onFinish(Consumer<O> consumer) {
        mOnFinishAction = consumer;
        return this;
    }

    public interface Consumer<T> {
        void accept(T t);
    }

}
