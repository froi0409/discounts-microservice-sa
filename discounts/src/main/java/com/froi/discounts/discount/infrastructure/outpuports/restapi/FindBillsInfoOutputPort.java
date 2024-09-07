package com.froi.discounts.discount.infrastructure.outpuports.restapi;

public interface FindBillsInfoOutputPort {
    boolean findCustomerDiscountAvailability(String customerId);
}
