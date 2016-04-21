package com.example.intervenant.myapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intervenant.myapplication.fragments.ProductListViewFragment;


public class MainActivity extends AppCompatActivity implements ProductListViewFragment.OnFragmentListInteractionListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ListPagerAdapter(getSupportFragmentManager()));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentListInteraction(Product product) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("name", product.name);
        detailIntent.putExtra("image", product.image);
        detailIntent.putExtra("price", product.price);
        detailIntent.putExtra("info", product.info);

        startActivity(detailIntent);
    }

    public class ListPagerAdapter extends FragmentPagerAdapter {

        public ListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ProductListViewFragment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position== 0 ? "Produits" : "Panier";
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
