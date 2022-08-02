package mx.com.pandadevs.pibeapi.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogJwtService {
    private Logger logger = LoggerFactory.getLogger(LogJwtService.class);

    public Map<String, String> getPayload(String bearerToken) throws JsonProcessingException {
        Map<String, String> map;
        bearerToken = bearerToken.split(" ")[1];
        String[] parts = bearerToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(parts[1]));
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(payload, Map.class);
        return map;
    }

    public String getOnlyUsername(String bearerToken) throws JsonProcessingException {
        return getPayload(bearerToken).get("sub");
    }

    public String parseToJsonObeject(Object o) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            String data = mapper.writeValueAsString(o);
            return data;
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    public String getOnlyRole(String bearerToken) {
        try {
            String payload = getPayload(bearerToken).values().stream().findFirst().toString();
            return payload.substring(10, payload.length() - 2);
        } catch (Exception e) {
            return "";
        }
    }
}
