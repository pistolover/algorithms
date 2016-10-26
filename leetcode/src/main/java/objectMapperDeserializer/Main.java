package objectMapperDeserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = JsonUtil.OBJECT_MAPPER;
        String value = "{\"id\":\"001\",\"name\":\"jack\",\"desc\":{\"email\":\"www.126.com\",\"phone\":\"10086\"}}";

        value = "{\"id\":\"001\",\"name\":\"jack\",\"desc\":\"\"}}";

        Person readValue = mapper.readValue(value, Person.class);

        System.err.println(readValue.toString());
    }
}
