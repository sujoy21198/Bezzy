    package com.example.bezzy.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bezzy.Controller.SessionManager;
import com.example.bezzy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

    public class ProfileActivity extends AppCompatActivity {
        Button btn_logout;
        SessionManager sessionManager;
        String url = "http://bezzy.websteptech.co.uk/api/logout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_profile);


        //SessionManager.getInstance(getApplicationContext()).userLogout();
        BottomNavigationView btmnav = findViewById(R.id.bottomnav);
        btmnav.setOnNavigationItemSelectedListener(navlistner);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new HomeFragment()).commit();
       /* NavigationView navigationView = findViewById(R.id.bottomnav);
        //navigationView.setNavigationItemSelectedListener(this);
        navigateToFragment(new HomeFragment());*/
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

        private BottomNavigationView.OnNavigationItemSelectedListener navlistner=new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.menu_chat:
                        selectedFragment = new ChatFragment();
                        break;
                    case R.id.menu_search:
                        selectedFragment = new SearchFragment();
                        break;
                    case R.id.menu_profile:
                        selectedFragment = new ProfileFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, selectedFragment).commit();
                return true;
            }
        };

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater=getMenuInflater();
            inflater.inflate(R.menu.top_menu_bar,menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.hello:
                    return  true;
                case R.id.logoutt:
                    logout();
                    return  true;
                default:
                    return super.onOptionsItemSelected(item);
            }

        }

        public void logout(){
            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
            builder.setTitle("Log out");
            builder.setMessage("Are you sure to Log out?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        /*sessionManager.userLogin(false);
                        sessionManager.userLogin("");*/
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
}