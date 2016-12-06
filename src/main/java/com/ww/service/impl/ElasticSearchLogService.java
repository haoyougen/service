package com.ww.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.ww.service.LogService;
import com.ww.vo.AccessLogVO;

@Service
public class ElasticSearchLogService implements LogService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchLogService.class);
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private Client esClient;

	@PostConstruct
	public void init() {
		if (!elasticsearchTemplate.indexExists("access_log_index")) {
			elasticsearchTemplate.createIndex("access_log_index");
		}
		elasticsearchTemplate.putMapping(AccessLogVO.class);
	}

	public boolean saveAccessLog(AccessLogVO logvo) {
		try {
			IndexQuery indexQuery = new IndexQueryBuilder().withObject(logvo).build();
			elasticsearchTemplate.index(indexQuery);
			return true;
		} catch (Exception e) {
			LOGGER.error("insert or update task info error.", e);
			return false;
		}
	}

	public List<AccessLogVO> query(String fieldName,String fieldValue) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryString(fieldValue).field(fieldName)).build();
		Page<AccessLogVO> sampleEntities = elasticsearchTemplate.queryForPage(searchQuery, AccessLogVO.class);
	}
}
