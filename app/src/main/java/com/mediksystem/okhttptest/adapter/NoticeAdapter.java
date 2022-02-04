package com.mediksystem.okhttptest.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediksystem.okhttptest.R;
import com.mediksystem.okhttptest.item.NoticeItem;

import java.util.ArrayList;

public class NoticeAdapter extends BaseAdapter {
    private ArrayList<NoticeItem> noticeListViewItemArrayList = new ArrayList<>() ;

    public NoticeAdapter() {

    }

    @Override
    public int getCount() {
        return noticeListViewItemArrayList.size() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notice_listview_item, parent, false);
        }

        ImageView iconImageView = convertView.findViewById(R.id.imageView);
        TextView titleTextView = convertView.findViewById(R.id.titleView);
        TextView descTextView = convertView.findViewById(R.id.descView);
        TextView dateTextView = convertView.findViewById(R.id.dateView);

        NoticeItem noticeListViewItem = noticeListViewItemArrayList.get(position);

        iconImageView.setImageDrawable(noticeListViewItem.getIcon());
        titleTextView.setText(noticeListViewItem.getTitle());
        descTextView.setText(noticeListViewItem.getDesc());
        dateTextView.setText(noticeListViewItem.getDate());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return noticeListViewItemArrayList.get(position) ;
    }

    public void addItem(Drawable icon, String title, String desc, String date) {
        NoticeItem item = new NoticeItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);
        item.setDate(date);

        noticeListViewItemArrayList.add(item);
    }
}

