package com.dev.buivanphuc.apporderfood.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.buivanphuc.apporderfood.DTO.MonAnDTO;
import com.dev.buivanphuc.apporderfood.R;

import java.util.List;

public class AdaperHienThiDanhSachMonAn extends BaseAdapter {
    Context context;
    int layout;
    List<MonAnDTO> monAnDTOList;
    ViewHolderHienThiDanhSachMonAn viewHolder;

    public AdaperHienThiDanhSachMonAn(Context context, int layout, List<MonAnDTO> monAnDTOList) {
        this.context = context;
        this.monAnDTOList = monAnDTOList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return monAnDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return monAnDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return monAnDTOList.get(i).getMaMonAn();
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {
        View view = view1;
        if (view == null) {
            viewHolder = new ViewHolderHienThiDanhSachMonAn();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, viewGroup, false);

            viewHolder.imageView = view.findViewById(R.id.ivCustom_HinhAnhMonAn);
            viewHolder.txtGiaTien = view.findViewById(R.id.tvCustom_GiaTienMonAn);
            viewHolder.txtTenMonAn = view.findViewById(R.id.tvCustom_TenMonAn);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderHienThiDanhSachMonAn) view.getTag();
        }
        MonAnDTO monAnDTO = monAnDTOList.get(i);
        Uri uri = Uri.parse(monAnDTO.getHinhAnh().toString());
        Glide.with(context).load(uri).into(viewHolder.imageView);
        //viewHolder.imageView.setImageURI(uri);
        viewHolder.txtTenMonAn.setText(monAnDTO.getTenMonAN());
        viewHolder.txtGiaTien.setText("Giá: "+monAnDTO.getGiaTien());
        return view;
    }

    public class ViewHolderHienThiDanhSachMonAn {
        ImageView imageView;
        TextView txtTenMonAn, txtGiaTien;
    }
}