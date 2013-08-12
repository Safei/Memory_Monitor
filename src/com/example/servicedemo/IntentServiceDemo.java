package com.example.servicedemo;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.view.View.OnClickListener;

//IntentService�̳���Service
public class IntentServiceDemo extends IntentService {
	private static final String TAG = "IntentServiceDemo";  
    private static final SimpleDateFormat SDF_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS");  
  
    //�̳�IntentServiceʱ�������ṩһ���޲ι��캯�������ڸù��캯���ڣ���Ҫ���ø���Ĺ��캯��
    public IntentServiceDemo() {  
        super(TAG);  
        Log.e(TAG, " ----> constructor");  
    }  
  
    @Override  
    public void onCreate() {  
        super.onCreate();  
  
        // ��ӡ����Service�����̵߳�ID  
        long id = Thread.currentThread().getId();  
        Log.e(TAG, " ----> service��:onCreate() in thread id: "+ id);  //���߳�(ui)��id
    }  
  
    @Override  
    public void onDestroy() {  
        super.onDestroy();  
        Log.e(TAG, " ----> onDestroy()");                         //�����е�����(Intent)����ִ�����Ժ���Զ�ֹͣ����
    }  
  
    @Override  
    public int onStartCommand(Intent intent, int flags, int startId) {  
    	Log.e(TAG, " ----> onStartCommand()");  
    	Log.e("flags", "flags" + flags);
    	Log.e("startId", "startId" + startId);
        // ��¼���ʹ������ʱ��  
        intent.putExtra("time", System.currentTimeMillis());  //����һ��Ĭ�ϵ��������̻߳�������Ĺ������߳���ִ�����д����� onStartCommand() ������Intetnt
        return super.onStartCommand(intent, flags, startId);  //�ṩ��һ��onStartCommand()������Ĭ��ʵ�֣�����Intent�ȴ������������У�
    }                                                         //Ȼ��ӹ���������ÿ��ȡ��һ��������onHandleIntent()������ͬһʱ��ֻ����һ��Intent����,�ڸ÷����ж�Intent����Ӧ�Ĵ���
  
    @Override  
    public void setIntentRedelivery(boolean enabled) {  
    	Log.e(TAG, " ----> setIntentRedelivery()");  
        super.setIntentRedelivery(enabled);  
    }  
  
    @Override  
    protected void onHandleIntent(Intent intent) {  
        // ��ӡ������intent���õ��̵߳�ID  
        long id = Thread.currentThread().getId();  
        Log.e(TAG," ----> onHandleIntent() in thread id: " + id);  
        long time = intent.getLongExtra("time", 0);  
        Date date = new Date(time);  
        try {  
            // ��ӡ��ÿ�������Ӧ�Ĵ���ʱ��  
        	Log.e(TAG," ----> onHandleIntent(): �����ļ���..." + SDF_DATE_FORMAT.format(date));  
            Thread.sleep(3000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
  
}
