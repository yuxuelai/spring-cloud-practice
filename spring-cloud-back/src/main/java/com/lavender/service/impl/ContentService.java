package com.lavender.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.lavender.pojo.Content;
import com.lavender.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// 业务编写
@Service
public class ContentService {

    @Autowired //需要spring容器 所以报错nullpointer
    private RestHighLevelClient restHighLevelClient;


    // 1 解析数据 放入到es库中
    public Boolean parseContent(String keyword) throws IOException {

        List<Content> contents = new HtmlParseUtil ().getJingDongInfo (keyword);

        // 2 批量插入 es中
        BulkRequest bulkRequest = new BulkRequest ();

        bulkRequest.timeout ("2m");
        for (int i = 0; i < contents.size (); i++) {


            bulkRequest.add (
                    new IndexRequest ("jd_content")
                            .source (JSONObject.toJSONString (contents.get (i)), XContentType.JSON));

        }
        // 执行批量操作
        BulkResponse bulk = restHighLevelClient.bulk (bulkRequest, RequestOptions.DEFAULT);
        return bulk.hasFailures ();

    }

    // 3 获取这些数据 实现搜索功能
    public List<Map<String,Object>> searchPage(String keyword,int pageNo,int pageSize) throws IOException {

        if(pageNo<=1){

            pageNo=1;

        }

        // 条件搜索
        SearchRequest searchRequest = new SearchRequest ("jd_content");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder ();

        // 分页
        searchSourceBuilder.from (pageNo);
        searchSourceBuilder.size (pageSize);

        // 精准匹配
        TermQueryBuilder title = QueryBuilders.termQuery ("title", keyword);
        searchSourceBuilder.query (title);
        searchSourceBuilder.timeout (new TimeValue (60, TimeUnit.SECONDS));

        // 执行搜索
        searchRequest.source (searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search (searchRequest, RequestOptions.DEFAULT);


        ArrayList<Map<String,Object>> list = new ArrayList<> ();
        // 解析结果
        for (SearchHit document : search.getHits ().getHits ()) {
            list.add (document.getSourceAsMap ());
        }

        return list;


    }

    // 实现搜索高亮显示
    public List<Map<String,Object>> searchContentHighLighter(String keyword
            ,int pageNo,int pageSize) throws IOException {
        // 分页判断
        if(pageNo<=1){

            pageNo=1;
        }

        // 基本的条件搜索
        SearchRequest searchRequest= new SearchRequest ("jd_content");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder ();
        // 分页
        sourceBuilder.from (pageNo);
        sourceBuilder.size (pageSize);

        // 精准匹配 QueryBuilders 根据自己要求配置查询条件即可

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery ("title", keyword);
        sourceBuilder.query (termQueryBuilder);

        // 高亮构建 1 生成高亮查询
        HighlightBuilder highlightBuilder = new HighlightBuilder ();
        highlightBuilder.field ("title"); // 高亮查询字段
        highlightBuilder.requireFieldMatch (false);// 设置多个字段高亮显示
        // 高亮设置
        highlightBuilder.preTags ("<span style=\"color:red\">");
        highlightBuilder.postTags ("</span>");

        sourceBuilder.highlighter (highlightBuilder);
        sourceBuilder.timeout (new TimeValue (60, TimeUnit.SECONDS));


        // 搜索
        searchRequest.source (sourceBuilder);
        SearchResponse response = restHighLevelClient.search (searchRequest, RequestOptions.DEFAULT);

        //解析结果
        List<Map<String,Object>> list = new ArrayList<> ();
        for (SearchHit hit : response.getHits ()) {
            Map<String, HighlightField>  highlightFields = hit.getHighlightFields();
            HighlightField titleField = highlightFields.get ("title");

            Map<String,Object> source = hit.getSourceAsMap ();

            // 这里判断为空
            if(titleField!=null){
                Text[] fragments = titleField.fragments ();
                String name="";

                for (Text text : fragments) {
                    name+=text;

                }
                source.put ("title",name); // 高亮字段替换掉原本的内容

            }
            list.add (source);

        }
        return list;

    }



}
