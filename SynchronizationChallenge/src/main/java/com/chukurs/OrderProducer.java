package com.chukurs;

import java.util.Random;

public class OrderProducer implements Runnable {
    private ShoeWarehouse shoeWarehouse;

    public OrderProducer(ShoeWarehouse shoeWarehouse) {
        this.shoeWarehouse = shoeWarehouse;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            shoeWarehouse.receiveOrder(new Order(
                    random.nextInt(1000, 50_000),
                    ShoeType.values()[random.nextInt(1, 5)],
                    random.nextInt(99, 545)
            ));
            try {
                Thread.sleep(random.nextInt(500, 1000));
                //give time to "consumer" to process the "order"
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
