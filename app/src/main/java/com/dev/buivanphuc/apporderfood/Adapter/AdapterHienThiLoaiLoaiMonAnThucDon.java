package com.dev.buivanphuc.apporderfood.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.buivanphuc.apporderfood.DAO.LoaiMonAnDAO;
import com.dev.buivanphuc.apporderfood.DTO.LoaiMonAnDTO;
import com.dev.buivanphuc.apporderfood.R;

import java.util.List;

public class AdapterHienThiLoaiLoaiMonAnThucDon extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    ViewHlderHienThiLoaiThucDon viewHlderHienThiLoaiThucDon;
    LoaiMonAnDAO loaiMonAnDAO;

    public AdapterHienThiLoaiLoaiMonAnThucDon(Context context, int layout, List<LoaiMonAnDTO> loaiMonAnDTOList) {
        this.context = context;
        this.layout = layout;
        this.loaiMonAnDTOList = loaiMonAnDTOList;
        loaiMonAnDAO = new LoaiMonAnDAO(context);
    }

    @Override
    public int getCount() {
        return loaiMonAnDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return loaiMonAnDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return loaiMonAnDTOList.get(i).getMaLoai();
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        View view = convertview;
        if (view == null) {
            viewHlderHienThiLoaiThucDon = new ViewHlderHienThiLoaiThucDon();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, viewGroup, false);
            viewHlderHienThiLoaiThucDon.ivCustom_HinhAnhLMA = view.findViewById(R.id.ivCustom_HinhAnhLMA);
            viewHlderHienThiLoaiThucDon.tvCustom_HT_LMA = view.findViewById(R.id.tvCustom_HT_LMA);

            view.setTag(viewHlderHienThiLoaiThucDon);
        } else {
            viewHlderHienThiLoaiThucDon = (ViewHlderHienThiLoaiThucDon) view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDTOList.get(i);
        String hinhanh = loaiMonAnDAO.LayLinkHinhAnhLoaiMonAn(loaiMonAnDTO.getMaLoai());
        Log.d("LinkHinh",hinhanh);
        Glide.with(context).load(hinhanh).into(viewHlderHienThiLoaiThucDon.ivCustom_HinhAnhLMA);
        viewHlderHienThiLoaiThucDon.tvCustom_HT_LMA.setText(loaiMonAnDTO.getTenLoai());
        return view;
    }

    public class ViewHlderHienThiLoaiThucDon {
        ImageView ivCustom_HinhAnhLMA;
        TextView tvCustom_HT_LMA;
    }
}
