package com.mediksystem.okhttptest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class NoticeListViewAdapter extends BaseAdapter {
    private ArrayList<NoticeListViewItem> noticeListViewItemArrayList = new ArrayList<>();

    public NoticeListViewAdapter(MainActivity mainActivity, int notice_listview_item, ArrayList<Notice> noticeListViewItemArrayList) {

    }
    @Override
    public int getCount() {
        return noticeListViewItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticeListViewItemArrayList.get(position) ;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notice_listview_item, parent, false);
        }

        TextView idView = convertView.findViewById(R.id.idView);
        TextView titleTextView = convertView.findViewById(R.id.titleView);
        TextView descTextView = convertView.findViewById(R.id.descView);
        TextView dateTextView = convertView.findViewById(R.id.dateView);

        NoticeListViewItem noticeListViewItem = noticeListViewItemArrayList.get(position);

        return convertView;
    }

}
