package com.challenge1.module.controllers;

import com.challenge1.module.models.AddressRequest;
import com.challenge1.module.services.IAddressService;
import com.challenge1.module.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LobAddressControllerTest {

    @Mock
    private IAddressService addressService;

    @InjectMocks
    private LobAddressController lobAddressController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAutoCompleteAddress_Success() {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix("123 Main St");
        addressRequest.setCity("San Francisco");
        addressRequest.setState("CA");
        addressRequest.setZipCode("94107");

        when(addressService.autoComplete(addressRequest))
                .thenReturn(new ResponseEntity<>(TestUtils.MOCKED_ADDRESSES_RESPONSE, HttpStatus.OK));

        ResponseEntity<String> response = lobAddressController.autoCompleteAddress(addressRequest);

        verify(addressService, times(1)).autoComplete(addressRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TestUtils.MOCKED_ADDRESSES_RESPONSE, response.getBody());
    }

    @Test
    public void testAutoCompleteAddressPrefixEmpty_BadRequest() {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix("");
        addressRequest.setCity("San Francisco");
        addressRequest.setState("CA");
        addressRequest.setZipCode("94107");

        when(addressService.autoComplete(addressRequest))
                .thenReturn(new ResponseEntity<>("address_prefix is required", HttpStatus.BAD_REQUEST));

        ResponseEntity<String> response = lobAddressController.autoCompleteAddress(addressRequest);

        verify(addressService, times(1)).autoComplete(addressRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("address_prefix is required", response.getBody());
    }

    @Test
    public void testAutoCompleteAddressPrefixNull_BadRequest() {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix(null);
        addressRequest.setCity("San Francisco");
        addressRequest.setState("CA");
        addressRequest.setZipCode("94107");

        when(addressService.autoComplete(addressRequest))
                .thenReturn(new ResponseEntity<>("address_prefix is required", HttpStatus.BAD_REQUEST));

        ResponseEntity<String> response = lobAddressController.autoCompleteAddress(addressRequest);

        verify(addressService, times(1)).autoComplete(addressRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("address_prefix is required", response.getBody());
    }
}
