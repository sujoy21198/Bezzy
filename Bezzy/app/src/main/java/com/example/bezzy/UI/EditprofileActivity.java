package com.example.bezzy.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.bezzy.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class EditprofileActivity extends AppCompatActivity {
    TextInputEditText ed_name, ed_username, ed_email, ed_password, ed_cnfpasswd,ed_dob;
    Spinner spinner;
    private String str_gender;
    Button btn_register;
    ImageView imageView;
    DatePickerDialog datePickerDialog;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        ed_dob = findViewById(R.id.dob);
        spinner = findViewById(R.id.spinner);
        ed_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(EditprofileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = month + " - " + dayOfMonth + " - " + year;
                        ed_dob.setText(date);

                    }
                },month,day,year);
                datePickerDialog.show();

            }
        });
    }
}