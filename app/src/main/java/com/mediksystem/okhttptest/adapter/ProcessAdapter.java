package com.mediksystem.okhttptest.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediksystem.okhttptest.R;
import com.mediksystem.okhttptest.item.ProcessItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ViewHolder> {
    private ArrayList<ProcessItem> mData = null;
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_process_item, parent, false) ;
        ProcessAdapter.ViewHolder vh = new ProcessAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProcessAdapter.ViewHolder holder, int position) {
        ProcessItem item = mData.get(position) ;
        holder.SubUniquenumber.setText(item.getSubUniquenumber()) ;
        holder.ProcessSubType.setText(item.getProcessSubType());
        holder.BeginTime.setText(item.getBeginTime());
        holder.FinishTime.setText(item.getFinishTime());
        holder.RegistrationTime.setText(item.getRegistrationTime());
        holder.ModificationTime.setText(item.getModificationTime());

        if (mSelectedItems.get(position, false)) {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView SubUniquenumber, ProcessSubType, BeginTime, FinishTime, RegistrationTime, ModificationTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            SubUniquenumber = itemView.findViewById(R.id.SubUniquenumber);
            ProcessSubType = itemView.findViewById(R.id.ProcessSubType);
            BeginTime = itemView.findViewById(R.id.BeginTime);
            FinishTime = itemView.findViewById(R.id.FinishTime);
            RegistrationTime = itemView.findViewById(R.id.RegistrationTime);
            ModificationTime = itemView.findViewById(R.id.ModificationTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        ProcessItem item = mData.get(pos);


                        Log.e("입력된 position : ", String.valueOf(pos));

                        Log.d("해당 SubUniquenumber : ", String.valueOf(item.getSubUniquenumber()));
                        Log.d("해당 ProcessSubType : ", item.getProcessSubType());
                        Log.d("해당 BeginTime : ", item.getBeginTime());
                        Log.d("해당 FinishTime : ", item.getFinishTime());
                        Log.d("해당 RegistrationTime : ", item.getRegistrationTime());
                        Log.d("해당 ModificationTime : ", item.getModificationTime());

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle(String.valueOf(item.getSubUniquenumber()));
                        builder.setMessage(item.getProcessSubType());
                        builder.show();

                    }
                }
            });




        }
    }








    public ProcessAdapter(ArrayList<ProcessItem> list) {
        mData = list;
    }
}
