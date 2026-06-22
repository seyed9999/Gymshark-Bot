package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class InOrOut {

    public String[] checkClothes(String size, String url) {

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (doc == null) {
            return null;
        }
        String data = doc.select("script#__NEXT_DATA__").first().data();
        int sizeIndex = data.indexOf("\"size\":\"" + size + "\"");
        if (sizeIndex != -1) {
            String before = data.substring(sizeIndex - 150, sizeIndex);
            if (before.lastIndexOf("\"inStock\":true") > before.lastIndexOf("\"inStock\":false")) {
                String name = doc.title();
                Element priceText = doc.select(".product-information_price__LJWYG div").first();
                if (priceText == null) {
                    throw new NullPointerException("price text is null");
                }
                String priceCurrent = priceText.text().replace("€", "").replace(",", ".").trim();
                Element priceTextOr = doc.select(".product-information_compare-at-price__gN_Bb").first();
                String priceOriginal;
                if (priceTextOr != null) {
                    priceOriginal = priceTextOr.text().replace("€", "").replace(",", ".").trim();
                } else {
                    priceOriginal = priceCurrent;
                }

                double result = Double.parseDouble(priceCurrent) - Double.parseDouble(priceOriginal);

                String[] info = {size, name, priceCurrent, result + ""};
                return info;
            }
        }
        return null;
    }
}
