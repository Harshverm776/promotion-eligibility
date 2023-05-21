package com.cog.news.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cog.news.domain.News;
import com.cog.news.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@PostMapping
	public ResponseEntity<?> produceNews(@RequestBody @Valid News news) {
		newsService.produceNews(news);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
