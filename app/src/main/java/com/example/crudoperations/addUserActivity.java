package com.example.crudoperations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONException;
import org.json.JSONObject;

public class addUserActivity extends AppCompatActivity {
MaterialToolbar toolbar;
AppCompatButton register,View;
AppCompatEditText roll,division,enrollmentNo,nameOfStudent,gender,category,mentor,password;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        toolbar=findViewById(R.id.toolbar);
        register=findViewById(R.id.register);
        roll=findViewById(R.id.roll);
        division=findViewById(R.id.division);
        enrollmentNo=findViewById(R.id.enrollmentNo);
        nameOfStudent=findViewById(R.id.nameOfStudent);
        gender=findViewById(R.id.gender);
        category=findViewById(R.id.category);
        mentor=findViewById(R.id.mentor);
        password=findViewById(R.id.password);
        View=findViewById(R.id.View);
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(addUserActivity.this,FetchActivity.class));
            }
        });

  register.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          addUsers();
      }
  });
    }

    private void addUsers() {
        String roll1=roll.getText().toString();
        String div=division.getText().toString();
        String eno=enrollmentNo.getText().toString();
        String name=nameOfStudent.getText().toString();
        String gen=gender.getText().toString();
        String cat=category.getText().toString();
        String men=mentor.getText().toString();
        String pass=password.getText().toString();
        Log.d("",""+linkApi.url+"addUsers.php?RollNo="+roll1+"&Division="+
                div+"&EnrollmentNo="+eno+"&Name_of_student="+name+"" +
                "&Gender="+gen+"&Category="+cat+"&Mentor="+men+"&Password="+pass);
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, linkApi.url+"addUsers.php?RollNo="+roll1+"&Division="+
                div+"&EnrollmentNo="+eno+"&Name_of_student="+name+"&Gender="+gen+"&Category="+cat+"&Mentor="+men+"&Password="+pass, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean bol=response.getBoolean("success");
                    if(bol)
                    {
                        Toast.makeText(addUserActivity.this, "records Inserted", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(addUserActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(addUserActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(objectRequest);
    }
}