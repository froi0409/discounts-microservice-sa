package com.froi.discounts.discount.infrastructure.outputadapters.restapi;

import com.froi.discounts.discount.infrastructure.outpuports.restapi.FindAvailableDiscountsByPaymentsOutputPort;

public class DiscountRestOutputAdapter implements FindAvailableDiscountsByPaymentsOutputPort {
    @Override
    public boolean findAvailableRoomDiscountByPayments(String userNit) {
        return false;
    }
}
