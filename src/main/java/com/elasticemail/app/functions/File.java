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
 * Manage the files on your account
 */
public class File extends API
{
    /**
     * Permanently deletes the file from your account
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param fileID 
     * @param filename Name of your file.
     * @throws Exception
     */
    public void delete(int fileID, String filename) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("fileID", String.valueOf(fileID));
       values.put("filename", filename);
       uploadValues(API_URI + "/file/delete", values, VoidApiResponse.class);
   }

    /**
     * Gets content of the chosen File
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param filename Name of your file.
     * @param fileID 
     * @return FileData
     * @throws Exception
     */
    public FileData download(String filename, int fileID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("filename", filename);
       values.put("fileID", String.valueOf(fileID));
       return uploadValues(API_URI + "/file/download", values, FileData.class);
   }

    /**
     * Lists your available Attachments in the given email
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param msgID ID number of selected message.
     * @return ApiTypes.FileArray
     * @throws Exception
     */
    public ApiTypes.FileArray list(String msgID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("msgID", msgID);
       return uploadValues(API_URI + "/file/list", values, ApiTypes.FileArray.class);
   }

    /**
     * Lists all your available files
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.FileArray
     * @throws Exception
     */
    public ApiTypes.FileArray listAll() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/file/listall", values, ApiTypes.FileArray.class);
   }

    /**
     * Gets chosen File
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param filename Name of your file.
     * @return ApiTypes.File
     * @throws Exception
     */
    public ApiTypes.File load(String filename) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("filename", filename);
       return uploadValues(API_URI + "/file/load", values, ApiTypes.File.class);
   }

    /**
     * Uploads selected file to the server using http form upload format (MIME multipart/form-data) or PUT method.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param file 
     * @param name Filename
     * @param expiresAfterDays After how many days should the file be deleted.
     * @return ApiTypes.File
     * @throws Exception
     */
    public ApiTypes.File upload(FileData file, String name, int expiresAfterDays) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("name", name);
       values.put("expiresAfterDays", String.valueOf(expiresAfterDays));
       return httpPostFile(API_URI + "/file/upload", Arrays.asList(file), values, ApiTypes.File.class);
   }

}

