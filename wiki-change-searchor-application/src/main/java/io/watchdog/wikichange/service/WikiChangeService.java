package io.watchdog.wikichange.service;

import io.watchdog.wikichange.dao.WikiChangeDocumentDao;
import io.watchdog.wikichange.domain.WikiChangeDocument;
import io.watchdog.wikichange.pojo.vo.PaginationReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WikiChangeService {

    private final WikiChangeDocumentDao wikiChangeDocumentDao;

    public List<WikiChangeDocument> search(String keywords, PaginationReq paginationReq) {
        return wikiChangeDocumentDao.search(keywords, paginationReq);
    }
}
