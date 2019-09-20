package com.dev.buivanphuc.apporderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev.buivanphuc.apporderfood.DTO.ThanhToanDTO;
import com.dev.buivanphuc.apporderfood.R;

import java.util.List;

public class AdapterHienThiThanhToan extends BaseAdapter {
    Context context;
    int layout;
    List<ThanhToanDTO> thanhToanDTOList;
    ViewHolderThanhToan viewHolderThanhToan;

    public AdapterHienThiThanhToan(Context context, int layout, List<ThanhToanDTO> thanhToanDTOList) {
        this.context = context;
        this.layout = layout;
        this.thanhToanDTOList = thanhToanDTOList;
    }

    @Override
    public int getCount() {
        return thanhToanDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return thanhToanDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {
        View view = view1;
        if (view == null) {
            viewHolderThanhToan = new ViewHolderThanhToan();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, viewGroup, false);

            viewHolderThanhToan.txtTenMonAn = view.findViewById(R.id.tvTenMonAnThanhToan);
            viewHolderThanhToan.txtSoLuong = view.findViewById(R.id.tvSoLuongMonAnThanhToan);
            viewHolderThanhToan.txtGiaTien = view.findViewById(R.id.tvGiaTienMonAnThanhToan);

            view.setTag(viewHolderThanhToan);

        } else {
            viewHolderThanhToan = (ViewHolderThanhToan) view.getTag();
        }
        ThanhToanDTO thanhToanDTO = thanhToanDTOList.get(i);

        viewHolderThanhToan.txtTenMonAn.setText(thanhToanDTO.getTenMonAn());
        viewHolderThanhToan.txtSoLuong.setText(thanhToanDTO.getSoLuong() + "");
        viewHolderThanhToan.txtGiaTien.setText(thanhToanDTO.getGiaTien() + "");

        return view;

    }

    public class ViewHolderThanhToan {
        TextView txtTenMonAn, txtSoLuong, txtGiaTien;
    }
}
