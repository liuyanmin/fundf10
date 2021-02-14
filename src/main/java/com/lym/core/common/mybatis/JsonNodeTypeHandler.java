package com.lym.core.common.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <columnOverride column="json_string" javaType="com.fasterxml.jackson.databind.JsonNode" typeHandler="JsonNodeTypeHandler"/>
 *
 * Created by liuyanmin on 2019/9/30.
 */
@Log4j
public class JsonNodeTypeHandler extends BaseTypeHandler<JsonNode> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonNode parameter, JdbcType jdbcType) throws SQLException {
        String str;
        try {
            str = mapper.writeValueAsString(parameter);
        } catch (JsonProcessingException e) {
            log.error("类型转换异常: ", e);
            str = "{}";
        }
        ps.setString(i, str);
    }

    @Override
    public JsonNode getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonSource = rs.getString(columnName);
        if (jsonSource == null) {
            return null;
        }
        try {
            JsonNode jsonNode = mapper.readTree(jsonSource);
            return jsonNode;
        } catch (IOException e) {
            log.error("类型转换异常: ", e);
        }
        return null;
    }

    @Override
    public JsonNode getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonSource = rs.getString(columnIndex);
        if (jsonSource == null) {
            return null;
        }
        try {
            JsonNode jsonNode = mapper.readTree(jsonSource);
            return jsonNode;
        } catch (IOException e) {
            log.error("类型转换异常: ", e);
        }
        return null;

    }

    @Override
    public JsonNode getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonSource = cs.getString(columnIndex);
        if (jsonSource == null) {
            return null;
        }
        try {
            JsonNode jsonNode = mapper.readTree(jsonSource);
            return jsonNode;
        } catch (IOException e) {
            log.error("类型转换异常: ", e);
        }
        return null;
    }

}
