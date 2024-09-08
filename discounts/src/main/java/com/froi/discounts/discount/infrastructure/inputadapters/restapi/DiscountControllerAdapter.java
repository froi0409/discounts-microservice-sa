package com.froi.discounts.discount.infrastructure.inputadapters.restapi;

import com.froi.discounts.common.WebAdapter;
import com.froi.discounts.discount.application.makediscountusecase.MakeCustomerDiscountRequest;
import com.froi.discounts.discount.application.makediscountusecase.MakeDishDiscountRequest;
import com.froi.discounts.discount.application.makediscountusecase.MakeRoomDiscountRequest;
import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.domain.exceptions.DiscountException;
import com.froi.discounts.discount.infrastructure.inputports.restapi.FindDiscountInputPort;
import com.froi.discounts.discount.infrastructure.inputports.restapi.MakeDiscountsInputPort;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.hibernate.annotations.CollectionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("discounts/v1/discounts")
@WebAdapter
@SecurityRequirement(name = "bearerAuth")
public class DiscountControllerAdapter {

    private MakeDiscountsInputPort makeDiscountsInputPort;
    private FindDiscountInputPort findDiscountInputPort;

    @Autowired
    public DiscountControllerAdapter(MakeDiscountsInputPort makeDiscountsInputPort, FindDiscountInputPort findDiscountInputPort) {
        this.makeDiscountsInputPort = makeDiscountsInputPort;
        this.findDiscountInputPort = findDiscountInputPort;
    }

    @PostMapping("/dish")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> makeDishDiscount(@RequestBody MakeDishDiscountRequest request) throws DiscountException {
        makeDiscountsInputPort.makeDishDiscount(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/room")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> makeRoomDiscount(@RequestBody MakeRoomDiscountRequest request) throws DiscountException {
        makeDiscountsInputPort.makeRoomDiscount(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> makeCustomerDiscount(@RequestBody MakeCustomerDiscountRequest request) throws DiscountException {
        makeDiscountsInputPort.makeCustomerDiscount(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findDishDiscount/{dishId}/{date}")
    @PreAuthorize("hasRole('RESTAURANT_EMPLOYEE')")
    public ResponseEntity<DiscountInformationResponse> findDishDiscount(@PathVariable String dishId, @PathVariable String date) {
        DiscountInformationResponse response = findDiscountInputPort.findDishDiscount(dishId, date);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/findRoomDiscount/{roomCode}/{hotelId}/{date}")
    @PreAuthorize("hasRole('HOTEL_EMPLOYEE')")
    public ResponseEntity<DiscountInformationResponse> findRoomDiscount(@PathVariable String roomCode, @PathVariable String hotelId, @PathVariable String date) {
        DiscountInformationResponse response = findDiscountInputPort.findRoomDiscount(roomCode, hotelId, date);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/findCustomerDiscount/{customerNit}/{date}")
    @PreAuthorize("hasAnyRole('HOTEL_EMPLOYEE', 'RESTAURANT_EMPLOYEE')")
    public ResponseEntity<DiscountInformationResponse> findCustomerDiscount(@PathVariable String customerNit, @PathVariable String date) {
        DiscountInformationResponse response = findDiscountInputPort.findCustomerDiscount(customerNit, date);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
