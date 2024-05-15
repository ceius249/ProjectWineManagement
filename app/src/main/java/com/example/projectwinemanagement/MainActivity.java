package com.example.projectwinemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.projectwinemanagement.adapter.ViewPagerAdapter;
import com.example.projectwinemanagement.subactivity.queryoption.WineByAlcoholContentActivity;
import com.example.projectwinemanagement.subactivity.queryoption.WineByCountryOptionActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_wine).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_original_country).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.menu_wine) {
                    viewPager.setCurrentItem(0);
                }
                if (menuItem.getItemId() == R.id.menu_original_country) {
                    viewPager.setCurrentItem(1);
                }

                return true;
            }
        });
    }

    // Option Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_query_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.select_wine_where_original_country_id) {
            Intent intent_select_wine_where_original_country_id = new Intent(this, WineByCountryOptionActivity.class);
            startActivity(intent_select_wine_where_original_country_id);
        }

        else if(item.getItemId() == R.id.select_wine_alcolhol_content_where_original_country_id) {
            Intent intent_select_wine_alcolhol_content_where_original_country_id = new Intent(this, WineByAlcoholContentActivity.class);
            startActivity(intent_select_wine_alcolhol_content_where_original_country_id);
        }

        return super.onOptionsItemSelected(item);
    }
}