package io.dabank.sdkexample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import io.dabank.sdk.providers.JsonProvider;

import java.io.IOException;

public class DabankJsonProvider implements JsonProvider {
    private ObjectMapper _mapper;

    private ObjectMapper mapper() {
        if (_mapper == null) {
            _mapper = new ObjectMapper();
            _mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }
        return _mapper;
    }

    @Override
    public String toJson(Object o) {
        try {
            return mapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "error: json write failed -> " + e.getMessage();
        }
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper().readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
