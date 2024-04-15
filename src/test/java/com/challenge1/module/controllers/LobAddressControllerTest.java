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
        AddressRequest addressRequest = TestUtils.getTestAddressRequest("123 Main St");

        when(addressService.autoComplete(addressRequest))
                .thenReturn(new ResponseEntity<>(TestUtils.MOCKED_ADDRESSES_RESPONSE, HttpStatus.OK));

        ResponseEntity<String> response = lobAddressController.autoCompleteAddress(addressRequest);

        verify(addressService, times(1)).autoComplete(addressRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TestUtils.MOCKED_ADDRESSES_RESPONSE, response.getBody());
    }

    @Test
    public void testAutoCompleteAddressPrefixEmpty_BadRequest() {
        AddressRequest addressRequest = TestUtils.getTestAddressRequest("");

        when(addressService.autoComplete(addressRequest))
                .thenReturn(new ResponseEntity<>(TestUtils.BAD_REQUEST_ERROR, HttpStatus.BAD_REQUEST));

        ResponseEntity<String> response = lobAddressController.autoCompleteAddress(addressRequest);

        verify(addressService, times(1)).autoComplete(addressRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(TestUtils.BAD_REQUEST_ERROR, response.getBody());
    }

    @Test
    public void testAutoCompleteAddressPrefixNull_BadRequest() {
        AddressRequest addressRequest = TestUtils.getTestAddressRequest(null);

        when(addressService.autoComplete(addressRequest))
                .thenReturn(new ResponseEntity<>(TestUtils.BAD_REQUEST_ERROR, HttpStatus.BAD_REQUEST));

        ResponseEntity<String> response = lobAddressController.autoCompleteAddress(addressRequest);

        verify(addressService, times(1)).autoComplete(addressRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(TestUtils.BAD_REQUEST_ERROR, response.getBody());
    }

    @Test
    public void testAutoCompleteAddressWithBadApiKey_Unauthorized() {
        AddressRequest addressRequest = TestUtils.getTestAddressRequest("123 Main St");

        when(addressService.autoComplete(addressRequest))
                .thenReturn(new ResponseEntity<>(TestUtils.API_KEY_NOT_VALID_ERROR, HttpStatus.UNAUTHORIZED));

        ResponseEntity<String> response = lobAddressController.autoCompleteAddress(addressRequest);

        verify(addressService, times(1)).autoComplete(addressRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(TestUtils.API_KEY_NOT_VALID_ERROR, response.getBody());
    }
}
