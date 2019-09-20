package com.dev.buivanphuc.apporderfood.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.Activity.DangKyActivity;
import com.dev.buivanphuc.apporderfood.Activity.ThemBanAnActivity;
import com.dev.buivanphuc.apporderfood.Adapter.AdapterHienThiNhanVien;
import com.dev.buivanphuc.apporderfood.DAO.NhanVienDAO;
import com.dev.buivanphuc.apporderfood.DTO.NhanVienDTO;
import com.dev.buivanphuc.apporderfood.R;

import java.util.List;

public class HienThiNhanVienFragment extends Fragment {
    ListView lvHienThiNhanVien;
    AdapterHienThiNhanVien adapterHienThiNhanVien;
    NhanVienDAO nhanVienDAO;
    List<NhanVienDTO> nhanVienDTOList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthinhanvien, container, false);
        lvHienThiNhanVien = view.findViewById(R.id.lvHienThiNhanVien);
        getActivity().setTitle("Nhân viên");
        HienThiDanhSachNhanVien();
        setHasOptionsMenu(true);
        registerForContextMenu(lvHienThiNhanVien);
        return view;
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
        int manv = nhanVienDTOList.get(vitri).getMANV();

        int id = item.getItemId();
        switch (id) {
            case R.id.itSua:
                break;
            case R.id.itXoa:
                boolean kiemtraXoa = nhanVienDAO.XoaNhanVien(manv);
                if (kiemtraXoa) {
                    HienThiDanhSachNhanVien();
                    Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    private void HienThiDanhSachNhanVien() {
        nhanVienDAO = new NhanVienDAO(getActivity());
        nhanVienDTOList = nhanVienDAO.LayDanhSachNhanVien();
        adapterHienThiNhanVien = new AdapterHienThiNhanVien(getActivity(), R.layout.custom_layout_hienthinhanvien, nhanVienDTOList);
        lvHienThiNhanVien.setAdapter(adapterHienThiNhanVien);
        adapterHienThiNhanVien.notifyDataSetChanged();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemThemBanAn = menu.add(1, R.id.itNhanVien, 1, R.string.nhanvien);
        itemThemBanAn.setIcon(R.drawable.nhanvien);
        itemThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itNhanVien:
                Intent iDangKyNhanVien = new Intent(getActivity(), DangKyActivity.class);
                getActivity().startActivity(iDangKyNhanVien);
                break;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        HienThiDanhSachNhanVien();
    }
}
