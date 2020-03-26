package com.denizturkmen.MultiGetApi;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MuitiGetAPIEx {
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

        //Farklı indedxlerde arama yapabilirsin
        MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
                .add("elasticdb", "product", "2")
                .add("elasticdb", "product", "9")
                .get();


        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
            }
            System.out.println(response.getSourceAsString());
        }

    }
}

