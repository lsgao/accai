package com.ruptech.ai;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.baidu.android.pushservice.PushManager;
import com.baidu.frontia.FrontiaApplication;
import com.ruptech.ai.model.User;
import com.ruptech.ai.utils.AssetsPropertyReader;
import com.ruptech.ai.utils.PrefUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;


/**
 * A login screen that offers login via email/password.
 */
public class App extends FrontiaApplication {
    public final static String TAG = App.class.getName();

    private static final String SAVE_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard ";//保存到SD卡
    public static final String SAVE_PIC_PATH = SAVE_PATH + "/ai/images";//保存图片的确切位置
    public static final String SAVE_FILE_PATH = SAVE_PATH + "/ai/properties";//保存图片的确切位置

    public static  Properties evn_properties;
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
         evn_properties = assetsPropertyReader.getProperties("env.properties");

    }

    public static Properties loadProperties(Context context, String file) {
        Properties properties = new Properties();
        try {
            FileInputStream s = new FileInputStream(file);
            properties.load(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void saveConfig(Context context, String file, Properties properties) {
        try {
            FileOutputStream s = new FileOutputStream(file, false);
            properties.store(s, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
