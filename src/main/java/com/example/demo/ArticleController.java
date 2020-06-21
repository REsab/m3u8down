package com.example.demo;

import com.example.demo.domain.DownloadFile;
import com.example.demo.service.DownService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ArticleController {

    @GetMapping("/")
    public String hello() {
        return "index.html";
    }


    @Autowired
    private DownService downService;

    @GetMapping("/down1")
    public Map get23d33(DownloadFile downloadFile) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("path", downloadFile.getPath());
        map.put("filename", downloadFile.getFileName());
        map.put("url", downloadFile.getUrlm3u8());

        downService.Down(downloadFile);

        log.info("download  --------------------------------------------------");
        log.info("download  -************************************************-");
        log.info(downloadFile.getPath());
        log.info(downloadFile.getFileName());
        log.info(downloadFile.getUrlm3u8());
        return map;
    }


    @GetMapping("/adown/{path}/{id}/{id2}")
    public Map get2333(@PathVariable String path, @PathVariable String id, @PathVariable String id2) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", path);
        map.put("name", id2);
        map.put("desc", id2);

        log.info("download  --------------------------------------------------");
        log.info("download  -************************************************-");

        return map;
    }


}
