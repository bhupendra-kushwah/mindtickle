package org.mindtickle.petstore.utils.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonSerializationAndDeserialization<T> {
    public JsonSerializationAndDeserialization() {
    }

    @SuppressWarnings("unchecked")
	public T deserializeJson(String jsonString, Class<T> deserializableClass) {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = null;

        try {
            obj = mapper.readValue(jsonString, deserializableClass);
        } catch (JsonGenerationException var6) {
            var6.printStackTrace();
        } catch (JsonMappingException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return (T) obj;
    }

    public String serializeJson(Object serializableClass) {
        ObjectMapper mapper = new ObjectMapper();
        String serializedString = null;

        try {
            serializedString = mapper.writeValueAsString(serializableClass);
        } catch (JsonProcessingException var5) {
            var5.printStackTrace();
        }

        return serializedString;
    }
    
    public T getApiJsonResponseBeen(String responseBody, Class<T> responseClass ) {
		T beenObj = new JsonSerializationAndDeserialization<T>()
		.deserializeJson( responseBody, responseClass);
		return beenObj;
	}
}

