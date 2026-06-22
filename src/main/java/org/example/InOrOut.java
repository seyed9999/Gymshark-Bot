package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class InOrOut {
    private final String[] GymsharkUrls =
            {"https://de.gymshark.com/products/gymshark-shadow-seamless-t-shirt-black-aw24",
                    "https://de.gymshark.com/products/gymshark-geo-seamless-t-shirt-black-charcoal-grey-ss23",
                    "https://de.gymshark.com/products/gymshark-element-baselayer-t-shirt-black-ss25"};
    private String[] sizes = {"s", "m"};

    public String[] checkClothes(String size) {
        for (int i = 0; i < GymsharkUrls.length; i++) {

            Document doc = null;
            try {
                doc = Jsoup.connect(GymsharkUrls[i]).get();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (doc == null) {
                return null;
            }
            String data = doc.select("script#__NEXT_DATA__").first().data();
            if (data.contains("\"size\":\"" + size + "\"")
                    && data.contains("\"inStock\":true")) {
                String name = doc.title();
                String priceText = doc.select(".product-information_price__LJWYG div").first().text();
                String priceCurrent = priceText.replace("€", "").trim();
                String priceTextO = doc.select(".product-information_compare-at-price__gN_Bb").first().text();
                String priceOriginal = priceText.replace("€", "").trim();
                int result = Integer.parseInt(priceCurrent) - Integer.parseInt(priceOriginal);

                String[] info = {size, name, priceCurrent,result + ""};
                return info;
            }
        }
        return null;
    }

}