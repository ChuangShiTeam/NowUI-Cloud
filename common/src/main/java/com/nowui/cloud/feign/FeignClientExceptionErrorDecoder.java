package com.nowui.cloud.feign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.constant.Constant;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author ZhongYongQiang
 */
@Configuration
public class FeignClientExceptionErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String body = Util.toString(response.body().asReader());
            JSONObject jsonObject = JSON.parseObject(body);

            throw new RuntimeException(jsonObject.getString(Constant.MESSAGE));
        } catch (IOException ex) {
            throw new RuntimeException("解析JSON错误");
        }
    }

}
