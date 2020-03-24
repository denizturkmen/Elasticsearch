package com.denizturkmen.DeleteApi;

import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DeleteAPIEx {
    public static void main(String[] args) {

        Settings settings = Settings.builder().
                put("cluster.name","elasticsearch").build();

        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete("elasticdb", "product", "33");
        System.out.println(deleteRequestBuilder.get());
        System.out.println("---------------------------------");

        BulkByScrollResponse bulkByScrollResponse = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("_id", "26"))
                .source("elasticdb")
                .get();

        BulkByScrollResponse bulkByScrollResponse1 = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("ProductName", "Boston"))
                .source("elasticdb")
                .get();


        long deleteed = bulkByScrollResponse1.getDeleted();
        System.out.println("Silinen sayi :" +deleteed);

    }
}
