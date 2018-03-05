package com.company;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


public class Store {

    private Map<String, Integer> productAvail;

    public Store() {
        productAvail = new HashMap<String, Integer>();
    }

    public Map<String, Integer> getProductAvail() {
        return productAvail;
    }

    public void setProductAvail(String product, Integer num) {
        this.productAvail.put(product, num);
    }

    public int getItemInventory(String key) {
        return this.productAvail.get(key);
    }

    public void decreaseInventory(Product product) {
        int prevItemCount = productAvail.get(product.getName());
        this.setProductAvail(product.getName(), prevItemCount - 1);
    }

    public void increaseInventory(Product product) {
        int prevItemCount = productAvail.get(product.getName());
        this.setProductAvail(product.getName(), prevItemCount + 1);
    }



}
