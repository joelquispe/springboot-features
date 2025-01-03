package com.yoecode.features_springboot.core.models;

import lombok.Data;

@Data
public class DataResponse<T> {
    private T data;
    private int status;
    private String message;
}
