package com.euler.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

/**
 * 手机验证码发送工具类
 *
 * @author <a href="mailto:87340454@qq.com">Li Hangfei</a>
 * @date 2021/4/6
 */
public class SmsUtil {
    private static String x_id = "wgsx";
    private static String x_pwd = "x396311";
    private static int verCodeCount=6;

    public static String SendSms(String mobile, String content) {
        HttpURLConnection httpConnection = null;
        String result = "Error";
        StringBuilder sb = new StringBuilder();
        sb.append("http://service.winic.org:8009/sys_port/gateway/index.asp?");

        try {
            sb.append("id=").append(URLEncoder.encode(x_id, "gb2312"));
            sb.append("&pwd=").append(x_pwd);
            sb.append("&to=").append(mobile);
            sb.append("&content=").append(URLEncoder.encode(content, "gb2312"));
            sb.append("&time=").append("");

            URL url = new URL(sb.toString());
            httpConnection = (HttpURLConnection) url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            result = rd.readLine();
            rd.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return result;
    }

    public static String getPhoneVerCode() {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < verCodeCount; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
}
