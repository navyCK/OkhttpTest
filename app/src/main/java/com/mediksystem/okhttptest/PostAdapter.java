package com.mediksystem.okhttptest;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<NoticeListViewItem> mData = null ;
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, userId, title, body;

        ViewHolder(View itemView) {
            super(itemView) ;


            // 뷰 객체에 대한 참조. (hold strong reference)
            id = itemView.findViewById(R.id.idListItem) ;
            userId = itemView.findViewById(R.id.userIdListItem) ;
            title = itemView.findViewById(R.id.titleListItem) ;
            body = itemView.findViewById(R.id.bodyListItem) ;


            // 아이템 클릭 이벤트 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        NoticeListViewItem item = mData.get(pos);



//                        if (mSelectedItems.get(pos, false)) {
//                            mSelectedItems.put(pos, false);
//                            itemView.setBackgroundColor(Color.WHITE);
//                        } else {
//                            mSelectedItems.put(pos, true);
//                            itemView.setBackgroundColor(Color.GRAY);
//                        }

                        Log.e("입력된 position : ", String.valueOf(pos));

                        Log.d("해당 id data : ", item.getId());
                        Log.d("해당 user id data : ", item.getUserId());
                        Log.d("해당 title data : ", item.getTitle());
                        Log.d("해당 body data : ", item.getBody());

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle(item.getTitle());
                        builder.setMessage(item.getBody());
                        builder.show();



                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        NoticeListViewItem item = mData.get(pos);

                        if (mSelectedItems.get(pos, false)) {
                            mSelectedItems.put(pos, false);
                            itemView.setBackgroundColor(Color.WHITE);
                        } else {
                            mSelectedItems.put(pos, true);
                            itemView.setBackgroundColor(Color.YELLOW);
                        }


                    }
                    return true;
                }
            });



        }

    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    PostAdapter(ArrayList<NoticeListViewItem> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false) ;
        PostAdapter.ViewHolder vh = new PostAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position 에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder holder, int position) {
        NoticeListViewItem item = mData.get(position) ;
        holder.id.setText(item.getId()) ;
        holder.userId.setText(item.getUserId());
        holder.title.setText(item.getTitle());
        holder.body.setText(item.getBody());

        if (mSelectedItems.get(position, false)) {
            holder.itemView.setBackgroundColor(Color.GRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }


}
