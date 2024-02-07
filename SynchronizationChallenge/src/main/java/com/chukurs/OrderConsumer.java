package com.chukurs;

import java.util.Random;

public class OrderConsumer implements Runnable {
    private ShoeWarehouse shoeWarehouse;

    public OrderConsumer(ShoeWarehouse shoeWarehouse) {
        this.shoeWarehouse = shoeWarehouse;
    }

    @Override
    public void run() {
        Random random = new Random();
        Order order;
        //consuming 5 "orders" as per instructions
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(random.nextInt(500, 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            order = shoeWarehouse.fulfillOrder();
            //printing out is considered as fulfilled order
            System.out.println(order.toString());
        }
    }
}
