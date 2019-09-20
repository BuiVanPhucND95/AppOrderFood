package com.dev.buivanphuc.apporderfood.Adapter;

import android.content.Context;
import android.text.NoCopySpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.buivanphuc.apporderfood.DTO.NhanVienDTO;
import com.dev.buivanphuc.apporderfood.R;

import java.util.List;

public class AdapterHienThiNhanVien extends BaseAdapter {
    Context context;
    int layout;
    List<NhanVienDTO> nhanVienDTOList;
    ViewHolderNhanVien viewHolderNhanVien;

    public AdapterHienThiNhanVien(Context context, int layout, List<NhanVienDTO> nhanVienDTOList) {
        this.context = context;
        this.layout = layout;
        this.nhanVienDTOList = nhanVienDTOList;
    }

    @Override
    public int getCount() {
        return nhanVienDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return nhanVienDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return nhanVienDTOList.get(i).getMANV();
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {
        View view = view1;
        if (view == null) {
            viewHolderNhanVien = new ViewHolderNhanVien();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, viewGroup, false);
            viewHolderNhanVien.imgHinhNhanVien = view.findViewById(R.id.imgHinhNhanVien);
            viewHolderNhanVien.txtCMND = view.findViewById(R.id.tvSdtNV);
            viewHolderNhanVien.txtTenNhanVien = view.findViewById(R.id.tvTenNV);

            view.setTag(viewHolderNhanVien);
        } else {
            viewHolderNhanVien = (ViewHolderNhanVien) view.getTag();
        }
        NhanVienDTO nhanVienDTO = nhanVienDTOList.get(i);
        viewHolderNhanVien.txtTenNhanVien.setText(nhanVienDTO.getTENDN());
        viewHolderNhanVien.txtCMND.setText(String.valueOf(nhanVienDTO.getCMND()));

        return view;
    }

    public class ViewHolderNhanVien {
        TextView txtTenNhanVien, txtCMND;
        ImageView imgHinhNhanVien;
    }
}

