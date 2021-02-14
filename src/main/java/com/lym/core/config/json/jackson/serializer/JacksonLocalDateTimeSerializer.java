package com.lym.core.config.json.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tustrip.time.TimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by liuyanmin on 2019/10/9.
 */
public class JacksonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localDateTime != null) {
            String string = TimeUtil.formatDateTime(localDateTime);
            jsonGenerator.writeString(string);
        }
    }

}
