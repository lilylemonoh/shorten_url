package com.spring.shortenedURL.repository;

import com.spring.shortenedURL.entity.URLMapping;


public interface ShortenedURLRepository {

    URLMapping save(URLMapping urlMapping);

   URLMapping findByShortenedURL(Long shortenedURL);


   URLMapping findByOriginalURL(String originalURL);


}
