package com.cog.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cog.news.domain.News;
import com.cog.news.kafka.producer.NewsProducer;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsProducer newsProducer;

	@Override
	public void produceNews(News news) {
		newsProducer.produceNews(news);
	}
	
}
