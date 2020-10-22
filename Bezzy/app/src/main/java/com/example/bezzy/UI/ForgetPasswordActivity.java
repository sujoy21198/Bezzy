package com.example.bezzy.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bezzy.R;
import com.example.bezzy.Utils.APIs;
import com.example.bezzy.Utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPasswordActivity extends AppCompatActivity {
    ImageView imageView;
    EditText editText;
    Button button;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        imageView=findViewById(R.id.back_image);
        editText=findViewById(R.id.email_send);
        button=findViewById(R.id.btn_logedin);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emailsend();

            }
        });
        progressDialog = new ProgressDialog(ForgetPasswordActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Please Wait..");
    }
    public void Emailsend(){
        if(Utility.internet_check(ForgetPasswordActivity.this)) {
            progressDialog.show();
            callAPIEmailSend(APIs.BASE_URL+ APIs.FORGETPASSWORDSEND);
        }
        else {
            progressDialog.dismiss();
            Toast.makeText(ForgetPasswordActivity.this,"No Network!",Toast.LENGTH_SHORT).show();
        }


    }
    private void callAPIEmailSend(String url){
        final StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("resp").equals("true")){
                        progressDialog.dismiss();
                        Toast.makeText(ForgetPasswordActivity.this,jsonObject.getString("reg_msg"),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgetPasswordActivity.this,COTPActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Utility.setOtpScreen(ForgetPasswordActivity.this,"1");
                        Utility.setUserId(ForgetPasswordActivity.this,jsonObject.getString("log_userID"));

                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(ForgetPasswordActivity.this,jsonObject.getString("reg_msg"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Error",error.toString());

            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("email",editText.getText().toString());
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(ForgetPasswordActivity.this);
        queue.add(stringRequest);

    }
}