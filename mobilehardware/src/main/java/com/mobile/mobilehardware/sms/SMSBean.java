package com.tranos.app.mobilehardware.sms;

import android.util.Log;

import com.tranos.app.mobilehardware.base.BaseBean;
import com.tranos.app.mobilehardware.base.BaseData;

import org.json.JSONObject;


public class SMSBean extends BaseBean {
	
	private static final String TAG = SMSBean.class.getSimpleName();

    private String smsDate;
    private String smsThreadId;
    private String smsId;
    private String smsAddress;
    private String smsBody;
    private String smsName;
    private String smsType;

    public String getSmsDate() { return  smsDate; }
    public String getSmsTheardId() { return  smsThreadId; }
    public String getSmsId() { return  smsId; }
    public String getSmsAdress() { return  smsAddress; }
    public String getSmsBody() { return  smsBody; }
    public String getSmsName() { return  smsName; }
    public String getSmsType() { return  smsType; }

    public void setSmsDate(String smsDate) { this.smsDate = smsDate; }
    public void setSmsTheardId(String smsThreadId) { this.smsThreadId = smsThreadId; }
    public void setSmsId(String smsId) { this.smsId = smsId; }
    public void setSmsAdress(String smsAddress) { this.smsAddress = smsAddress; }
    public void setSmsBody(String smsBody) { this.smsBody = smsBody; }
    public void setSmsName(String smsName) { this.smsName = smsName; }
    public void setSmsType(String smsType) { this.smsType = smsType; }


    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.SMS.SMS_DATE, isEmpty(smsDate));
            jsonObject.put(BaseData.SMS.SMS_THERD_ID, isEmpty(smsThreadId));
            jsonObject.put(BaseData.SMS.SMS_ID, isEmpty(smsId));
            jsonObject.put(BaseData.SMS.SMS_ADRESS, isEmpty(smsAddress));
            jsonObject.put(BaseData.SMS.SMS_BODY, isEmpty(smsBody));
            jsonObject.put(BaseData.SMS.SMS_NAME, isEmpty(smsName));
            jsonObject.put(BaseData.SMS.SMS_NAME, isEmpty(smsType));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
