package com.example.jpastudy.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDERED("주문 완료"),
    PAYMENT_COMPLETED("결제 완료"),
    SHIPPED("배송 중"),
    COMPLETE("배송 완료"),
    CANCELED("취소");

    private final String description;
    private final String name;

    OrderStatus(String description) {
        this.name = this.name();
        this.description = description;
    }
}
