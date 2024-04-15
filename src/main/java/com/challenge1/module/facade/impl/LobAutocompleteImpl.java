package com.challenge1.module.facade.impl;

import com.challenge1.module.facade.IAutocompleteFacade;
import com.challenge1.module.models.AddressRequest;
import com.lob.api.ApiClient;
import com.lob.api.ApiException;
import com.lob.api.client.UsAutocompletionsApi;
import org.openapitools.client.model.UsAutocompletions;
import org.openapitools.client.model.UsAutocompletionsWritable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobAutocompleteImpl implements IAutocompleteFacade<String, AddressRequest> {

    private ApiClient lobClient;

    @Autowired
    public LobAutocompleteImpl(ApiClient lobClient) {
        this.lobClient = lobClient;
    }

    @Override
    public String autoComplete(AddressRequest addressRequest) throws ApiException {

        UsAutocompletionsApi apiInstance = new UsAutocompletionsApi(lobClient);
        UsAutocompletionsWritable usAutocompletionsWritable = getUsAutocompletionsWritable(addressRequest);
        UsAutocompletions usAutocompletions = apiInstance.autocomplete(usAutocompletionsWritable);

        return usAutocompletions.getSuggestions().toString();
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
