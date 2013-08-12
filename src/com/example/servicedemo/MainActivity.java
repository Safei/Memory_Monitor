package com.example.servicedemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{  
     
    private MyService  mMyService;  
    private TextView mTextView;  
    private Button startServiceButton;  
    private Button stopServiceButton;  
    private Button bindServiceButton;  
    private Button unbindServiceButton;  
    private Button startAnotherActivity;
    private Context mContext;  
    private Button btnInternal1,btnInternal2,btnSystem;
    
    
  //�Զ��巢�͵Ĺ㲥����
    static final String INTENAL_ACTION_1 = "com.example.servicedemo.Internal_1";  
    static final String INTENAL_ACTION_2 = "com.example.servicedemo.Internal_2";  
    static final String INTENAL_ACTION_3 = "com.example.servicedemo.Internal_3"; 
    static final String LOG = "mainActivity"; 
      
    //������Ҫ�õ�ServiceConnection��Context.bindService��context.unBindService()���õ�  
    private ServiceConnection mServiceConnection = new ServiceConnection() { 
    	
        //����bindServiceʱ����TextView��ʾMyService��getSystemTime()�����ķ���ֵ   
        public void onServiceConnected(ComponentName name, IBinder service) {  
            // TODO Auto-generated method stub  
            mMyService = ((MyService.MyBinder)service).getService();  
            mTextView.setText("I am frome Service :" + mMyService.getSystemTime());  
        }  
          
        public void onServiceDisconnected(ComponentName name) {  
            // TODO Auto-generated method stub  
              
        }  
    };  
    
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        Log.e(LOG,"onCreate");
        setContentView(R.layout.main);    
        setupViews();  
        
        
    
    }  
    @Override
    public void onStart() {
    	super.onStart();
    	Log.e(LOG,"onStart");
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	Log.e(LOG,"onResume");
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	Log.e(LOG,"onPause");
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	Log.e(LOG,"onStop");
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	Log.e(LOG,"onDestroy");
    }
    //sdCard��С 
