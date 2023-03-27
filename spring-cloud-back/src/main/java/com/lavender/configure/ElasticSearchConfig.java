package com.lavender.configure;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    // 1 找对象
    // 2 放到spring中

    @Bean
    public RestHighLevelClient restHighLevelClient(){

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient (
                // 连接集群 一个就只写一个
                RestClient.builder (
                        new HttpHost ("127.0.0.1",9200,"http")
                )
        );

        return restHighLevelClient;
    }

}
