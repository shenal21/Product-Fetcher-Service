package com.example.productfetcher.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ErrorResponse {
    @JsonIgnore
    private int status;
    private String error;
    private String message;

}
