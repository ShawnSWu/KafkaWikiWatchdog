package io.watchdog.wikichange.converter;

import com.google.gson.Gson;
import io.watchdog.wikichange.domain.WikiChangeDocument;
import io.watchdog.wikichange.pojo.vo.EnquiryWikiChangeRes;
import io.watchdog.wikichange.pojo.vo.WikiChangeRecord;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.search.SearchHit;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WikiChangeDocumentConverter {

    public EnquiryWikiChangeRes toEnquiryWikiChangeRes(SearchResponse searchResponse, long queryTime) {
        List<String> hitsString = Arrays.stream(searchResponse.getHits().getHits())
                .map(SearchHit::getSourceAsString)
                .collect(Collectors.toList());

        List<WikiChangeRecord> wikiChangeRecords = hitsString.stream()
                .map(j -> new Gson().fromJson(j, WikiChangeDocument.class))
                .map(wikiChangeDocument -> WikiChangeRecord.builder()
                        .title(wikiChangeDocument.getTitle())
                        .titleUrl(wikiChangeDocument.getTitleUrl())
                        .domainUrl(wikiChangeDocument.getServerUrl())
                        .domainName(wikiChangeDocument.getServerName())
                        .comment(wikiChangeDocument.getComment())
                        .build())
                .collect(Collectors.toList());

        long totalHits = searchResponse.getHits().getTotalHits().value;

        return EnquiryWikiChangeRes.builder()
                .records(wikiChangeRecords)
                .totalSize(totalHits)
                .queryTime(queryTime)
                .build();
    }
}
