package com.denizturkmen.IndexApi;

import com.denizturkmen.Model.Product;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class IndexAPIEx {
    public static void main(String[] args) {

        //ElasticSearch Cluster belirliyoruz
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();

        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        Map<String, Object> json = new HashMap<String, Object>();
        json.put("ProductName", "Boston Crab Meat");
        json.put("SupplierId", "19");
        json.put("CategoryId", "8");
        json.put("QuantityPerUnit", "24 - 4 oz tins");
        json.put("UnitPrice", "18,0000");
        json.put("UnitInStock", "123");
        json.put("UnitOnOrder", "0");
        json.put("ReorderLevel", "30");
        json.put("Discontinued", "False");

        

        IndexResponse response = client.prepareIndex("elasticdb", "product", "33")
                .setSource(json)
                .get();

        String _index = response.getId();
        String _type = response.getType();
        Long _version = response.getVersion();
        RestStatus _statues = response.status();
        System.out.println(_index);
        System.out.println(_type);
        System.out.println(_version);
        System.out.println(_statues);


    }
}
