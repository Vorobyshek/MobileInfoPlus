package com.tranos.app.mobilehardware.calls;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONObject;

import android.content.ContentResolver;

import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallLogsInfo {
	
    private static final String TAG = CallLogsInfo.class.getSimpleName();

    public static List<JSONObject> getMobCallLogs(Context context) {
        List<JSONObject> callLogsList = new ArrayList<>();
        try {
            String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
            Uri callUri = Uri.parse("content://call_log/calls");
            Cursor cur = context.getApplicationContext().getContentResolver().query(callUri, null, null, null, strOrder);

            while (cur.moveToNext()) {
                CallLogsBean listCallLogsBean = new CallLogsBean();
                listCallLogsBean.setCallNumber(cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.NUMBER)));
                listCallLogsBean.setCallName(cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME)));
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                listCallLogsBean.setCallDate(formatter.format(new Date(Long.parseLong(cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.DATE))))));
                listCallLogsBean.setCallDuration(cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.DURATION)));
                if (cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.TYPE)) == "1") {
                    listCallLogsBean.setCallType("Incoming");
                } else {
                    listCallLogsBean.setCallType("Outgoing");
                }
                callLogsList.add(listCallLogsBean.toJSONObject());
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return callLogsList;
    }
}
