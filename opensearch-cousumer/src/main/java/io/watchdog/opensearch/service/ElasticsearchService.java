package io.watchdog.opensearch.service;

import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.opensearch.action.bulk.BulkRequest;
import org.opensearch.action.bulk.BulkResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElasticsearchService {

    private final RestHighLevelClient openSearchClient;

    public int save(ConsumerRecords<String, String> records) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();

        for (ConsumerRecord<String, String> record : records) {
            // send the record into OpenSearch
            String id = extractId(record.value());
            IndexRequest indexRequest = new IndexRequest("wikimedia")
                    .source(record.value(), XContentType.JSON)
                    .id(id);
            bulkRequest.add(indexRequest);
        }


        if (bulkRequest.numberOfActions() > 0) {
            BulkResponse bulkResponse = openSearchClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            log.info("Inserted " + bulkResponse.getItems().length + " record(s).");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("Offsets have been committed!");
        }

        return bulkRequest.numberOfActions();
    }

    private String extractId(String json) {
        return JsonParser.parseString(json)
                .getAsJsonObject()
                .get("meta")
                .getAsJsonObject()
                .get("id")
                .getAsString();
    }

}