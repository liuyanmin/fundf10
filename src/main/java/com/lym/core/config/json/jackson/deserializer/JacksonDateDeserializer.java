package com.lym.core.config.json.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.lym.core.config.converter.StringToDateUtil;

import java.io.IOException;
import java.util.Date;

/**
 * Created by liuyanmin on 2019/9/30.
 */
public class JacksonDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        return StringToDateUtil.convert(date);
    }
}
