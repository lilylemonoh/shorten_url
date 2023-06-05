package com.spring.shortenedURL.repository;

import com.spring.shortenedURL.entity.URLMapping;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MemoryShortenedURLRepositoryTest {

    @Autowired
    MemoryShortenedURLRepository repository;

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void saveTest(){
        //given
        URLMapping urlMapping = new URLMapping();
        urlMapping.setOriginalURL("https://www.naver.com/");
        //when
        repository.save(urlMapping);
        //then
        assertEquals(urlMapping.getOriginalURL(), "https://www.naver.com/");
    }

    @Test
    public void findByShortenedURLTest(){
        //given
        URLMapping urlMapping1 = new URLMapping();
        urlMapping1.setOriginalURL("https://www.naver.com/");
        repository.save(urlMapping1);

        URLMapping urlMapping2 = new URLMapping();
        urlMapping2.setOriginalURL("https://www.google.com/");
        repository.save(urlMapping2);
        //when
        URLMapping result = repository.findByShortenedURL(urlMapping2.getShortenedURL());
        //then
        assertEquals(result, urlMapping2);

    }

    @Test
    public void findByOriginalURLTest(){
        //given
        URLMapping urlMapping1 = new URLMapping();
        urlMapping1.setOriginalURL("https://www.naver.com/");
        repository.save(urlMapping1);

        URLMapping urlMapping2 = new URLMapping();
        urlMapping2.setOriginalURL("https://www.google.com/");
        repository.save(urlMapping2);
        //when
        URLMapping result = repository.findByOriginalURL("https://www.naver.com/");
        //then
        assertEquals(result, urlMapping1);

    }



}
