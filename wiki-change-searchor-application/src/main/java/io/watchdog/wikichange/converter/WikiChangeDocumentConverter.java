package io.watchdog.wikichange.converter;

import com.google.gson.Gson;
import io.watchdog.wikichange.domain.WikiChangeDocument;
import org.springframework.stereotype.Component;

@Component
public class WikiChangeDocumentConverter {

    public WikiChangeDocument toWikiChangeDocument(String jsonString) {
        return new Gson().fromJson(jsonString, WikiChangeDocument.class);
    }
}
