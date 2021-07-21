package com.continental.blogapplication.common.exception;

import com.continental.blogapplication.common.response.SpringApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PhotoNotFoundException {
    private static final long serialVersionUID = 1L;

    private transient SpringApiResponse apiResponse;

    private String photoTitle;
    private String fieldName;
    private Object fieldValue;


    private void setApiResponse() {
        String message = String.format("%s not found for- %s: '%s'", photoTitle, fieldName, fieldValue);

        apiResponse = new SpringApiResponse(Boolean.FALSE, message);
    }

}
