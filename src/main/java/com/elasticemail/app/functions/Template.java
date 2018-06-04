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
 * Managing and editing templates of your emails
 */
public class Template extends API
{
    /**
     * Create new Template. Needs to be sent using POST method
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param name Filename
     * @param subject Default subject of email.
     * @param fromEmail Default From: email address.
     * @param fromName Default From: name.
     * @param templateType 0 for API connections
     * @param templateScope Enum: 0 - private, 1 - public, 2 - mockup
     * @param bodyHtml HTML code of email (needs escaping).
     * @param bodyText Text body of email.
     * @param css CSS style
     * @param originalTemplateID ID number of original template.
     * @return int
     * @throws Exception
     */
    public int add(String name, String subject, String fromEmail, String fromName, ApiTypes.TemplateType templateType, ApiTypes.TemplateScope templateScope, String bodyHtml, String bodyText, String css, int originalTemplateID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("name", name);
       values.put("subject", subject);
       values.put("fromEmail", fromEmail);
       values.put("fromName", fromName);
       values.put("templateType", String.valueOf(templateType));
       values.put("templateScope", String.valueOf(templateScope));
       values.put("bodyHtml", bodyHtml);
       values.put("bodyText", bodyText);
       values.put("css", css);
       values.put("originalTemplateID", String.valueOf(originalTemplateID));
       return uploadValues(API_URI + "/template/add", values, int.class);
   }

    /**
     * Check if template is used by campaign.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param templateID ID number of template.
     * @return Boolean
     * @throws Exception
     */
    public Boolean checkUsage(int templateID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("templateID", String.valueOf(templateID));
       return uploadValues(API_URI + "/template/checkusage", values, Boolean.class);
   }

    /**
     * Copy Selected Template
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param templateID ID number of template.
     * @param name Filename
     * @param subject Default subject of email.
     * @param fromEmail Default From: email address.
     * @param fromName Default From: name.
     * @return ApiTypes.Template
     * @throws Exception
     */
    public ApiTypes.Template copy(int templateID, String name, String subject, String fromEmail, String fromName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("templateID", String.valueOf(templateID));
       values.put("name", name);
       values.put("subject", subject);
       values.put("fromEmail", fromEmail);
       values.put("fromName", fromName);
       return uploadValues(API_URI + "/template/copy", values, ApiTypes.Template.class);
   }

    /**
     * Delete template with the specified ID
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param templateID ID number of template.
     * @throws Exception
     */
    public void delete(int templateID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("templateID", String.valueOf(templateID));
       uploadValues(API_URI + "/template/delete", values, VoidApiResponse.class);
   }

    /**
     * Search for references to images and replaces them with base64 code.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param templateID ID number of template.
     * @return String
     * @throws Exception
     */
    public String getEmbeddedHtml(int templateID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("templateID", String.valueOf(templateID));
       return uploadValues(API_URI + "/template/getembeddedhtml", values, String.class);
   }

    /**
     * Lists your templates
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @return ApiTypes.TemplateList
     * @throws Exception
     */
    public ApiTypes.TemplateList getList(int limit, int offset) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       return uploadValues(API_URI + "/template/getlist", values, ApiTypes.TemplateList.class);
   }

    /**
     * Load template with content
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param templateID ID number of template.
     * @param ispublic 
     * @return ApiTypes.Template
     * @throws Exception
     */
    public ApiTypes.Template loadTemplate(int templateID, Boolean ispublic) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("templateID", String.valueOf(templateID));
       values.put("ispublic", String.valueOf(ispublic));
       return uploadValues(API_URI + "/template/loadtemplate", values, ApiTypes.Template.class);
   }

    /**
     * Removes previously generated screenshot of template
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param templateID ID number of template.
     * @throws Exception
     */
    public void removeScreenshot(int templateID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("templateID", String.valueOf(templateID));
       uploadValues(API_URI + "/template/removescreenshot", values, VoidApiResponse.class);
   }

    /**
     * Saves screenshot of chosen Template
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param base64Image Image, base64 coded.
     * @param templateID ID number of template.
     * @return String
     * @throws Exception
     */
    public String saveScreenshot(String base64Image, int templateID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("base64Image", base64Image);
       values.put("templateID", String.valueOf(templateID));
       return uploadValues(API_URI + "/template/savescreenshot", values, String.class);
   }

    /**
     * Update existing template, overwriting existing data. Needs to be sent using POST method.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param templateID ID number of template.
     * @param templateScope Enum: 0 - private, 1 - public, 2 - mockup
     * @param name Filename
     * @param subject Default subject of email.
     * @param fromEmail Default From: email address.
     * @param fromName Default From: name.
     * @param bodyHtml HTML code of email (needs escaping).
     * @param bodyText Text body of email.
     * @param css CSS style
     * @param removeScreenshot 
     * @throws Exception
     */
    public void update(int templateID, ApiTypes.TemplateScope templateScope, String name, String subject, String fromEmail, String fromName, String bodyHtml, String bodyText, String css, Boolean removeScreenshot) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("templateID", String.valueOf(templateID));
       values.put("templateScope", String.valueOf(templateScope));
       values.put("name", name);
       values.put("subject", subject);
       values.put("fromEmail", fromEmail);
       values.put("fromName", fromName);
       values.put("bodyHtml", bodyHtml);
       values.put("bodyText", bodyText);
       values.put("css", css);
       values.put("removeScreenshot", String.valueOf(removeScreenshot));
       uploadValues(API_URI + "/template/update", values, VoidApiResponse.class);
   }

}

