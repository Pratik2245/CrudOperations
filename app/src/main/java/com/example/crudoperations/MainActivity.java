package com.example.crudoperations;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
AppCompatButton check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check=findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, linkApi.url+"db_connection.php", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                        boolean b=response.getBoolean("success");
                            AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                        if(b)
                        {
                            alert.setMessage("Database Connected").create().show();
                        }
                        else{
                            alert.setMessage("Database not Connected").create().show();
                        }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(objectRequest);
            }
        });


    }
}