package com.example.crudoperations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    Context context;
    static List<view_class> list;
    public adapter(Context context,List<view_class> list)
    {
        this.context=context;
        this.list=list;
    }

    //searchview
    @SuppressLint("NotifyDataSetChanged")
    public void setFilterList(List<view_class> Filteredlist)
    {
        this.list=Filteredlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.student_layout_recycleview,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     final  view_class viewClass=list.get(position);
     holder.roll1.setText(viewClass.getRoll());
        holder.division1.setText(viewClass.getDivision());
        holder.enrollmentNo1.setText(viewClass.getEnrollmentNo());
        holder.Mentor1.setText(viewClass.getMentor());
        holder.nameOfStudent1.setText(viewClass.getNameOfStudent());
//        update button onclick listener
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(context,UpdateActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("roll",viewClass.getRoll());
            i.putExtra("name",viewClass.getNameOfStudent());
            i.putExtra("division",viewClass.getDivision());
            i.putExtra("mentor",viewClass.getMentor());
            i.putExtra("enroll",viewClass.getEnrollmentNo());
            context.startActivity(i);
            }
        });
        //delete button onclick listener
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, linkApi.url+"delete.php?RollNo="+viewClass.getRoll(), null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                 try{
                  boolean bol=response.getBoolean("success");
                  if(bol)
                     {
                         Toast.makeText(context, "Record Deleted", Toast.LENGTH_SHORT).show();
                         Intent intent=new Intent(context,FetchActivity.class);
                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         context.startActivity(intent);
                         ((FetchActivity) context).finish();
                     }
                  else{
                      Toast.makeText(context, "Record Not Deleted", Toast.LENGTH_SHORT).show();
                  }
                 }
                 catch (Exception e)
                 {
                     Log.d("",""+e);
                 }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                Volley.newRequestQueue(context).add(objectRequest);
            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
TextView roll1,division1,enrollmentNo1,Mentor1,nameOfStudent1;
Button delete,update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roll1=itemView.findViewById(R.id.roll1);
            division1=itemView.findViewById(R.id.division1);
            enrollmentNo1=itemView.findViewById(R.id.enrollmentNo1);
            Mentor1=itemView.findViewById(R.id.Mentor1);
            nameOfStudent1=itemView.findViewById(R.id.nameOfStudent1);
            delete=itemView.findViewById(R.id.delete);
            update=itemView.findViewById(R.id.update);



        }
    }
}
