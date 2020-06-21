package com.example.demo.domain;

import lombok.Data;

@Data
public class DownloadFile {

    private String fileName;
    private String path;
    private String urlm3u8;

    public DownloadFile setPath(String path) {
        if (path == null) {
            path = "C://Downm3u8";
        }
        this.path = path;
        return this;
    }
}
