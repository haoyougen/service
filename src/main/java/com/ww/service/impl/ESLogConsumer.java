package com.ww.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder.Field;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ww.service.LogConsumer;
import com.ww.utils.PropertyHelper;
import com.ww.vo.AccessLogVO;

@Service
public class ESLogConsumer implements LogConsumer {
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public void consume(AccessLogVO vo) {
		String json = JSON.toJSONString(vo);
		System.out.println("_________________consumer________________" + json);
	}

	public List<AccessLogVO> query(Map<String, Object> filedContentMap, final List<String> heightLightFields,
			String sortField, SortOrder order) {
		Field[] hfields = null;
		Page<AccessLogVO> page = null;
		if (heightLightFields != null && !heightLightFields.isEmpty()) {
			hfields = new Field[heightLightFields.size()];
			int mark = 0;
			for (String hf : heightLightFields) {
				// String o="{\"abc\" : \"[abc]\"}";
				hfields[mark] = new HighlightBuilder.Field(hf).preTags("<em>").postTags("</em>").fragmentSize(250);
				mark++;
			}
		}
		NativeSearchQueryBuilder nsb = new NativeSearchQueryBuilder().withHighlightFields(hfields);// 高亮字段
		// 排序
		if (sortField != null && order != null) {
			nsb.withSort(new FieldSortBuilder(sortField).ignoreUnmapped(true).order(order));
		}
		// 字段查询
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		for (String key : filedContentMap.keySet()) {
			qb.must(QueryBuilders.matchQuery(key, filedContentMap.get(key)));
		}
		nsb.withQuery(qb);

		// 查询建立
		SearchQuery searchQuery = nsb.build();
		// 如果设置高亮
		if (heightLightFields != null && heightLightFields.size() > 0) {
			// 分页查询
			elasticsearchTemplate.queryForPage(searchQuery, AccessLogVO.class, new SearchResultMapper() {

				@SuppressWarnings("unchecked")
				@Override
				public <T> Page<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
					List<T> chunk = new ArrayList<T>();
					SearchHits hits = response.getHits();
					SearchHit[] hitss = hits.hits();
					for (SearchHit hit : hitss) {
						Map<String, Object> entityMap = hit.getSource();
						for (String highName : heightLightFields) {
							String highValue = hit.getHighlightFields().get(highName).fragments()[0].toString();
							entityMap.put(highName, highValue);
						}

						chunk.add((T) PropertyHelper.mapToBean(entityMap, AccessLogVO.class));
					}
					if (chunk.size() > 0) {
						return new PageImpl<T>((List<T>) chunk);
					}

					return null;
				}

			});

		} else {// 如果不设置高亮
			page = elasticsearchTemplate.queryForPage(searchQuery, AccessLogVO.class);
		}
		return page.getContent();
	}

	public static void main(String[] args) {
		boolean ss = ((Integer) 129 == (Integer) (129));
		System.out.println(ss);
	}
}
