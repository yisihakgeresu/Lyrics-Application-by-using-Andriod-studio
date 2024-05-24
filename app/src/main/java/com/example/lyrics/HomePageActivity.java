package com.example.lyrics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class HomePageActivity extends AppCompatActivity {
    LinearLayout card,card2;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View header;
    ImageView menu;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        card=findViewById(R.id.card);
//        card2=findViewById(R.id.card2);
        menu=findViewById(R.id.menu);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.nabView);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   finish();
                Intent intent=new Intent(HomePageActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
//        card2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               // finish();
//                Toast.makeText(context, "Empty View !!!", Toast.LENGTH_SHORT).show();
////                Intent intent=new Intent(HomePageActivity.this,MainActivity.class);
////                startActivity(intent);
//            }
//        });

//    header = navigationView.getHeaderView(0);
    header=navigationView.getHeaderView(0);
        menu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
    });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    finish();
                    Intent intents = new Intent(HomePageActivity.this, HomePageActivity.class);
                    startActivity(intents);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.share:
                    String shareBody = "Hey I am using Best Lyrics Application";
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.rate:
                    AlertDialog.Builder builders = new AlertDialog.Builder(HomePageActivity.this);
                    builders.setTitle("Rate Our App")
                            .setMessage("If you enjoy using our app, please take a moment to rate it on the Google Play Store.")
                            .setPositiveButton("Rate Now", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                                }
                            })
                            .setNegativeButton("Maybe Later", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.exit:
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);
                    builder.setTitle("Alert!!")
                            .setMessage("Do you want to exit?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
            return true;
        }
    });

}
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}