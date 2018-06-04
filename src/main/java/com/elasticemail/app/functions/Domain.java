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
 * Managing sender domains. Creating new entries and validating domain records.
 */
public class Domain extends API
{
    /**
     * Add new domain to account
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param domain Name of selected domain.
     * @param trackingType 
     * @throws Exception
     */
    public void add(String domain, ApiTypes.TrackingType trackingType) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("domain", domain);
       values.put("trackingType", String.valueOf(trackingType));
       uploadValues(API_URI + "/domain/add", values, VoidApiResponse.class);
   }

    /**
     * Deletes configured domain from account
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param domain Name of selected domain.
     * @throws Exception
     */
    public void delete(String domain) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("domain", domain);
       uploadValues(API_URI + "/domain/delete", values, VoidApiResponse.class);
   }

    /**
     * Lists all domains configured for this account.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.DomainDetailArray
     * @throws Exception
     */
    public ApiTypes.DomainDetailArray list() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/domain/list", values, ApiTypes.DomainDetailArray.class);
   }

    /**
     * Verification of email addres set for domain.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param domain Default email sender, example: mail@yourdomain.com
     * @throws Exception
     */
    public void setDefault(String domain) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("domain", domain);
       uploadValues(API_URI + "/domain/setdefault", values, VoidApiResponse.class);
   }

    /**
     * Verification of DKIM record for domain
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param domain Name of selected domain.
     * @return String
     * @throws Exception
     */
    public String verifyDkim(String domain) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("domain", domain);
       return uploadValues(API_URI + "/domain/verifydkim", values, String.class);
   }

    /**
     * Verification of MX record for domain
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param domain Name of selected domain.
     * @return String
     * @throws Exception
     */
    public String verifyMX(String domain) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("domain", domain);
       return uploadValues(API_URI + "/domain/verifymx", values, String.class);
   }

    /**
     * Verification of SPF record for domain
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param domain Name of selected domain.
     * @return String
     * @throws Exception
     */
    public String verifySpf(String domain) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("domain", domain);
       return uploadValues(API_URI + "/domain/verifyspf", values, String.class);
   }

    /**
     * Verification of tracking CNAME record for domain
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param domain Name of selected domain.
     * @param trackingType 
     * @return String
     * @throws Exception
     */
    public String verifyTracking(String domain, ApiTypes.TrackingType trackingType) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("domain", domain);
       values.put("trackingType", String.valueOf(trackingType));
       return uploadValues(API_URI + "/domain/verifytracking", values, String.class);
   }

}

