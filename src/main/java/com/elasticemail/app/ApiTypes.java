package com.elasticemail.app;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class ApiTypes {
   /**
    * 
    */
   public enum AccessLevel {
       /**
        * 
        */
       NONE,

       /**
        * 
        */
       VIEWACCOUNT,

       /**
        * 
        */
       VIEWCONTACTS,

       /**
        * 
        */
       VIEWFORMS,

       /**
        * 
        */
       VIEWTEMPLATES,

       /**
        * 
        */
       VIEWCAMPAIGNS,

       /**
        * 
        */
       VIEWCHANNELS,

       /**
        * 
        */
       VIEWJOURNEYS,

       /**
        * 
        */
       VIEWSURVEYS,

       /**
        * 
        */
       VIEWSETTINGS,

       /**
        * 
        */
       VIEWBILLING,

       /**
        * 
        */
       VIEWSUBACCOUNTS,

       /**
        * 
        */
       VIEWUSERS,

       /**
        * 
        */
       VIEWFILES,

       /**
        * 
        */
       VIEWREPORTS,

       /**
        * 
        */
       MODIFYACCOUNT,

       /**
        * 
        */
       MODIFYCONTACTS,

       /**
        * 
        */
       MODIFYFORMS,

       /**
        * 
        */
       MODIFYTEMPLATES,

       /**
        * 
        */
       MODIFYCAMPAIGNS,

       /**
        * 
        */
       MODIFYCHANNELS,

       /**
        * 
        */
       MODIFYJOURNEYS,

       /**
        * 
        */
       MODIFYSURVEYS,

       /**
        * 
        */
       MODIFYFILES,

       /**
        * 
        */
       EXPORT,

       /**
        * 
        */
       SENDSMTP,

       /**
        * 
        */
       SENDSMS,

       /**
        * 
        */
       MODIFYSETTINGS,

       /**
        * 
        */
       MODIFYBILLING,

       /**
        * 
        */
       MODIFYPROFILE,

       /**
        * 
        */
       MODIFYSUBACCOUNTS,

       /**
        * 
        */
       MODIFYUSERS,

       /**
        * 
        */
       SECURITY,

       /**
        * 
        */
       MODIFYLANGUAGE,

       /**
        * 
        */
       MODIFYSUPPORT,

       /**
        * 
        */
       VIEWSUPPORT,

       /**
        * 
        */
       SENDHTTP
   }

   /**
    * 
    */
   public static class AccessToken {
       /**
        * Access which this Token grants
        */
       public ApiTypes.AccessLevel accesslevel;

       /**
        * Filename
        */
       public String name;

       /**
        * When was this AccessToken used last
        */
       public Date lastuse;

   }

   /**
    * Detailed information about your account
    */
   public static class Account {
       /**
        * Code used for tax purposes.
        */
       public String taxcode;

       /**
        * Public key for limited access to your account such as contact/add so you can use it safely on public websites.
        */
       public String publicaccountid;

       /**
        * ApiKey that gives you access to our SMTP and HTTP API's.
        */
       public String apikey;

       /**
        * Second ApiKey that gives you access to our SMTP and HTTP API's.  Used mainly for changing ApiKeys without disrupting services.
        */
       public String apikey2;

       /**
        * True, if account is a subaccount. Otherwise, false
        */
       public Boolean issub;

       /**
        * The number of subaccounts this account has.
        */
       public long subaccountscount;

       /**
        * Number of status: 1 - Active
        */
       public int statusnumber;

       /**
        * Account status: Active
        */
       public String statusformatted;

       /**
        * URL form for payments.
        */
       public String paymentformurl;

       /**
        * URL to your logo image.
        */
       public String logourl;

       /**
        * HTTP address of your website.
        */
       public String website;

       /**
        * True: Turn on or off ability to send mails under your brand. Otherwise, false
        */
       public Boolean enableprivatebranding;

       /**
        * Address to your support.
        */
       public String supportlink;

       /**
        * Subdomain for your rebranded service
        */
       public String privatebrandingurl;

       /**
        * First name.
        */
       public String firstname;

       /**
        * Last name.
        */
       public String lastname;

       /**
        * Company name.
        */
       public String company;

       /**
        * First line of address.
        */
       public String address1;

       /**
        * Second line of address.
        */
       public String address2;

       /**
        * City.
        */
       public String city;

       /**
        * State or province.
        */
       public String state;

       /**
        * Zip/postal code.
        */
       public String zip;

       /**
        * Numeric ID of country. A file with the list of countries is available <a href="http://api.elasticemail.com/public/countries"><b>here</b></a>
        */
       public int countryid;

       /**
        * Phone number
        */
       public String phone;

       /**
        * Proper email address.
        */
       public String email;

       /**
        * URL for affiliating.
        */
       public String affiliatelink;

       /**
        * Numeric reputation
        */
       public double reputation;

       /**
        * Amount of emails sent from this account
        */
       public long totalemailssent;

       /**
        * Amount of emails sent from this account
        */
       public long monthlyemailssent;

       /**
        * Amount of emails sent from this account
        */
       public BigDecimal credit;

       /**
        * Amount of email credits
        */
       public int emailcredits;

       /**
        * Amount of emails sent from this account
        */
       public BigDecimal priceperemail;

       /**
        * Why your clients are receiving your emails.
        */
       public String deliveryreason;

       /**
        * URL for making payments.
        */
       public String accountpaymenturl;

       /**
        * Address of SMTP server.
        */
       public String smtp;

       /**
        * Address of alternative SMTP server.
        */
       public String smtpalternative;

       /**
        * Status of automatic payments configuration.
        */
       public String autocreditstatus;

       /**
        * When AutoCreditStatus is Enabled, the credit level that triggers the credit to be recharged.
        */
       public BigDecimal autocreditlevel;

       /**
        * When AutoCreditStatus is Enabled, the amount of credit to be recharged.
        */
       public BigDecimal autocreditamount;

       /**
        * Amount of emails account can send daily
        */
       public int dailysendlimit;

       /**
        * Creation date.
        */
       public Date datecreated;

       /**
        * True, if you have enabled link tracking. Otherwise, false
        */
       public Boolean linktracking;

       /**
        * Type of content encoding
        */
       public String contenttransferencoding;

       /**
        * Amount of Litmus credits
        */
       public BigDecimal litmuscredits;

       /**
        * Enable contact delivery and optimization tools on your Account.
        */
       public Boolean enablecontactfeatures;

       /**
        * 
        */
       public Boolean needssmsverification;

       /**
        * 
        */
       public Boolean disableglobalcontacts;

   }

   /**
    * Basic overview of your account
    */
   public static class AccountOverview {
       /**
        * Amount of emails sent from this account
        */
       public long totalemailssent;

       /**
        * Amount of emails sent from this account
        */
       public BigDecimal credit;

       /**
        * Cost of 1000 emails
        */
       public BigDecimal costperthousand;

       /**
        * Number of messages in progress
        */
       public long inprogresscount;

       /**
        * Number of contacts currently with blocked status of Unsubscribed, Complaint, Bounced or InActive
        */
       public long blockedcontactscount;

       /**
        * Numeric reputation
        */
       public double reputation;

       /**
        * Number of contacts
        */
       public long contactcount;

       /**
        * Number of created campaigns
        */
       public long campaigncount;

       /**
        * Number of available templates
        */
       public long templatecount;

       /**
        * Number of created subaccounts
        */
       public long subaccountcount;

       /**
        * Number of active referrals
        */
       public long referralcount;

   }

   /**
    * Account's ready to send e-mails Status
    */
   public enum AccountSendStatus {
       /**
        * Account doesn't have enough credits
        */
       NOTENOUGHCREDITS,

       /**
        * Account can send e-mails but only without the attachments
        */
       CANSENDEMAILSNOATTACHMENTS,

       /**
        * Account has exceeded his daily send limit
        */
       DAILYSENDLIMITEXCEEDED,

       /**
        * Account is ready to send e-mails
        */
       CANSENDEMAILS
   }

   /**
    * Lists advanced sending options of your account.
    */
   public static class AdvancedOptions {
       /**
        * True, if you want to track clicks. Otherwise, false
        */
       public Boolean enableclicktracking;

       /**
        * True, if you want to track by link tracking. Otherwise, false
        */
       public Boolean enablelinkclicktracking;

       /**
        * True, if you want to use template scripting in your emails {{}}. Otherwise, false
        */
       public Boolean enabletemplatescripting;

       /**
        * True, if text BODY of message should be created automatically. Otherwise, false
        */
       public Boolean autotextformat;

       /**
        * True, if you want bounce notifications returned. Otherwise, false
        */
       public Boolean emailnotificationforerror;

       /**
        * True, if you want to send web notifications for sent email. Otherwise, false
        */
       public Boolean webnotificationforsent;

       /**
        * True, if you want to send web notifications for opened email. Otherwise, false
        */
       public Boolean webnotificationforopened;

       /**
        * True, if you want to send web notifications for clicked email. Otherwise, false
        */
       public Boolean webnotificationforclicked;

       /**
        * True, if you want to send web notifications for unsubscribed email. Otherwise, false
        */
       public Boolean webnotificationforunsubscribed;

       /**
        * True, if you want to send web notifications for complaint email. Otherwise, false
        */
       public Boolean webnotificationforabuse;

       /**
        * True, if you want to send web notifications for bounced email. Otherwise, false
        */
       public Boolean webnotificationforerror;

       /**
        * True, if you want to receive notifications for each type only once per email. Otherwise, false
        */
       public Boolean webnotificationnotifyonceperemail;

       /**
        * True, if you want to receive low credit email notifications. Otherwise, false
        */
       public Boolean lowcreditnotification;

       /**
        * True, if you want inbound email to only process contacts from your account. Otherwise, false
        */
       public Boolean inboundcontactsonly;

       /**
        * True, if this account is a sub-account. Otherwise, false
        */
       public Boolean issubaccount;

       /**
        * True, if this account resells Elastic Email. Otherwise, false.
        */
       public Boolean isownedbyreseller;

       /**
        * True, if you want to enable list-unsubscribe header. Otherwise, false
        */
       public Boolean enableunsubscribeheader;

       /**
        * True, if you want to display your labels on your unsubscribe form. Otherwise, false
        */
       public Boolean managesubscriptions;

       /**
        * True, if you want to only display labels that the contact is subscribed to on your unsubscribe form. Otherwise, false
        */
       public Boolean managesubscribedonly;

       /**
        * True, if you want to display an option for the contact to opt into transactional email only on your unsubscribe form. Otherwise, false
        */
       public Boolean transactionalonunsubscribe;

       /**
        * 
        */
       public Boolean consenttrackingonunsubscribe;

       /**
        * 
        */
       public String previewmessageid;

       /**
        * True, if you want to apply custom headers to your emails. Otherwise, false
        */
       public Boolean allowcustomheaders;

       /**
        * Email address to send a copy of all email to.
        */
       public String bccemail;

       /**
        * Type of content encoding
        */
       public String contenttransferencoding;

       /**
        * True, if you want to receive bounce email notifications. Otherwise, false
        */
       public String emailnotification;

       /**
        * Email addresses to send a copy of all notifications from our system. Separated by semicolon
        */
       public String notificationsemails;

       /**
        * Emails, separated by semicolon, to which the notification about contact unsubscribing should be sent to
        */
       public String unsubscribenotificationemails;

       /**
        * URL address to receive web notifications to parse and process.
        */
       public String webnotificationurl;

       /**
        * URL used for tracking action of inbound emails
        */
       public String hubcallbackurl;

       /**
        * Domain you use as your inbound domain
        */
       public String inbounddomain;

       /**
        * True, if account has tooltips active. Otherwise, false
        */
       public Boolean enableuitooltips;

       /**
        * True, if you want to use Contact Delivery Tools.  Otherwise, false
        */
       public Boolean enablecontactfeatures;

       /**
        * URL to your logo image.
        */
       public String logourl;

       /**
        * (0 means this functionality is NOT enabled) Score, depending on the number of times you have sent to a recipient, at which the given recipient should be moved to the Stale status
        */
       public int stalecontactscore;

       /**
        * (0 means this functionality is NOT enabled) Number of days of inactivity for a contact after which the given recipient should be moved to the Stale status
        */
       public int stalecontactinactivedays;

       /**
        * Why your clients are receiving your emails.
        */
       public String deliveryreason;

   }

   /**
    * Blocked Contact - Contact returning Hard Bounces
    */
   public static class BlockedContact {
       /**
        * Proper email address.
        */
       public String email;

       /**
        * Name of status: Active, Engaged, Inactive, Abuse, Bounced, Unsubscribed.
        */
       public String status;

       /**
        * RFC error message
        */
       public String friendlyerrormessage;

       /**
        * Last change date
        */
       public String dateupdated;

   }

   /**
    * Summary of bounced categories, based on specified date range.
    */
   public static class BouncedCategorySummary {
       /**
        * Number of messages marked as SPAM
        */
       public long spam;

       /**
        * Number of blacklisted messages
        */
       public long blacklisted;

       /**
        * Number of messages flagged with 'No Mailbox'
        */
       public long nomailbox;

       /**
        * Number of messages flagged with 'Grey Listed'
        */
       public long greylisted;

       /**
        * Number of messages flagged with 'Throttled'
        */
       public long throttled;

       /**
        * Number of messages flagged with 'Timeout'
        */
       public long timeout;

       /**
        * Number of messages flagged with 'Connection Problem'
        */
       public long connectionproblem;

       /**
        * Number of messages flagged with 'SPF Problem'
        */
       public long spfproblem;

       /**
        * Number of messages flagged with 'Account Problem'
        */
       public long accountproblem;

       /**
        * Number of messages flagged with 'DNS Problem'
        */
       public long dnsproblem;

       /**
        * Number of messages flagged with 'WhiteListing Problem'
        */
       public long whitelistingproblem;

       /**
        * Number of messages flagged with 'Code Error'
        */
       public long codeerror;

       /**
        * Number of messages flagged with 'Not Delivered'
        */
       public long notdelivered;

       /**
        * Number of manually cancelled messages
        */
       public long manualcancel;

       /**
        * Number of messages flagged with 'Connection terminated'
        */
       public long connectionterminated;

   }

   /**
    * Campaign
    */
   public static class Campaign {
       /**
        * ID number of selected Channel.
        */
       public int channelid;

       /**
        * Campaign's name
        */
       public String name;

       /**
        * Name of campaign's status
        */
       public ApiTypes.CampaignStatus status;

       /**
        * List of Segment and List IDs, preceded with 'l' for Lists and 's' for Segments, comma separated
        */
       public String[] targets;

       /**
        * Number of event, triggering mail sending
        */
       public ApiTypes.CampaignTriggerType triggertype;

       /**
        * Date of triggered send
        */
       public Date triggerdate;

       /**
        * How far into the future should the campaign be sent, in minutes
        */
       public double triggerdelay;

       /**
        * When your next automatic mail will be sent, in minutes
        */
       public double triggerfrequency;

       /**
        * How many times should the campaign be sent
        */
       public int triggercount;

       /**
        * ID number of transaction
        */
       public int triggerchannelid;

       /**
        * Data for filtering event campaigns such as specific link addresses.
        */
       public String triggerdata;

       /**
        * What should be checked for choosing the winner: opens or clicks
        */
       public ApiTypes.SplitOptimization splitoptimization;

       /**
        * Number of minutes between sends during optimization period
        */
       public int splitoptimizationminutes;

       /**
        * 
        */
       public int timingoption;

       /**
        * Should the opens be tracked? If no value has been provided, account's default setting will be used.
        */
       public Boolean trackopens;

       /**
        * Should the clicks be tracked? If no value has been provided, account's default setting will be used.
        */
       public Boolean trackclicks;

       /**
        * 
        */
       public ApiTypes.CampaignTemplateArray campaigntemplates;

   }

   /**
    * Channel
    */
   public static class CampaignChannel {
       /**
        * ID number of selected Channel.
        */
       public int channelid;

       /**
        * Filename
        */
       public String name;

       /**
        * True, if you are sending a campaign. Otherwise, false.
        */
       public Boolean iscampaign;

       /**
        * Name of your custom IP Pool to be used in the sending process
        */
       public String poolname;

       /**
        * Date of creation in YYYY-MM-DDThh:ii:ss format
        */
       public Date dateadded;

       /**
        * Name of campaign's status
        */
       public ApiTypes.CampaignStatus status;

       /**
        * Date of last activity on account
        */
       public Date lastactivity;

       /**
        * Datetime of last action done on campaign.
        */
       public Date lastprocessed;

       /**
        * Id number of parent channel
        */
       public int parentchannelid;

       /**
        * List of Segment and List IDs, preceded with 'l' for Lists and 's' for Segments, comma separated
        */
       public String[] targets;

       /**
        * Number of event, triggering mail sending
        */
       public ApiTypes.CampaignTriggerType triggertype;

       /**
        * Date of triggered send
        */
       public Date triggerdate;

       /**
        * How far into the future should the campaign be sent, in minutes
        */
       public double triggerdelay;

       /**
        * When your next automatic mail will be sent, in minutes
        */
       public double triggerfrequency;

       /**
        * How many times should the campaign be sent
        */
       public int triggercount;

       /**
        * ID number of transaction
        */
       public int triggerchannelid;

       /**
        * Data for filtering event campaigns such as specific link addresses.
        */
       public String triggerdata;

       /**
        * What should be checked for choosing the winner: opens or clicks
        */
       public ApiTypes.SplitOptimization splitoptimization;

       /**
        * Number of minutes between sends during optimization period
        */
       public int splitoptimizationminutes;

       /**
        * 
        */
       public int timingoption;

       /**
        * ID number of template.
        */
       public int templateid;

       /**
        * Default subject of email.
        */
       public String templatesubject;

       /**
        * Default From: email address.
        */
       public String templatefromemail;

       /**
        * Default From: name.
        */
       public String templatefromname;

       /**
        * Default Reply: email address.
        */
       public String templatereplyemail;

       /**
        * Default Reply: name.
        */
       public String templatereplyname;

       /**
        * Total emails clicked
        */
       public int clickedcount;

       /**
        * Total emails opened.
        */
       public int openedcount;

       /**
        * Overall number of recipients
        */
       public int recipientcount;

       /**
        * Total emails sent.
        */
       public int sentcount;

       /**
        * Total emails sent.
        */
       public int failedcount;

       /**
        * Total emails clicked
        */
       public int unsubscribedcount;

       /**
        * Abuses - mails sent to user without their consent
        */
       public int failedabuse;

       /**
        * List of CampaignTemplate for sending A-X split testing.
        */
       public ApiTypes.CampaignChannelArray templatechannels;

       /**
        * Should the opens be tracked? If no value has been provided, account's default setting will be used.
        */
       public Boolean trackopens;

       /**
        * Should the clicks be tracked? If no value has been provided, account's default setting will be used.
        */
       public Boolean trackclicks;

   }

   /**
    * 
    */
   public enum CampaignStatus {
       /**
        * Campaign is logically deleted and not returned by API or interface calls.
        */
       DELETED,

       /**
        * Campaign is curently active and available.
        */
       ACTIVE,

       /**
        * Campaign is currently being processed for delivery.
        */
       PROCESSING,

       /**
        * Campaign is currently sending.
        */
       SENDING,

       /**
        * Campaign has completed sending.
        */
       COMPLETED,

       /**
        * Campaign is currently paused and not sending.
        */
       PAUSED,

       /**
        * Campaign has been cancelled during delivery.
        */
       CANCELLED,

       /**
        * Campaign is save as draft and not processing.
        */
       DRAFT
   }

   /**
    * 
    */
   public static class CampaignTemplate {
       /**
        * ID number of selected Channel.
        */
       public int channelid;

       /**
        * Name of campaign's status
        */
       public ApiTypes.CampaignStatus status;

       /**
        * Name of your custom IP Pool to be used in the sending process
        */
       public String poolname;

       /**
        * ID number of template.
        */
       public int templateid;

       /**
        * Default subject of email.
        */
       public String templatesubject;

       /**
        * Default From: email address.
        */
       public String templatefromemail;

       /**
        * Default From: name.
        */
       public String templatefromname;

       /**
        * Default Reply: email address.
        */
       public String templatereplyemail;

       /**
        * Default Reply: name.
        */
       public String templatereplyname;

   }

   /**
    * 
    */
   public enum CampaignTriggerType {
       /**
        * 
        */
       SENDNOW,

       /**
        * 
        */
       FUTURESCHEDULED,

       /**
        * 
        */
       ONADD,

       /**
        * 
        */
       ONOPEN,

       /**
        * 
        */
       ONCLICK
   }

   /**
    * 
    */
   public enum CertificateValidationStatus {
       /**
        * 
        */
       ERROROCCURED,

       /**
        * 
        */
       CERTNOTSET,

       /**
        * 
        */
       VALID,

       /**
        * 
        */
       NOTVALID
   }

   /**
    * SMTP and HTTP API channel for grouping email delivery
    */
   public static class Channel {
       /**
        * Descriptive name of the channel.
        */
       public String name;

       /**
        * The date the channel was added to your account.
        */
       public Date dateadded;

       /**
        * The date the channel was last sent through.
        */
       public Date lastactivity;

       /**
        * The number of email jobs this channel has been used with.
        */
       public int jobcount;

       /**
        * The number of emails that have been clicked within this channel.
        */
       public int clickedcount;

       /**
        * The number of emails that have been opened within this channel.
        */
       public int openedcount;

       /**
        * The number of emails attempted to be sent within this channel.
        */
       public int recipientcount;

       /**
        * The number of emails that have been sent within this channel.
        */
       public int sentcount;

       /**
        * The number of emails that have been bounced within this channel.
        */
       public int failedcount;

       /**
        * The number of emails that have been unsubscribed within this channel.
        */
       public int unsubscribedcount;

       /**
        * The number of emails that have been marked as abuse or complaint within this channel.
        */
       public int failedabuse;

       /**
        * The total cost for emails/attachments within this channel.
        */
       public BigDecimal cost;

   }

   /**
    * FileResponse compression format
    */
   public enum CompressionFormat {
       /**
        * No compression
        */
       NONE,

       /**
        * Zip compression
        */
       ZIP
   }

   /**
    * 
    */
   public enum ConsentTracking {
       /**
        * 
        */
       UNKNOWN,

       /**
        * 
        */
       ALLOW,

       /**
        * 
        */
       DENY
   }

   /**
    * Contact
    */
   public static class Contact {
       /**
        * 
        */
       public int contactscore;

       /**
        * Date of creation in YYYY-MM-DDThh:ii:ss format
        */
       public Date dateadded;

       /**
        * Proper email address.
        */
       public String email;

       /**
        * First name.
        */
       public String firstname;

       /**
        * Last name.
        */
       public String lastname;

       /**
        * Name of status: Active, Engaged, Inactive, Abuse, Bounced, Unsubscribed.
        */
       public ApiTypes.ContactStatus status;

       /**
        * RFC Error code
        */
       public int bouncederrorcode;

       /**
        * RFC error message
        */
       public String bouncederrormessage;

       /**
        * Total emails sent.
        */
       public int totalsent;

       /**
        * Total emails sent.
        */
       public int totalfailed;

       /**
        * Total emails opened.
        */
       public int totalopened;

       /**
        * Total emails clicked
        */
       public int totalclicked;

       /**
        * Date of first failed message
        */
       public Date firstfaileddate;

       /**
        * Number of fails in sending to this Contact
        */
       public int lastfailedcount;

       /**
        * Last change date
        */
       public Date dateupdated;

       /**
        * Source of URL of payment
        */
       public ApiTypes.ContactSource source;

       /**
        * RFC Error code
        */
       public int errorcode;

       /**
        * RFC error message
        */
       public String friendlyerrormessage;

       /**
        * IP address
        */
       public String createdfromip;

       /**
        * IP address of consent to send this contact(s) your email. If not provided your current public IP address is used for consent.
        */
       public String consentip;

       /**
        * Date of consent to send this contact(s) your email. If not provided current date is used for consent.
        */
       public Date consentdate;

       /**
        * 
        */
       public ApiTypes.ConsentTracking consenttracking;

       /**
        * Unsubscribed date in YYYY-MM-DD format
        */
       public Date unsubscribeddate;

       /**
        * Free form field of notes
        */
       public String notes;

       /**
        * Website of contact
        */
       public String websiteurl;

       /**
        * Date this contact last opened an email
        */
       public Date lastopened;

       /**
        * 
        */
       public Date lastclicked;

       /**
        * Custom contact field like firstname, lastname, city etc. JSON serialized text like { "city":"london" } 
        */
       public HashMap<String, String> customfields;

   }

   /**
    * Collection of lists and segments
    */
   public static class ContactCollection {
       /**
        * Lists which contain the requested contact
        */
       public ApiTypes.ContactContainerArray lists;

       /**
        * Segments which contain the requested contact
        */
       public ApiTypes.ContactContainerArray segments;

   }

   /**
    * List's or segment's short info
    */
   public static class ContactContainer {
       /**
        * ID of the list/segment
        */
       public int id;

       /**
        * Name of the list/segment
        */
       public String name;

   }

   /**
    * 
    */
   public enum ContactHistEventType {
       /**
        * Contact opened an e-mail
        */
       OPENED,

       /**
        * Contact clicked an e-mail
        */
       CLICKED,

       /**
        * E-mail sent to the contact bounced
        */
       BOUNCED,

       /**
        * Contact unsubscribed
        */
       UNSUBSCRIBED,

       /**
        * Contact complained to an e-mail
        */
       COMPLAINED,

       /**
        * Contact clicked an activation link
        */
       ACTIVATED,

       /**
        * Contact has opted to receive Transactional-only e-mails
        */
       TRANSACTIONALUNSUBSCRIBED,

       /**
        * Contact's status was changed manually
        */
       MANUALSTATUSCHANGE,

       /**
        * An Activation e-mail was sent
        */
       ACTIVATIONSENT,

       /**
        * Contact was deleted
        */
       DELETED
   }

   /**
    * History of chosen Contact
    */
   public static class ContactHistory {
       /**
        * ID of history of selected Contact.
        */
       public long contacthistoryid;

       /**
        * Type of event occured on this Contact.
        */
       public String eventtype;

       /**
        * Numeric code of event occured on this Contact.
        */
       public ApiTypes.ContactHistEventType eventtypevalue;

       /**
        * Formatted date of event.
        */
       public String eventdate;

       /**
        * Name of selected channel.
        */
       public String channelname;

       /**
        * Name of template.
        */
       public String templatename;

       /**
        * IP Address of the event.
        */
       public String ipaddress;

       /**
        * Country of the event.
        */
       public String country;

       /**
        * Information about the event
        */
       public String data;

   }

   /**
    * 
    */
   public enum ContactSource {
       /**
        * Source of the contact is from sending an email via our SMTP or HTTP API's
        */
       DELIVERYAPI,

       /**
        * Contact was manually entered from the interface.
        */
       MANUALINPUT,

       /**
        * Contact was uploaded via a file such as CSV.
        */
       FILEUPLOAD,

       /**
        * Contact was added from a public web form.
        */
       WEBFORM,

       /**
        * Contact was added from the contact api.
        */
       CONTACTAPI
   }

   /**
    * 
    */
   public enum ContactStatus {
       /**
        * Only transactional email can be sent to contacts with this status.
        */
       TRANSACTIONAL,

       /**
        * Contact has had an open or click in the last 6 months.
        */
       ENGAGED,

       /**
        * Contact is eligible to be sent to.
        */
       ACTIVE,

       /**
        * Contact has had a hard bounce and is no longer eligible to be sent to.
        */
       BOUNCED,

       /**
        * Contact has unsubscribed and is no longer eligible to be sent to.
        */
       UNSUBSCRIBED,

       /**
        * Contact has complained and is no longer eligible to be sent to.
        */
       ABUSE,

       /**
        * Contact has not been activated or has been de-activated and is not eligible to be sent to.
        */
       INACTIVE,

       /**
        * Contact has not been opening emails for a long period of time and is not eligible to be sent to.
        */
       STALE,

       /**
        * Contact has not confirmed their double opt-in activation and is not eligible to be sent to.
        */
       NOTCONFIRMED
   }

   /**
    * Number of Contacts, grouped by Status;
    */
   public static class ContactStatusCounts {
       /**
        * Number of engaged contacts
        */
       public long engaged;

       /**
        * Number of active contacts
        */
       public long active;

       /**
        * Number of complaint messages
        */
       public long complaint;

       /**
        * Number of unsubscribed messages
        */
       public long unsubscribed;

       /**
        * Number of bounced messages
        */
       public long bounced;

       /**
        * Number of inactive contacts
        */
       public long inactive;

       /**
        * Number of transactional contacts
        */
       public long transactional;

       /**
        * 
        */
       public long stale;

       /**
        * 
        */
       public long notconfirmed;

   }

   /**
    * Number of Unsubscribed or Complaint Contacts, grouped by Unsubscribe Reason;
    */
   public static class ContactUnsubscribeReasonCounts {
       /**
        * 
        */
       public long unknown;

       /**
        * 
        */
       public long nolongerwant;

       /**
        * 
        */
       public long irrelevantcontent;

       /**
        * 
        */
       public long toofrequent;

       /**
        * 
        */
       public long neverconsented;

       /**
        * 
        */
       public long deceptivecontent;

       /**
        * 
        */
       public long abusereported;

       /**
        * 
        */
       public long thirdparty;

       /**
        * 
        */
       public long listunsubscribe;

   }

   /**
    * Type of credits
    */
   public enum CreditType {
       /**
        * Used to send emails.  One credit = one email.
        */
       EMAIL,

       /**
        * Used to run a litmus test on a template.  1 credit = 1 test.
        */
       LITMUS
   }

   /**
    * Daily summary of log status, based on specified date range.
    */
   public static class DailyLogStatusSummary {
       /**
        * Date in YYYY-MM-DDThh:ii:ss format
        */
       public String date;

       /**
        * Proper email address.
        */
       public int email;

       /**
        * Number of SMS
        */
       public int sms;

       /**
        * Number of delivered messages
        */
       public int delivered;

       /**
        * Number of opened messages
        */
       public int opened;

       /**
        * Number of clicked messages
        */
       public int clicked;

       /**
        * Number of unsubscribed messages
        */
       public int unsubscribed;

       /**
        * Number of complaint messages
        */
       public int complaint;

       /**
        * Number of bounced messages
        */
       public int bounced;

       /**
        * Number of inbound messages
        */
       public int inbound;

       /**
        * Number of manually cancelled messages
        */
       public int manualcancel;

       /**
        * Number of messages flagged with 'Not Delivered'
        */
       public int notdelivered;

   }

   /**
    * Domain data, with information about domain records.
    */
   public static class DomainDetail {
       /**
        * Name of selected domain.
        */
       public String domain;

       /**
        * True, if domain is used as default. Otherwise, false,
        */
       public Boolean defaultdomain;

       /**
        * True, if SPF record is verified
        */
       public Boolean spf;

       /**
        * True, if DKIM record is verified
        */
       public Boolean dkim;

       /**
        * True, if MX record is verified
        */
       public Boolean mx;

       /**
        * 
        */
       public Boolean dmarc;

       /**
        * True, if tracking CNAME record is verified
        */
       public Boolean isrewritedomainvalid;

       /**
        * True, if verification is available
        */
       public Boolean verify;

       /**
        * 
        */
       public ApiTypes.TrackingType type;

       /**
        * 0 - NotValidated, 1 - Validated successfully, 2 - Invalid, 3 - Broken (tracking was frequnetly verfied in given period and still is invalid). For statuses: 0, 1, 3 tracking will be verified in normal periods. For status 2 tracking will be verified in high frequent periods.
        */
       public ApiTypes.TrackingValidationStatus trackingstatus;

       /**
        * 
        */
       public ApiTypes.CertificateValidationStatus certificatestatus;

       /**
        * 
        */
       public String certificatevalidationerror;

       /**
        * 
        */
       public ApiTypes.TrackingType trackingtypeuserrequest;

   }

   /**
    * Detailed information about email credits
    */
   public static class EmailCredits {
       /**
        * Date in YYYY-MM-DDThh:ii:ss format
        */
       public Date date;

       /**
        * Amount of money in transaction
        */
       public BigDecimal amount;

       /**
        * Source of URL of payment
        */
       public String source;

       /**
        * Free form field of notes
        */
       public String notes;

   }

   /**
    * 
    */
   public static class EmailJobFailedStatus {
       /**
        * 
        */
       public String address;

       /**
        * 
        */
       public String error;

       /**
        * RFC Error code
        */
       public int errorcode;

       /**
        * 
        */
       public String category;

   }

   /**
    * 
    */
   public static class EmailJobStatus {
       /**
        * ID number of your attachment
        */
       public String id;

       /**
        * Name of status: submitted, complete, in_progress
        */
       public String status;

       /**
        * 
        */
       public int recipientscount;

       /**
        * 
        */
       public ApiTypes.EmailJobFailedStatusArray failed;

       /**
        * Total emails sent.
        */
       public int failedcount;

       /**
        * 
        */
       public StringArray sent;

       /**
        * Total emails sent.
        */
       public int sentcount;

       /**
        * Number of delivered messages
        */
       public StringArray delivered;

       /**
        * 
        */
       public int deliveredcount;

       /**
        * 
        */
       public StringArray pending;

       /**
        * 
        */
       public int pendingcount;

       /**
        * Number of opened messages
        */
       public StringArray opened;

       /**
        * Total emails opened.
        */
       public int openedcount;

       /**
        * Number of clicked messages
        */
       public StringArray clicked;

       /**
        * Total emails clicked
        */
       public int clickedcount;

       /**
        * Number of unsubscribed messages
        */
       public StringArray unsubscribed;

       /**
        * Total emails clicked
        */
       public int unsubscribedcount;

       /**
        * 
        */
       public StringArray abusereports;

       /**
        * 
        */
       public int abusereportscount;

       /**
        * List of all MessageIDs for this job.
        */
       public StringArray messageids;

   }

   /**
    * 
    */
   public static class EmailSend {
       /**
        * ID number of transaction
        */
       public String transactionid;

       /**
        * Unique identifier for this email.
        */
       public String messageid;

   }

   /**
    * Status information of the specified email
    */
   public static class EmailStatus {
       /**
        * Email address this email was sent from.
        */
       public String from;

       /**
        * Email address this email was sent to.
        */
       public String to;

       /**
        * Date the email was submitted.
        */
       public Date date;

       /**
        * Value of email's status
        */
       public ApiTypes.LogJobStatus status;

       /**
        * Name of email's status
        */
       public String statusname;

       /**
        * Date of last status change.
        */
       public Date statuschangedate;

       /**
        * Date when the email was sent
        */
       public Date datesent;

       /**
        * Date when the email changed the status to 'opened'
        */
       public Date dateopened;

       /**
        * Date when the email changed the status to 'clicked'
        */
       public Date dateclicked;

       /**
        * Detailed error or bounced message.
        */
       public String errormessage;

       /**
        * ID number of transaction
        */
       public UUID transactionid;

   }

   /**
    * Email details formatted in json
    */
   public static class EmailView {
       /**
        * Body (text) of your message.
        */
       public String body;

       /**
        * Default subject of email.
        */
       public String subject;

       /**
        * Starting date for search in YYYY-MM-DDThh:mm:ss format.
        */
       public String from;

   }

   /**
    * Encoding type for the email headers
    */
   public enum EncodingType {
       /**
        * Encoding of the email is provided by the sender and not altered.
        */
       USERPROVIDED,

       /**
        * No endcoding is set for the email.
        */
       NONE,

       /**
        * Encoding of the email is in Raw7bit format.
        */
       RAW7BIT,

       /**
        * Encoding of the email is in Raw8bit format.
        */
       RAW8BIT,

       /**
        * Encoding of the email is in QuotedPrintable format.
        */
       QUOTEDPRINTABLE,

       /**
        * Encoding of the email is in Base64 format.
        */
       BASE64,

       /**
        * Encoding of the email is in Uue format.
        */
       UUE
   }

   /**
    * Record of exported data from the system.
    */
   public static class Export {
       /**
        * 
        */
       public UUID publicexportid;

       /**
        * Date the export was created
        */
       public Date dateadded;

       /**
        * Type of export
        */
       public String type;

       /**
        * Current status of export
        */
       public String status;

       /**
        * Long description of the export
        */
       public String info;

       /**
        * Name of the file
        */
       public String filename;

       /**
        * Link to download the export
        */
       public String link;

       /**
        * Log start date (for Type = Log only)
        */
       public Date logfrom;

       /**
        * Log end date (for Type = Log only)
        */
       public Date logto;

   }

   /**
    * Type of export
    */
   public enum ExportFileFormats {
       /**
        * Export in comma separated values format.
        */
       CSV,

       /**
        * Export in xml format
        */
       XML,

       /**
        * Export in json format
        */
       JSON
   }

   /**
    * 
    */
   public static class ExportLink {
       /**
        * Direct URL to the exported file
        */
       public String link;

   }

   /**
    * Current status of export
    */
   public enum ExportStatus {
       /**
        * Export had an error and can not be downloaded.
        */
       ERROR,

       /**
        * Export is currently loading and can not be downloaded.
        */
       LOADING,

       /**
        * Export is currently available for downloading.
        */
       READY,

       /**
        * Export is no longer available for downloading.
        */
       EXPIRED
   }

   /**
    * Number of Exports, grouped by export type
    */
   public static class ExportTypeCounts {
       /**
        * 
        */
       public long log;

       /**
        * 
        */
       public long contact;

       /**
        * Json representation of a campaign
        */
       public long campaign;

       /**
        * True, if you have enabled link tracking. Otherwise, false
        */
       public long linktracking;

       /**
        * Json representation of a survey
        */
       public long survey;

   }

   /**
    * 
    */
   public static class File {
       /**
        * Name of your file.
        */
       public String filename;

       /**
        * Size of your attachment (in bytes).
        */
       public int size;

       /**
        * Date of creation in YYYY-MM-DDThh:ii:ss format
        */
       public Date dateadded;

       /**
        * When will the file be deleted from the system
        */
       public Date expirationdate;

       /**
        * Content type of the file
        */
       public String contenttype;

   }

   /**
    * 
    */
   public enum IntervalType {
       /**
        * Daily overview
        */
       SUMMARY,

       /**
        * Hourly, detailed information
        */
       HOURLY
   }

   /**
    * Object containig tracking data.
    */
   public static class LinkTrackingDetails {
       /**
        * Number of items.
        */
       public int count;

       /**
        * True, if there are more detailed data available. Otherwise, false
        */
       public Boolean moreavailable;

       /**
        * 
        */
       public ApiTypes.TrackedLinkArray trackedlink;

   }

   /**
    * List of Lists, with detailed data about its contents.
    */
   public static class List {
       /**
        * ID number of selected list.
        */
       public int listid;

       /**
        * Name of your list.
        */
       public String listname;

       /**
        * Number of items.
        */
       public int count;

       /**
        * ID code of list
        */
       public UUID publiclistid;

       /**
        * Date of creation in YYYY-MM-DDThh:ii:ss format
        */
       public Date dateadded;

       /**
        * True: Allow unsubscribing from this list. Otherwise, false
        */
       public Boolean allowunsubscribe;

       /**
        * Query used for filtering.
        */
       public String rule;

   }

   /**
    * Detailed information about litmus credits
    */
   public static class LitmusCredits {
       /**
        * Date in YYYY-MM-DDThh:ii:ss format
        */
       public Date date;

       /**
        * Amount of money in transaction
        */
       public BigDecimal amount;

   }

   /**
    * Logs for selected date range
    */
   public static class Log {
       /**
        * Starting date for search in YYYY-MM-DDThh:mm:ss format.
        */
       public Date from;

       /**
        * Ending date for search in YYYY-MM-DDThh:mm:ss format.
        */
       public Date to;

       /**
        * Number of recipients
        */
       public ApiTypes.RecipientArray recipients;

   }

   /**
    * 
    */
   public enum LogJobStatus {
       /**
        * All emails
        */
       ALL,

       /**
        * Email has been submitted successfully and is queued for sending.
        */
       READYTOSEND,

       /**
        * Email has soft bounced and is scheduled to retry.
        */
       WAITINGTORETRY,

       /**
        * Email is currently sending.
        */
       SENDING,

       /**
        * Email has errored or bounced for some reason.
        */
       ERROR,

       /**
        * Email has been successfully delivered.
        */
       SENT,

       /**
        * Email has been opened by the recipient.
        */
       OPENED,

       /**
        * Email has had at least one link clicked by the recipient.
        */
       CLICKED,

       /**
        * Email has been unsubscribed by the recipient.
        */
       UNSUBSCRIBED,

       /**
        * Email has been complained about or marked as spam by the recipient.
        */
       ABUSEREPORT
   }

   /**
    * Summary of log status, based on specified date range.
    */
   public static class LogStatusSummary {
       /**
        * Starting date for search in YYYY-MM-DDThh:mm:ss format.
        */
       public String from;

       /**
        * Ending date for search in YYYY-MM-DDThh:mm:ss format.
        */
       public String to;

       /**
        * Overall duration
        */
       public double duration;

       /**
        * Number of recipients
        */
       public long recipients;

       /**
        * Number of emails
        */
       public long emailtotal;

       /**
        * Number of SMS
        */
       public long smstotal;

       /**
        * Number of delivered messages
        */
       public long delivered;

       /**
        * Number of bounced messages
        */
       public long bounced;

       /**
        * Number of messages in progress
        */
       public long inprogress;

       /**
        * Number of opened messages
        */
       public long opened;

       /**
        * Number of clicked messages
        */
       public long clicked;

       /**
        * Number of unsubscribed messages
        */
       public long unsubscribed;

       /**
        * Number of complaint messages
        */
       public long complaints;

       /**
        * Number of inbound messages
        */
       public long inbound;

       /**
        * Number of manually cancelled messages
        */
       public long manualcancel;

       /**
        * Number of messages flagged with 'Not Delivered'
        */
       public long notdelivered;

       /**
        * ID number of template used
        */
       public Boolean templatechannel;

   }

   /**
    * Overall log summary information.
    */
   public static class LogSummary {
       /**
        * Summary of log status, based on specified date range.
        */
       public ApiTypes.LogStatusSummary logstatussummary;

       /**
        * Summary of bounced categories, based on specified date range.
        */
       public ApiTypes.BouncedCategorySummary bouncedcategorysummary;

       /**
        * Daily summary of log status, based on specified date range.
        */
       public ApiTypes.DailyLogStatusSummaryArray dailylogstatussummary;

   }

   /**
    * 
    */
   public enum MessageCategory {
       /**
        * 
        */
       UNKNOWN,

       /**
        * 
        */
       IGNORE,

       /**
        * Number of messages marked as SPAM
        */
       SPAM,

       /**
        * Number of blacklisted messages
        */
       BLACKLISTED,

       /**
        * Number of messages flagged with 'No Mailbox'
        */
       NOMAILBOX,

       /**
        * Number of messages flagged with 'Grey Listed'
        */
       GREYLISTED,

       /**
        * Number of messages flagged with 'Throttled'
        */
       THROTTLED,

       /**
        * Number of messages flagged with 'Timeout'
        */
       TIMEOUT,

       /**
        * Number of messages flagged with 'Connection Problem'
        */
       CONNECTIONPROBLEM,

       /**
        * Number of messages flagged with 'SPF Problem'
        */
       SPFPROBLEM,

       /**
        * Number of messages flagged with 'Account Problem'
        */
       ACCOUNTPROBLEM,

       /**
        * Number of messages flagged with 'DNS Problem'
        */
       DNSPROBLEM,

       /**
        * 
        */
       NOTDELIVEREDCANCELLED,

       /**
        * Number of messages flagged with 'Code Error'
        */
       CODEERROR,

       /**
        * Number of manually cancelled messages
        */
       MANUALCANCEL,

       /**
        * Number of messages flagged with 'Connection terminated'
        */
       CONNECTIONTERMINATED,

       /**
        * Number of messages flagged with 'Not Delivered'
        */
       NOTDELIVERED
   }

   /**
    * Queue of notifications
    */
   public static class NotificationQueue {
       /**
        * Creation date.
        */
       public String datecreated;

       /**
        * Date of last status change.
        */
       public String statuschangedate;

       /**
        * Actual status.
        */
       public String newstatus;

       /**
        * 
        */
       public String reference;

       /**
        * Error message.
        */
       public String errormessage;

       /**
        * Number of previous delivery attempts
        */
       public String retrycount;

   }

   /**
    * 
    */
   public enum NotificationType {
       /**
        * Both, email and web, notifications
        */
       ALL,

       /**
        * Only email notifications
        */
       EMAIL,

       /**
        * Only web notifications
        */
       WEB
   }

   /**
    * Detailed information about existing money transfers.
    */
   public static class Payment {
       /**
        * Date in YYYY-MM-DDThh:ii:ss format
        */
       public Date date;

       /**
        * Amount of money in transaction
        */
       public BigDecimal amount;

       /**
        * Source of URL of payment
        */
       public String source;

   }

   /**
    * Basic information about your profile
    */
   public static class Profile {
       /**
        * First name.
        */
       public String firstname;

       /**
        * Last name.
        */
       public String lastname;

       /**
        * Company name.
        */
       public String company;

       /**
        * First line of address.
        */
       public String address1;

       /**
        * Second line of address.
        */
       public String address2;

       /**
        * City.
        */
       public String city;

       /**
        * State or province.
        */
       public String state;

       /**
        * Zip/postal code.
        */
       public String zip;

       /**
        * Numeric ID of country. A file with the list of countries is available <a href="http://api.elasticemail.com/public/countries"><b>here</b></a>
        */
       public int countryid;

       /**
        * Phone number
        */
       public String phone;

       /**
        * Proper email address.
        */
       public String email;

       /**
        * Code used for tax purposes.
        */
       public String taxcode;

   }

   /**
    * 
    */
   public enum QuestionType {
       /**
        * 
        */
       RADIOBUTTONS,

       /**
        * 
        */
       DROPDOWNMENU,

       /**
        * 
        */
       CHECKBOXES,

       /**
        * 
        */
       LONGANSWER,

       /**
        * 
        */
       TEXTBOX,

       /**
        * Date in YYYY-MM-DDThh:ii:ss format
        */
       DATE
   }

   /**
    * Detailed information about message recipient
    */
   public static class Recipient {
       /**
        * True, if message is SMS. Otherwise, false
        */
       public Boolean issms;

       /**
        * ID number of selected message.
        */
       public String msgid;

       /**
        * Ending date for search in YYYY-MM-DDThh:mm:ss format.
        */
       public String to;

       /**
        * Name of recipient's status: Submitted, ReadyToSend, WaitingToRetry, Sending, Bounced, Sent, Opened, Clicked, Unsubscribed, AbuseReport
        */
       public String status;

       /**
        * Name of selected Channel.
        */
       public String channel;

       /**
        * Creation date
        */
       public String date;

       /**
        * Date when the email was sent
        */
       public String datesent;

       /**
        * Date when the email changed the status to 'opened'
        */
       public String dateopened;

       /**
        * Date when the email changed the status to 'clicked'
        */
       public String dateclicked;

       /**
        * Content of message, HTML encoded
        */
       public String message;

       /**
        * True, if message category should be shown. Otherwise, false
        */
       public Boolean showcategory;

       /**
        * Name of message category
        */
       public String messagecategory;

       /**
        * ID of message category
        */
       public ApiTypes.MessageCategory messagecategoryid;

       /**
        * Date of last status change.
        */
       public String statuschangedate;

       /**
        * Date of next try
        */
       public String nexttryon;

       /**
        * Default subject of email.
        */
       public String subject;

       /**
        * Default From: email address.
        */
       public String fromemail;

       /**
        * ID of certain mail job
        */
       public String jobid;

       /**
        * True, if message is a SMS and status is not yet confirmed. Otherwise, false
        */
       public Boolean smsupdaterequired;

       /**
        * Content of message
        */
       public String textmessage;

       /**
        * Comma separated ID numbers of messages.
        */
       public String messagesid;

       /**
        * Recipient's last bounce error because of which this e-mail was suppressed
        */
       public String contactlasterror;

   }

   /**
    * Referral details for this account.
    */
   public static class Referral {
       /**
        * Current amount of dolars you have from referring.
        */
       public BigDecimal currentreferralcredit;

       /**
        * Number of active referrals.
        */
       public long currentreferralcount;

   }

   /**
    * Detailed sending reputation of your account.
    */
   public static class ReputationDetail {
       /**
        * Overall reputation impact, based on the most important factors.
        */
       public ApiTypes.ReputationImpact impact;

       /**
        * Percent of Complaining users - those, who do not want to receive email from you.
        */
       public double abusepercent;

       /**
        * Percent of Unknown users - users that couldn't be found
        */
       public double unknownuserspercent;

       /**
        * 
        */
       public double openedpercent;

       /**
        * 
        */
       public double clickedpercent;

       /**
        * Penalty from messages marked as spam.
        */
       public double averagespamscore;

       /**
        * Percent of Bounced users
        */
       public double failedspampercent;

       /**
        * Points from quantity of your emails.
        */
       public double repemailssent;

       /**
        * Average reputation.
        */
       public double averagereputation;

       /**
        * Actual price level.
        */
       public double pricelevelreputation;

       /**
        * Reputation needed to change pricing.
        */
       public double nextpricelevelreputation;

       /**
        * Amount of emails sent from this account
        */
       public String pricelevel;

       /**
        * True, if tracking domain is correctly configured. Otherwise, false.
        */
       public Boolean trackingdomainvalid;

       /**
        * True, if sending domain is correctly configured. Otherwise, false.
        */
       public Boolean senderdomainvalid;

   }

   /**
    * Reputation history of your account.
    */
   public static class ReputationHistory {
       /**
        * Creation date.
        */
       public String datecreated;

       /**
        * Percent of Complaining users - those, who do not want to receive email from you.
        */
       public double abusepercent;

       /**
        * Percent of Unknown users - users that couldn't be found
        */
       public double unknownuserspercent;

       /**
        * 
        */
       public double openedpercent;

       /**
        * 
        */
       public double clickedpercent;

       /**
        * Penalty from messages marked as spam.
        */
       public double averagespamscore;

       /**
        * Points from proper setup of your account
        */
       public double setupscore;

       /**
        * Points from quantity of your emails.
        */
       public double repemailssent;

       /**
        * Numeric reputation
        */
       public double reputation;

   }

   /**
    * Overall reputation impact, based on the most important factors.
    */
   public static class ReputationImpact {
       /**
        * Abuses - mails sent to user without their consent
        */
       public double abuse;

       /**
        * Users, that could not be reached.
        */
       public double unknownusers;

       /**
        * Number of opened messages
        */
       public double opened;

       /**
        * Number of clicked messages
        */
       public double clicked;

       /**
        * Penalty from messages marked as spam.
        */
       public double averagespamscore;

       /**
        * Content analysis.
        */
       public double serverfilter;

       /**
        * Tracking domain.
        */
       public double trackingdomain;

       /**
        * Sending domain.
        */
       public double senderdomain;

   }

   /**
    * Information about Contact Segment, selected by RULE.
    */
   public static class Segment {
       /**
        * ID number of your segment.
        */
       public int segmentid;

       /**
        * Filename
        */
       public String name;

       /**
        * Query used for filtering.
        */
       public String rule;

       /**
        * Number of items from last check.
        */
       public long lastcount;

       /**
        * History of segment information.
        */
       public ApiTypes.SegmentHistoryArray history;

   }

   /**
    * Segment History
    */
   public static class SegmentHistory {
       /**
        * ID number of history.
        */
       public int segmenthistoryid;

       /**
        * ID number of your segment.
        */
       public int segmentid;

       /**
        * Date in YYYY-MM-DD format
        */
       public int day;

       /**
        * Number of items.
        */
       public long count;

       /**
        * 
        */
       public long engagedcount;

       /**
        * 
        */
       public long activecount;

       /**
        * 
        */
       public long bouncedcount;

       /**
        * Total emails clicked
        */
       public long unsubscribedcount;

       /**
        * 
        */
       public long abusecount;

       /**
        * 
        */
       public long inactivecount;

   }

   /**
    * 
    */
   public enum SendingPermission {
       /**
        * Sending not allowed.
        */
       NONE,

       /**
        * Allow sending via SMTP only.
        */
       SMTP,

       /**
        * Allow sending via HTTP API only.
        */
       HTTPAPI,

       /**
        * Allow sending via SMTP and HTTP API.
        */
       SMTPANDHTTPAPI,

       /**
        * Allow sending via the website interface only.
        */
       INTERFACE,

       /**
        * Allow sending via SMTP and the website interface.
        */
       SMTPANDINTERFACE,

       /**
        * Allow sendnig via HTTP API and the website interface.
        */
       HTTPAPIANDINTERFACE,

       /**
        * Use access level sending permission.
        */
       USEACCESSLEVEL,

       /**
        * Sending allowed via SMTP, HTTP API and the website interface.
        */
       ALL
   }

   /**
    * Spam check of specified message.
    */
   public static class SpamCheck {
       /**
        * Total spam score from
        */
       public String totalscore;

       /**
        * Date in YYYY-MM-DDThh:ii:ss format
        */
       public String date;

       /**
        * Default subject of email.
        */
       public String subject;

       /**
        * Default From: email address.
        */
       public String fromemail;

       /**
        * ID number of selected message.
        */
       public String msgid;

       /**
        * Name of selected channel.
        */
       public String channelname;

       /**
        * 
        */
       public ApiTypes.SpamRuleArray rules;

   }

   /**
    * Single spam score
    */
   public static class SpamRule {
       /**
        * Spam score
        */
       public String score;

       /**
        * Name of rule
        */
       public String key;

       /**
        * Description of rule.
        */
       public String description;

   }

   /**
    * 
    */
   public enum SplitOptimization {
       /**
        * Number of opened messages
        */
       OPENED,

       /**
        * Number of clicked messages
        */
       CLICKED
   }

   /**
    * Subaccount. Contains detailed data of your Subaccount.
    */
   public static class SubAccount {
       /**
        * Public key for limited access to your account such as contact/add so you can use it safely on public websites.
        */
       public String publicaccountid;

       /**
        * ApiKey that gives you access to our SMTP and HTTP API's.
        */
       public String apikey;

       /**
        * Proper email address.
        */
       public String email;

       /**
        * ID number of mailer
        */
       public String mailerid;

       /**
        * Name of your custom IP Pool to be used in the sending process
        */
       public String poolname;

       /**
        * Date of last activity on account
        */
       public String lastactivity;

       /**
        * Amount of email credits
        */
       public String emailcredits;

       /**
        * True, if account needs credits to send emails. Otherwise, false
        */
       public Boolean requiresemailcredits;

       /**
        * Amount of credits added to account automatically
        */
       public double monthlyrefillcredits;

       /**
        * True, if account needs credits to buy templates. Otherwise, false
        */
       public Boolean requirestemplatecredits;

       /**
        * Amount of Litmus credits
        */
       public BigDecimal litmuscredits;

       /**
        * True, if account is able to send template tests to Litmus. Otherwise, false
        */
       public Boolean enablelitmustest;

       /**
        * True, if account needs credits to send emails. Otherwise, false
        */
       public Boolean requireslitmuscredits;

       /**
        * True, if account can buy templates on its own. Otherwise, false
        */
       public Boolean enablepremiumtemplates;

       /**
        * True, if account can request for private IP on its own. Otherwise, false
        */
       public Boolean enableprivateiprequest;

       /**
        * Amount of emails sent from this account
        */
       public long totalemailssent;

       /**
        * Percent of Unknown users - users that couldn't be found
        */
       public double unknownuserspercent;

       /**
        * Percent of Complaining users - those, who do not want to receive email from you.
        */
       public double abusepercent;

       /**
        * Percent of Bounced users
        */
       public double failedspampercent;

       /**
        * Numeric reputation
        */
       public double reputation;

       /**
        * Amount of emails account can send daily
        */
       public long dailysendlimit;

       /**
        * Name of account's status: Deleted, Disabled, UnderReview, NoPaymentsAllowed, NeverSignedIn, Active, SystemPaused
        */
       public String status;

       /**
        * Maximum size of email including attachments in MB's
        */
       public int emailsizelimit;

       /**
        * Maximum number of contacts the account can have
        */
       public int maxcontacts;

       /**
        * True, if you want to use Contact Delivery Tools.  Otherwise, false
        */
       public Boolean enablecontactfeatures;

       /**
        * Sending permission setting for account
        */
       public ApiTypes.SendingPermission sendingpermission;

   }

   /**
    * Detailed account settings.
    */
   public static class SubAccountSettings {
       /**
        * Proper email address.
        */
       public String email;

       /**
        * True, if account needs credits to send emails. Otherwise, false
        */
       public Boolean requiresemailcredits;

       /**
        * True, if account needs credits to buy templates. Otherwise, false
        */
       public Boolean requirestemplatecredits;

       /**
        * Amount of credits added to account automatically
        */
       public double monthlyrefillcredits;

       /**
        * Amount of Litmus credits
        */
       public BigDecimal litmuscredits;

       /**
        * True, if account is able to send template tests to Litmus. Otherwise, false
        */
       public Boolean enablelitmustest;

       /**
        * True, if account needs credits to send emails. Otherwise, false
        */
       public Boolean requireslitmuscredits;

       /**
        * Maximum size of email including attachments in MB's
        */
       public int emailsizelimit;

       /**
        * Amount of emails account can send daily
        */
       public int dailysendlimit;

       /**
        * Maximum number of contacts the account can have
        */
       public int maxcontacts;

       /**
        * True, if account can request for private IP on its own. Otherwise, false
        */
       public Boolean enableprivateiprequest;

       /**
        * True, if you want to use Contact Delivery Tools.  Otherwise, false
        */
       public Boolean enablecontactfeatures;

       /**
        * Sending permission setting for account
        */
       public ApiTypes.SendingPermission sendingpermission;

       /**
        * Name of your custom IP Pool to be used in the sending process
        */
       public String poolname;

       /**
        * Public key for limited access to your account such as contact/add so you can use it safely on public websites.
        */
       public String publicaccountid;

   }

   /**
    * A survey object
    */
   public static class Survey {
       /**
        * Survey identifier
        */
       public UUID publicsurveyid;

       /**
        * Creation date.
        */
       public Date datecreated;

       /**
        * Last change date
        */
       public Date dateupdated;

       /**
        * 
        */
       public Date expirydate;

       /**
        * Filename
        */
       public String name;

       /**
        * Activate, delete, or pause your survey
        */
       public ApiTypes.SurveyStatus status;

       /**
        * Number of results count
        */
       public int resultcount;

       /**
        * 
        */
       public ApiTypes.SurveyStepArray surveysteps;

       /**
        * URL of the survey
        */
       public String surveylink;

   }

   /**
    * Object with the single answer's data
    */
   public static class SurveyResultAnswerInfo {
       /**
        * Answer's content
        */
       public String content;

       /**
        * Identifier of the step
        */
       public int surveystepid;

       /**
        * Identifier of the answer of the step
        */
       public String surveystepanswerid;

   }

   /**
    * Single answer's data with user's specific info
    */
   public static class SurveyResultInfo {
       /**
        * Identifier of the result
        */
       public String surveyresultid;

       /**
        * IP address
        */
       public String createdfromip;

       /**
        * Completion date
        */
       public Date datecompleted;

       /**
        * Start date
        */
       public Date datestart;

       /**
        * Answers for the survey
        */
       public ApiTypes.SurveyResultAnswerInfoArray surveyresultanswers;

   }

   /**
    * 
    */
   public static class SurveyResultsAnswer {
       /**
        * Identifier of the answer of the step
        */
       public String surveystepanswerid;

       /**
        * Number of items.
        */
       public int count;

       /**
        * Answer's content
        */
       public String content;

   }

   /**
    * Data on the survey's result
    */
   public static class SurveyResultsSummaryInfo {
       /**
        * Number of items.
        */
       public int count;

       /**
        * Summary statistics
        */
       public HashMap<Integer, ApiTypes.SurveyResultsAnswerArray> summary;

   }

   /**
    * 
    */
   public enum SurveyStatus {
       /**
        * The survey is deleted
        */
       DELETED,

       /**
        * The survey is not receiving result for now
        */
       EXPIRED,

       /**
        * The survey is active and receiving answers
        */
       ACTIVE
   }

   /**
    * Survey's single step info with the answers
    */
   public static class SurveyStep {
       /**
        * Identifier of the step
        */
       public int surveystepid;

       /**
        * Type of the step
        */
       public ApiTypes.SurveyStepType surveysteptype;

       /**
        * Type of the question
        */
       public ApiTypes.QuestionType questiontype;

       /**
        * Answer's content
        */
       public String content;

       /**
        * Is the answer required
        */
       public Boolean required;

       /**
        * Sequence of the answers
        */
       public int sequence;

       /**
        * 
        */
       public ApiTypes.SurveyStepAnswerArray surveystepanswers;

   }

   /**
    * Single step's answer object
    */
   public static class SurveyStepAnswer {
       /**
        * Identifier of the answer of the step
        */
       public String surveystepanswerid;

       /**
        * Answer's content
        */
       public String content;

       /**
        * Sequence of the answers
        */
       public int sequence;

   }

   /**
    * 
    */
   public enum SurveyStepType {
       /**
        * 
        */
       PAGEBREAK,

       /**
        * 
        */
       QUESTION,

       /**
        * 
        */
       TEXTMEDIA,

       /**
        * 
        */
       CONFIRMATIONPAGE,

       /**
        * 
        */
       EXPIREDPAGE
   }

   /**
    * Template
    */
   public static class Template {
       /**
        * ID number of template.
        */
       public int templateid;

       /**
        * 0 for API connections
        */
       public ApiTypes.TemplateType templatetype;

       /**
        * Filename
        */
       public String name;

       /**
        * Date of creation in YYYY-MM-DDThh:ii:ss format
        */
       public Date dateadded;

       /**
        * CSS style
        */
       public String css;

       /**
        * Default subject of email.
        */
       public String subject;

       /**
        * Default From: email address.
        */
       public String fromemail;

       /**
        * Default From: name.
        */
       public String fromname;

       /**
        * HTML code of email (needs escaping).
        */
       public String bodyhtml;

       /**
        * Text body of email.
        */
       public String bodytext;

       /**
        * ID number of original template.
        */
       public int originaltemplateid;

       /**
        * Enum: 0 - private, 1 - public, 2 - mockup
        */
       public ApiTypes.TemplateScope templatescope;

   }

   /**
    * List of templates (including drafts)
    */
   public static class TemplateList {
       /**
        * List of templates
        */
       public ApiTypes.TemplateArray templates;

       /**
        * List of draft templates
        */
       public ApiTypes.TemplateArray drafttemplate;

   }

   /**
    * 
    */
   public enum TemplateScope {
       /**
        * Template is available for this account only.
        */
       PRIVATE,

       /**
        * Template is available for this account and it's sub-accounts.
        */
       PUBLIC
   }

   /**
    * 
    */
   public enum TemplateType {
       /**
        * Template supports any valid HTML
        */
       RAWHTML,

       /**
        * Template is created and can only be modified in drag and drop editor
        */
       DRAGDROPEDITOR
   }

   /**
    * Information about tracking link and its clicks.
    */
   public static class TrackedLink {
       /**
        * URL clicked
        */
       public String link;

       /**
        * Number of clicks
        */
       public String clicks;

       /**
        * Percent of clicks
        */
       public String percent;

   }

   /**
    * 
    */
   public enum TrackingType {
       /**
        * 
        */
       NONE,

       /**
        * 
        */
       DELETE,

       /**
        * 
        */
       HTTP,

       /**
        * 
        */
       EXTERNALHTTPS,

       /**
        * 
        */
       INTERNALCERTHTTPS,

       /**
        * 
        */
       LETSENCRYPTCERT
   }

   /**
    * Status of ValidDomain used by DomainValidationService to determine how often tracking validation should be performed.
    */
   public enum TrackingValidationStatus {
       /**
        * 
        */
       VALIDATED,

       /**
        * 
        */
       NOTVALIDATED,

       /**
        * 
        */
       INVALID,

       /**
        * 
        */
       BROKEN
   }

   /**
    * Account usage
    */
   public static class Usage {
       /**
        * Proper email address.
        */
       public String email;

       /**
        * True, if this account is a sub-account. Otherwise, false
        */
       public Boolean issubaccount;

       /**
        * 
        */
       public ApiTypes.UsageDataArray list;

   }

   /**
    * Detailed data about daily usage
    */
   public static class UsageData {
       /**
        * Date in YYYY-MM-DDThh:ii:ss format
        */
       public Date date;

       /**
        * Number of finished tasks
        */
       public int jobcount;

       /**
        * Overall number of recipients
        */
       public int recipientcount;

       /**
        * Number of inbound emails
        */
       public int inboundcount;

       /**
        * Number of attachments sent
        */
       public int attachmentcount;

       /**
        * Size of attachments sent
        */
       public long attachmentssize;

       /**
        * Calculated cost of sending
        */
       public BigDecimal cost;

       /**
        * Number of pricate IPs
        */
       public int privateipcount;

       /**
        * 
        */
       public BigDecimal privateipcost;

       /**
        * Number of SMS
        */
       public int smscount;

       /**
        * Overall cost of SMS
        */
       public BigDecimal smscost;

       /**
        * Cost of templates
        */
       public BigDecimal templatecost;

       /**
        * Cost of email credits
        */
       public int emailcreditscost;

       /**
        * Cost of template credit
        */
       public int templatecreditscost;

       /**
        * Cost of litmus credits
        */
       public BigDecimal litmuscost;

       /**
        * Cost of 1 litmus credit
        */
       public BigDecimal litmuscreditscost;

       /**
        * Daily cost of Contact Delivery Tools
        */
       public BigDecimal contactcost;

       /**
        * Number of contacts
        */
       public long contactcount;

       /**
        * 
        */
       public BigDecimal supportcost;

       /**
        * 
        */
       public BigDecimal emailcost;

   }

   public static class AccessTokenArray extends ArrayList<AccessToken> { }

   public static class BlockedContactArray extends ArrayList<BlockedContact> { }

   public static class CampaignChannelArray extends ArrayList<CampaignChannel> { }

   public static class CampaignTemplateArray extends ArrayList<CampaignTemplate> { }

   public static class ChannelArray extends ArrayList<Channel> { }

   public static class ContactArray extends ArrayList<Contact> { }

   public static class ContactContainerArray extends ArrayList<ContactContainer> { }

   public static class ContactHistoryArray extends ArrayList<ContactHistory> { }

   public static class ContactStatusArray extends ArrayList<ContactStatus> { }

   public static class DailyLogStatusSummaryArray extends ArrayList<DailyLogStatusSummary> { }

   public static class DomainDetailArray extends ArrayList<DomainDetail> { }

   public static class EmailCreditsArray extends ArrayList<EmailCredits> { }

   public static class EmailJobFailedStatusArray extends ArrayList<EmailJobFailedStatus> { }

   public static class ExportArray extends ArrayList<Export> { }

   public static class FileArray extends ArrayList<File> { }

   public static class ListArray extends ArrayList<List> { }

   public static class LitmusCreditsArray extends ArrayList<LitmusCredits> { }

   public static class LogJobStatusArray extends ArrayList<LogJobStatus> { }

   public static class MessageCategoryArray extends ArrayList<MessageCategory> { }

   public static class NotificationQueueArray extends ArrayList<NotificationQueue> { }

   public static class PaymentArray extends ArrayList<Payment> { }

   public static class RecipientArray extends ArrayList<Recipient> { }

   public static class ReputationHistoryArray extends ArrayList<ReputationHistory> { }

   public static class SegmentArray extends ArrayList<Segment> { }

   public static class SegmentHistoryArray extends ArrayList<SegmentHistory> { }

   public static class SpamCheckArray extends ArrayList<SpamCheck> { }

   public static class SpamRuleArray extends ArrayList<SpamRule> { }

   public static class StringArray extends ArrayList<String> { }

   public static class SubAccountArray extends ArrayList<SubAccount> { }

   public static class SurveyArray extends ArrayList<Survey> { }

   public static class SurveyResultAnswerInfoArray extends ArrayList<SurveyResultAnswerInfo> { }

   public static class SurveyResultInfoArray extends ArrayList<SurveyResultInfo> { }

   public static class SurveyResultsAnswerArray extends ArrayList<SurveyResultsAnswer> { }

   public static class SurveyStepArray extends ArrayList<SurveyStep> { }

   public static class SurveyStepAnswerArray extends ArrayList<SurveyStepAnswer> { }

   public static class TemplateArray extends ArrayList<Template> { }

   public static class TrackedLinkArray extends ArrayList<TrackedLink> { }

   public static class UsageArray extends ArrayList<Usage> { }

   public static class UsageDataArray extends ArrayList<UsageData> { }

}