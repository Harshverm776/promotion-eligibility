package com.cog.news.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.cog.news.domain.News;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NewsProducer {

	private static final String TOPIC = "news";
	
	@Autowired
	private KafkaTemplate<String, News> kafkaTemplate;
	
	public void produceNews(News news) {
		log.info("News Producer call with data-"+news.toString());
		this.kafkaTemplate.send(TOPIC, news);
	}
	
}
