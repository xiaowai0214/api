package com.egova.api.model;

import com.egova.api.enums.GrantType;
import lombok.Data;

@Data
public class AuthenticationOauth2Config extends AuthenticationBasicConfig{

    /*
     *  获取token接口相应结果的token参数路径
     */
    private String tokenPath;

    /*
     *  最终token的前缀 例如： bearer
     */
    private String headerPrefix;

    /*
     * 授权类型
     */
    private GrantType grantType;

    /*
     * 获取token 的http 调用地址
     */
    private String tokenUrl;

    /*
     * 获取授权码的 http地址
     */
    private String authUrl;

    /*
     * 获取授权码后的 http回调地址
     */
    private String callbackUrl;

    private String clientId;

    private String clientSecret;

    private String scope;


}
