package com.pexcy.write_out;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class dashbord extends AppCompatActivity {
   private TabLayout tablayoutt;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        tablayoutt=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        tablayoutt.setupWithViewPager(viewPager);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pageAdapter.addFragment(new frag_fav(), "FAVOURITES");
        pageAdapter.addFragment(new frag_mine(), "MINE");
        pageAdapter.addFragment(new frag_other(), "OTHERS");
        viewPager.setAdapter(pageAdapter);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashbord.this, article_write.class));
            }
        });
    }

}