package io.watchdog.wikichange.converter;

import com.google.gson.Gson;
import io.watchdog.wikichange.domain.WikiChangeDocument;
import io.watchdog.wikichange.pojo.vo.EnquiryWikiChangeRes;
import org.springframework.stereotype.Component;

@Component
public class WikiChangeDocumentConverter {

    public WikiChangeDocument toWikiChangeDocument(String jsonString) {
        return new Gson().fromJson(jsonString, WikiChangeDocument.class);
    }

    public EnquiryWikiChangeRes toEnquiryWikiChangeRes(WikiChangeDocument result) {
        return EnquiryWikiChangeRes.builder()
                .title(result.getTitle())
                .titleUrl(result.getTitleUrl())
                .domainUrl(result.getServerUrl())
                .domainName(result.getServerName())
                .comment(result.getComment())
                .build();
    }
}
