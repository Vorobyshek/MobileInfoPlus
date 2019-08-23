package com.tranos.app.mobilehardware.sms;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONObject;

import android.content.ContentResolver;

import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SMSInfo {

	private static final String TAG = SMSInfo.class.getSimpleName();
	
	public static List<JSONObject> getMobSMS(Context context) {
        List<JSONObject> SmsList = new ArrayList<>();
        try {
            Uri callUri = Uri.parse("content://sms/inbox");
            Cursor cur = context.getApplicationContext().getContentResolver().query(callUri, null, null, null, null);
            while (cur.moveToNext()) {
                SMSBean listSMSBean = new SMSBean();
                listSMSBean.setSmsAdress(cur.getString(cur.getColumnIndex("address")));
                listSMSBean.setSmsBody(cur.getString(cur.getColumnIndex("body")));
                listSMSBean.setSmsId(cur.getString(cur.getColumnIndex("_id")));
                listSMSBean.setSmsType("inbox");
                listSMSBean.setSmsName(getContactName(context.getApplicationContext(), cur.getString(cur.getColumnIndexOrThrow("address"))));
                listSMSBean.setSmsTheardId(cur.getString(cur.getColumnIndex("thread_id")));
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                listSMSBean.setSmsDate(formatter.format(new Date(Long.parseLong(cur.getString(cur.getColumnIndex("date"))))));
                SmsList.add(listSMSBean.toJSONObject());
            }

            callUri = Uri.parse("content://sms/sent");
            cur = context.getApplicationContext().getContentResolver().query(callUri, null, null, null, null);
            while (cur.moveToNext()) {
                SMSBean listSMSBean = new SMSBean();
                listSMSBean.setSmsAdress(cur.getString(cur.getColumnIndex("address")));
                listSMSBean.setSmsBody(cur.getString(cur.getColumnIndex("body")));
                listSMSBean.setSmsId(cur.getString(cur.getColumnIndex("_id")));
                listSMSBean.setSmsType("sent");
                listSMSBean.setSmsName(getContactName(context.getApplicationContext(), cur.getString(cur.getColumnIndexOrThrow("address"))));
                listSMSBean.setSmsTheardId(cur.getString(cur.getColumnIndex("thread_id")));
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                listSMSBean.setSmsDate(formatter.format(new Date(Long.parseLong(cur.getString(cur.getColumnIndex("date"))))));
                SmsList.add(listSMSBean.toJSONObject());
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return SmsList;
    }

    public static String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri,
                new String[] { ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return contactName;
    }

}
