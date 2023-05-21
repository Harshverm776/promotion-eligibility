package com.cog.news.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cog.news.domain.ForceTermination;
import com.cog.news.domain.News;
import com.cog.news.processor.EmployeeDataProcessor;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NewsConsumer {

	@Autowired
	private EmployeeDataProcessor employeeDataProcessor;

	@KafkaListener(topics = "news", groupId = "group_id", containerFactory = "newsListener")
	public void consume(News news) {
		log.info(news.toString());
		if (news.getForceTermination().equals(ForceTermination.Y))
			employeeDataProcessor.processIsActive();
	}
}
