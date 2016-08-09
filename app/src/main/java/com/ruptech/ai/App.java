package com.ruptech.ai;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import com.baidu.android.pushservice.PushManager;
import com.baidu.frontia.FrontiaApplication;
import com.ruptech.ai.main.MainActivity;
import com.ruptech.ai.model.User;
import com.ruptech.ai.utils.AssetsPropertyReader;
import com.ruptech.ai.utils.PrefUtils;

import java.util.Properties;


/**
 * A login screen that offers login via email/password.
 */
public class App extends FrontiaApplication {
    public final static String TAG = App.class.getName();
    static public Properties properties;
    public static Context mContext;
    public static NotificationManager notificationManager;
    private static User user;

    public static User readUser() {
        if (user == null)
            user = PrefUtils.readUser();
        return user;
    }

    public static void saveUser(User user) {
        PrefUtils.writeUser(user);
        App.user = user;
    }

    public static void logout() {
        PushManager.stopWork(App.mContext);

        Log.v(TAG, "logout.");
        MainActivity.close();
        App.saveUser(null);
    }

     @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG)
            Log.i(TAG, "App.onCreate");

        mContext = this.getApplicationContext();
        notificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        AssetsPropertyReader assetsPropertyReader = new AssetsPropertyReader(this);
        properties = assetsPropertyReader.getProperties("env.properties");

    }

}



