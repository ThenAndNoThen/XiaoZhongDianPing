package com.example.administrator.xiaozhongdianping.util;

import org.apache.http.conn.ConnectTimeoutException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class HttpUtil {
    public static String  serverUrl="http://10.210.100.12:8080/ServerProject/";
    public static void sendHttpRequest(final String address,final String gsonstr, final
    HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {

                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.connect();
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.write(gsonstr.getBytes("UTF-8"));
                    out.flush();
                    out.close();

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
// 回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                }catch(ConnectTimeoutException e){
                    e.printStackTrace();
                    listener.onError(e);
                }catch (IOException e){
                    e.printStackTrace();
                    listener.onError(e);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    public interface HttpCallbackListener {
        void onFinish(String response);
        void onError(Exception e);
    }
}
