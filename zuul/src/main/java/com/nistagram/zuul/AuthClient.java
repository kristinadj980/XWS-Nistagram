package com.nistagram.zuul;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "auth")
public interface  AuthClient {

}
