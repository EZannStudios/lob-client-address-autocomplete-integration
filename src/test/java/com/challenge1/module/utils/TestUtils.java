package com.challenge1.module.utils;

import com.lob.api.ApiClient;
import com.lob.api.auth.HttpBasicAuth;

public class TestUtils {

    public static final String LOB_API_BASEURL = "https://api.lob.com/v1";

    public static final String AUTH_MODE = "basicAuth";

    public static final String TEST_API_KEY = "test_pub_3c9c401e55927501b9ce3c443bb6b3a";

    public static final String ONE_RESULT_REQUEST = "1 suggestion";

    public static final String MULTIPLE_RESULT_REQUEST = "2 sugg";

    public static final String BAD_REQUEST_ERROR = "address_prefix is required";

    public static final String MOCKED_ADDRESSES_RESPONSE = "[{\n" +
            "    primaryLine: 185 BRANNAN ST\n" +
            "    city: SAN FRANCISCO\n" +
            "    state: CA\n" +
            "    zipCode: 94107\n" +
            "    _object: us_autocompletion\n" +
            "}, {\n" +
            "    primaryLine: 185 BLUXOME ST\n" +
            "    city: SAN FRANCISCO\n" +
            "    state: CA\n" +
            "    zipCode: 94107\n" +
            "    _object: us_autocompletion\n" +
            "}, {\n" +
            "    primaryLine: 185 BERRY ST\n" +
            "    city: SAN FRANCISCO\n" +
            "    state: CA\n" +
            "    zipCode: 94107\n" +
            "    _object: us_autocompletion\n" +
            "}, {\n" +
            "    primaryLine: 185 BLAIR TER\n" +
            "    city: SAN FRANCISCO\n" +
            "    state: CA\n" +
            "    zipCode: 94107\n" +
            "    _object: us_autocompletion\n" +
            "}]";

    public static final String MOCKED_SUGGESTION_1 = "[{\n" +
            "    primaryLine: 1 TELEGRAPH HILL BLVD\n" +
            "    city: SAN FRANCISCO\n" +
            "    state: CA\n" +
            "    zipCode: 94133\n" +
            "    _object: us_autocompletion\n" +
            "}]";

    public static final String MOCKED_SUGG_2 = "[{\n" +
            "    primaryLine: 2 TELEPHONE RD\n" +
            "    city: OXFORD\n" +
            "    state: AR\n" +
            "    zipCode: 72565\n" +
            "    _object: us_autocompletion\n" +
            "}, {\n" +
            "    primaryLine: 2 TELEGA PL\n" +
            "    city: PALMDALE\n" +
            "    state: CA\n" +
            "    zipCode: 93550\n" +
            "    _object: us_autocompletion\n" +
            "}, {\n" +
            "    primaryLine: 2 TELEGRAM AVE\n" +
            "    city: ELMONT\n" +
            "    state: NY\n" +
            "    zipCode: 11003\n" +
            "    _object: us_autocompletion\n" +
            "}, {\n" +
            "    primaryLine: 2 TELEGRAM AVE\n" +
            "    city: GARDEN CITY\n" +
            "    state: KS\n" +
            "    zipCode: 67846\n" +
            "    _object: us_autocompletion\n" +
            "}, {\n" +
            "    primaryLine: 2 TELEGRAPH HILL RD\n" +
            "    city: HOLMDEL\n" +
            "    state: NJ\n" +
            "    zipCode: 07733\n" +
            "    _object: us_autocompletion\n" +
            "}, {\n" +
            "    primaryLine: 2 TELEGRAPH HILL RD S\n" +
            "    city: HOLMDEL\n" +
            "    state: NJ\n" +
            "    zipCode: 07733\n" +
            "    _object: us_autocompletion\n" +
            "}, {\n" +
            "    primaryLine: 2 TELEGRAPH HILL BLVD\n" +
            "    city: SAN FRANCISCO\n" +
            "    state: CA\n" +
            "    zipCode: 94133\n" +
            "    _object: us_autocompletion\n" +
            "}]";

    public static final String UNAUTHORIZED_ERROR = "Cannot process address: \n" +
            "{\n" +
            "    addressPrefix: 1 suggestion\n" +
            "    city: null\n" +
            "    state: null\n" +
            "    zipCode: null\n" +
            "    geoIpSort: false\n" +
            "}\n" +
            "Missing authentication";

    public static ApiClient lobTestClient() {
        ApiClient lobTestClient = com.lob.api.Configuration.getBadConfigForIntegration();
        lobTestClient.setBasePath(LOB_API_BASEURL);
        HttpBasicAuth basicAuth = (HttpBasicAuth) lobTestClient.getAuthentication(AUTH_MODE);
        basicAuth.setUsername(TEST_API_KEY);

        return lobTestClient;
    }

    public static ApiClient badLobTestClient() {
        ApiClient lobTestClient = com.lob.api.Configuration.getConfigForIntegration();

        return lobTestClient;
    }
}
