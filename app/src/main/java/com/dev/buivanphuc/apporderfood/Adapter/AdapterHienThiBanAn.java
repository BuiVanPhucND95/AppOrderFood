package com.dev.buivanphuc.apporderfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.Activity.ThanhToanActivity;
import com.dev.buivanphuc.apporderfood.Activity.TrangChuActivity;
import com.dev.buivanphuc.apporderfood.DAO.BanAnDAO;
import com.dev.buivanphuc.apporderfood.DAO.GoiMonDAO;
import com.dev.buivanphuc.apporderfood.DTO.BanAnDTO;
import com.dev.buivanphuc.apporderfood.DTO.GoiMonDTO;
import com.dev.buivanphuc.apporderfood.Fragment.HienThiThucDonFragment;
import com.dev.buivanphuc.apporderfood.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class AdapterHienThiBanAn extends BaseAdapter implements View.OnClickListener {
    Context context;
    int layout;
    List<BanAnDTO> banAnDTOList;
    ViewHolderBanAn viewHolderBanAn;
    BanAnDAO banAnDAO;
    GoiMonDAO goiMonDAO;
    FragmentManager fragmentManager;

    public AdapterHienThiBanAn(Context context, int layout, List<BanAnDTO> banAnDTOList) {
        this.context = context;
        this.layout = layout;
        this.banAnDTOList = banAnDTOList;
        banAnDAO = new BanAnDAO(context);
        goiMonDAO = new GoiMonDAO(context);
        fragmentManager = ((TrangChuActivity) context).getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return banAnDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return banAnDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return banAnDTOList.get(i).getMaBan();
    }

    @Override
    public View getView(int i, View Convertview, ViewGroup viewGroup) {
        View view = Convertview;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, viewGroup, false);
            viewHolderBanAn = new ViewHolderBanAn();

            viewHolderBanAn.imgBanAn = view.findViewById(R.id.imBanAn_Custom);
            viewHolderBanAn.imgGoiMon = view.findViewById(R.id.imGoiMon_Custom);
            viewHolderBanAn.imgThanhToan = view.findViewById(R.id.imThanhToan_Custom);
            viewHolderBanAn.imgAnButton = view.findViewById(R.id.imAnButton_Custom);
            viewHolderBanAn.txtTenBanAn = view.findViewById(R.id.tvtenBA_Custom);
            view.setTag(viewHolderBanAn);
        } else {
            viewHolderBanAn = (ViewHolderBanAn) view.getTag();
        }
        if (banAnDTOList.get(i).isDuocChon()) {
            viewHolderBanAn.imgAnButton.setVisibility(View.VISIBLE);
            viewHolderBanAn.imgThanhToan.setVisibility(View.VISIBLE);
            viewHolderBanAn.imgGoiMon.setVisibility(View.VISIBLE);
        } else {
            viewHolderBanAn.imgAnButton.setVisibility(View.INVISIBLE);
            viewHolderBanAn.imgThanhToan.setVisibility(View.INVISIBLE);
            viewHolderBanAn.imgGoiMon.setVisibility(View.INVISIBLE);
        }


        BanAnDTO banAnDTO = banAnDTOList.get(i);
        String KTtinhTrang = banAnDAO.LayTinhTrangBanTheoMa(banAnDTO.getMaBan());
        if (KTtinhTrang.equals("true")) {
            viewHolderBanAn.imgBanAn.setImageResource(R.drawable.banantrue);
        } else {
            viewHolderBanAn.imgBanAn.setImageResource(R.drawable.banan);
        }

        viewHolderBanAn.txtTenBanAn.setText(banAnDTO.getTenBan());
        viewHolderBanAn.imgBanAn.setTag(i);
        viewHolderBanAn.imgBanAn.setOnClickListener(this);
        viewHolderBanAn.imgGoiMon.setOnClickListener(this);
        viewHolderBanAn.imgThanhToan.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        viewHolderBanAn = (ViewHolderBanAn) ((View) view.getParent()).getTag();
        int vitri1 = (int) viewHolderBanAn.imgBanAn.getTag();
        int maban = banAnDTOList.get(vitri1).getMaBan();


        switch (id) {
            case R.id.imBanAn_Custom:
                String tenBan = viewHolderBanAn.txtTenBanAn.getText().toString();
                int vitri = (int) viewHolderBanAn.imgBanAn.getTag();
                banAnDTOList.get(vitri).setDuocChon(true);

                viewHolderBanAn.imgAnButton.setVisibility(View.VISIBLE);
                viewHolderBanAn.imgThanhToan.setVisibility(View.VISIBLE);
                viewHolderBanAn.imgGoiMon.setVisibility(View.VISIBLE);
                break;
            case R.id.imGoiMon_Custom:

                Log.d("maban", maban + "");
                Intent layITrangChu = ((TrangChuActivity) context).getIntent();
                int manv = layITrangChu.getIntExtra("manv", 0);
                String tinhTrang = banAnDAO.LayTinhTrangBanTheoMa(maban);
                if (tinhTrang.equals("false")) {
                    // Thực hiện thêm bảng gọi món và cập nhật lại tình trạng bàn
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String ngayGoi = dateFormat.format(calendar.getTime());

                    GoiMonDTO goiMonDTO = new GoiMonDTO();
                    goiMonDTO.setMaBan(maban);

                    goiMonDTO.setMaNV(manv);
                    goiMonDTO.setNgayGoi(ngayGoi);
                    goiMonDTO.setTinhTrang("false");
                    long kiemtra = goiMonDAO.ThemGoiMon(goiMonDTO);
                    banAnDAO.CapNhatTinhTrangBan(maban, "true");
                    if (kiemtra == 0) {
                        Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                HienThiThucDonFragment hienThiThucDonFragment = new HienThiThucDonFragment();

                Bundle bDuLieuThucDon = new Bundle();
                bDuLieuThucDon.putInt("mabanthucdon", maban);

                hienThiThucDonFragment.setArguments(bDuLieuThucDon);
                transaction.replace(R.id.frameTrangChu, hienThiThucDonFragment).addToBackStack("hienthibanan");
                transaction.commit();
                break;
            case R.id.imThanhToan_Custom:
                Intent iThanhhToan = new Intent(context, ThanhToanActivity.class);
                iThanhhToan.putExtra("maban",maban);
                context.startActivity(iThanhhToan);
                break;
        }
    }

    public class ViewHolderBanAn {
        ImageView imgBanAn, imgGoiMon, imgThanhToan, imgAnButton;
        TextView txtTenBanAn;
    }
}
