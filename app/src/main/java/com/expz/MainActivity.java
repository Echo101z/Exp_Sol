package com.expz;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.FirebaseException;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mdatabase;
    private ArrayList<String> list=new ArrayList<>();
    private ArrayAdapter<String>  adapter ;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listview=(ListView)findViewById(R.id.listv);
        mdatabase=FirebaseDatabase.getInstance().getReference();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);  //requires 3 parameter
        listview.setAdapter(adapter);
        final farmer fz=new farmer();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("ree",">>>>>>>>>>>>>>>>>>>>>>>>>"+fz.getPhno());
                Intent in=new Intent(getApplicationContext(),details.class);
               in.putExtra("phno",list.get(i));
                Log.d("sdd",">>>"+list.get(i));
                getApplicationContext().startActivity(in);
            }
        });

        /*mdatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              //  for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
//The key of the question
                //    String que = questionSnapshot.getKey();
                  //Log.d("tag",que);

                //}
                String value=dataSnapshot.getValue().toString();


                list.add(value);
                //user
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        DatabaseReference farmers = mdatabase.child("farmer");
        farmers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                   int i=1;
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ;
                        farmer f = snapshot.getValue(farmer.class);
                       // String fd[]=new String[20];
                            // fd[i]=snapshot.getValue().toString();
                        list.add(i+". "+f.getPhno()+":-"+f.getQuery());
                       i++;
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

      /*class customadapter extends BaseAdapter {


          @Override
          public int getCount() {
              return 0;
          }

          @Override
          public Object getItem(int i) {
              return null;
          }

          @Override
          public long getItemId(int i) {
              return 0;
          }

          @Override
          public View getView(int i, View view, ViewGroup viewGroup) {
              return null;
          }
      }*/


    }
}
