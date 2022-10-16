package com.example.demo;

import java.util.Map;

public class CreateOrderDto {
   private Map<String,Integer>items;

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public Map<String, Integer>getItems(){
       return items;
   }

}
