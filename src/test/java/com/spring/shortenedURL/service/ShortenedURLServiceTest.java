package com.spring.shortenedURL.service;

import com.spring.shortenedURL.entity.URLMapping;
import com.spring.shortenedURL.repository.MemoryShortenedURLRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortenedURLServiceTest {

    @Autowired
    ShortenedURLService shortenedURLService;

    @Autowired
    MemoryShortenedURLRepository memoryShortenedURLRepository;

    @AfterEach
    public void afterEach(){
        memoryShortenedURLRepository.clearStore();
    }

    @Test
    void shortenTest() {
        //given
        URLMapping urlMapping = new URLMapping();
        urlMapping.setOriginalURL("https://www.naver.com/");
        //when
        Long shorten = shortenedURLService.shorten(urlMapping);
        //then
        URLMapping findOriginalUrl= shortenedURLService.findOriginalURL(shorten);
        assertEquals(urlMapping.getOriginalURL(),findOriginalUrl.getOriginalURL());
    }

    @Test
    void duplicateShortenTest(){ // 중복 주소 검증
        //given
        URLMapping urlMapping1 = new URLMapping();
        urlMapping1.setOriginalURL("https://www.naver.com/");

        URLMapping urlMapping2 = new URLMapping();
        urlMapping2.setOriginalURL("https://www.naver.com/");

        //when
        Long shorten1 = shortenedURLService.shorten(urlMapping1);
        Long shorten2 = shortenedURLService.shorten(urlMapping2);
        //then
        assertEquals(shorten1,shorten2);
    }


    @Test
    void findOriginalURLTest() {
        //given
        URLMapping urlMapping = new URLMapping();
        urlMapping.setOriginalURL("https://www.naver.com/");
        //when
        shortenedURLService.shorten(urlMapping);
        URLMapping result = shortenedURLService.findOriginalURL(urlMapping.getShortenedURL());
        //then
        assertEquals(result, urlMapping);
    }
}