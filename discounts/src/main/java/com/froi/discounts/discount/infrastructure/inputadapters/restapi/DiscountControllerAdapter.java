package com.froi.discounts.discount.infrastructure.inputadapters.restapi;

import com.froi.discounts.common.WebAdapter;
import com.froi.discounts.discount.application.makediscountusecase.MakeDishDiscountRequest;
import com.froi.discounts.discount.application.makediscountusecase.MakeRoomDiscountRequest;
import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.domain.exceptions.DiscountException;
import com.froi.discounts.discount.infrastructure.inputports.restapi.MakeDiscountsInputPort;
import org.hibernate.annotations.CollectionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("discounts/v1/discounts")
@WebAdapter
public class DiscountControllerAdapter {

    private MakeDiscountsInputPort makeDiscountsInputPort;

    @Autowired
    public DiscountControllerAdapter(MakeDiscountsInputPort makeDiscountsInputPort) {
        this.makeDiscountsInputPort = makeDiscountsInputPort;
    }

    @PostMapping("/dish")
    public ResponseEntity<Void> makeDishDiscount(@RequestBody MakeDishDiscountRequest request) throws DiscountException {
        makeDiscountsInputPort.makeDishDiscount(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/room")
    public ResponseEntity<Void> makeRoomDiscount(@RequestBody MakeRoomDiscountRequest request) throws DiscountException {
        makeDiscountsInputPort.makeRoomDiscount(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customer")
    public ResponseEntity<Void> makeCustomerDiscount(@RequestBody MakeRoomDiscountRequest request) throws DiscountException {
        makeDiscountsInputPort.makeRoomDiscount(request);
        return ResponseEntity.ok().build();
    }

}
