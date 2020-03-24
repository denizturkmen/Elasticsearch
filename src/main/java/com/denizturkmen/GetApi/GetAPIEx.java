package com.denizturkmen.GetApi;

import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class GetAPIEx {
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

        //IndexRequestBuilder indexRequestBuilder = client.prepareIndex("elasticdb", "product","1");

        GetRequestBuilder getRequestBuilder = client.prepareGet("elasticdb", "product", "33");
        System.out.println(getRequestBuilder.get());
        System.out.println("-----------------------------");

        GetResponse response = client.prepareGet("elasticdb", "product", "33").get();
        Map<String,Object> source = response.getSource();

        if(source!=null) {
            String CategoryId = (String) source.get("CategoryId");
            String ProductName = (String) source.get("ProductName");
            String Discontinued = (String) source.get("Discontinued");
            String SupplierId = (String) source.get("SupplierId");
            String UnitPrice = (String) source.get("UnitPrice");
            String UnitOnOrder = (String) source.get("UnitOnOrder");
            String UnitInStock = (String) source.get("UnitInStock");
            String QuantityPerUnit = (String) source.get("QuantityPerUnit");

            System.out.println("CategoryID : " + CategoryId);
            System.out.println("ProductName : " + ProductName);
            System.out.println("Discontinued : " + Discontinued);
            System.out.println("SupplierId : " + SupplierId);
            System.out.println("UnitPrice : " + UnitPrice);
            System.out.println("UnitOnOrder : " + UnitOnOrder);
            System.out.println("UnitInStock : " + UnitInStock);
            System.out.println("QuantityPerUnit : " + QuantityPerUnit);
        }
        else{
            System.out.println("Kayitli "+response.getId()+" yok");
        }


    }
}
