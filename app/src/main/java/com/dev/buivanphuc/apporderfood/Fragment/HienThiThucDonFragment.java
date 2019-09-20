package com.dev.buivanphuc.apporderfood.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.Activity.SuaThucDonActicity;
import com.dev.buivanphuc.apporderfood.Activity.ThemThucDonActivity;
import com.dev.buivanphuc.apporderfood.Adapter.AdapterHienThiLoaiLoaiMonAnThucDon;
import com.dev.buivanphuc.apporderfood.DAO.LoaiMonAnDAO;
import com.dev.buivanphuc.apporderfood.DTO.LoaiMonAnDTO;
import com.dev.buivanphuc.apporderfood.R;

import java.util.List;

public class HienThiThucDonFragment extends Fragment {
    GridView gvHienThiThucDon;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    LoaiMonAnDAO loaiMonAnDAO;
    int maban;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon, container, false);
        getActivity().setTitle("Menu");
        setHasOptionsMenu(true);
        gvHienThiThucDon = view.findViewById(R.id.gvHienThiThucDon);
        LayDanhSachLoaiMonAn();
        fragmentManager = getActivity().getSupportFragmentManager();

        Bundle bDuLieuThucDon = getArguments();
        if (bDuLieuThucDon != null) {
            maban = bDuLieuThucDon.getInt("mabanthucdon", 0);
        } else {
            registerForContextMenu(gvHienThiThucDon);
        }

        gvHienThiThucDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int maloai = loaiMonAnDTOList.get(i).getMaLoai();
                HienThiDanhSachMonAnFragment hienThiDanhSachMonAnFragment = new HienThiDanhSachMonAnFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("maloai", maloai);
                bundle.putInt("maban", maban);
                hienThiDanhSachMonAnFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameTrangChu, hienThiDanhSachMonAnFragment).addToBackStack("hienthiloai");
                fragmentTransaction.commit();
            }
        });


        return view;
    }

    public void LayDanhSachLoaiMonAn() {
        loaiMonAnDAO = new LoaiMonAnDAO(getActivity());
        loaiMonAnDTOList = loaiMonAnDAO.LayDanhSachLoaiMonAn();
        AdapterHienThiLoaiLoaiMonAnThucDon adapter = new AdapterHienThiLoaiLoaiMonAnThucDon(getActivity(), R.layout.custom_layout_hienthiloaimonan, loaiMonAnDTOList);
        gvHienThiThucDon.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        LayDanhSachLoaiMonAn();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vitri = menuInfo.position;
        int maloai = loaiMonAnDTOList.get(vitri).getMaLoai();

        int id = item.getItemId();
        switch (id) {
            case R.id.itSua:
                Intent iSuaThucSon = new Intent(getActivity(), SuaThucDonActicity.class);
                iSuaThucSon.putExtra("maloai", maloai);
                getActivity().startActivity(iSuaThucSon);
                break;
            case R.id.itXoa:
                boolean kiemtra = loaiMonAnDAO.XoaLoaiMonAn(maloai);
                if (kiemtra) {
                    Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    LayDanhSachLoaiMonAn();
                } else {
                    Toast.makeText(getActivity(), "Lỗi!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
        return super.onContextItemSelected(item);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemThucDon = menu.add(1, R.id.itThemThucDon, 1, R.string.thongke);
        itemThucDon.setIcon(R.drawable.logodangnhap);
        itemThucDon.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itThemThucDon:
                Intent iThemThucDon = new Intent(getActivity(), ThemThucDonActivity.class);
                startActivity(iThemThucDon);
                break;
        }
        return true;
    }
}
