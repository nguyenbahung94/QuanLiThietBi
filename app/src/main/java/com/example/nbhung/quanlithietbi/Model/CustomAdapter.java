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
 * Created by nbhung on 7/25/2017.
 */

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<loaitb> loaitbmew;

    public CustomAdapter(Context mContext, ArrayList<loaitb> loaitb) {
        this.mContext = mContext;
        loaitbmew = loaitb;
    }

    @Override
    public int getCount() {
        return loaitbmew.size();
    }

    @Override
    public Object getItem(int i) {
        return loaitbmew.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.row_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        loaitb itemLoatb=loaitbmew.get(i);
        viewHolder.tvTenThietBi.setText(itemLoatb.getTentb());
        viewHolder.tvSoluong.setText(itemLoatb.getSoluong()+"");

        return  view;
    }

    private class ViewHolder {
        TextView tvTenThietBi, tvSoluong;

        public ViewHolder(View view) {
            tvTenThietBi = (TextView) view.findViewById(R.id.tvThietBi);
            tvSoluong = (TextView) view.findViewById(R.id.tvsoluong);
        }
    }
}
