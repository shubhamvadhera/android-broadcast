package com.shubhamvadhera.www.bcreceiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String currency;
    public static String amount;

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

    @Override
    protected void onStart() {
        super.onStart();
        currency = getIntent().getExtras().getString("CURRENCY");
        amount = getIntent().getExtras().getString("AMOUNT");
        TextView tv = (TextView)findViewById(R.id.textViewAmount);
        tv.setText("Dollar Amount: $" + amount);
        tv = (TextView)findViewById(R.id.textViewCurrency);
        tv.setText("Convert To: " + currency);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendBroadcastReply (View view) {
        Log.d("Debug","Entering sendBroadcastReply");
        Double amt = Double.parseDouble(amount);
        Double tgt = 0.0;
        if (currency.equals("British Pound")) tgt = amt*0.71;
        else if (currency.equals("Indian Rupee")) tgt = amt*69;
        else tgt = amt*0.92;
        String amountConvert = tgt.toString();
        Log.d("Debug","Converted amount " + amountConvert);
        Intent intent = new Intent();
        intent.setAction("com.shubhamvadhera.www.bcreceiver");
        intent.putExtra("CURRENCY", currency);
        intent.putExtra("AMOUNT", amountConvert);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
        finish();
    }
}
