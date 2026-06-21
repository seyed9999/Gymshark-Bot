package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CallGymshark {
    private final int time = 2;
    public void startBot() {
        InOrOut inOrOut = new InOrOut();
        Notifier notifier = new Notifier();
        ScheduledExecutorService worker = Executors.newScheduledThreadPool(1);
        worker.scheduleAtFixedRate(() -> {
            if(inOrOut.checkClothes()) {
                notifier.sendMsg("Product is back in stock");
            }
        },0,time, TimeUnit.MINUTES);
    }
}
