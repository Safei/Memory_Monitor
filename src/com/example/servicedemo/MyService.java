package com.example.servicedemo;

import android.app.Service;  
import android.content.Intent;  
import android.os.Binder;  
import android.os.IBinder;  
import android.text.format.Time;  
import android.util.Log;  

public class MyService extends Service {  
    //�����һ��Tag��ǩ  
    private static final String TAG = "MyService";
    
    //service����������
    @Override  
    public void onCreate() {  
        Log.e(TAG, "start onCreate~~~");  
        super.onCreate();  
    }  
      
    @Override  
    public void onStart(Intent intent, int startId) {  
    	String s1 = intent.getStringExtra("key");  //���ܲ���
        Log.e(TAG, "start onStart~~~" + s1);  
        super.onStart(intent, startId);   
    }  
      
    @Override  
    public void onDestroy() {  
        Log.e(TAG, "start onDestroy~~~");  
        super.onDestroy();  
    }  
      
    @Override  
    public IBinder onBind(Intent intent) {  
        Log.e(TAG, "start IBinder~~~");  
        return mBinder;       //������һ��ʵ���� IBinder �ӿڵĶ�������������ڰ�Service �� Activity �� Local Service ͨ��
    } 
    
    @Override  
    public boolean onUnbind(Intent intent) {  
        Log.e(TAG, "start onUnbind~~~");  
        return super.onUnbind(intent);  
    }  
      
    //��service���ṩ���÷������������
    public String getSystemTime(){  
          
        Time t = new Time();  
        t.setToNow();  
        return t.toString();  
    }  
    
    //ֱ�Ӽ̳� Binder ������ IBinder,��Ϊ Binder ʵ���� IBinder �ӿڣ��������ǿ��������ܶ๤����
    public class MyBinder extends Binder{  
    	public MyService getService()  
        {  
            return MyService.this;  //��ȡ Service ʵ��
        }  
    }  
  //������MyBinder��ʵ���Թ���onbind�з���
    private MyBinder mBinder = new MyBinder();  
    
     
}  
