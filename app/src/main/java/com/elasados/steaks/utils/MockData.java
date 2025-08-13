package com.elasados.steaks.utils;

import java.util.ArrayList;
import java.util.List;
import com.elasados.steaks.models.Product;

public class MockData {
    public static final boolean MOCK_MODE = true;

    public static List<Product> getProducts() {
        List<Product> list = new ArrayList<>();
        list.add(make("1","Carne Asada","https://images.unsplash.com/photo-1604908177522-7a4a5a5d4d3b?auto=format&fit=crop&w=800&q=60",120.0));
        list.add(make("2","Parrillada Mixta","https://images.unsplash.com/photo-1604908177228-3b2e3c6d4a3f?auto=format&fit=crop&w=800&q=60",220.0));
        list.add(make("3","Hamburguesa Especial","https://images.unsplash.com/photo-1606756793189-1f6f7b8c1a2b?auto=format&fit=crop&w=800&q=60",140.0));
        list.add(make("4","Papas Fritas","https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=800&q=60",50.0));
        list.add(make("5","Ensalada Cesar","https://images.unsplash.com/photo-1568605117036-5fe5e7bab0b7?auto=format&fit=crop&w=800&q=60",80.0));
        return list;
    }

    private static Product make(String id, String name, String url, double price) {
        Product p = new Product();
        p.id = id;
        p.name = name;
        p.imageUrl = url;
        p.price = price;
        return p;
    }
}
