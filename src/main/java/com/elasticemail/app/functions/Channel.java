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
 * SMTP and HTTP API channels for grouping email delivery.
 */
public class Channel extends API
{
    /**
     * Manually add a channel to your account to group email
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param name Descriptive name of the channel
     * @return String
     * @throws Exception
     */
    public String add(String name) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("name", name);
       return uploadValues(API_URI + "/channel/add", values, String.class);
   }

    /**
     * Delete the channel.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param name The name of the channel to delete.
     * @throws Exception
     */
    public void delete(String name) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("name", name);
       uploadValues(API_URI + "/channel/delete", values, VoidApiResponse.class);
   }

    /**
     * Export channels in CSV file format.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param channelNames List of channel names used for processing
     * @param compressionFormat FileResponse compression format. None or Zip.
     * @param fileName Name of your file.
     * @return FileData
     * @throws Exception
     */
    public FileData exportCsv(StringArray channelNames, ApiTypes.CompressionFormat compressionFormat, String fileName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (channelNames != null) values.put("channelNames", joinList(channelNames));
       values.put("compressionFormat", String.valueOf(compressionFormat));
       values.put("fileName", fileName);
       return uploadValues(API_URI + "/channel/exportcsv", values, FileData.class);
   }

    /**
     * Export channels in JSON file format.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param channelNames List of channel names used for processing
     * @param compressionFormat FileResponse compression format. None or Zip.
     * @param fileName Name of your file.
     * @return FileData
     * @throws Exception
     */
    public FileData exportJson(StringArray channelNames, ApiTypes.CompressionFormat compressionFormat, String fileName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (channelNames != null) values.put("channelNames", joinList(channelNames));
       values.put("compressionFormat", String.valueOf(compressionFormat));
       values.put("fileName", fileName);
       return uploadValues(API_URI + "/channel/exportjson", values, FileData.class);
   }

    /**
     * Export channels in XML file format.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param channelNames List of channel names used for processing
     * @param compressionFormat FileResponse compression format. None or Zip.
     * @param fileName Name of your file.
     * @return FileData
     * @throws Exception
     */
    public FileData exportXml(StringArray channelNames, ApiTypes.CompressionFormat compressionFormat, String fileName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       if (channelNames != null) values.put("channelNames", joinList(channelNames));
       values.put("compressionFormat", String.valueOf(compressionFormat));
       values.put("fileName", fileName);
       return uploadValues(API_URI + "/channel/exportxml", values, FileData.class);
   }

    /**
     * List all of your channels
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.ChannelArray
     * @throws Exception
     */
    public ApiTypes.ChannelArray list() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/channel/list", values, ApiTypes.ChannelArray.class);
   }

    /**
     * Rename an existing channel.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param name The name of the channel to update.
     * @param newName The new name for the channel.
     * @return String
     * @throws Exception
     */
    public String update(String name, String newName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("name", name);
       values.put("newName", newName);
       return uploadValues(API_URI + "/channel/update", values, String.class);
   }

}

