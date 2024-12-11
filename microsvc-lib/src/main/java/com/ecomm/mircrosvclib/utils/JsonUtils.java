package com.ecomm.mircrosvclib.utils;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.micrometer.common.util.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonUtils {

    private static final Logger LOG = Logger.getLogger(JsonUtils.class.getName());
    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    private JsonUtils() {
        // Utility class
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper;
    }

    public static String removeKeyFromJSONString(final String jsonString, final String keyName) throws JSONException {
        if (StringUtils.isNotEmpty(jsonString) && StringUtils.isNotEmpty(keyName)) {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.has(keyName)) {
                jsonObject.remove(keyName);
                return jsonObject.toString();
            }
        }
        return jsonString;
    }

    public static String getJSON(final Object objToConvert) {
        return getStringFromObj(objToConvert);
    }

    public static String unescape(final String input) {
        if (input == null) {
            return null;
        }
        return input.replace("\\\"", "\"")
                .replace("\\\\", "\\")
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t")
                .replace("\\/", "/");
    }

    private static String getStringFromObj(final Object objToConvert) {
        if (objToConvert == null) {
            return "";
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            JsonGenerator jsonGenerator = OBJECT_MAPPER.getFactory().createGenerator(out, JsonEncoding.UTF8);
            jsonGenerator.writeObject(objToConvert);
            return out.toString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error converting object to JSON: {0}", e.getMessage());
            return "";
        }
    }

    public static <T> T getBeanByJson(final String jsonString, final Class<T> claz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(jsonString, claz);
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error parsing JSON to bean: {0}", e.getMessage());
            return null;
        }
    }

    public static <T> List<T> getListBeanByJson(final String jsonString, final Class<T> claz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(
                    jsonString, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, claz)
            );
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error parsing JSON to list: {0}", e.getMessage());
            return null;
        }
    }

    public static <T> T getBeanByObject(final Object obj, final Class<T> claz) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.convertValue(obj, claz);
        } catch (IllegalArgumentException e) {
            LOG.log(Level.WARNING, "Error converting object to bean: {0}", e.getMessage());
            return null;
        }
    }
}
