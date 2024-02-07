package com.chukurs;

public record Order(int orderId, ShoeType type, int qty) {
    //this can be placed inside other class too
}
