package com.adityawasnik.zeemitricsassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        text = findViewById(R.id.textView);
        button = findViewById(R.id.button);


        MakeVolleyConnection();



    }




    private void MakeVolleyConnection()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);


      //  recyclerViewDataList = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://randomuser.me/api/?results=10", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {
                    JSONArray dataArray = response.getJSONArray("results");
                    System.out.println("dataArray.length() "+dataArray.length());
                   // text.setText("This is second activity");
                 //   text.setText(Arrays.toString(new int[]{dataArray.length()}));

                    text.setText(Arrays.toString(new int[]{dataArray.length()}).replaceAll("\\[|\\]", ""));

                   // text.setText(dataArray.length());


                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent send = new Intent(MainActivity2.this, MainActivity.class);
                            startActivity(send);
                        }
                    });

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, ""+error.networkResponse,Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

}