package com.spring.shortenedURL.controller;

import com.spring.shortenedURL.entity.URLMapping;
import com.spring.shortenedURL.service.ShortenedURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShortenedURLController {

    private final ShortenedURLService shortenedURLService;

    @Autowired
    public ShortenedURLController(ShortenedURLService shortenedURLService){
        this.shortenedURLService = shortenedURLService;
    }


    @GetMapping("/")
    public String main(){
        return "main";
    }

    @PostMapping ("/shortenedURL")
    public String createShortenedURL(String originalURL, Model model) {
        URLMapping urlMapping = new URLMapping();
        urlMapping.setOriginalURL(originalURL);
        model.addAttribute("shortenedURL", shortenedURLService.shorten(urlMapping));
        return "/shortenedURL";
    }

    @GetMapping("/{shortenedURL}")
    public String redirectShortenedURL(@PathVariable String shortenedURL){
        URLMapping originalURL = shortenedURLService.findOriginalURL(Long.valueOf(shortenedURL));
        return "redirect:" + originalURL.getOriginalURL();
    }

}
