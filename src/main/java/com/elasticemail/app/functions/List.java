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
 * API methods for managing your Lists
 */
public class List extends API
{
    /**
     * Create new list, based on filtering rule or list of IDs
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param listName Name of your list.
     * @param createEmptyList True to create an empty list, otherwise false. Ignores rule and emails parameters if provided.
     * @param allowUnsubscribe True: Allow unsubscribing from this list. Otherwise, false
     * @param rule Query used for filtering.
     * @param emails Comma delimited list of contact emails
     * @param allContacts True: Include every Contact in your Account. Otherwise, false
     * @return int
     * @throws Exception
     */
    public int add(String listName, Boolean createEmptyList, Boolean allowUnsubscribe, String rule, StringArray emails, Boolean allContacts) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("listName", listName);
       values.put("createEmptyList", String.valueOf(createEmptyList));
       values.put("allowUnsubscribe", String.valueOf(allowUnsubscribe));
       values.put("rule", rule);
       if (emails != null) values.put("emails", joinList(emails));
       values.put("allContacts", String.valueOf(allContacts));
       return uploadValues(API_URI + "/list/add", values, int.class);
   }

    /**
     * Add existing Contacts to chosen list
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param listName Name of your list.
     * @param rule Query used for filtering.
     * @param emails Comma delimited list of contact emails
     * @param allContacts True: Include every Contact in your Account. Otherwise, false
     * @throws Exception
     */
    public void addContacts(String listName, String rule, StringArray emails, Boolean allContacts) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("listName", listName);
       values.put("rule", rule);
       if (emails != null) values.put("emails", joinList(emails));
       values.put("allContacts", String.valueOf(allContacts));
       uploadValues(API_URI + "/list/addcontacts", values, VoidApiResponse.class);
   }

    /**
     * Copy your existing List with the option to provide new settings to it. Some fields, when left empty, default to the source list's settings
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param sourceListName The name of the list you want to copy
     * @param newlistName Name of your list if you want to change it.
     * @param createEmptyList True to create an empty list, otherwise false. Ignores rule and emails parameters if provided.
     * @param allowUnsubscribe True: Allow unsubscribing from this list. Otherwise, false
     * @param rule Query used for filtering.
     * @return int
     * @throws Exception
     */
    public int copy(String sourceListName, String newlistName, Boolean createEmptyList, Boolean allowUnsubscribe, String rule) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("sourceListName", sourceListName);
       values.put("newlistName", newlistName);
       values.put("createEmptyList", String.valueOf(createEmptyList));
       values.put("allowUnsubscribe", String.valueOf(allowUnsubscribe));
       values.put("rule", rule);
       return uploadValues(API_URI + "/list/copy", values, int.class);
   }

    /**
     * Create a new list from the recipients of the given campaign, using the given statuses of Messages
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param campaignID ID of the campaign which recipients you want to copy
     * @param listName Name of your list.
     * @param statuses Statuses of a campaign's emails you want to include in the new list (but NOT the contacts' statuses)
     * @return int
     * @throws Exception
     */
    public int createFromCampaign(int campaignID, String listName, ApiTypes.LogJobStatusArray statuses) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("campaignID", String.valueOf(campaignID));
       values.put("listName", listName);
       if (statuses != null) if (statuses != null) values.put("statuses", joinList(statuses));
       return uploadValues(API_URI + "/list/createfromcampaign", values, int.class);
   }

    /**
     * Create a series of nth selection lists from an existing list or segment
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param listName Name of your list.
     * @param numberOfLists The number of evenly distributed lists to create.
     * @param excludeBlocked True if you want to exclude contacts that are currently in a blocked status of either unsubscribe, complaint or bounce. Otherwise, false.
     * @param allowUnsubscribe True: Allow unsubscribing from this list. Otherwise, false
     * @param rule Query used for filtering.
     * @param allContacts True: Include every Contact in your Account. Otherwise, false
     * @throws Exception
     */
    public void createNthSelectionLists(String listName, int numberOfLists, Boolean excludeBlocked, Boolean allowUnsubscribe, String rule, Boolean allContacts) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("listName", listName);
       values.put("numberOfLists", String.valueOf(numberOfLists));
       values.put("excludeBlocked", String.valueOf(excludeBlocked));
       values.put("allowUnsubscribe", String.valueOf(allowUnsubscribe));
       values.put("rule", rule);
       values.put("allContacts", String.valueOf(allContacts));
       uploadValues(API_URI + "/list/createnthselectionlists", values, VoidApiResponse.class);
   }

    /**
     * Create a new list with randomized contacts from an existing list or segment
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param listName Name of your list.
     * @param count Number of items.
     * @param excludeBlocked True if you want to exclude contacts that are currently in a blocked status of either unsubscribe, complaint or bounce. Otherwise, false.
     * @param allowUnsubscribe True: Allow unsubscribing from this list. Otherwise, false
     * @param rule Query used for filtering.
     * @param allContacts True: Include every Contact in your Account. Otherwise, false
     * @return int
     * @throws Exception
     */
    public int createRandomList(String listName, int count, Boolean excludeBlocked, Boolean allowUnsubscribe, String rule, Boolean allContacts) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("listName", listName);
       values.put("count", String.valueOf(count));
       values.put("excludeBlocked", String.valueOf(excludeBlocked));
       values.put("allowUnsubscribe", String.valueOf(allowUnsubscribe));
       values.put("rule", rule);
       values.put("allContacts", String.valueOf(allContacts));
       return uploadValues(API_URI + "/list/createrandomlist", values, int.class);
   }

    /**
     * Deletes List and removes all the Contacts from it (does not delete Contacts).
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param listName Name of your list.
     * @throws Exception
     */
    public void delete(String listName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("listName", listName);
       uploadValues(API_URI + "/list/delete", values, VoidApiResponse.class);
   }

    /**
     * Exports all the contacts from the provided list
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param listName Name of your list.
     * @param fileFormat Format of the exported file
     * @param compressionFormat FileResponse compression format. None or Zip.
     * @param fileName Name of your file.
     * @return ApiTypes.ExportLink
     * @throws Exception
     */
    public ApiTypes.ExportLink export(String listName, ApiTypes.ExportFileFormats fileFormat, ApiTypes.CompressionFormat compressionFormat, String fileName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("listName", listName);
       values.put("fileFormat", String.valueOf(fileFormat));
       values.put("compressionFormat", String.valueOf(compressionFormat));
       values.put("fileName", fileName);
       return uploadValues(API_URI + "/list/export", values, ApiTypes.ExportLink.class);
   }

    /**
     * Shows all your existing lists
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param from Starting date for search in YYYY-MM-DDThh:mm:ss format.
     * @param to Ending date for search in YYYY-MM-DDThh:mm:ss format.
     * @return ApiTypes.ListArray
     * @throws Exception
     */
    public ApiTypes.ListArray list(Date from, Date to) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (from != null) values.put("from", String.valueOf(from));
       if (to != null) values.put("to", String.valueOf(to));
       return uploadValues(API_URI + "/list/list", values, ApiTypes.ListArray.class);
   }

    /**
     * Returns detailed information about specific list.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param listName Name of your list.
     * @return ApiTypes.List
     * @throws Exception
     */
    public ApiTypes.List load(String listName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("listName", listName);
       return uploadValues(API_URI + "/list/load", values, ApiTypes.List.class);
   }

    /**
     * Move selected contacts from one List to another
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param oldListName The name of the list from which the contacts will be copied from
     * @param newListName The name of the list to copy the contacts to
     * @param emails Comma delimited list of contact emails
     * @param moveAll TRUE - moves all contacts; FALSE - moves contacts provided in the 'emails' parameter. This is ignored if the 'statuses' parameter has been provided
     * @param statuses List of contact statuses which are eligible to move. This ignores the 'moveAll' parameter
     * @param rule Query used for filtering.
     * @throws Exception
     */
    public void moveContacts(String oldListName, String newListName, StringArray emails, Boolean moveAll, ApiTypes.ContactStatusArray statuses, String rule) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("oldListName", oldListName);
       values.put("newListName", newListName);
       if (emails != null) values.put("emails", joinList(emails));
       values.put("moveAll", String.valueOf(moveAll));
       if (statuses != null) if (statuses != null) values.put("statuses", joinList(statuses));
       values.put("rule", rule);
       uploadValues(API_URI + "/list/movecontacts", values, VoidApiResponse.class);
   }

    /**
     * Remove selected Contacts from your list
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param listName Name of your list.
     * @param rule Query used for filtering.
     * @param emails Comma delimited list of contact emails
     * @throws Exception
     */
    public void removeContacts(String listName, String rule, StringArray emails) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("listName", listName);
       values.put("rule", rule);
       if (emails != null) values.put("emails", joinList(emails));
       uploadValues(API_URI + "/list/removecontacts", values, VoidApiResponse.class);
   }

    /**
     * Update existing list
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param listName Name of your list.
     * @param newListName Name of your list if you want to change it.
     * @param allowUnsubscribe True: Allow unsubscribing from this list. Otherwise, false
     * @throws Exception
     */
    public void update(String listName, String newListName, Boolean allowUnsubscribe) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("listName", listName);
       values.put("newListName", newListName);
       values.put("allowUnsubscribe", String.valueOf(allowUnsubscribe));
       uploadValues(API_URI + "/list/update", values, VoidApiResponse.class);
   }

}

