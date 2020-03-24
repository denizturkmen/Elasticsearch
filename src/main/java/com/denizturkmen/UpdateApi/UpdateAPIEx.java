package com.denizturkmen.UpdateApi;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class UpdateAPIEx {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();

        TransportClient client = null;

        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

/*
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("elasticdb");
        updateRequest.type("elasticdb");
        updateRequest.id("1");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("CategoryId","28")
                .endObject());
        UpdateResponse updateResponse = client.update(updateRequest).get();
        System.out.println(updateResponse.getId());
        System.out.println("----------------------------");

        UpdateRequest updateRequest1 = new UpdateRequest("elasticdb", "product", "9")
                .script(new Script("ctx._source.CategoryId = \"42\""));
        client.update(updateRequest1).get();
        System.out.println(updateRequest1);
        System.out.println("--------------------------");


        UpdateRequest updateRequest2 = new UpdateRequest("elasticdb", "product", "9")
                .doc(jsonBuilder()
                        .startObject()
                        .field("CategoryId", "44")
                        .endObject());
        client.update(updateRequest2).get();
        System.out.println(updateRequest2);


 */
        IndexRequest indexRequest = new IndexRequest("product", "product", "9")
                .source(jsonBuilder()
                        .startObject()
                        .field("Discontinued", "False")
                        .endObject());
        UpdateRequest updateRequest = new UpdateRequest("elasticdb", "product", "9")
                .doc(jsonBuilder()
                        .startObject()
                        .field("Discontinued", "True")
                        .endObject())
                .upsert(indexRequest);
        client.update(updateRequest).get();

    }
}
