package com.spring.shortenedURL.repository;

import com.spring.shortenedURL.entity.URLMapping;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryShortenedURLRepository implements ShortenedURLRepository {

    private static Map<Long, URLMapping> store = new HashMap<>();

    private static long sequence = 0L; //key 값 생성

    @Override
    public URLMapping save(URLMapping urlMapping) {
        urlMapping.setShortenedURL(++sequence);
        store.put(urlMapping.getShortenedURL(), urlMapping);
        return urlMapping;
    }

    @Override
    public URLMapping findByShortenedURL(Long shortenedURL) {
        return store.get(shortenedURL);
    }

    @Override
    public URLMapping findByOriginalURL(String originalURL) {
        for(URLMapping urlMapping : store.values()) {
            if(urlMapping.getOriginalURL().equals(originalURL)) {
                return urlMapping;
            }
        }
        return null;
    }

    public void clearStore(){
        store.clear();
    }


}


