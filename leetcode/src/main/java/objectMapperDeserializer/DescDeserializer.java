package objectMapperDeserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class DescDeserializer extends JsonDeserializer<Description> {

    @Override
    public Description deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken currentToken = jp.getCurrentToken();
        JavaType contextualType = ctxt.getContextualType();
        DeserializationConfig config = ctxt.getConfig();
        TypeFactory typeFactory = ctxt.getTypeFactory();
        
        Class<?> activeView = ctxt.getActiveView();
        
        
        if (currentToken == JsonToken.START_OBJECT) {
            return (Description) ctxt.readValue(jp, Description.class);
        }
        return ctxt.readValue(jp, Object.class) == null ? null : null;
    }

}
