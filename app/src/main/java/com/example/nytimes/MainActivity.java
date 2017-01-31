package com.example.nytimes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String URL = "https://api.nytimes.com/svc/topstories/v2/home.json?api-key=934c57f3c8414e3d8ff28092775d71b8";
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private List<DataModel> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        myAdapter = new MyAdapter(dataList);
        recyclerView.setAdapter(myAdapter);

        prepareJsonRequest();
    }

    private void prepareJsonRequest(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            dataList = MyAdapter.parsejson(response);
                            myAdapter.setDataList((ArrayList<DataModel>) dataList);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                "Network error", Toast.LENGTH_SHORT).show();
                    }
                });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


    }
}