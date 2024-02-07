package com.chukurs;

import java.util.ArrayList;
import java.util.List;

public class ShoeWarehouse {
    private List<Order> orderList = new ArrayList<>();

    public synchronized void receiveOrder(Order order) {
        //called by "PRODUCER" thread to add a new "order" to ArrayList
        while (orderList.size() >= 5) {
            try {
                //wait for the other thread to consume the orders so there is room
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        orderList.add(order);
        notifyAll();
    }

    public synchronized Order fulfillOrder() {
        //called by "CONSUMER" to take the "order" from ArrayList
        while (orderList.isEmpty()) {
            try {
                //wait for some "order" to be placed in the ArrayList
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        notifyAll();
        //can use .removeFirst() as it returns the object and removes within the same method
        return orderList.removeFirst();
    }
}
