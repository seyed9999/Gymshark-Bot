package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CallGymshark {
    private final int time = 2;
    private String[] sizes = {"s", "m"};
    private String[][] inStocks = new String[sizes.length][];
    private boolean[] once = new boolean[sizes.length];
    private String[] prices = new String[sizes.length];

    public void startBot() {
        InOrOut inOrOut = new InOrOut();
        Notifier notifier = new Notifier();
        ScheduledExecutorService worker = Executors.newScheduledThreadPool(1);
        worker.scheduleAtFixedRate(() -> {
            for (int i = 0; i < sizes.length; i++) {
                inStocks[i] = inOrOut.checkClothes(sizes[i]);
                if (inStocks[i] != null && !once[i]) {
                    if (Integer.parseInt(inStocks[i][3]) < 0) {
                        notifier.sendMsg("The product: " + inStocks[i][1] + "is back in stock in the size of " + inStocks[i][0] +
                                " and is also in sale in the price of " + inStocks[i][2]);
                    } else {
                        notifier.sendMsg("The product: " + inStocks[i][1] + "is back in stock in the size of " + inStocks[i][0]);

                    }
                    once[i] = true;
                }


                if (inStocks[i] == null) {
                    once[i] = false;
                }
            }
        }, 0, time, TimeUnit.MINUTES);
    }
}


