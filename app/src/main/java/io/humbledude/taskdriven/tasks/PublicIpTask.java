package io.humbledude.taskdriven.tasks;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

/**
 * Created by keunhui.park on 2018. 4. 26..
 */

public class PublicIpTask extends TaskBase<String> {

    public PublicIpTask() {
        super(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    URL url = new URL("http://ifconfig.co/ip");
                    URLConnection connection = url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

                    String buffer = reader.readLine();
                    Log.i("TEST", buffer);

                    reader.close();

                    return buffer;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

}
