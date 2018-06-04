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
 * Methods used to manage your Contacts.
 */
public class Contact extends API
{
    /**
     * Add a new contact and optionally to one of your lists.  Note that your API KEY is not required for this call.
     * @param publicAccountID Public key for limited access to your account such as contact/add so you can use it safely on public websites.
     * @param email Proper email address.
     * @param publicListID ID code of list
     * @param listName Name of your list.
     * @param firstName First name.
     * @param lastName Last name.
     * @param source Specifies the way of uploading the contact
     * @param returnUrl URL to navigate to after account creation
     * @param sourceUrl URL from which request was sent.
     * @param activationReturnUrl The url to return the contact to after activation.
     * @param activationTemplate 
     * @param sendActivation True, if you want to send activation email to this account. Otherwise, false
     * @param consentDate Date of consent to send this contact(s) your email. If not provided current date is used for consent.
     * @param consentIP IP address of consent to send this contact(s) your email. If not provided your current public IP address is used for consent.
     * @param field Custom contact field like firstname, lastname, city etc. Request parameters prefixed by field_ like field_firstname, field_lastname 
     * @param notifyEmail Emails, separated by semicolon, to which the notification about contact subscribing should be sent to
     * @param alreadyActiveUrl 
     * @param consentTracking 
     * @return String
     * @throws Exception
     */
    public String add(String publicAccountID, String email, StringArray publicListID, String[] listName, String firstName, String lastName, ApiTypes.ContactSource source, String returnUrl, String sourceUrl, String activationReturnUrl, String activationTemplate, Boolean sendActivation, Date consentDate, String consentIP, HashMap<String, String> field, String notifyEmail, String alreadyActiveUrl, ApiTypes.ConsentTracking consentTracking) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("publicAccountID", publicAccountID);
       values.put("email", email);
       if (publicListID != null) values.put("publicListID", joinList(publicListID));
       values.put("listName", String.valueOf(listName));
       values.put("firstName", firstName);
       values.put("lastName", lastName);
       values.put("source", String.valueOf(source));
       values.put("returnUrl", returnUrl);
       values.put("sourceUrl", sourceUrl);
       values.put("activationReturnUrl", activationReturnUrl);
       values.put("activationTemplate", activationTemplate);
       values.put("sendActivation", String.valueOf(sendActivation));
       if (consentDate != null) values.put("consentDate", String.valueOf(consentDate));
       values.put("consentIP", consentIP);
       if (field != null) 
           for (String key : field.keySet())
              values.put("field_" + key, field.get(key));
       values.put("notifyEmail", notifyEmail);
       values.put("alreadyActiveUrl", alreadyActiveUrl);
       values.put("consentTracking", String.valueOf(consentTracking));
       return uploadValues(API_URI + "/contact/add", values, String.class);
   }

    /**
     * Manually add or update a contacts status to Abuse or Unsubscribed status (blocked).
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param email Proper email address.
     * @param status Name of status: Active, Engaged, Inactive, Abuse, Bounced, Unsubscribed.
     * @throws Exception
     */
    public void addBlocked(String email, ApiTypes.ContactStatus status) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("email", email);
       values.put("status", String.valueOf(status));
       uploadValues(API_URI + "/contact/addblocked", values, VoidApiResponse.class);
   }

    /**
     * Change any property on the contact record.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param email Proper email address.
     * @param name Name of the contact property you want to change.
     * @param value Value you would like to change the contact property to.
     * @throws Exception
     */
    public void changeProperty(String email, String name, String value) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("email", email);
       values.put("name", name);
       values.put("value", value);
       uploadValues(API_URI + "/contact/changeproperty", values, VoidApiResponse.class);
   }

    /**
     * Changes status of selected Contacts. You may provide RULE for selection or specify list of Contact IDs.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param status Name of status: Active, Engaged, Inactive, Abuse, Bounced, Unsubscribed.
     * @param rule Query used for filtering.
     * @param emails Comma delimited list of contact emails
     * @throws Exception
     */
    public void changeStatus(ApiTypes.ContactStatus status, String rule, StringArray emails) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("status", String.valueOf(status));
       values.put("rule", rule);
       if (emails != null) values.put("emails", joinList(emails));
       uploadValues(API_URI + "/contact/changestatus", values, VoidApiResponse.class);
   }

    /**
     * Returns number of Contacts, RULE specifies contact Status.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param rule Query used for filtering.
     * @param allContacts True: Include every Contact in your Account. Otherwise, false
     * @return ApiTypes.ContactStatusCounts
     * @throws Exception
     */
    public ApiTypes.ContactStatusCounts countByStatus(String rule, Boolean allContacts) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("rule", rule);
       values.put("allContacts", String.valueOf(allContacts));
       return uploadValues(API_URI + "/contact/countbystatus", values, ApiTypes.ContactStatusCounts.class);
   }

    /**
     * Returns count of unsubscribe reasons for unsubscribed and complaint contacts.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param from Starting date for search in YYYY-MM-DDThh:mm:ss format.
     * @param to Ending date for search in YYYY-MM-DDThh:mm:ss format.
     * @return ApiTypes.ContactUnsubscribeReasonCounts
     * @throws Exception
     */
    public ApiTypes.ContactUnsubscribeReasonCounts countByUnsubscribeReason(Date from, Date to) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (from != null) values.put("from", String.valueOf(from));
       if (to != null) values.put("to", String.valueOf(to));
       return uploadValues(API_URI + "/contact/countbyunsubscribereason", values, ApiTypes.ContactUnsubscribeReasonCounts.class);
   }

    /**
     * Permanantly deletes the contacts provided.  You can provide either a qualified rule or a list of emails (comma separated string).
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param rule Query used for filtering.
     * @param emails Comma delimited list of contact emails
     * @throws Exception
     */
    public void delete(String rule, StringArray emails) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("rule", rule);
       if (emails != null) values.put("emails", joinList(emails));
       uploadValues(API_URI + "/contact/delete", values, VoidApiResponse.class);
   }

    /**
     * Export selected Contacts to file.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param fileFormat Format of the exported file
     * @param rule Query used for filtering.
     * @param emails Comma delimited list of contact emails
     * @param compressionFormat FileResponse compression format. None or Zip.
     * @param fileName Name of your file.
     * @return ApiTypes.ExportLink
     * @throws Exception
     */
    public ApiTypes.ExportLink export(ApiTypes.ExportFileFormats fileFormat, String rule, StringArray emails, ApiTypes.CompressionFormat compressionFormat, String fileName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("fileFormat", String.valueOf(fileFormat));
       values.put("rule", rule);
       if (emails != null) values.put("emails", joinList(emails));
       values.put("compressionFormat", String.valueOf(compressionFormat));
       values.put("fileName", fileName);
       return uploadValues(API_URI + "/contact/export", values, ApiTypes.ExportLink.class);
   }

    /**
     * Export contacts' unsubscribe reasons count to file.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param from Starting date for search in YYYY-MM-DDThh:mm:ss format.
     * @param to Ending date for search in YYYY-MM-DDThh:mm:ss format.
     * @param fileFormat Format of the exported file
     * @param compressionFormat FileResponse compression format. None or Zip.
     * @param fileName Name of your file.
     * @return ApiTypes.ExportLink
     * @throws Exception
     */
    public ApiTypes.ExportLink exportUnsubscribeReasonCount(Date from, Date to, ApiTypes.ExportFileFormats fileFormat, ApiTypes.CompressionFormat compressionFormat, String fileName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (from != null) values.put("from", String.valueOf(from));
       if (to != null) values.put("to", String.valueOf(to));
       values.put("fileFormat", String.valueOf(fileFormat));
       values.put("compressionFormat", String.valueOf(compressionFormat));
       values.put("fileName", fileName);
       return uploadValues(API_URI + "/contact/exportunsubscribereasoncount", values, ApiTypes.ExportLink.class);
   }

    /**
     * Finds all Lists and Segments this email belongs to.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param email Proper email address.
     * @return ApiTypes.ContactCollection
     * @throws Exception
     */
    public ApiTypes.ContactCollection findContact(String email) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("email", email);
       return uploadValues(API_URI + "/contact/findcontact", values, ApiTypes.ContactCollection.class);
   }

    /**
     * List of Contacts for provided List
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param listName Name of your list.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @return ApiTypes.ContactArray
     * @throws Exception
     */
    public ApiTypes.ContactArray getContactsByList(String listName, int limit, int offset) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("listName", listName);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       return uploadValues(API_URI + "/contact/getcontactsbylist", values, ApiTypes.ContactArray.class);
   }

    /**
     * List of Contacts for provided Segment
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param segmentName Name of your segment.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @return ApiTypes.ContactArray
     * @throws Exception
     */
    public ApiTypes.ContactArray getContactsBySegment(String segmentName, int limit, int offset) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("segmentName", segmentName);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       return uploadValues(API_URI + "/contact/getcontactsbysegment", values, ApiTypes.ContactArray.class);
   }

    /**
     * List of all contacts. If you have not specified RULE, all Contacts will be listed.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param rule Query used for filtering.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @return ApiTypes.ContactArray
     * @throws Exception
     */
    public ApiTypes.ContactArray list(String rule, int limit, int offset) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("rule", rule);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       return uploadValues(API_URI + "/contact/list", values, ApiTypes.ContactArray.class);
   }

    /**
     * Load blocked contacts
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param statuses List of blocked statuses: Abuse, Bounced or Unsubscribed
     * @param search Text fragment used for searching.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @return ApiTypes.BlockedContactArray
     * @throws Exception
     */
    public ApiTypes.BlockedContactArray loadBlocked(ApiTypes.ContactStatusArray statuses, String search, int limit, int offset) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (statuses != null) values.put("statuses", joinList(statuses));
       values.put("search", search);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       return uploadValues(API_URI + "/contact/loadblocked", values, ApiTypes.BlockedContactArray.class);
   }

    /**
     * Load detailed contact information
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param email Proper email address.
     * @return ApiTypes.Contact
     * @throws Exception
     */
    public ApiTypes.Contact loadContact(String email) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("email", email);
       return uploadValues(API_URI + "/contact/loadcontact", values, ApiTypes.Contact.class);
   }

    /**
     * Shows detailed history of chosen Contact.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param email Proper email address.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @return ApiTypes.ContactHistoryArray
     * @throws Exception
     */
    public ApiTypes.ContactHistoryArray loadHistory(String email, int limit, int offset) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("email", email);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       return uploadValues(API_URI + "/contact/loadhistory", values, ApiTypes.ContactHistoryArray.class);
   }

    /**
     * Add new Contact to one of your Lists.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param emails Comma delimited list of contact emails
     * @param firstName First name.
     * @param lastName Last name.
     * @param publicListID ID code of list
     * @param listName Name of your list.
     * @param status Name of status: Active, Engaged, Inactive, Abuse, Bounced, Unsubscribed.
     * @param notes Free form field of notes
     * @param consentDate Date of consent to send this contact(s) your email. If not provided current date is used for consent.
     * @param consentIP IP address of consent to send this contact(s) your email. If not provided your current public IP address is used for consent.
     * @param field Custom contact field like firstname, lastname, city etc. Request parameters prefixed by field_ like field_firstname, field_lastname 
     * @param notifyEmail Emails, separated by semicolon, to which the notification about contact subscribing should be sent to
     * @param consentTracking 
     * @throws Exception
     */
    public void quickAdd(StringArray emails, String firstName, String lastName, String publicListID, String listName, ApiTypes.ContactStatus status, String notes, Date consentDate, String consentIP, HashMap<String, String> field, String notifyEmail, ApiTypes.ConsentTracking consentTracking) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (emails != null) values.put("emails", joinList(emails));
       values.put("firstName", firstName);
       values.put("lastName", lastName);
       values.put("publicListID", publicListID);
       values.put("listName", listName);
       values.put("status", String.valueOf(status));
       values.put("notes", notes);
       if (consentDate != null) values.put("consentDate", String.valueOf(consentDate));
       values.put("consentIP", consentIP);
       if (field != null) 
           for (String key : field.keySet())
              values.put("field_" + key, field.get(key));
       values.put("notifyEmail", notifyEmail);
       values.put("consentTracking", String.valueOf(consentTracking));
       uploadValues(API_URI + "/contact/quickadd", values, VoidApiResponse.class);
   }

    /**
     * Basic double opt-in email subscribe form for your account.  This can be used for contacts that need to re-subscribe as well.
     * @param publicAccountID Public key for limited access to your account such as contact/add so you can use it safely on public websites.
     * @return String
     * @throws Exception
     */
    public String subscribe(String publicAccountID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("publicAccountID", publicAccountID);
       return uploadValues(API_URI + "/contact/subscribe", values, String.class);
   }

    /**
     * Update selected contact. Omitted contact's fields will be reset by default (see the clearRestOfFields parameter)
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param email Proper email address.
     * @param firstName First name.
     * @param lastName Last name.
     * @param clearRestOfFields States if the fields that were omitted in this request are to be reset or should they be left with their current value
     * @param field Custom contact field like firstname, lastname, city etc. Request parameters prefixed by field_ like field_firstname, field_lastname 
     * @param customFields Custom contact field like firstname, lastname, city etc. JSON serialized text like { "city":"london" } 
     * @return ApiTypes.Contact
     * @throws Exception
     */
    public ApiTypes.Contact update(String email, String firstName, String lastName, Boolean clearRestOfFields, HashMap<String, String> field, String customFields) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("email", email);
       values.put("firstName", firstName);
       values.put("lastName", lastName);
       values.put("clearRestOfFields", String.valueOf(clearRestOfFields));
       if (field != null) 
           for (String key : field.keySet())
              values.put("field_" + key, field.get(key));
       values.put("customFields", customFields);
       return uploadValues(API_URI + "/contact/update", values, ApiTypes.Contact.class);
   }

    /**
     * Upload contacts in CSV file.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param contactFile Name of CSV file with Contacts.
     * @param allowUnsubscribe True: Allow unsubscribing from this (optional) newly created list. Otherwise, false
     * @param listID ID number of selected list.
     * @param listName Name of your list to upload contacts to, or how the new, automatically created list should be named
     * @param status Name of status: Active, Engaged, Inactive, Abuse, Bounced, Unsubscribed.
     * @param consentDate Date of consent to send this contact(s) your email. If not provided current date is used for consent.
     * @param consentIP IP address of consent to send this contact(s) your email. If not provided your current public IP address is used for consent.
     * @param consentTracking 
     * @return int
     * @throws Exception
     */
    public int upload(FileData contactFile, Boolean allowUnsubscribe, int listID, String listName, ApiTypes.ContactStatus status, Date consentDate, String consentIP, ApiTypes.ConsentTracking consentTracking) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("allowUnsubscribe", String.valueOf(allowUnsubscribe));
       values.put("listID", String.valueOf(listID));
       values.put("listName", listName);
       values.put("status", String.valueOf(status));
       if (consentDate != null) values.put("consentDate", String.valueOf(consentDate));
       values.put("consentIP", consentIP);
       values.put("consentTracking", String.valueOf(consentTracking));
       return httpPostFile(API_URI + "/contact/upload", Arrays.asList(contactFile), values, int.class);
   }

}

