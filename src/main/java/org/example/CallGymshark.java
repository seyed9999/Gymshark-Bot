package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CallGymshark {
    private final int time = 2;
    private String[] sizes = {"s", "m"};
    private final String[] GymsharkUrls =
            {"https://de.gymshark.com/products/gymshark-shadow-seamless-t-shirt-black-aw24",
                    "https://de.gymshark.com/products/gymshark-geo-seamless-t-shirt-black-charcoal-grey-ss23",
                    "https://de.gymshark.com/products/gymshark-element-baselayer-t-shirt-black-ss25"};
    private String[][][] inStocks = new String[GymsharkUrls.length][sizes.length][];
    private boolean[][] once = new boolean[GymsharkUrls.length][sizes.length];
    private String[] prices = new String[sizes.length];


    public void startBot() {
        InOrOut inOrOut = new InOrOut();
        Notifier notifier = new Notifier();
        ScheduledExecutorService worker = Executors.newScheduledThreadPool(1);
        worker.scheduleAtFixedRate(() -> {
            for (int i = 0; i < GymsharkUrls.length; i++) {
                for (int j = 0; j < sizes.length; j++) {
                    inStocks[i][j] = inOrOut.checkClothes(sizes[j], GymsharkUrls[i]);
                    if (inStocks[i][j] != null && !once[i][j]) {
                        if (Double.parseDouble(inStocks[i][j][3]) < 0) {
                            notifier.sendMsg("The product: " + inStocks[i][j][1] + "is back in stock in the size of " + inStocks[i][j][0] +
                                    " and is also in sale in the price of " + inStocks[i][j][2]);
                        } else {
                            notifier.sendMsg("The product: " + inStocks[i][j][1] + "is back in stock in the size of " + inStocks[i][j][0] + "in the price of "
                                    + inStocks[i][j][2]);


                        }
                        once[i][j] = true;
                    }


                    if (inStocks[i][j] == null) {
                        once[i][j] = false;
                    }
                }
            }
        }, 0, time, TimeUnit.MINUTES);
    }
}


