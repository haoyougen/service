package com.ww.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Configuration
@PropertySource(value = "file:${APP_HOME}/conf/${env}/elasticsearch.properties")
public class ElasticSearchConfig {
	@Value("${elasticsearch.host}")
	private String host;
	@Value("${elasticsearch.cluster.name}")
	private String clusterName;

	@Bean
	public Client client() {
		TransportClient client = null;
		final Settings settings = Settings.settingsBuilder().put("cluster.name", this.clusterName).build();

		final String[] a = this.host.split("\\,", -1);
		if (a == null || a.length == 0) {
			throw new RuntimeException("invliad hosts");
		}
		try {

			final TransportAddress[] tas = new TransportAddress[a.length];
			for (int i = 0; i < a.length; i++) {
				final String host = a[i];
				final String[] b = host.split("\\:", 2);
				if (b == null || b.length != 2) {
					throw new RuntimeException("invliad host: " + host);
				}
				tas[i] = new InetSocketTransportAddress(InetAddress.getByName(b[0]), Integer.parseInt(b[1]));
			}
			client = TransportClient.builder().settings(settings).build().addTransportAddresses(tas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		ElasticsearchTemplate elasticsearchTemplate = new ElasticsearchTemplate(client());

		if (!elasticsearchTemplate.indexExists("access_log_index")) {
			elasticsearchTemplate.createIndex("access_log_index");
		}
		elasticsearchTemplate.createIndex("access_log_index");
		return elasticsearchTemplate;
	}
}
