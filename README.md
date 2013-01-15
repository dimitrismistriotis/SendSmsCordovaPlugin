SendSmsCordovaPlugin
====================

A plugin to send SMSes using apache Cordova, developed against version 2.3.0

Setup
-----

1. Copy Java files from src folder to the src folder or your project.
2. In config.xml (under "res/xml"), register the plugin (see also "res/xml/config.additional.xml"):
    <plugin name="SendSms" value="net.webootu.cordova.plugin.SendSms" />
3. Add the following lines in your AndroidManifest.additional.xml (see also AndroidManifest.additional.xml):
```<uses-permission android:name="android.permission.SEND_SMS"/>```
        
4. Call can be made using the [Cordova plugin invocation/interface](http://docs.phonegap.com/en/2.3.0/guide_plugin-development_index.md.html#Plugin%20Development%20Guide):
```cordova.exec(function(winParam) {}, function(error) {}, "SendSms", 
"SendSms", ["phoneNumber", "text message"]);```
For the JavaScript call, first parameter the success callback, followed by the error callback, then the service, followed by the action. Finally we have the invocation parameters for the plugin as an array with the phone number first and the text second. There is a sample execution in "assets/www/index.sample.html".

Notes
-----
In setup, (1) Is the actual plugin code, (3) gives the application the permission to send a text/SMS.

As we can see in the Java code, there is a "magic" phone number, "TESTNUM" (also in the sample execution). Using this will always trigger the successful callback, without sending any text message anywhere.

Callback functions receive a json object with three properties: sms_send (true when calling successful dispatch, false if something has gone wrong), receiving_number (the number the text/SMS was distributed to) and send_text (the text send).

License
-------
Please refer to LICENSE file in this repository.

Note
----
Initially by Dimitrios of [WeBootU](http://www.webootu.com) please consider us for your Cordova related projects.

