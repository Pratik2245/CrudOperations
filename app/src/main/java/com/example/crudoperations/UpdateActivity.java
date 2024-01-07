package com.example.crudoperations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {
AppCompatEditText roll,nameOfStudent,mentor,division,enrollmentNo;
Button update;
Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        roll=findViewById(R.id.roll);
        nameOfStudent=findViewById(R.id.nameOfStudent);
        mentor=findViewById(R.id.mentor);
        division=findViewById(R.id.division);
        enrollmentNo=findViewById(R.id.enrollmentNo);
        update=findViewById(R.id.Update);
        toolbar=findViewById(R.id.toolbar);


        //settting to edittext
        roll.setText(getIntent().getStringExtra("roll"));
        nameOfStudent.setText(getIntent().getStringExtra("name"));
        division.setText(getIntent().getStringExtra("division"));
        mentor.setText(getIntent().getStringExtra("mentor"));
        enrollmentNo.setText(getIntent().getStringExtra("enroll"));

//        Log.d("tag",""+roll.toString());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(UpdateActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                String roll1= Objects.requireNonNull(roll.getText()).toString();
                String enrollment1= Objects.requireNonNull(enrollmentNo.getText()).toString();
                String div1= Objects.requireNonNull(division.getText()).toString();
                String mentor1= Objects.requireNonNull(mentor.getText()).toString();
                String name1= Objects.requireNonNull(nameOfStudent.getText()).toString();

                JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, linkApi.url + "update.php?RollNo=" + roll1 + "&Division=" +
                        div1 + "&EnrollmentNo=" + enrollment1 + "&Name_of_student=" + name1 + "&Mentor=" + mentor1, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean b=response.getBoolean("success");
                            if(b)
                            {
                                Toast.makeText(UpdateActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(UpdateActivity.this,FetchActivity.class);
                                startActivity(i) ;
                            }
                            else{
                                Toast.makeText(UpdateActivity.this, "Records Not deleted", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(objectRequest);
            }
        });

//hello

    }
}