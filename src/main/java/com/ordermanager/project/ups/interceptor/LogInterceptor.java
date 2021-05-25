package com.ordermanager.project.ups.interceptor;

import com.ordermanager.project.ups.domain.ApiLog;
import com.ordermanager.project.ups.mapper.ApiLogMapper;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LogInterceptor implements Interceptor {


    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Autowired(required = false)
    private ApiLogMapper apiLogMapper;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        ApiLog apiLog = new ApiLog();

        long start = System.currentTimeMillis();


        RequestBody requestBody =  request.body();
        apiLog.setApiUrl(request.url().toString());  //请求地址

        Headers headers = request.headers();   //请求头
        apiLog.setRequestHeader(headers.toString());


        if(requestBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBody;

            StringBuilder requestParamBuilder = new StringBuilder("{");
            for (int i = 0; i < formBody.size(); i++) {
                if(i > 0) {
                    requestParamBuilder.append(", ");
                }
                requestParamBuilder.append(formBody.encodedName(i) + ":" + formBody.encodedValue(i));
            }
            requestParamBuilder.append("}");
            String reuqestParam = requestParamBuilder.toString();
            apiLog.setRequestParams(reuqestParam);

        } else if(requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            apiLog.setRequestParams(buffer.clone().readString(StandardCharsets.UTF_8));
        }


        Response response = chain.proceed(request);
        apiLog.setResponseStatus(String.valueOf(response.code()));  //返回状态码
        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        apiLog.setResponseBody(buffer.clone().readString(StandardCharsets.UTF_8));

        long end = System.currentTimeMillis();

        logger.info("接口："+apiLog.getApiUrl()+"消耗时间："+(new Long(end - start).intValue()));
        apiLogMapper.insertApiLog(apiLog);
        return response;
    }
}
