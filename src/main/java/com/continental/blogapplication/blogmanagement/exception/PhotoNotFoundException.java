package com.continental.blogapplication.blogmanagement.exception;

import com.continental.blogapplication.common.response.SpringApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PhotoNotFoundException  extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private transient SpringApiResponse apiResponse;

    private Integer photoId;
    private String photoTitle;

    public PhotoNotFoundException(Integer photoId){
        super();
        this.photoId = photoId;
    }

    private void setApiResponse() {
        String message = String.format("Photo id - %s not found'", photoId);

        apiResponse = new SpringApiResponse(Boolean.FALSE, message);
    }
    public SpringApiResponse getApiResponse() {
        return apiResponse;
    }

}
