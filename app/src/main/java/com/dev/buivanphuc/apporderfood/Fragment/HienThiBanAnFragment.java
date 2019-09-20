package com.dev.buivanphuc.apporderfood.Fragment;

import android.app.Activity;
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
import android.widget.GridView;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.Activity.SuaBanAnActivity;
import com.dev.buivanphuc.apporderfood.Activity.ThemBanAnActivity;
import com.dev.buivanphuc.apporderfood.Adapter.AdapterHienThiBanAn;
import com.dev.buivanphuc.apporderfood.DAO.BanAnDAO;
import com.dev.buivanphuc.apporderfood.DTO.BanAnDTO;
import com.dev.buivanphuc.apporderfood.R;

import java.util.ArrayList;
import java.util.List;

public class HienThiBanAnFragment extends Fragment {
    public static int RESQUEST_CODE_THEM = 111;

    GridView gridViewHienThienBanBan;
    List<BanAnDTO> banAnDTOList;
    BanAnDAO banAnDAO;
    AdapterHienThiBanAn adapterHienThiBanAn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthibanan, container, false);
        gridViewHienThienBanBan = view.findViewById(R.id.gvHienThiBanAn);
        getActivity().setTitle("Home");
        CapNhatDanhSachBanAn();
        setHasOptionsMenu(true);
        registerForContextMenu(gridViewHienThienBanBan);
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
        int maban = banAnDTOList.get(vitri).getMaBan();

        int id = item.getItemId();
        switch (id) {
            case R.id.itSua:
                Intent iSuaBanAn = new Intent(getActivity(), SuaBanAnActivity.class);
                iSuaBanAn.putExtra("maban",maban);
                startActivity(iSuaBanAn);
                break;
            case R.id.itXoa:
                boolean kiemtraXoa= banAnDAO.XoaBanAnTheoMa(maban);
                if(kiemtraXoa)
                {
                    Toast.makeText(getActivity(),"Xóa thành công",Toast.LENGTH_SHORT).show();
                    CapNhatDanhSachBanAn();
                }else {
                    Toast.makeText(getActivity(),"Xóa thất bại",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void CapNhatDanhSachBanAn() {
        banAnDAO = new BanAnDAO(getActivity());
        banAnDTOList = banAnDAO.LayTatCaBanAn();
        adapterHienThiBanAn = new AdapterHienThiBanAn(getActivity(), R.layout.custom_layout_hienthibanan, banAnDTOList);
        gridViewHienThienBanBan.setAdapter(adapterHienThiBanAn);
        adapterHienThiBanAn.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemThemBanAn = menu.add(1, R.id.itThemBanAn, 1, R.string.thongke);
        itemThemBanAn.setIcon(R.drawable.thembanan);
        itemThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itThemBanAn:
                Intent iThemBanAn = new Intent(getActivity(), ThemBanAnActivity.class);
                startActivityForResult(iThemBanAn, RESQUEST_CODE_THEM);
                break;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        CapNhatDanhSachBanAn();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESQUEST_CODE_THEM) {
            if (resultCode == Activity.RESULT_OK) {
                Intent intent = data;
                boolean kiemtra = intent.getBooleanExtra("ketquathem", false);
                if (kiemtra) {
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    CapNhatDanhSachBanAn();
                } else {
                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
