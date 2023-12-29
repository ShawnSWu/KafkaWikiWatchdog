package io.watchdog.wikichange.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnquireWikiChangeReq extends PaginationReq {
    private String keyword;
}
