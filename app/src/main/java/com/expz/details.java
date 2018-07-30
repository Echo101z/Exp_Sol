package com.expz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class details extends AppCompatActivity {
TextView phone,query;
EditText edit;
Button bt;
    String mes,ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        phone=(TextView) findViewById(R.id.phon);
        query=(TextView)findViewById(R.id.quer);
        edit=(EditText)findViewById(R.id.ed);
        bt=(Button)findViewById(R.id.send);
        //lis.
        // add()
        Bundle bundle;
        bundle = getIntent().getExtras();

        setTitle(bundle.getCharSequence("phno"));

        String t=bundle.getCharSequence("phno").toString();
        Log.d("dsdd",">>>>>"+t);

        ph=t.substring(3,14);
        String qu=t.substring(15);
        phone.setText(ph);
        query.setText(qu);

        mes=edit.getText().toString();
     //int pho=Integer.parseInt(ph);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("smsto:"+ph));  // This ensures only SMS apps respond
                intent.putExtra("sms_body", mes);
               //intent.putExtra(Intent.EXTRA_STREAM, attachment);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);}
            }
        });












      //  Log.d("dsfsfd",">>>>>>>>>>>>>>>>>>>>>>>>>>"+qu);

    }
}
