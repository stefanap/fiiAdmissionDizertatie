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
 * Methods to check logs of your campaigns
 */
public class Log extends API
{
    /**
     * Cancels emails that are waiting to be sent.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param channelName Name of selected channel.
     * @param transactionID ID number of transaction
     * @throws Exception
     */
    public void cancelInProgress(String channelName, String transactionID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("channelName", channelName);
       values.put("transactionID", transactionID);
       uploadValues(API_URI + "/log/cancelinprogress", values, VoidApiResponse.class);
   }

    /**
     * Export email log information to the specified file format.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param statuses List of comma separated message statuses: 0 for all, 1 for ReadyToSend, 2 for InProgress, 4 for Bounced, 5 for Sent, 6 for Opened, 7 for Clicked, 8 for Unsubscribed, 9 for Abuse Report
     * @param fileFormat Format of the exported file
     * @param from Start date.
     * @param to End date.
     * @param channelName Name of selected channel.
     * @param includeEmail True: Search includes emails. Otherwise, false.
     * @param includeSms True: Search includes SMS. Otherwise, false.
     * @param messageCategory ID of message category
     * @param compressionFormat FileResponse compression format. None or Zip.
     * @param fileName Name of your file.
     * @param email Proper email address.
     * @return ApiTypes.ExportLink
     * @throws Exception
     */
    public ApiTypes.ExportLink export(ApiTypes.LogJobStatusArray statuses, ApiTypes.ExportFileFormats fileFormat, Date from, Date to, String channelName, Boolean includeEmail, Boolean includeSms, ApiTypes.MessageCategoryArray messageCategory, ApiTypes.CompressionFormat compressionFormat, String fileName, String email) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (statuses != null) values.put("statuses", joinList(statuses));
       values.put("fileFormat", String.valueOf(fileFormat));
       if (from != null) values.put("from", String.valueOf(from));
       if (to != null) values.put("to", String.valueOf(to));
       values.put("channelName", channelName);
       values.put("includeEmail", String.valueOf(includeEmail));
       values.put("includeSms", String.valueOf(includeSms));
       if (messageCategory != null) if (messageCategory != null) values.put("messageCategory", joinList(messageCategory));
       values.put("compressionFormat", String.valueOf(compressionFormat));
       values.put("fileName", fileName);
       values.put("email", email);
       return uploadValues(API_URI + "/log/export", values, ApiTypes.ExportLink.class);
   }

    /**
     * Export detailed link tracking information to the specified file format.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param from Starting date for search in YYYY-MM-DDThh:mm:ss format.
     * @param to Ending date for search in YYYY-MM-DDThh:mm:ss format.
     * @param channelName Name of selected channel.
     * @param fileFormat Format of the exported file
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @param compressionFormat FileResponse compression format. None or Zip.
     * @param fileName Name of your file.
     * @return ApiTypes.ExportLink
     * @throws Exception
     */
    public ApiTypes.ExportLink exportLinkTracking(Date from, Date to, String channelName, ApiTypes.ExportFileFormats fileFormat, int limit, int offset, ApiTypes.CompressionFormat compressionFormat, String fileName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (from != null) values.put("from", String.valueOf(from));
       if (to != null) values.put("to", String.valueOf(to));
       values.put("channelName", channelName);
       values.put("fileFormat", String.valueOf(fileFormat));
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       values.put("compressionFormat", String.valueOf(compressionFormat));
       values.put("fileName", fileName);
       return uploadValues(API_URI + "/log/exportlinktracking", values, ApiTypes.ExportLink.class);
   }

    /**
     * Track link clicks
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param from Starting date for search in YYYY-MM-DDThh:mm:ss format.
     * @param to Ending date for search in YYYY-MM-DDThh:mm:ss format.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @param channelName Name of selected channel.
     * @return ApiTypes.LinkTrackingDetails
     * @throws Exception
     */
    public ApiTypes.LinkTrackingDetails linkTracking(Date from, Date to, int limit, int offset, String channelName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (from != null) values.put("from", String.valueOf(from));
       if (to != null) values.put("to", String.valueOf(to));
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       values.put("channelName", channelName);
       return uploadValues(API_URI + "/log/linktracking", values, ApiTypes.LinkTrackingDetails.class);
   }

    /**
     * Returns logs filtered by specified parameters.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param statuses List of comma separated message statuses: 0 for all, 1 for ReadyToSend, 2 for InProgress, 4 for Bounced, 5 for Sent, 6 for Opened, 7 for Clicked, 8 for Unsubscribed, 9 for Abuse Report
     * @param from Starting date for search in YYYY-MM-DDThh:mm:ss format.
     * @param to Ending date for search in YYYY-MM-DDThh:mm:ss format.
     * @param channelName Name of selected channel.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @param includeEmail True: Search includes emails. Otherwise, false.
     * @param includeSms True: Search includes SMS. Otherwise, false.
     * @param messageCategory ID of message category
     * @param email Proper email address.
     * @param useStatusChangeDate True, if 'from' and 'to' parameters should resolve to the Status Change date. To resolve to the creation date - false
     * @return ApiTypes.Log
     * @throws Exception
     */
    public ApiTypes.Log load(ApiTypes.LogJobStatusArray statuses, Date from, Date to, String channelName, int limit, int offset, Boolean includeEmail, Boolean includeSms, ApiTypes.MessageCategoryArray messageCategory, String email, Boolean useStatusChangeDate) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (statuses != null) values.put("statuses", joinList(statuses));
       if (from != null) values.put("from", String.valueOf(from));
       if (to != null) values.put("to", String.valueOf(to));
       values.put("channelName", channelName);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       values.put("includeEmail", String.valueOf(includeEmail));
       values.put("includeSms", String.valueOf(includeSms));
       if (messageCategory != null) if (messageCategory != null) values.put("messageCategory", joinList(messageCategory));
       values.put("email", email);
       values.put("useStatusChangeDate", String.valueOf(useStatusChangeDate));
       return uploadValues(API_URI + "/log/load", values, ApiTypes.Log.class);
   }

    /**
     * Returns notification logs filtered by specified parameters.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param statuses List of comma separated message statuses: 0 for all, 1 for ReadyToSend, 2 for InProgress, 4 for Bounced, 5 for Sent, 6 for Opened, 7 for Clicked, 8 for Unsubscribed, 9 for Abuse Report
     * @param from Starting date for search in YYYY-MM-DDThh:mm:ss format.
     * @param to Ending date for search in YYYY-MM-DDThh:mm:ss format.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @param messageCategory ID of message category
     * @param useStatusChangeDate True, if 'from' and 'to' parameters should resolve to the Status Change date. To resolve to the creation date - false
     * @param notificationType 
     * @return ApiTypes.Log
     * @throws Exception
     */
    public ApiTypes.Log loadNotifications(ApiTypes.LogJobStatusArray statuses, Date from, Date to, int limit, int offset, ApiTypes.MessageCategoryArray messageCategory, Boolean useStatusChangeDate, ApiTypes.NotificationType notificationType) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (statuses != null) values.put("statuses", joinList(statuses));
       if (from != null) values.put("from", String.valueOf(from));
       if (to != null) values.put("to", String.valueOf(to));
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       if (messageCategory != null) if (messageCategory != null) values.put("messageCategory", joinList(messageCategory));
       values.put("useStatusChangeDate", String.valueOf(useStatusChangeDate));
       values.put("notificationType", String.valueOf(notificationType));
       return uploadValues(API_URI + "/log/loadnotifications", values, ApiTypes.Log.class);
   }

    /**
     * Retry sending of temporarily not delivered message.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param msgID ID number of selected message.
     * @throws Exception
     */
    public void retryNow(String msgID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("msgID", msgID);
       uploadValues(API_URI + "/log/retrynow", values, VoidApiResponse.class);
   }

    /**
     * Loads summary information about activity in chosen date range.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param from Starting date for search in YYYY-MM-DDThh:mm:ss format.
     * @param to Ending date for search in YYYY-MM-DDThh:mm:ss format.
     * @param channelName Name of selected channel.
     * @param interval 'Hourly' for detailed information, 'summary' for daily overview
     * @param transactionID ID number of transaction
     * @return ApiTypes.LogSummary
     * @throws Exception
     */
    public ApiTypes.LogSummary summary(Date from, Date to, String channelName, ApiTypes.IntervalType interval, String transactionID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("from", String.valueOf(from));
       values.put("to", String.valueOf(to));
       values.put("channelName", channelName);
       values.put("interval", String.valueOf(interval));
       values.put("transactionID", transactionID);
       return uploadValues(API_URI + "/log/summary", values, ApiTypes.LogSummary.class);
   }

}

