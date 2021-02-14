package com.lym.core.config.json.fastjson;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.lym.core.common.constant.DatePattern;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyanmin on 2019/10/9.
 */
//@Configuration
public class FastJsonMvcConfig implements WebMvcConfigurer {

    private static final String DEFAULT_CHARSET = "UTF-8";

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(new MediaType("application", "xml"));
        mediaTypes.add(new MediaType("text", "xml"));
        mediaTypes.add(new MediaType("application", "*+xml"));
        mediaTypes.add(MediaType.ALL);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.forName(DEFAULT_CHARSET));
        fastJsonConfig.setDateFormat(DatePattern.yyyy_MM_dd_HH_mm_ss);
        SerializerFeature[] serializerFeatures = new SerializerFeature[]{
                SerializerFeature.PrettyFormat,                 // 格式化
                SerializerFeature.WriteMapNullValue,            // 输出空值
                SerializerFeature.WriteNullListAsEmpty,         // 空集合输出[]
                SerializerFeature.WriteDateUseDateFormat        // 日期格式化
        };
        fastJsonConfig.setSerializerFeatures(serializerFeatures);


        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);

        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(0, fastJsonHttpMessageConverter);
    }

}
