package be.pxl.springboot.test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelperClass {

    public static <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    public static <T> List<T> createMockList(T type, T type2, T type3) {
        List<T> objects = new ArrayList<T>();
        objects.add(type);
        objects.add(type2);
        objects.add(type3);
        return objects;
    }
}
