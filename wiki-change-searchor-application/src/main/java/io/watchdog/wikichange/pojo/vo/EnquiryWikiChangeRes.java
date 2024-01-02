package io.watchdog.wikichange.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryWikiChangeRes {

    private long totalSize;

    private List<WikiChangeRecord> records;

    private long queryTime;
}
