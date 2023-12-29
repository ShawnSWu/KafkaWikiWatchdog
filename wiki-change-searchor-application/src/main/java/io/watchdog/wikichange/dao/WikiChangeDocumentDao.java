package io.watchdog.wikichange.dao;

import io.watchdog.infra.tool.OpenSearchTool;
import io.watchdog.wikichange.converter.WikiChangeDocumentConverter;
import io.watchdog.wikichange.domain.WikiChangeDocument;
import io.watchdog.wikichange.pojo.vo.PaginationReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WikiChangeDocumentDao {

    private final OpenSearchTool openSearchTool;
    private final WikiChangeDocumentConverter wikiChangeConverter;

    public List<WikiChangeDocument> search(String keywords, PaginationReq paginationReq) {
        String indexName = "wikimedia_change";

        List<String> fields = Arrays.asList("title", "comment");

        List<String> results = openSearchTool.multiMatchQuery(keywords, indexName, fields, paginationReq);

        return results.stream().map(wikiChangeConverter::toWikiChangeDocument).collect(Collectors.toList());
    }
}
