package com.nowui.cloud.elasticsearch;

import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author ZhongYongQiang
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.nowui.cloud"})
public class ElasticSearchConfig {

    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

//    @Bean
//    public Client client() throws UnknownHostException {
//        Settings settings = Settings.builder()
//                .put("cluster.name", "elasticsearch")
//                .build();
//        TransportClient client = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new
//                        InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//
//        return client;
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() {
//        try {
//            return new ElasticsearchTemplate(client());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

//    @Bean
//    public ElasticsearchTemplate elasticsearchTemplate(Client client) {
//        return new ElasticsearchTemplate(client);
//    }

}