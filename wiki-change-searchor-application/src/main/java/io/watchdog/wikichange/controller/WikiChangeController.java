package io.watchdog.wikichange.controller;

import io.watchdog.wikichange.pojo.vo.EnquireWikiChangeReq;
import io.watchdog.wikichange.pojo.vo.EnquiryWikiChangeRes;
import io.watchdog.wikichange.service.WikiChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WikiChangeController {

    private final WikiChangeService wikiChangeService;

    //only for dev
    @CrossOrigin(origins = "*")
    @PostMapping("/search")
    public EnquiryWikiChangeRes search(@RequestBody EnquireWikiChangeReq req) {
        return wikiChangeService.search(req);
    }
}
