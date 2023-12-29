package io.watchdog.infra.tool;

import io.watchdog.infra.config.exception.vo.Error500;
import io.watchdog.wikichange.pojo.vo.PaginationReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.index.query.MultiMatchQueryBuilder;
import org.opensearch.search.SearchHit;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenSearchTool {

    private final RestHighLevelClient restHighLevelClient;

    public List<String> multiMatchQuery(String queryText, String indexName, List<String> fields, PaginationReq paginationReq) {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQuery = new MultiMatchQueryBuilder(queryText);
        fields.forEach(multiMatchQuery::field);

        searchSourceBuilder
                .query(multiMatchQuery)
                .from((paginationReq.getPage() - 1) * paginationReq.getSize())
                .size(paginationReq.getSize());

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

        return Arrays.stream(searchResponse.getHits().getHits()).map(SearchHit::getSourceAsString).collect(Collectors.toList());
    }
}
