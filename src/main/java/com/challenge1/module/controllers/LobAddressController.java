package com.challenge1.module.controllers;

import com.challenge1.module.models.AddressRequest;
import com.challenge1.module.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lobAddresses")
public class LobAddressController {
    IAddressService addressService;

    @Autowired
    public LobAddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @Secured("ADMIN")
    @PostMapping("/autocompletion")
    public ResponseEntity<String> autoCompleteAddress(@RequestBody AddressRequest addressRequest) {
        return addressService.autoComplete(addressRequest);
    }
}
