package com.challenge1.module.services.impl;

import com.challenge1.module.models.AddressRequest;
import com.challenge1.module.utils.TestUtils;
import com.lob.api.ApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.challenge1.module.utils.TestUtils.BAD_REQUEST_ERROR;
import static com.challenge1.module.utils.TestUtils.MOCKED_SUGGESTION_1;
import static com.challenge1.module.utils.TestUtils.ONE_RESULT_REQUEST;
import static com.challenge1.module.utils.TestUtils.UNAUTHORIZED_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LobAddressServiceImplTest {

    private ApiClient testLobApiClient;

    private LobAddressServiceImpl lobAddressService;

    @Test
    public void testAutoComplete_WithNonEmptyAddressPrefix_Success() {
        testLobApiClient = TestUtils.lobTestClient();
        lobAddressService = new LobAddressServiceImpl(testLobApiClient);

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix(ONE_RESULT_REQUEST);

        ResponseEntity<String> response = lobAddressService.autoComplete(addressRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MOCKED_SUGGESTION_1, response.getBody());
    }

    @Test
    public void testAutoComplete_WithEmptyAddressPrefix_BadRequest() {
        testLobApiClient = TestUtils.lobTestClient();
        lobAddressService = new LobAddressServiceImpl(testLobApiClient);

        AddressRequest addressRequest = new AddressRequest();

        ResponseEntity<String> response = lobAddressService.autoComplete(addressRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(BAD_REQUEST_ERROR, response.getBody());
    }

    @Test
    public void testAutoComplete_WithBadApiClient_Unauthorized() {
        testLobApiClient = TestUtils.badLobTestClient();
        lobAddressService = new LobAddressServiceImpl(testLobApiClient);

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix(ONE_RESULT_REQUEST);

        ResponseEntity<String> response = lobAddressService.autoComplete(addressRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(UNAUTHORIZED_ERROR, response.getBody());
    }
}
