package com.elasticemail.app.functions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Date;

import java.util.UUID;

import com.elasticemail.app.API;
import com.elasticemail.app.ApiTypes;
import com.elasticemail.app.ApiTypes.*;
import com.elasticemail.app.FileData;
import com.elasticemail.app.APIResponse.VoidApiResponse;

/**
 * Manage your AccessTokens (ApiKeys)
 */
public class AccessToken extends API
{
    /**
     * Add new AccessToken
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param tokenName 
     * @param accessLevel 
     * @return String
     * @throws Exception
     */
    public String add(String tokenName, ApiTypes.AccessLevel accessLevel) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("tokenName", tokenName);
       values.put("accessLevel", String.valueOf(accessLevel));
       return uploadValues(API_URI + "/accesstoken/add", values, String.class);
   }

    /**
     * Permanently delete AccessToken.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param tokenName 
     * @throws Exception
     */
    public void delete(String tokenName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("tokenName", tokenName);
       uploadValues(API_URI + "/accesstoken/delete", values, VoidApiResponse.class);
   }

    /**
     * Get AccessToken list.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.AccessTokenArray
     * @throws Exception
     */
    public ApiTypes.AccessTokenArray list() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/accesstoken/list", values, ApiTypes.AccessTokenArray.class);
   }

    /**
     * Edit AccessToken.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param tokenName 
     * @param accessLevel 
     * @param tokenNameNew 
     * @throws Exception
     */
    public void update(String tokenName, ApiTypes.AccessLevel accessLevel, String tokenNameNew) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("tokenName", tokenName);
       values.put("accessLevel", String.valueOf(accessLevel));
       values.put("tokenNameNew", tokenNameNew);
       uploadValues(API_URI + "/accesstoken/update", values, VoidApiResponse.class);
   }

}

