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
 * Methods for managing your account and subaccounts.
 */
public class Account extends API
{
    /**
     * Create new subaccount and provide most important data about it.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param email Proper email address.
     * @param password Current password.
     * @param confirmPassword Repeat new password.
     * @param requiresEmailCredits True, if account needs credits to send emails. Otherwise, false
     * @param enableLitmusTest True, if account is able to send template tests to Litmus. Otherwise, false
     * @param requiresLitmusCredits True, if account needs credits to send emails. Otherwise, false
     * @param maxContacts Maximum number of contacts the account can have
     * @param enablePrivateIPRequest True, if account can request for private IP on its own. Otherwise, false
     * @param sendActivation True, if you want to send activation email to this account. Otherwise, false
     * @param returnUrl URL to navigate to after account creation
     * @param sendingPermission Sending permission setting for account
     * @param enableContactFeatures True, if you want to use Contact Delivery Tools.  Otherwise, false
     * @param poolName Private IP required. Name of the custom IP Pool which Sub Account should use to send its emails. Leave empty for the default one or if no Private IPs have been bought
     * @param emailSizeLimit Maximum size of email including attachments in MB's
     * @param dailySendLimit Amount of emails account can send daily
     * @return String
     * @throws Exception
     */
    public String addSubAccount(String email, String password, String confirmPassword, Boolean requiresEmailCredits, Boolean enableLitmusTest, Boolean requiresLitmusCredits, int maxContacts, Boolean enablePrivateIPRequest, Boolean sendActivation, String returnUrl, ApiTypes.SendingPermission sendingPermission, Boolean enableContactFeatures, String poolName, int emailSizeLimit, int dailySendLimit) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("email", email);
       values.put("password", password);
       values.put("confirmPassword", confirmPassword);
       values.put("requiresEmailCredits", String.valueOf(requiresEmailCredits));
       values.put("enableLitmusTest", String.valueOf(enableLitmusTest));
       values.put("requiresLitmusCredits", String.valueOf(requiresLitmusCredits));
       values.put("maxContacts", String.valueOf(maxContacts));
       values.put("enablePrivateIPRequest", String.valueOf(enablePrivateIPRequest));
       values.put("sendActivation", String.valueOf(sendActivation));
       values.put("returnUrl", returnUrl);
       if (sendingPermission != null) values.put("sendingPermission", String.valueOf(sendingPermission));
       values.put("enableContactFeatures", String.valueOf(enableContactFeatures));
       values.put("poolName", poolName);
       values.put("emailSizeLimit", String.valueOf(emailSizeLimit));
       values.put("dailySendLimit", String.valueOf(dailySendLimit));
       return uploadValues(API_URI + "/account/addsubaccount", values, String.class);
   }

    /**
     * Add email, template or litmus credits to a sub-account
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param credits Amount of credits to add
     * @param notes Specific notes about the transaction
     * @param creditType Type of credits to add (Email or Litmus)
     * @param subAccountEmail Email address of sub-account
     * @param publicAccountID Public key of sub-account to add credits to. Use subAccountEmail or publicAccountID not both.
     * @throws Exception
     */
    public void addSubAccountCredits(int credits, String notes, ApiTypes.CreditType creditType, String subAccountEmail, String publicAccountID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("credits", String.valueOf(credits));
       values.put("notes", notes);
       values.put("creditType", String.valueOf(creditType));
       values.put("subAccountEmail", subAccountEmail);
       values.put("publicAccountID", publicAccountID);
       uploadValues(API_URI + "/account/addsubaccountcredits", values, VoidApiResponse.class);
   }

    /**
     * Change your email address. Remember, that your email address is used as login!
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param newEmail New email address.
     * @param confirmEmail New email address.
     * @param sourceUrl URL from which request was sent.
     * @return String
     * @throws Exception
     */
    public String changeEmail(String newEmail, String confirmEmail, String sourceUrl) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("newEmail", newEmail);
       values.put("confirmEmail", confirmEmail);
       values.put("sourceUrl", sourceUrl);
       return uploadValues(API_URI + "/account/changeemail", values, String.class);
   }

    /**
     * Create new password for your account. Password needs to be at least 6 characters long.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param currentPassword Current password.
     * @param newPassword New password for account.
     * @param confirmPassword Repeat new password.
     * @throws Exception
     */
    public void changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("currentPassword", currentPassword);
       values.put("newPassword", newPassword);
       values.put("confirmPassword", confirmPassword);
       uploadValues(API_URI + "/account/changepassword", values, VoidApiResponse.class);
   }

    /**
     * Deletes specified Subaccount
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param notify True, if you want to send an email notification. Otherwise, false
     * @param subAccountEmail Email address of sub-account
     * @param publicAccountID Public key of sub-account to delete. Use subAccountEmail or publicAccountID not both.
     * @param deleteDomains 
     * @throws Exception
     */
    public void deleteSubAccount(Boolean notify, String subAccountEmail, String publicAccountID, Boolean deleteDomains) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("notify", String.valueOf(notify));
       values.put("subAccountEmail", subAccountEmail);
       values.put("publicAccountID", publicAccountID);
       values.put("deleteDomains", String.valueOf(deleteDomains));
       uploadValues(API_URI + "/account/deletesubaccount", values, VoidApiResponse.class);
   }

    /**
     * Validate account's ability to send e-mail
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.AccountSendStatus
     * @throws Exception
     */
    public ApiTypes.AccountSendStatus getAccountAbilityToSendEmail() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/getaccountabilitytosendemail", values, ApiTypes.AccountSendStatus.class);
   }

    /**
     * Returns API Key for the given Sub Account.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param subAccountEmail Email address of sub-account
     * @param publicAccountID Public key of sub-account to retrieve sub-account API Key. Use subAccountEmail or publicAccountID not both.
     * @return String
     * @throws Exception
     */
    public String getSubAccountApiKey(String subAccountEmail, String publicAccountID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("subAccountEmail", subAccountEmail);
       values.put("publicAccountID", publicAccountID);
       return uploadValues(API_URI + "/account/getsubaccountapikey", values, String.class);
   }

    /**
     * Lists all of your subaccounts
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @return ApiTypes.SubAccountArray
     * @throws Exception
     */
    public ApiTypes.SubAccountArray getSubAccountList(int limit, int offset) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       return uploadValues(API_URI + "/account/getsubaccountlist", values, ApiTypes.SubAccountArray.class);
   }

    /**
     * Loads your account. Returns detailed information about your account.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.Account
     * @throws Exception
     */
    public ApiTypes.Account load() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/load", values, ApiTypes.Account.class);
   }

    /**
     * Load advanced options of your account
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.AdvancedOptions
     * @throws Exception
     */
    public ApiTypes.AdvancedOptions loadAdvancedOptions() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/loadadvancedoptions", values, ApiTypes.AdvancedOptions.class);
   }

    /**
     * Lists email credits history
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.EmailCreditsArray
     * @throws Exception
     */
    public ApiTypes.EmailCreditsArray loadEmailCreditsHistory() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/loademailcreditshistory", values, ApiTypes.EmailCreditsArray.class);
   }

    /**
     * Loads your account. Returns detailed information about your account.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.Account
     * @throws Exception
     */
    public ApiTypes.Account loadInfo() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/loadinfo", values, ApiTypes.Account.class);
   }

    /**
     * Lists litmus credits history
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.LitmusCreditsArray
     * @throws Exception
     */
    public ApiTypes.LitmusCreditsArray loadLitmusCreditsHistory() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/loadlitmuscreditshistory", values, ApiTypes.LitmusCreditsArray.class);
   }

    /**
     * Shows queue of newest notifications - very useful when you want to check what happened with mails that were not received.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.NotificationQueueArray
     * @throws Exception
     */
    public ApiTypes.NotificationQueueArray loadNotificationQueue() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/loadnotificationqueue", values, ApiTypes.NotificationQueueArray.class);
   }

    /**
     * Lists all payments
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @param fromDate Starting date for search in YYYY-MM-DDThh:mm:ss format.
     * @param toDate Ending date for search in YYYY-MM-DDThh:mm:ss format.
     * @return ApiTypes.PaymentArray
     * @throws Exception
     */
    public ApiTypes.PaymentArray loadPaymentHistory(int limit, int offset, Date fromDate, Date toDate) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       values.put("fromDate", String.valueOf(fromDate));
       values.put("toDate", String.valueOf(toDate));
       return uploadValues(API_URI + "/account/loadpaymenthistory", values, ApiTypes.PaymentArray.class);
   }

    /**
     * Lists all referral payout history
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.PaymentArray
     * @throws Exception
     */
    public ApiTypes.PaymentArray loadPayoutHistory() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/loadpayouthistory", values, ApiTypes.PaymentArray.class);
   }

    /**
     * Shows information about your referral details
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.Referral
     * @throws Exception
     */
    public ApiTypes.Referral loadReferralDetails() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/loadreferraldetails", values, ApiTypes.Referral.class);
   }

    /**
     * Shows latest changes in your sending reputation
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @return ApiTypes.ReputationHistoryArray
     * @throws Exception
     */
    public ApiTypes.ReputationHistoryArray loadReputationHistory(int limit, int offset) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       return uploadValues(API_URI + "/account/loadreputationhistory", values, ApiTypes.ReputationHistoryArray.class);
   }

    /**
     * Shows detailed information about your actual reputation score
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.ReputationDetail
     * @throws Exception
     */
    public ApiTypes.ReputationDetail loadReputationImpact() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/loadreputationimpact", values, ApiTypes.ReputationDetail.class);
   }

    /**
     * Returns detailed spam check.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param limit Maximum of loaded items.
     * @param offset How many items should be loaded ahead.
     * @return ApiTypes.SpamCheckArray
     * @throws Exception
     */
    public ApiTypes.SpamCheckArray loadSpamCheck(int limit, int offset) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("limit", String.valueOf(limit));
       values.put("offset", String.valueOf(offset));
       return uploadValues(API_URI + "/account/loadspamcheck", values, ApiTypes.SpamCheckArray.class);
   }

    /**
     * Lists email credits history for sub-account
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param subAccountEmail Email address of sub-account
     * @param publicAccountID Public key of sub-account to list history for. Use subAccountEmail or publicAccountID not both.
     * @return ApiTypes.EmailCreditsArray
     * @throws Exception
     */
    public ApiTypes.EmailCreditsArray loadSubAccountsEmailCreditsHistory(String subAccountEmail, String publicAccountID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("subAccountEmail", subAccountEmail);
       values.put("publicAccountID", publicAccountID);
       return uploadValues(API_URI + "/account/loadsubaccountsemailcreditshistory", values, ApiTypes.EmailCreditsArray.class);
   }

    /**
     * Loads settings of subaccount
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param subAccountEmail Email address of sub-account
     * @param publicAccountID Public key of sub-account to load settings for. Use subAccountEmail or publicAccountID not both.
     * @return ApiTypes.SubAccountSettings
     * @throws Exception
     */
    public ApiTypes.SubAccountSettings loadSubAccountSettings(String subAccountEmail, String publicAccountID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("subAccountEmail", subAccountEmail);
       values.put("publicAccountID", publicAccountID);
       return uploadValues(API_URI + "/account/loadsubaccountsettings", values, ApiTypes.SubAccountSettings.class);
   }

    /**
     * Lists litmus credits history for sub-account
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param subAccountEmail Email address of sub-account
     * @param publicAccountID Public key of sub-account to list history for. Use subAccountEmail or publicAccountID not both.
     * @return ApiTypes.LitmusCreditsArray
     * @throws Exception
     */
    public ApiTypes.LitmusCreditsArray loadSubAccountsLitmusCreditsHistory(String subAccountEmail, String publicAccountID) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("subAccountEmail", subAccountEmail);
       values.put("publicAccountID", publicAccountID);
       return uploadValues(API_URI + "/account/loadsubaccountslitmuscreditshistory", values, ApiTypes.LitmusCreditsArray.class);
   }

    /**
     * Shows usage of your account in given time.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param from Starting date for search in YYYY-MM-DDThh:mm:ss format.
     * @param to Ending date for search in YYYY-MM-DDThh:mm:ss format.
     * @return ApiTypes.UsageArray
     * @throws Exception
     */
    public ApiTypes.UsageArray loadUsage(Date from, Date to) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("from", String.valueOf(from));
       values.put("to", String.valueOf(to));
       return uploadValues(API_URI + "/account/loadusage", values, ApiTypes.UsageArray.class);
   }

    /**
     * Shows summary for your account.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.AccountOverview
     * @throws Exception
     */
    public ApiTypes.AccountOverview overview() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/overview", values, ApiTypes.AccountOverview.class);
   }

    /**
     * Shows you account's profile basic overview
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return ApiTypes.Profile
     * @throws Exception
     */
    public ApiTypes.Profile profileOverview() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/profileoverview", values, ApiTypes.Profile.class);
   }

    /**
     * Remove email, template or litmus credits from a sub-account
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param creditType Type of credits to add (Email or Litmus)
     * @param notes Specific notes about the transaction
     * @param subAccountEmail Email address of sub-account
     * @param publicAccountID Public key of sub-account to remove credits from. Use subAccountEmail or publicAccountID not both.
     * @param credits Amount of credits to remove
     * @param removeAll Remove all credits of this type from sub-account (overrides credits if provided)
     * @throws Exception
     */
    public void removeSubAccountCredits(ApiTypes.CreditType creditType, String notes, String subAccountEmail, String publicAccountID, int credits, Boolean removeAll) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("creditType", String.valueOf(creditType));
       values.put("notes", notes);
       values.put("subAccountEmail", subAccountEmail);
       values.put("publicAccountID", publicAccountID);
       values.put("credits", String.valueOf(credits));
       values.put("removeAll", String.valueOf(removeAll));
       uploadValues(API_URI + "/account/removesubaccountcredits", values, VoidApiResponse.class);
   }

    /**
     * Request a new default APIKey.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @return String
     * @throws Exception
     */
    public String requestNewApiKey() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       return uploadValues(API_URI + "/account/requestnewapikey", values, String.class);
   }

    /**
     * Request premium support for your account
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @throws Exception
     */
    public void requestPremiumSupport() throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       uploadValues(API_URI + "/account/requestpremiumsupport", values, VoidApiResponse.class);
   }

    /**
     * Request a private IP for your Account
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param count Number of items.
     * @param notes Free form field of notes
     * @throws Exception
     */
    public void requestPrivateIP(int count, String notes) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("count", String.valueOf(count));
       values.put("notes", notes);
       uploadValues(API_URI + "/account/requestprivateip", values, VoidApiResponse.class);
   }

    /**
     * Update sending and tracking options of your account.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param enableClickTracking True, if you want to track clicks. Otherwise, false
     * @param enableLinkClickTracking True, if you want to track by link tracking. Otherwise, false
     * @param manageSubscriptions True, if you want to display your labels on your unsubscribe form. Otherwise, false
     * @param manageSubscribedOnly True, if you want to only display labels that the contact is subscribed to on your unsubscribe form. Otherwise, false
     * @param transactionalOnUnsubscribe True, if you want to display an option for the contact to opt into transactional email only on your unsubscribe form. Otherwise, false
     * @param skipListUnsubscribe True, if you do not want to use list-unsubscribe headers. Otherwise, false
     * @param autoTextFromHtml True, if text BODY of message should be created automatically. Otherwise, false
     * @param allowCustomHeaders True, if you want to apply custom headers to your emails. Otherwise, false
     * @param bccEmail Email address to send a copy of all email to.
     * @param contentTransferEncoding Type of content encoding
     * @param emailNotificationForError True, if you want bounce notifications returned. Otherwise, false
     * @param emailNotificationEmail Specific email address to send bounce email notifications to.
     * @param webNotificationUrl URL address to receive web notifications to parse and process.
     * @param webNotificationNotifyOncePerEmail True, if you want to receive notifications for each type only once per email. Otherwise, false
     * @param webNotificationForSent True, if you want to send web notifications for sent email. Otherwise, false
     * @param webNotificationForOpened True, if you want to send web notifications for opened email. Otherwise, false
     * @param webNotificationForClicked True, if you want to send web notifications for clicked email. Otherwise, false
     * @param webNotificationForUnsubscribed True, if you want to send web notifications for unsubscribed email. Otherwise, false
     * @param webNotificationForAbuseReport True, if you want to send web notifications for complaint email. Otherwise, false
     * @param webNotificationForError True, if you want to send web notifications for bounced email. Otherwise, false
     * @param hubCallBackUrl URL used for tracking action of inbound emails
     * @param inboundDomain Domain you use as your inbound domain
     * @param inboundContactsOnly True, if you want inbound email to only process contacts from your account. Otherwise, false
     * @param lowCreditNotification True, if you want to receive low credit email notifications. Otherwise, false
     * @param enableUITooltips True, if account has tooltips active. Otherwise, false
     * @param enableContactFeatures True, if you want to use Contact Delivery Tools.  Otherwise, false
     * @param notificationsEmails Email addresses to send a copy of all notifications from our system. Separated by semicolon
     * @param unsubscribeNotificationsEmails Emails, separated by semicolon, to which the notification about contact unsubscribing should be sent to
     * @param logoUrl URL to your logo image.
     * @param enableTemplateScripting True, if you want to use template scripting in your emails {{}}. Otherwise, false
     * @param staleContactScore (0 means this functionality is NOT enabled) Score, depending on the number of times you have sent to a recipient, at which the given recipient should be moved to the Stale status
     * @param staleContactInactiveDays (0 means this functionality is NOT enabled) Number of days of inactivity for a contact after which the given recipient should be moved to the Stale status
     * @param deliveryReason Why your clients are receiving your emails.
     * @param tutorialsEnabled True, if you want to enable Dashboard Tutotials
     * @param enableOpenTracking True, if you want to track opens. Otherwise, false
     * @param consentTrackingOnUnsubscribe 
     * @return ApiTypes.AdvancedOptions
     * @throws Exception
     */
    public ApiTypes.AdvancedOptions updateAdvancedOptions(Boolean enableClickTracking, Boolean enableLinkClickTracking, Boolean manageSubscriptions, Boolean manageSubscribedOnly, Boolean transactionalOnUnsubscribe, Boolean skipListUnsubscribe, Boolean autoTextFromHtml, Boolean allowCustomHeaders, String bccEmail, String contentTransferEncoding, Boolean emailNotificationForError, String emailNotificationEmail, String webNotificationUrl, Boolean webNotificationNotifyOncePerEmail, Boolean webNotificationForSent, Boolean webNotificationForOpened, Boolean webNotificationForClicked, Boolean webNotificationForUnsubscribed, Boolean webNotificationForAbuseReport, Boolean webNotificationForError, String hubCallBackUrl, String inboundDomain, Boolean inboundContactsOnly, Boolean lowCreditNotification, Boolean enableUITooltips, Boolean enableContactFeatures, String notificationsEmails, String unsubscribeNotificationsEmails, String logoUrl, Boolean enableTemplateScripting, int staleContactScore, int staleContactInactiveDays, String deliveryReason, Boolean tutorialsEnabled, Boolean enableOpenTracking, Boolean consentTrackingOnUnsubscribe) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("enableClickTracking", String.valueOf(enableClickTracking));
       values.put("enableLinkClickTracking", String.valueOf(enableLinkClickTracking));
       values.put("manageSubscriptions", String.valueOf(manageSubscriptions));
       values.put("manageSubscribedOnly", String.valueOf(manageSubscribedOnly));
       values.put("transactionalOnUnsubscribe", String.valueOf(transactionalOnUnsubscribe));
       values.put("skipListUnsubscribe", String.valueOf(skipListUnsubscribe));
       values.put("autoTextFromHtml", String.valueOf(autoTextFromHtml));
       values.put("allowCustomHeaders", String.valueOf(allowCustomHeaders));
       values.put("bccEmail", bccEmail);
       values.put("contentTransferEncoding", contentTransferEncoding);
       values.put("emailNotificationForError", String.valueOf(emailNotificationForError));
       values.put("emailNotificationEmail", emailNotificationEmail);
       values.put("webNotificationUrl", webNotificationUrl);
       values.put("webNotificationNotifyOncePerEmail", String.valueOf(webNotificationNotifyOncePerEmail));
       values.put("webNotificationForSent", String.valueOf(webNotificationForSent));
       values.put("webNotificationForOpened", String.valueOf(webNotificationForOpened));
       values.put("webNotificationForClicked", String.valueOf(webNotificationForClicked));
       values.put("webNotificationForUnsubscribed", String.valueOf(webNotificationForUnsubscribed));
       values.put("webNotificationForAbuseReport", String.valueOf(webNotificationForAbuseReport));
       values.put("webNotificationForError", String.valueOf(webNotificationForError));
       values.put("hubCallBackUrl", hubCallBackUrl);
       values.put("inboundDomain", inboundDomain);
       values.put("inboundContactsOnly", String.valueOf(inboundContactsOnly));
       values.put("lowCreditNotification", String.valueOf(lowCreditNotification));
       values.put("enableUITooltips", String.valueOf(enableUITooltips));
       values.put("enableContactFeatures", String.valueOf(enableContactFeatures));
       values.put("notificationsEmails", notificationsEmails);
       values.put("unsubscribeNotificationsEmails", unsubscribeNotificationsEmails);
       values.put("logoUrl", logoUrl);
       values.put("enableTemplateScripting", String.valueOf(enableTemplateScripting));
       values.put("staleContactScore", String.valueOf(staleContactScore));
       values.put("staleContactInactiveDays", String.valueOf(staleContactInactiveDays));
       values.put("deliveryReason", deliveryReason);
       values.put("tutorialsEnabled", String.valueOf(tutorialsEnabled));
       values.put("enableOpenTracking", String.valueOf(enableOpenTracking));
       values.put("consentTrackingOnUnsubscribe", String.valueOf(consentTrackingOnUnsubscribe));
       return uploadValues(API_URI + "/account/updateadvancedoptions", values, ApiTypes.AdvancedOptions.class);
   }

    /**
     * Update settings of your private branding. These settings are needed, if you want to use Elastic Email under your brand.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param enablePrivateBranding True: Turn on or off ability to send mails under your brand. Otherwise, false
     * @param logoUrl URL to your logo image.
     * @param supportLink Address to your support.
     * @param privateBrandingUrl Subdomain for your rebranded service
     * @param smtpAddress Address of SMTP server.
     * @param smtpAlternative Address of alternative SMTP server.
     * @param paymentUrl URL for making payments.
     * @throws Exception
     */
    public void updateCustomBranding(Boolean enablePrivateBranding, String logoUrl, String supportLink, String privateBrandingUrl, String smtpAddress, String smtpAlternative, String paymentUrl) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("enablePrivateBranding", String.valueOf(enablePrivateBranding));
       values.put("logoUrl", logoUrl);
       values.put("supportLink", supportLink);
       values.put("privateBrandingUrl", privateBrandingUrl);
       values.put("smtpAddress", smtpAddress);
       values.put("smtpAlternative", smtpAlternative);
       values.put("paymentUrl", paymentUrl);
       uploadValues(API_URI + "/account/updatecustombranding", values, VoidApiResponse.class);
   }

    /**
     * Update http notification URL.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param url URL of notification.
     * @param notifyOncePerEmail True, if you want to receive notifications for each type only once per email. Otherwise, false
     * @param settings Http notification settings serialized to JSON 
     * @throws Exception
     */
    public void updateHttpNotification(String url, Boolean notifyOncePerEmail, String settings) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("url", url);
       values.put("notifyOncePerEmail", String.valueOf(notifyOncePerEmail));
       values.put("settings", settings);
       uploadValues(API_URI + "/account/updatehttpnotification", values, VoidApiResponse.class);
   }

    /**
     * Update your profile.
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param firstName First name.
     * @param lastName Last name.
     * @param address1 First line of address.
     * @param city City.
     * @param state State or province.
     * @param zip Zip/postal code.
     * @param countryID Numeric ID of country. A file with the list of countries is available <a href="http://api.elasticemail.com/public/countries"><b>here</b></a>
     * @param marketingConsent True if you want to receive newsletters from Elastic Email. Otherwise, false. Empty to leave the current value.
     * @param address2 Second line of address.
     * @param company Company name.
     * @param website HTTP address of your website.
     * @param logoUrl URL to your logo image.
     * @param taxCode Code used for tax purposes.
     * @param phone Phone number
     * @throws Exception
     */
    public void updateProfile(String firstName, String lastName, String address1, String city, String state, String zip, int countryID, Boolean marketingConsent, String address2, String company, String website, String logoUrl, String taxCode, String phone) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("firstName", firstName);
       values.put("lastName", lastName);
       values.put("address1", address1);
       values.put("city", city);
       values.put("state", state);
       values.put("zip", zip);
       values.put("countryID", String.valueOf(countryID));
       values.put("marketingConsent", String.valueOf(marketingConsent));
       values.put("address2", address2);
       values.put("company", company);
       values.put("website", website);
       values.put("logoUrl", logoUrl);
       values.put("taxCode", taxCode);
       values.put("phone", phone);
       uploadValues(API_URI + "/account/updateprofile", values, VoidApiResponse.class);
   }

    /**
     * Updates settings of specified subaccount
     * @param apikey ApiKey that gives you access to our SMTP and HTTP API's.
     * @param requiresEmailCredits True, if account needs credits to send emails. Otherwise, false
     * @param monthlyRefillCredits Amount of credits added to account automatically
     * @param requiresLitmusCredits True, if account needs credits to send emails. Otherwise, false
     * @param enableLitmusTest True, if account is able to send template tests to Litmus. Otherwise, false
     * @param dailySendLimit Amount of emails account can send daily
     * @param emailSizeLimit Maximum size of email including attachments in MB's
     * @param enablePrivateIPRequest True, if account can request for private IP on its own. Otherwise, false
     * @param maxContacts Maximum number of contacts the account can have
     * @param subAccountEmail Email address of sub-account
     * @param publicAccountID Public key of sub-account to update. Use subAccountEmail or publicAccountID not both.
     * @param sendingPermission Sending permission setting for account
     * @param enableContactFeatures True, if you want to use Contact Delivery Tools.  Otherwise, false
     * @param poolName Name of your custom IP Pool to be used in the sending process
     * @throws Exception
     */
    public void updateSubAccountSettings(Boolean requiresEmailCredits, int monthlyRefillCredits, Boolean requiresLitmusCredits, Boolean enableLitmusTest, int dailySendLimit, int emailSizeLimit, Boolean enablePrivateIPRequest, int maxContacts, String subAccountEmail, String publicAccountID, ApiTypes.SendingPermission sendingPermission, Boolean enableContactFeatures, String poolName) throws Exception {
       HashMap<String, String> values = new HashMap<String, String>();
       values.put("apikey", API_KEY);
       values.put("requiresEmailCredits", String.valueOf(requiresEmailCredits));
       values.put("monthlyRefillCredits", String.valueOf(monthlyRefillCredits));
       values.put("requiresLitmusCredits", String.valueOf(requiresLitmusCredits));
       values.put("enableLitmusTest", String.valueOf(enableLitmusTest));
       values.put("dailySendLimit", String.valueOf(dailySendLimit));
       values.put("emailSizeLimit", String.valueOf(emailSizeLimit));
       values.put("enablePrivateIPRequest", String.valueOf(enablePrivateIPRequest));
       values.put("maxContacts", String.valueOf(maxContacts));
       values.put("subAccountEmail", subAccountEmail);
       values.put("publicAccountID", publicAccountID);
       if (sendingPermission != null) values.put("sendingPermission", String.valueOf(sendingPermission));
       values.put("enableContactFeatures", String.valueOf(enableContactFeatures));
       values.put("poolName", poolName);
       uploadValues(API_URI + "/account/updatesubaccountsettings", values, VoidApiResponse.class);
   }

}

