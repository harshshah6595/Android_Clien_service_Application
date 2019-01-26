package com.example.shahharsh85.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shahharsh85.common.Iproj5;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List x;
    EditText editName, editVal1, editVal2;
    TextView resultView;
    Button butto;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    protected Iproj5 calService = null;
    EditText year1;
    EditText year2;
    EditText year3;
    EditText day3;
    EditText month3;
    EditText gap3;
    Thread t1;
    int num1,num2,num3,num4;
    int bind = 0;
    GameStart1 g1 = new GameStart1();
    public static Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        year1 = (EditText) findViewById(R.id.year1);
        year2 = (EditText) findViewById(R.id.year2);
        year3 = (EditText) findViewById(R.id.year3);
        month3 = (EditText) findViewById(R.id.month3);
        day3 = (EditText) findViewById(R.id.day3);
        gap3 = (EditText) findViewById(R.id.gap3);
        resultView = (TextView) findViewById(R.id.result);
        button1 = (Button) findViewById(R.id.submit1);
        button2 = (Button) findViewById(R.id.submit2);
        button3 = (Button) findViewById(R.id.submit3);
        button = (Button) findViewById(R.id.start);
        butto = (Button) findViewById(R.id.stop);
        button4 = (Button) findViewById(R.id.act);
        t1 = new Thread(g1);
        t1.start();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //connecting with server
                if (bind == 0) {
                    Intent it = new Intent("com.example.shahharsh85.common.Iproj5");
                    it.setPackage("com.example.shahharsh85.server1");
                    it.setComponent(new ComponentName(
                            "com.example.shahharsh85.server1",
                            "com.example.shahharsh85.server1.proj5"));
                    bindService(it, connection, Context.BIND_AUTO_CREATE);
                    bind = 1;
                }
            }
        });

        butto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bind == 1)
                    unbindService(connection);
                Toast.makeText(getApplicationContext(), "Service Disconnected", Toast.LENGTH_SHORT).show();
                bind = 0;
            }
        });


        //calling monthly cash
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ed_text = year1.getText().toString().trim();
                if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null) {
                    Toast.makeText(getApplicationContext()," ENTER VALID DATA", Toast.LENGTH_SHORT).show();
                }else{
                int num5 = Integer.parseInt(year1.getText().toString());

                Message msg = g1.getmHandler1().obtainMessage(1);
                msg.arg1 = num5;
                g1.getmHandler1().sendMessage(msg);
            }}
        });

//yearly avg
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ed_text = year2.getText().toString().trim();
                if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null) {
                    Toast.makeText(getApplicationContext()," ENTER VALID DATA", Toast.LENGTH_SHORT).show();
                }else{

                int num5 = Integer.parseInt(year2.getText().toString());
                Message msg = g1.getmHandler1().obtainMessage(2);
                msg.arg1 = num5;
                g1.getmHandler1().sendMessage(msg);

            }}
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ed_text = year3.getText().toString().trim();
                String ed_text1 = day3.getText().toString().trim();
                String ed_text2 = month3.getText().toString().trim();
                String ed_text3 = gap3.getText().toString().trim();
                if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null||ed_text1.isEmpty() || ed_text1.length() == 0 || ed_text1.equals("") || ed_text1 == null||ed_text2.isEmpty() || ed_text2.length() == 0 || ed_text2.equals("") || ed_text2 == null||ed_text3.isEmpty() || ed_text3.length() == 0 || ed_text3.equals("") || ed_text3 == null) {
                    Toast.makeText(getApplicationContext()," ENTER VALID DATA", Toast.LENGTH_SHORT).show();
                }else{
                 num1 = Integer.parseInt(day3.getText().toString());
                 num2 = Integer.parseInt(month3.getText().toString());
                 num3 = Integer.parseInt(year3.getText().toString());
                 num4 = Integer.parseInt(gap3.getText().toString());
                Message msg = g1.getmHandler1().obtainMessage(3);
                msg.arg1 = num1;
                g1.getmHandler1().sendMessage(msg);
            }}
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), QuoteViewerActivity.class);
                startActivity(i);


            }
        });

    }


    public class GameStart1 implements Runnable {
        Handler mHandler1;

        int result;
int abc=0;
        public void run() {
            Looper.prepare();
            mHandler1 = new Handler() {
                public void handleMessage(Message msg) {
                    x= new ArrayList<String>();;
                    if (bind == 0) {
                        Intent it = new Intent("com.example.shahharsh85.common.Iproj5");
                        it.setPackage("com.example.shahharsh85.server1");
                        it.setComponent(new ComponentName(
                                "com.example.shahharsh85.server1",
                                "com.example.shahharsh85.server1.proj5"));
                        bindService(it, connection, Context.BIND_AUTO_CREATE);
                        bind = 1;
                    }
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int what = msg.what;
                    Log.i("result", String.valueOf(result));
                    switch (what) {
                        case 1:
                            try {
                                QuoteViewerActivity.mTitleArray.add("mcash" + msg.arg1);
                                x= calService.getMcash(String.valueOf(msg.arg1));
                                Log.i("result", x.toString());


                                mHandler.post(new Runnable() {
                                    public void run() {
                                        resultView.setText("");
                                        for(int i=0;i<x.size();i++)
                                        resultView.append(x.get(i).toString());
                                        QuoteViewerActivity.mQuoteArray.add(x.toString());

                                    }

                                });


                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                QuoteViewerActivity.mTitleArray.add("ycash" + msg.arg1);
                               abc = calService.getYcash(String.valueOf(msg.arg1));
                                Log.i("result", x.toString());
                                mHandler.post(new Runnable() {
                                    public void run() {
                                        resultView.setText(String.valueOf(abc));
                                        QuoteViewerActivity.mQuoteArray.add(String.valueOf(abc));
                                    }
                                });

                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                QuoteViewerActivity.mTitleArray.add("dcash" + msg.arg1);
                                x = calService.getdcash(String.valueOf(num1),String.valueOf(num2),String.valueOf(num3),String.valueOf(num4));
                                Log.i("result", x.toString());
                                mHandler.post(new Runnable() {
                                    public void run() {
                                        resultView.setText("");
                                        for(int i=0;i<x.size();i++)
                                            resultView.append(x.get(i).toString());
                                        QuoteViewerActivity.mQuoteArray.add(x.toString());
                                    }
                                });

                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            break;


                    }


                }


            };
            Looper.loop();

        }

        ;

        public Handler getmHandler1() {
            return mHandler1;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bind==1)
        unbindService(connection);
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            calService = Iproj5.Stub.asInterface(service);
            Toast.makeText(getApplicationContext(), "Service Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            calService = null;
            Toast.makeText(getApplicationContext(), "Service Disconnected", Toast.LENGTH_SHORT).show();
        }
    };
}