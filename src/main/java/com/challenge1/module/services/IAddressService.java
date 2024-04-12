package com.challenge1.module.services;

import com.challenge1.module.models.AddressRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IAddressService {
    ResponseEntity<String> autoComplete(AddressRequest addressRequest);
}
