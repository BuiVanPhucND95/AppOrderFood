package com.dev.buivanphuc.apporderfood.Activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dev.buivanphuc.apporderfood.Fragment.HienThiBanAnFragment;
import com.dev.buivanphuc.apporderfood.Fragment.HienThiNhanVienFragment;
import com.dev.buivanphuc.apporderfood.Fragment.HienThiThucDonFragment;
import com.dev.buivanphuc.apporderfood.R;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView tv_TenNhanVien_Navigation;
    FragmentManager fragmentManager;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);
        //tendangnhap
        String tendangnhap = getIntent().getStringExtra("tendangnhap");
        drawerLayout = findViewById(R.id.drawerTrangChu);
        navigationView = findViewById(R.id.NavigationViewTrangChu);
        toolbar = findViewById(R.id.ToolbarTrangChu);

        View view = navigationView.inflateHeaderView(R.layout.layout_header_navigationview);

        tv_TenNhanVien_Navigation = view.findViewById(R.id.tv_TenNhanVien_Navigation);
        tv_TenNhanVien_Navigation.setText(tendangnhap);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.mo, R.string.dong) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transactionHienThiBanAn = fragmentManager.beginTransaction();
        HienThiBanAnFragment hienThiBanAnFragment = new HienThiBanAnFragment();
        transactionHienThiBanAn.replace(R.id.frameTrangChu, hienThiBanAnFragment);
        transactionHienThiBanAn.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        switch (id) {
            case R.id.itTrangChu:
                FragmentTransaction transactionHienThiBanAn = fragmentManager.beginTransaction();
                HienThiBanAnFragment hienThiBanAnFragment = new HienThiBanAnFragment();
                transactionHienThiBanAn.replace(R.id.frameTrangChu, hienThiBanAnFragment);
                transactionHienThiBanAn.commit();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.itThucDon:
                FragmentTransaction transactionHienThiThucDon = fragmentManager.beginTransaction();
                HienThiThucDonFragment hienThiThucDonFragment = new HienThiThucDonFragment();
                transactionHienThiThucDon.replace(R.id.frameTrangChu, hienThiThucDonFragment);
                transactionHienThiThucDon.commit();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.itNhanVien:
                FragmentTransaction transactionHienThiNhanVien = fragmentManager.beginTransaction();
                HienThiNhanVienFragment hienThiNhanVienFragment = new HienThiNhanVienFragment();
                transactionHienThiNhanVien.replace(R.id.frameTrangChu, hienThiNhanVienFragment);
                transactionHienThiNhanVien.commit();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }
}
