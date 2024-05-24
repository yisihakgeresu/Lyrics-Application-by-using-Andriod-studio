package com.example.lyrics;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<SongModel> originalList;
    private List<SongModel> filteredList;
    private ArrayList<SongModel> list;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View header;
    ImageView menu;
    SongAdapter adapter;
    AlertDialog.Builder biulder;
    SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        menu = findViewById(R.id.menu);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nabView);
        searchView = findViewById(R.id.searchView);

        list = new ArrayList<>();
        originalList = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new SongAdapter(this, list);
        recyclerView.setAdapter(adapter);

        addSongsToList();
        header = navigationView.getHeaderView(0);
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
                        Intent intents = new Intent(MainActivity.this, HomePageActivity.class);
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
                        AlertDialog.Builder builders = new AlertDialog.Builder(MainActivity.this);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

        // Implement search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    private void filterList(String text) {
        List<SongModel> filteredList = new ArrayList<>();
        for (SongModel song : list) {
            if (song.getSongName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(song);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "No song found", Toast.LENGTH_SHORT).show();
        }else {
            list.clear();
            list.addAll(filteredList);
            adapter.notifyDataSetChanged();
        }

    }
    private void addSongsToList(){
        list.add(new SongModel("እኛ ግን የሞተልንን"));
        list.add(new SongModel("እኔም ያንን ፀጋ "));
        list.add(new SongModel("በእምነት እኖራለሁ"));
        list.add(new SongModel("አንተ ግን ጌታዬ"));
        list.add(new SongModel("ምህረቱ አያልቅምና"));
        list.add(new SongModel("ሕዝብን አበዛህ"));
        list.add(new SongModel("ኦ ክብር "));
        list.add(new SongModel("የፈርዖን ገንዘብ"));
        list.add(new SongModel("ከሸካራው መንገድ"));
        list.add(new SongModel("የባዘንከው ውድ "));
        list.add(new SongModel("ፍቅር ፍቅር "));
        list.add(new SongModel("ማደሪያዎችህ"));
        list.add(new SongModel("ቤዛ የሆንክልኝ "));
        list.add(new SongModel("ያይሃል   ኢየሱስ"));
        list.add(new SongModel("እታገላለሁ   እታገላለሁ"));
        list.add(new SongModel("ምሥጋና የሚገባውን "));
        list.add(new SongModel("የባዘነውን ፋላጊ "));
        list.add(new SongModel("ኃጢአት ሥሩን "));
        list.add(new SongModel("አየሁ (3x)"));
        list.add(new SongModel("ኢየሱስን እንደ ሰው"));
        list.add(new SongModel("የእኔ ጌታ"));
        list.add(new SongModel("እንዘምራለን (2x)"));
        list.add(new SongModel("በአምላክ አምሳል "));
        list.add(new SongModel("እንዲህ አልነበርኩም"));
        list.add(new SongModel("ዘመኑ ፈጠነ "));
        list.add(new SongModel("ኢየሱስ ኢየሱስ "));
        list.add(new SongModel("ልብህ ጽኑ ይሁን"));
        list.add(new SongModel("ኢየሱስን ለብሰህ"));
        list.add(new SongModel("እስከዛሬ የጠበከን"));
        list.add(new SongModel(" ያ ተከራታቹ"));
        list.add(new SongModel("በእውነት አሳድገን"));
        list.add(new SongModel("በክቡር ደሙ "));
        list.add(new SongModel(" መጣልን "));
        list.add(new SongModel("ማን እንዳንተ"));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}