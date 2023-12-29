package io.watchdog.wikichange.dao;

import io.watchdog.infra.tool.OpenSearchTool;
import io.watchdog.wikichange.converter.WikiChangeDocumentConverter;
import io.watchdog.wikichange.pojo.vo.EnquireWikiChangeReq;
import io.watchdog.wikichange.pojo.vo.EnquiryWikiChangeRes;
import lombok.RequiredArgsConstructor;
import org.opensearch.action.search.SearchResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WikiChangeDocumentDao {

    private final OpenSearchTool openSearchTool;
    private final WikiChangeDocumentConverter wikiChangeConverter;

    public EnquiryWikiChangeRes search(EnquireWikiChangeReq req) {
        String indexName = "wikimedia_change";

        List<String> fields = Arrays.asList("title", "comment");

        long startTime = System.currentTimeMillis();

        SearchResponse searchResponse = openSearchTool.multiMatchQuery(req, indexName, fields);

        long queryTime = System.currentTimeMillis() - startTime;

        return wikiChangeConverter.toEnquiryWikiChangeRes(searchResponse, queryTime);
    }
}
