package tasks.familyhub.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationResponse {
    private String token;

    private String id;
    private String message;

    @Autowired
    public AuthenticationResponse(String token, String id, String message) {
        this.token = token;
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
