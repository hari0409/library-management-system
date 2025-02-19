package com.example.library_management_system.misc;

public class ResponseObject<T> {
    public T data;
    public String status;

    public ResponseObject(String status, T data) {
        this.status = status;
        this.data = data;
    }
}
