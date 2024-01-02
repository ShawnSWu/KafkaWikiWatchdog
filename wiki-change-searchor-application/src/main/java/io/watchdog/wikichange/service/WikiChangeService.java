package io.watchdog.wikichange.service;

import io.watchdog.wikichange.dao.WikiChangeDocumentDao;
import io.watchdog.wikichange.pojo.vo.EnquireWikiChangeReq;
import io.watchdog.wikichange.pojo.vo.EnquiryWikiChangeRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WikiChangeService {

    private final WikiChangeDocumentDao wikiChangeDocumentDao;

    public EnquiryWikiChangeRes search(EnquireWikiChangeReq req) {
        return wikiChangeDocumentDao.search(req);
    }
}
