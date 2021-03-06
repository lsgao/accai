package com.ruptech.ai.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ls_gao on 2015/2/10.
 */
public class Utils {

    public static boolean isMobileNetworkAvailible(Context content) {
        ConnectivityManager conMan = (ConnectivityManager) content
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            // mobile 3G Data Network
            State mobile = conMan.getNetworkInfo(
                    ConnectivityManager.TYPE_MOBILE).getState();

            if (mobile == State.CONNECTED || mobile == State.CONNECTING)
                return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isWifiAvailible(Context content) {
        if (content == null) {
            return true;
        }

        ConnectivityManager conMan = (ConnectivityManager) content
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // wifi
        State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        if (wifi == State.CONNECTED || wifi == State.CONNECTING)
            return true;
        return false;
    }

    public static boolean checkNetwork(Context content) {
        boolean network = Utils.isMobileNetworkAvailible(content)
                || Utils.isWifiAvailible(content);
        return network;
    }

    /*
     * 工时的计算方法：
     * 可根据开始时间和结束时间计算工时。1小时=1工时，最小分辨率为0.5工时（不足15分钟不记工时，超过15分钟为0.5小时）。
     */
    public static String getWorkHour(long seconds) {
        long minutes = seconds / 60;
        int hours = (int)(minutes / 60);
        int rest_minutes = (int)(minutes % 60);
        String workhour = "0.0";
        if(rest_minutes >= 45) {
            workhour = new Integer(hours + 1).toString();
        } else if(rest_minutes < 45 && rest_minutes >= 15) {
            workhour = new Integer(hours).toString() + ".5";
        } else {
            workhour = new Integer(hours).toString();
        }


        return workhour;
    }

    public static boolean isMobileNumber(String str) {
        //移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        //联通：130、131、132、152、155、156、185、186
        //电信：133、153、180、189、（1349卫通）
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isPhoneNumber(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}[-]?[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if(str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    public static String getSubString(String str, int length, String append) {
        if(str.length() > length) {
            str = str.substring(0, length) + append;
        }
        return str;
    }
}
