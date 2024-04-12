package com.challenge1.module.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressRequest {
    @JsonProperty("address_prefix")
    private String addressPrefix;
    private String city;
    private String state;
    @JsonProperty("zip_code")
    private String zipCode;
    @JsonProperty("geo_ip_sort")
    private boolean geoIpSort;
}
