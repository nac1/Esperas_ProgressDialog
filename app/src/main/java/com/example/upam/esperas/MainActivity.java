package com.example.upam.esperas;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
   ProgressDialog bar;
   Handler updateH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

   public void Ring(View v)
   {
       final ProgressDialog ring=ProgressDialog.show(this,"Espere por favor..","Descargando algo",true);
       ring.setCancelable(true);

       new Thread(new Runnable() {
           @Override
           public void run() {

               try {
                   Thread.sleep(3000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               ring.dismiss();

           }
       }).start();


   }



    public void Bar(View v)
    {
        bar=new ProgressDialog(this);

        bar.setTitle("Descargando...");
        bar.setMessage("Desacarga en progreso");
        bar.setProgressStyle(bar.STYLE_HORIZONTAL);
        bar.setProgress(0);
        bar.setMax(100);
        bar.show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                while(bar.getProgress()<=bar.getMax()){

                        Thread.sleep(1000);
                        Message msg=new Message();
                        msg.obj=2;
                        updateH.sendMessage(msg);

                       if(bar.getProgress()== bar.getMax())
                           bar.dismiss();
                }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        updateH=new Handler(){
            public  void handleMessage(Message msg)
            {
                bar.incrementProgressBy((Integer) msg.obj);
            }
        };


    }





}
