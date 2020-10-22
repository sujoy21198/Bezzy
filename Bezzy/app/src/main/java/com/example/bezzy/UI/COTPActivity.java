package com.example.bezzy.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.mukesh.OtpView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class COTPActivity extends AppCompatActivity {
    Button btnVerify;
    OtpView otp_view;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_o_t_p);
    }
    private void initViews() {

        btnVerify = findViewById(R.id.btnVerifyy);
        otp_view = findViewById(R.id.otp_vieww);
        progressDialog = new ProgressDialog(COTPActivity.this);
        progressDialog.setMessage("Verifying Please Wait...");
        progressDialog.setCancelable(false);


        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Utility.internet_check(COTPActivity.this)) {

                    progressDialog.show();
                    callApiVerifyOtp(APIs.BASE_URL+APIs.OTPVERIFICATION);

                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(COTPActivity.this,"No Network!",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void callApiVerifyOtp(String url) {

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Response",response);

                try {
                    JSONObject object = new JSONObject(response);

                    String status = object.getString("resp");

                    if(status.equals("true")){

                        progressDialog.dismiss();

                        String message = object.getString("message");

                        Toast.makeText(COTPActivity.this,message,Toast.LENGTH_SHORT).show();

                        Utility.setOtpScreen(COTPActivity.this,"0");
                        Intent intent = new Intent(COTPActivity.this, ChangepasswordActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }else{
                        String message = object.getString("message");
                        progressDialog.dismiss();
                        Toast.makeText(COTPActivity.this,message,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    Log.e("Exception",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Error",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("otp_code",otp_view.getText().toString());
                map.put("userID", Utility.getUserId(COTPActivity.this));
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(COTPActivity.this);
        queue.add(request);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }
}