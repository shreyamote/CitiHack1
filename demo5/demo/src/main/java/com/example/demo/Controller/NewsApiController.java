package com.example.demo.Controller;

import com.example.demo.Service.NewsApiExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class NewsApiController {

    private final NewsApiExample newsApiExample;

    @Autowired
    public NewsApiController(NewsApiExample newsApiExample) {
        this.newsApiExample = newsApiExample;
    }

    @GetMapping("/news")
    public List<Map<String, String>> getNews(@RequestParam String query) {
        return newsApiExample.getNews(query);
    }
}
