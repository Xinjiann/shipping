package com.shipping.common.utils;

import lombok.Data;

@Data
public class AccessToken {
	private String access_token;
    private String token_type;
    private long expires_in;

}
