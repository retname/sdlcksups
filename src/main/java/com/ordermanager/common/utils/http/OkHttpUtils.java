package com.ordermanager.common.utils.http;

import com.ordermanager.common.utils.Md5Utils;
import com.ordermanager.project.ups.interceptor.LogInterceptor;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 与接口对接信息
 * Created by zhnagyu
 */
@Component
public class OkHttpUtils {

    private static OkHttpClient client;

    private OkHttpUtils(LogInterceptor loggingInterceptor) {
        client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(200, TimeUnit.SECONDS)
                .readTimeout(200, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }



    public static String post(String url,String params) throws IOException {
        Headers.Builder headersBuilder = new Headers.Builder();
        Headers headers = headersBuilder.set("Content-Type", "application/json").build();
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/xml"), params);
        Request request = new Request.Builder().url(url).headers(headers)
                .post(requestBody).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     *
     *
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     *            jsonFlag :是否是json格式发送
     *            token：
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性

            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            if(param!=null){
                out.print(param);
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }

            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static void main(String[] args){
        //发送 GET 请求

      /*  String s=sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
        System.out.println(s);*/

        //发送 POST 请求
       /* String url="http://test.b2s2c.com/crm/oauth/token?client_id=ERP&client_secret=ERP&grant_type=password&username=crm_test&password=123456";

        String sr=sendPost(url, null);
        System.out.println(sr);
*/
        long time  = System.currentTimeMillis();
        System.out.println(time);

        System.out.println(Md5Utils.hash("30669cfc157ba29a" + "a8bde428746b540bd95291c417b2d260" + time));

    }
}
