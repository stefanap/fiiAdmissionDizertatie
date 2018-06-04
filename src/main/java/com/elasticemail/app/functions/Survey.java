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
 * Methods to organize and get results of your surveys
 */
public class Survey extends API
{
    /**
     * Adds a new survey
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param survey Json representation of a survey
     * @return ApiTypes.Survey
     * @throws Exception
     */
    public ApiTypes.Survey add(ApiTypes.Survey survey) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("survey", String.valueOf(survey));
       return uploadValues(API_URI + "/survey/add", values, ApiTypes.Survey.class);
   }

    /**
     * Deletes the survey
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param publicSurveyID Survey identifier
     * @throws Exception
     */
    public void delete(UUID publicSurveyID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("publicSurveyID", String.valueOf(publicSurveyID));
       uploadValues(API_URI + "/survey/delete", values, VoidApiResponse.class);
   }

    /**
     * Export given survey's data to provided format
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param publicSurveyID Survey identifier
     * @param fileName Name of your file.
     * @param fileFormat Format of the exported file
     * @param compressionFormat FileResponse compression format. None or Zip.
     * @return ApiTypes.ExportLink
     * @throws Exception
     */
    public ApiTypes.ExportLink export(UUID publicSurveyID, String fileName, ApiTypes.ExportFileFormats fileFormat, ApiTypes.CompressionFormat compressionFormat) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("publicSurveyID", String.valueOf(publicSurveyID));
       values.put("fileName", fileName);
       values.put("fileFormat", String.valueOf(fileFormat));
       values.put("compressionFormat", String.valueOf(compressionFormat));
       return uploadValues(API_URI + "/survey/export", values, ApiTypes.ExportLink.class);
   }

    /**
     * Shows all your existing surveys
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.SurveyArray
     * @throws Exception
     */
    public ApiTypes.SurveyArray list() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/survey/list", values, ApiTypes.SurveyArray.class);
   }

    /**
     * Get list of personal answers for the specific survey
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param publicSurveyID Survey identifier
     * @return ApiTypes.SurveyResultInfoArray
     * @throws Exception
     */
    public ApiTypes.SurveyResultInfoArray loadResponseList(UUID publicSurveyID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("publicSurveyID", String.valueOf(publicSurveyID));
       return uploadValues(API_URI + "/survey/loadresponselist", values, ApiTypes.SurveyResultInfoArray.class);
   }

    /**
     * Get general results of the specific survey
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param publicSurveyID Survey identifier
     * @return ApiTypes.SurveyResultsSummaryInfo
     * @throws Exception
     */
    public ApiTypes.SurveyResultsSummaryInfo loadResults(UUID publicSurveyID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("publicSurveyID", String.valueOf(publicSurveyID));
       return uploadValues(API_URI + "/survey/loadresults", values, ApiTypes.SurveyResultsSummaryInfo.class);
   }

    /**
     * Update the survey information
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param survey Json representation of a survey
     * @return ApiTypes.Survey
     * @throws Exception
     */
    public ApiTypes.Survey update(ApiTypes.Survey survey) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("survey", String.valueOf(survey));
       return uploadValues(API_URI + "/survey/update", values, ApiTypes.Survey.class);
   }

}

