package com.example.crudoperations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class FetchActivity extends AppCompatActivity {
MaterialToolbar toolbar;
RecyclerView recycle;
SearchView search;
TextView text;
List<view_class> filterList;
List<view_class> list=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);
        toolbar=findViewById(R.id.toolbar);
        recycle=findViewById(R.id.recycle);
        search=findViewById(R.id.search);
        search.clearFocus();
//        text=findViewById(R.id.text);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        view();

        //search view listener
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }



    private void view() {
        StringRequest request=new StringRequest(Request.Method.GET, linkApi.url+"view.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
//                    text.setText(response);
                    JSONArray array = new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject jsonObject=array.getJSONObject(i);
                        list.add(new view_class(
                                jsonObject.getString("RollNo"),
                                jsonObject.getString("Division"),
                                jsonObject.getString("EnrollmentNo"),
                                jsonObject.getString("Name_of_student"),
                                jsonObject.getString("Gender"),
                                jsonObject.getString("Category"),
                                jsonObject.getString("Mentor"),
                                jsonObject.getString("Password")

                        ));
                    }
                    adapter adapter=new adapter(FetchActivity.this,list);
                    recycle.setAdapter(adapter);
                } catch (JSONException e) {
                    Toast.makeText(FetchActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

    //searchView implementation
    private void filterList(String newText) {
        filterList = new ArrayList<>();
        for (view_class viewClass : list) {
            if (viewClass.getNameOfStudent().toLowerCase().contains(newText.toLowerCase())) {
                filterList.add(viewClass);
            }
        }
        if (filterList.isEmpty()) {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter adapter = new adapter(FetchActivity.this, filterList);
            recycle.setAdapter(adapter); // Set the filtered list to the RecyclerView
        }
    }

}