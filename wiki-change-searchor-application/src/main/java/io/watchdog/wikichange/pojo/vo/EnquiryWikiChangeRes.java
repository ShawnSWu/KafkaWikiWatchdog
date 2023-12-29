package io.watchdog.wikichange.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryWikiChangeRes {

    private String title;

    private String titleUrl;

    private String comment;

    private String domainName;

    private String domainUrl;

}
