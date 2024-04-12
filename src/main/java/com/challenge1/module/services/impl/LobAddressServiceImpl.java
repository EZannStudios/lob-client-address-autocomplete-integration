package com.challenge1.module.services.impl;

import com.challenge1.module.models.AddressRequest;
import com.challenge1.module.services.IAddressService;
import com.lob.api.ApiClient;
import com.lob.api.ApiException;
import com.lob.api.client.UsAutocompletionsApi;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.client.model.UsAutocompletions;
import org.openapitools.client.model.UsAutocompletionsWritable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LobAddressServiceImpl implements IAddressService {

    private static final String BAD_REQUEST_ERROR = "address_prefix is required";
    private static final String INTERNAL_API_ERROR = "Cannot process address: \n";

    @Autowired
    ApiClient lobClient;

    public LobAddressServiceImpl(ApiClient lobClient) {
        this.lobClient = lobClient;
    }

    @Override
    public ResponseEntity<String> autoComplete(AddressRequest addressRequest) {
        if (isAddressPrefixEmpty(addressRequest)) {
            return ResponseEntity.badRequest().body(BAD_REQUEST_ERROR);
        }
        UsAutocompletionsApi apiInstance = new UsAutocompletionsApi(lobClient);
        UsAutocompletionsWritable usAutocompletionsWritable = getUsAutocompletionsWritable(addressRequest);
        UsAutocompletions usAutocompletions;
        try {
            usAutocompletions = apiInstance.autocomplete(usAutocompletionsWritable);
        } catch (ApiException e) {
            return ResponseEntity.status(e.getCode())
                    .body(INTERNAL_API_ERROR + usAutocompletionsWritable
                            + "\n" + e.getMessage());
        }
        return ResponseEntity.ok(usAutocompletions.getSuggestions().toString());
    }

    private boolean isAddressPrefixEmpty(AddressRequest addressRequest) {
        return addressRequest == null || StringUtils.isEmpty(addressRequest.getAddressPrefix());
    }

    private UsAutocompletionsWritable getUsAutocompletionsWritable(AddressRequest addressRequest) {
        UsAutocompletionsWritable usAutocompletionsWritable = new UsAutocompletionsWritable();
        usAutocompletionsWritable.setAddressPrefix(addressRequest.getAddressPrefix());
        usAutocompletionsWritable.setCity(addressRequest.getCity());
        usAutocompletionsWritable.setState(addressRequest.getState());
        usAutocompletionsWritable.setZipCode(addressRequest.getZipCode());
        usAutocompletionsWritable.setGeoIpSort(addressRequest.isGeoIpSort());
        return usAutocompletionsWritable;
    }
}
