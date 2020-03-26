package com.denizturkmen.BulkApi;
import static org.elasticsearch.common.xcontent.XContentFactory.*;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BulkAPIEx {
    public static void main(String[] args) throws IOException {

        Settings settings = Settings.builder().
                put("cluster.name","elasticsearch").build();

        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        BulkRequestBuilder bulkRequest = client.prepareBulk();


            bulkRequest.add(client.prepareIndex("elasticdb", "product", "38")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("CategoryId", "36")
                            .field("Discontinued", "False")
                            .field("SupplierId", "2")
                            .field("UnitPrice", "10")
                            .field("ProductName", "Deniz türkmen")
                            .field("UnitInStock", "51")
                            .field("QuantityPerUnit", "31")
                            .field("UnitOnOrder", "45")
                            .field("ReorderLevel", "25")
                            .endObject()
                    )
            );

        try {
            bulkRequest.add(client.prepareIndex("elasticdb", "product", "37")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("CategoryId", "37")
                            .field("Discontinued", "False")
                            .field("SupplierId", "2")
                            .field("UnitPrice", "10")
                            .field("ProductName", "Murat türkmen")
                            .field("UnitInStock", "51")
                            .field("QuantityPerUnit", "31")
                            .field("UnitOnOrder", "45")
                            .field("ReorderLevel", "25")
                            .endObject()
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }
        System.out.println(bulkResponse.status().getStatus());
        System.out.println("---------------");
        System.out.println(bulkResponse.getTook());
        System.out.println(bulkResponse.getIngestTookInMillis());


    }
}
