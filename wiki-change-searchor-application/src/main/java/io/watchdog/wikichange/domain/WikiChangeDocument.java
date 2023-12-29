package io.watchdog.wikichange.domain;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class WikiChangeDocument {

    @SerializedName("$schema")
    private String schema;

    private Meta meta;

    private Long id;

    private String type;

    private Long namespace;

    private String title;

    @SerializedName("title_url")
    private String titleUrl;

    private String comment;

    private long timestamp;

    private String user;

    private boolean bot;

    @SerializedName("notify_url")
    private String notifyUrl;

    @SerializedName("server_url")
    private String serverUrl;

    @SerializedName("server_name")
    private String serverName;

    @SerializedName("server_script_path")
    private String serverScriptPath;

    private String wiki;

    @SerializedName("parsedcomment")
    private String parsedComment;


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class Meta {
        private String uri;

        @SerializedName("request_id")
        private String requestId;

        private String id;

        private String dt;

        private String domain;

        private String stream;

        private String topic;

        private Long partition;

        private long offset;
    }
}


