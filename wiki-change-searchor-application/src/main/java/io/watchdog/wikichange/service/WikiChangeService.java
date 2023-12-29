package io.watchdog.wikichange.service;

import io.watchdog.wikichange.converter.WikiChangeDocumentConverter;
import io.watchdog.wikichange.dao.WikiChangeDocumentDao;
import io.watchdog.wikichange.domain.WikiChangeDocument;
import io.watchdog.wikichange.pojo.vo.EnquiryWikiChangeRes;
import io.watchdog.wikichange.pojo.vo.PaginationReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WikiChangeService {

    private final WikiChangeDocumentDao wikiChangeDocumentDao;

    private final WikiChangeDocumentConverter wikiChangeDocumentConverter;

    public List<EnquiryWikiChangeRes> search(String keywords, PaginationReq paginationReq) {
        List<WikiChangeDocument> results = wikiChangeDocumentDao.search(keywords, paginationReq);
        return results.stream()
                .map(wikiChangeDocumentConverter::toEnquiryWikiChangeRes).
                collect(Collectors.toList());
    }
}
