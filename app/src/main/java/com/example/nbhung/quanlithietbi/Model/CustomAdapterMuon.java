package com.example.nbhung.quanlithietbi.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nbhung.quanlithietbi.R;

import java.util.ArrayList;

/**
 * Created by nbhung on 7/26/2017.
 */

public class CustomAdapterMuon extends BaseAdapter {
    private Context context;
    private ArrayList<DoiTuongMuon> muonArrayList;

    public CustomAdapterMuon(Context context, ArrayList<DoiTuongMuon> muonArrayList) {
        this.context = context;
        this.muonArrayList = muonArrayList;
    }

    @Override
    public int getCount() {
        return muonArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return muonArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_item_muon, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DoiTuongMuon tam = muonArrayList.get(i);
        viewHolder.tvTenThietBi.setText(tam.getTentb());
        viewHolder.tvSoluong.setText(tam.getSoluong()+"");
        viewHolder.tvNgayMuon.setText(tam.getNgaymuon());
        viewHolder.tvNgayTra.setText(tam.getNgaytra());
        return view;
    }

    private class ViewHolder {
        TextView tvTenThietBi, tvSoluong, tvNgayMuon, tvNgayTra;

        public ViewHolder(View view) {
            tvTenThietBi = (TextView) view.findViewById(R.id.tvTenThietBiMuon);
            tvSoluong = (TextView) view.findViewById(R.id.tvsoluongmuon);
            tvNgayMuon = (TextView) view.findViewById(R.id.tvngaymuon);
            tvNgayTra = (TextView) view.findViewById(R.id.tvngaytra);
        }
    }
}
