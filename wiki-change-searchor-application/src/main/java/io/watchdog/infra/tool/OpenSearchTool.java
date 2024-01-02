package io.watchdog.infra.tool;

import io.watchdog.infra.config.exception.vo.Error500;
import io.watchdog.wikichange.pojo.vo.EnquireWikiChangeReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.index.query.MultiMatchQueryBuilder;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenSearchTool {

    private final RestHighLevelClient restHighLevelClient;

    public SearchResponse multiMatchQuery(EnquireWikiChangeReq req, String indexName, List<String> fields) {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQuery = new MultiMatchQueryBuilder(req.getKeyword());
        fields.forEach(multiMatchQuery::field);

        searchSourceBuilder
                .query(multiMatchQuery)
                .from((req.getPage() - 1) * req.getSize())
                .size(req.getSize());

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            String errorMsg = "OpenSearch query error, Message:" + e.getMessage();
            log.error(errorMsg);
            throw new Error500(errorMsg);
        }
        return searchResponse;
    }
}