//    public long[] getSDCardMemory() {  
//        long[] sdCardInfo=new long[2];  
//        String state = Environment.getExternalStorageState();  
//        if (Environment.MEDIA_MOUNTED.equals(state)) {  
//            File sdcardDir = Environment.getExternalStorageDirectory();  
//            StatFs sf = new StatFs(sdcardDir.getPath());  
//            long bSize = sf.getBlockSize();  
//            long bCount = sf.getBlockCount();  
//            long availBlocks = sf.getAvailableBlocks();  
//  
//            sdCardInfo[0] = bSize * bCount;//�ܴ�С  
//            sdCardInfo[1] = bSize * availBlocks;//���ô�С  
//        }  
//        return sdCardInfo;  
//    }  
      
    
    
    public void setupViews(){  
      
        mContext = MainActivity.this;   //����ǰactivity
        mTextView = (TextView)findViewById(R.id.text);  
          
          
        
        
        //ͨ��id�ҵ�button  
        startServiceButton = (Button)findViewById(R.id.startservice);  
        stopServiceButton = (Button)findViewById(R.id.stopservice);  
        bindServiceButton = (Button)findViewById(R.id.bindservice);  
        unbindServiceButton = (Button)findViewById(R.id.unbindservice);  
        startAnotherActivity = (Button)findViewById(R.id.startAnotherActivity); 
        
        //�㲥button
        btnInternal1 = (Button)findViewById(R.id.Button01);
        btnInternal2 = (Button)findViewById(R.id.Button02);
        btnSystem = (Button)findViewById(R.id.Button03);
        
        //�����¼�����
        startServiceButton.setOnClickListener(this);  
        stopServiceButton.setOnClickListener(this);  
        bindServiceButton.setOnClickListener(this);  
        unbindServiceButton.setOnClickListener(this); 
        startAnotherActivity.setOnClickListener(this);
        
        btnInternal1.setOnClickListener(this);
        btnInternal2.setOnClickListener(this);
        btnSystem.setOnClickListener(this);
        
        
      //ʵ����������������Ҫ���˵Ĺ㲥
        IntentFilter intentFilter = new IntentFilter();
        
      //��̬ע��㲥��Ϣ  
        registerReceiver(bcrIntenal1, intentFilter); 
        
      //���ɹ㲥����  
        //clsReceiver2 = new clsReceiver2();  
        //ʵ����������������Ҫ���˵Ĺ㲥  
//        IntentFilter smsintentFilter = new IntentFilter();  
//        smsintentFilter.addAction(SMS_ACTION);  
//          
//        //ע��㲥  
//        registerReceiver(smsBroadCastReceiver, intentFilter);
        
    }  
    
    //service��activityͨ��
    public void onClick(View v) {  
        // TODO Auto-generated method stub  
        if(v == startServiceButton){  
            Intent i  = new Intent();  
            i.setClass(MainActivity.this, MyService.class);  //����.calss�ķ�ʽ��ȡClassʵ��
            i.putExtra("key", "diliver params");
            mContext.startService(i);   //ʵ����ת
        }else if(v == stopServiceButton){  
            Intent i  = new Intent();  
            i.setClass(MainActivity.this, MyService.class);  
            mContext.stopService(i);  
        }else if(v == bindServiceButton){  
            Intent i  = new Intent();  
            i.setClass(MainActivity.this, MyService.class);   
            mContext.bindService(i, mServiceConnection, BIND_AUTO_CREATE);  //mServiceConnection������Ϊ�ص������� Context.BIND_AUTO_CREATE��ʾ�����Զ���
        }else if(v == unbindServiceButton){  
            mContext.unbindService(mServiceConnection);  
        }else if(v == startAnotherActivity) {
        	Intent i = new Intent("android.intent.action.IntentServiceActivity");
        	//i.setAction("com.example.servicedemo.IntentServiceDemoActivity");
        	startActivity(i);       //����action name����������һ��activitys
        }else if(v == btnInternal1){                        //����̬ע���BroadcastReceiver��������
        	Intent intent = new Intent(INTENAL_ACTION_1);
        	sendBroadcast(intent);                         //���͹㲥
        }else if(v == btnInternal2){                        //����̬ע���BroadcastReceiver��������
        	Intent intent = new Intent(INTENAL_ACTION_2);
        	sendBroadcast(intent);
        }else if(v == btnSystem){                           //��̬ע�� ����2����Ϣ��BroadcastReceiver
        	IntentFilter filter = new IntentFilter();
        	filter.addAction(Intent.ACTION_BATTERY_CHANGED); //ϵͳ���������Ϣ
        	filter.addAction(INTENAL_ACTION_3);//�������Զ�����Ϣ  
            registerReceiver(batInfoReceiver, filter);  //ע��㲥 
              
            Intent intent = new Intent(INTENAL_ACTION_3);  
            intent.putExtra("Name", "hellogv");  
            intent.putExtra("Blog", "http://blog.csdn.net/hellogv");  
            sendBroadcast(intent);       //���ݹ�ȥ 
        }  
    }  
      
    /* 
     * ���ն�̬ע��㲥��BroadcastReceiver(�㲥������) 
     */  
    private BroadcastReceiver bcrIntenal1 = new BroadcastReceiver() {  
          
        public void onReceive(Context context, Intent intent) {  
            String action = intent.getAction();  
            Toast.makeText(context, "��̬:"+action, 1000).show();  
        }  
    };  
    
    private BroadcastReceiver batInfoReceiver = new BroadcastReceiver() {  
        
        public void onReceive(Context context, Intent intent) {  
            String action = intent.getAction();  
            //�����׽����action��ACTION_BATTERY_CHANGED  
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {  
                //��δ֪Intent���������ݣ�����Ҫͨ�����·������о�  
                Bundle b=intent.getExtras();  
                Object[] lstName=b.keySet().toArray();  
  
                for(int i=0;i<lstName.length;i++)  
                {  
                    String keyName=lstName[i].toString();  
                    Log.e(keyName,String.valueOf(b.get(keyName)));  
                }  
            }  
            //�����׽����action��INTENAL_ACTION_3  
            if (INTENAL_ACTION_3.equals(action)) {  
                //��δ֪Intent���������ݣ�����Ҫͨ�����·������о�  
                Bundle b=intent.getExtras();  
                Object[] lstName=b.keySet().toArray();  
  
                for(int i=0;i<lstName.length;i++)  
                {  
                    String keyName=lstName[i].toString();  
                    Log.e(keyName,b.getString(keyName));  
                }  
            }  
        }  
    };  
      
}  