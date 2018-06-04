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
 * Sending and monitoring progress of your Campaigns
 */
public class Campaign extends API
{
    /**
     * Adds a campaign to the queue for processing based on the configuration
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param campaign Json representation of a campaign
     * @return int
     * @throws Exception
     */
    public int add(ApiTypes.Campaign campaign) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("campaign", String.valueOf(campaign));
       return uploadValues(API_URI + "/campaign/add", values, int.class);
   }

    /**
     * Copy selected campaign
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param channelID ID number of selected Channel.
     * @return int
     * @throws Exception
     */
    public int copy(int channelID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("channelID", String.valueOf(channelID));
       return uploadValues(API_URI + "/campaign/copy", values, int.class);
   }

    /**
     * Delete selected campaign
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param channelID ID number of selected Channel.
     * @throws Exception
     */
    public void delete(int channelID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("channelID", String.valueOf(channelID));
       uploadValues(API_URI + "/campaign/delete", values, VoidApiResponse.class);
   }

    /**
     * Export selected campaigns to chosen file format.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param channelIDs List of campaign IDs used for processing
     * @param fileFormat Format of the exported file
     * @param compressionFormat FileResponse compression format. None or Zip.
     * @param fileName Name of your file.
     * @return ApiTypes.ExportLink
     * @throws Exception
     */
    public ApiTypes.ExportLink export(Iterable<Integer> channelIDs, ApiTypes.ExportFileFormats fileFormat, ApiTypes.CompressionFormat compressionFormat, String fileName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (channelIDs != null) values.put("channelIDs", joinList(channelIDs));
       values.put("fileFormat", String.valueOf(fileFormat));
       values.put("compressionFormat", String.valueOf(compressionFormat));
       values.put("fileName", fileName);
       return uploadValues(API_URI + "/campaign/export", values, ApiTypes.ExportLink.class);
   }

    /**
     * List all of your campaigns
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param search Text fragment used for searching.
     * @param offset How many items should be loaded ahead.
     * @param limit Maximum of loaded items.
     * @return ApiTypes.CampaignChannelArray
     * @throws Exception
     */
    public ApiTypes.CampaignChannelArray list(String search, int offset, int limit) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("search", search);
       values.put("offset", String.valueOf(offset));
       values.put("limit", String.valueOf(limit));
       return uploadValues(API_URI + "/campaign/list", values, ApiTypes.CampaignChannelArray.class);
   }

    /**
     * Updates a previously added campaign.  Only Active and Paused campaigns can be updated.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param campaign Json representation of a campaign
     * @return int
     * @throws Exception
     */
    public int update(ApiTypes.Campaign campaign) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("campaign", String.valueOf(campaign));
       return uploadValues(API_URI + "/campaign/update", values, int.class);
   }

}

