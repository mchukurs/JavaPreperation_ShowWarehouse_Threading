package com.chukurs;

public class Main {
    public static void main(String[] args) {
        System.out.println("App started!");
        //shared resource between threads
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();

        //"producer" will create "orders" and place in the ArrayList
        Thread producer1 = new Thread(new OrderProducer(shoeWarehouse));

        //each "consumer" will loop 5 times to get an "order" from ArrayList, if no "orders" then it wait()
        Thread consumer1 = new Thread(new OrderConsumer(shoeWarehouse));
        Thread consumer2 = new Thread(new OrderConsumer(shoeWarehouse));

        //start threads
        producer1.start();
        consumer1.start();
        consumer2.start();

        // join "threads" to print that work has finished (if not joined, then "main" thread prints message right away
        try {
            producer1.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Shoe task is completed!");
    }
}
