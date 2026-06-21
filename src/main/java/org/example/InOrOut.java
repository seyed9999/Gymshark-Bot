package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class InOrOut {
    private final String GymsharkUrl = "https://de.gymshark.com/products/gymshark-shadow-seamless-t-shirt-black-aw24";

    public boolean checkClothes() {
        Document doc = null;
        try {
            doc = Jsoup.connect(GymsharkUrl).get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (doc == null) {
            return false;
        }
        String data = doc.select("script#__NEXT_DATA__").first().data();
        if (data.contains("\"inStock\":true,\"inventoryQuantity\":2,\"price\":32,\"size\":\"s\"")) {
            return true;
        } else {
            return false;
        }

    }
}
