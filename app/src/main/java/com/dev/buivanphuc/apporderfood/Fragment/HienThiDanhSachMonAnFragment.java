package com.dev.buivanphuc.apporderfood.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.Activity.SoLuongActivity;
import com.dev.buivanphuc.apporderfood.Activity.SuaMonAnActivity;
import com.dev.buivanphuc.apporderfood.Adapter.AdaperHienThiDanhSachMonAn;
import com.dev.buivanphuc.apporderfood.DAO.MonAnDAO;
import com.dev.buivanphuc.apporderfood.DTO.MonAnDTO;
import com.dev.buivanphuc.apporderfood.R;

import java.util.List;

public class HienThiDanhSachMonAnFragment extends Fragment {

    GridView gvHienThiThucDon;

    MonAnDAO monAnDAO;
    List<MonAnDTO> monAnDTOList;
    int maloai;
    int maban;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon, container, false);
        getActivity().setTitle("Danh sách món ăn");
        Bundle bundle = getArguments();

        if (bundle != null) {

            maloai = bundle.getInt("maloai");
            maban = bundle.getInt("maban");

            gvHienThiThucDon = view.findViewById(R.id.gvHienThiThucDon);
            LayDanhSachMonAnTheoLoai();

            gvHienThiThucDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (maban != 0) {
                        Intent iSoLuong = new Intent(getActivity(), SoLuongActivity.class);
                        iSoLuong.putExtra("maban", maban);
                        iSoLuong.putExtra("mamonan", monAnDTOList.get(i).getMaMonAn());
                        startActivity(iSoLuong);
                    } else {
                        registerForContextMenu(gvHienThiThucDon);
                    }

                }
            });
        }
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    getFragmentManager().popBackStack("hienthiloai", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });

        return view;
    }

    private void LayDanhSachMonAnTheoLoai() {
        monAnDAO = new MonAnDAO(getContext());
        monAnDTOList = monAnDAO.LayDanhSachMonAnTheoLoai(maloai);
        AdaperHienThiDanhSachMonAn adapter = new AdaperHienThiDanhSachMonAn(getActivity(), R.layout.custom_layout_hienthidanhsachmonan, monAnDTOList);
        gvHienThiThucDon.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
        int mamonan = monAnDTOList.get(vitri).getMaMonAn();
        int id = item.getItemId();
        switch (id) {
            case R.id.itSua:
                Intent iSuaMonAn = new Intent(getActivity(), SuaMonAnActivity.class);
                iSuaMonAn.putExtra("mamonan", mamonan);
                getActivity().startActivity(iSuaMonAn);
                break;
            case R.id.itXoa:
                boolean kiemtra = monAnDAO.XoaMonAn(mamonan);
                if(kiemtra)
                {
                    Toast.makeText(getActivity(),"Xóa thành công",Toast.LENGTH_SHORT).show();
                    LayDanhSachMonAnTheoLoai();
                }
                else {
                    Toast.makeText(getActivity(),"Lỗi !!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        LayDanhSachMonAnTheoLoai();
    }
}
