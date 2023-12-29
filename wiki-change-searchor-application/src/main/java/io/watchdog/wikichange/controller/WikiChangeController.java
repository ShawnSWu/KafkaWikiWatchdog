package io.watchdog.wikichange.controller;

import io.watchdog.wikichange.pojo.vo.EnquiryWikiChangeRes;
import io.watchdog.wikichange.pojo.vo.PaginationReq;
import io.watchdog.wikichange.service.WikiChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WikiChangeController {

    private final WikiChangeService wikiChangeService;

    @PostMapping("/search")
    public List<EnquiryWikiChangeRes> search(@RequestParam("keywords") String keywords, @RequestBody PaginationReq paginationReq) {
        return wikiChangeService.search(keywords, paginationReq);
    }
}
