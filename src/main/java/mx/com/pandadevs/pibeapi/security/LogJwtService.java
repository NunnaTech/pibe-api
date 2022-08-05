package mx.com.pandadevs.pibeapi.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import mx.com.pandadevs.pibeapi.models.users.dto.UserProfileDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogJwtService {
    private Logger logger = LoggerFactory.getLogger(LogJwtService.class);
    @Autowired
    private UserService userService;

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

    public Map<String, String> getUsernameAndRole(String bearerToken) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        Map<String, String> payload = getPayload(bearerToken);
        String payloadString = payload.values().stream().findFirst().toString();
        map.put("username", payload.get("sub"));
        map.put("role", payloadString.substring(10, payloadString.length() - 2));
        return map;
    }
    
    public Boolean isRecruiter(String bearerToken) {
        try {
            Map<String, String> auth = getUsernameAndRole(bearerToken);
            return auth.get("role").contains("ROLE_RECRUITER");
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    public Boolean isCandidate(String bearerToken) {
        try {
            Map<String, String> auth = getUsernameAndRole(bearerToken);
            return auth.get("role").contains("ROLE_CANDIDATE");
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    public Boolean isOwner(String bearerToken, String owner) {
        try {
            Map<String, String> auth = getUsernameAndRole(bearerToken);
            Optional<UserProfileDto> user = userService.getByUsername(auth.get("username"));
            return user.get().getUsername().equals(owner);
        } catch (JsonProcessingException e) {
            return false;
        }
    }

}
