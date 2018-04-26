package io.humbledude.taskdriven;

import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.concurrent.Callable;

import io.humbledude.taskdriven.taskutil.Threads;
import io.humbledude.taskdriven.taskutil.Task;

/**
 * Created by keunhui.park on 2018. 4. 26..
 */

public class SnackBarTask extends Task {
    public SnackBarTask(final View view, final String ip) {
        super(
            new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    Snackbar.make(view, "Replace with your own action, " + ip, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    return null;
                }
            },
            Threads.MAIN_LOOPER);
    }
}
