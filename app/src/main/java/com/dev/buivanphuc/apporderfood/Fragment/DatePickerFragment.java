package com.dev.buivanphuc.apporderfood.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.dev.buivanphuc.apporderfood.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),this,day,month,year);
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        EditText etNgaySinh = (EditText) getActivity().findViewById(R.id.etNgaySinh);
        String ngaySinh = day + "/" + (month+1) + "/" + year;
        etNgaySinh.setText(ngaySinh);
    }
}
