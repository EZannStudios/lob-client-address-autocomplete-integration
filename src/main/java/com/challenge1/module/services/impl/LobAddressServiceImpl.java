package com.challenge1.module.services.impl;

import com.challenge1.module.facade.IAutocompleteFacade;
import com.challenge1.module.models.AddressRequest;
import com.challenge1.module.services.IAddressService;
import com.lob.api.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LobAddressServiceImpl implements IAddressService {
    private static final String BAD_REQUEST_ERROR = "address_prefix is required";
    private static final String INTERNAL_API_ERROR = "Cannot process address: \n";

    private IAutocompleteFacade<String, AddressRequest> autocompleteFacade;

    @Autowired
    public LobAddressServiceImpl(IAutocompleteFacade<String, AddressRequest> autocompleteFacade) {
        this.autocompleteFacade = autocompleteFacade;
    }

    @Override
    public ResponseEntity<String> autoComplete(AddressRequest addressRequest) {
        if (isAddressPrefixEmpty(addressRequest)) {
            return ResponseEntity.badRequest().body(BAD_REQUEST_ERROR);
        }

        try {
            return ResponseEntity.ok(autocompleteFacade.autoComplete(addressRequest));
        } catch (ApiException e) {
            return ResponseEntity.status(e.getCode())
                    .body(INTERNAL_API_ERROR + addressRequest
                            + "\n" + e.getMessage());
        }
    }

    private boolean isAddressPrefixEmpty(AddressRequest addressRequest) {
        return addressRequest == null || StringUtils.isEmpty(addressRequest.getAddressPrefix());
    }
}
