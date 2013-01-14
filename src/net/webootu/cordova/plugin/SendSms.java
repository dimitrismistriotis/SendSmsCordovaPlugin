package net.webootu.cordova.plugin;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

/**
 * Initial version created by extending echo plugin located here in the official documentation:
 * {@link http://docs.phonegap.com/en/2.3.0/guide_plugin-development_android_index.md.html}
 */
public class SendSms extends CordovaPlugin {
    private static final String TAG = "SemdSmsPlugin";
    public final static String TEST_PHONE_NUMBER = "TESTNUM";
    public final static String ACTION_SEND_SMS = "SendSms";
    public final static String UKNOWN_ACTION_SUPPLIED = "Action supplied is not supported from this plugin.";

    /**
     * Adds all existing information to call back context,
     * believe it is better this way, so that calling functions can use
     * one source of information and also debug easier.
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(ACTION_SEND_SMS)) {
            String phoneNumber = args.getString(0);
            String message = args.getString(1);

            if (phoneNumber.equals(TEST_PHONE_NUMBER)) {
                JSONObject testResultOK = new JSONObject();
                testResultOK.put("sms_send", true);
                testResultOK.put("receiving_number", TEST_PHONE_NUMBER);
                testResultOK.put("send_text", message);
                callbackContext.success(testResultOK);
                return true;
            }

            Boolean sendResult = sendSMS(phoneNumber, message);
            JSONObject sendResultJSON = new JSONObject();
            sendResultJSON.put("sms_send", sendResult);
            sendResultJSON.put("receiving_number", phoneNumber);
            sendResultJSON.put("send_text", message);
            callbackContext.success(sendResultJSON);
            return true;
        } else {
            callbackContext.error(UKNOWN_ACTION_SUPPLIED);
            return false;
        }
    }

    private Boolean sendSMS(String phoneNumber, String message) {
        try {
            SmsManager manager = SmsManager.getDefault();
            PendingIntent sentIntent = PendingIntent.getActivity(cordova.getActivity().getApplicationContext(), 0, new Intent(), 0);
            manager.sendTextMessage(phoneNumber, null, message, sentIntent, null);
        } catch (Exception exp) {
            Log.e(TAG, "Error while sending sms", exp);
            return false;
        }
        return true;
    }
}

