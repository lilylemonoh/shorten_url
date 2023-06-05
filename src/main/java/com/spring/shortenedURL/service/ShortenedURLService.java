package com.spring.shortenedURL.service;

import com.spring.shortenedURL.entity.URLMapping;
import com.spring.shortenedURL.repository.MemoryShortenedURLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShortenedURLService {

    private final MemoryShortenedURLRepository memoryShortenedURLRepository;

    @Autowired
    ShortenedURLService(MemoryShortenedURLRepository memoryShortenedURLRepository){
        this.memoryShortenedURLRepository = memoryShortenedURLRepository;
    }

    public Long shorten(URLMapping urlMapping){
        // 중복 검증
        URLMapping result = memoryShortenedURLRepository.findByOriginalURL(urlMapping.getOriginalURL());
        if(result != null) {
            return result.getShortenedURL();
        } else {
            // 중복이 아니면 save
            memoryShortenedURLRepository.save(urlMapping);
            return urlMapping.getShortenedURL();
        }
    }


    public URLMapping findOriginalURL(Long shortenedURL){
        return memoryShortenedURLRepository.findByShortenedURL(shortenedURL);
    }

}
