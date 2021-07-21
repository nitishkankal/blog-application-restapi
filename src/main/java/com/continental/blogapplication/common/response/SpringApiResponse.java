package com.continental.blogapplication.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
public class SpringApiResponse {
    @JsonIgnore
    private static final long serialVersionUID = 0L;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("message")
    private String message;

    @JsonIgnore
    private HttpStatus status;

    public SpringApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
