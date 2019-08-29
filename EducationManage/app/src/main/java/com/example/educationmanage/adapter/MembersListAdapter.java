package com.example.educationmanage.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.educationmanage.R;
import com.example.educationmanage.bean.MembersListBean;

import java.util.ArrayList;
import java.util.List;

public class MembersListAdapter extends RecyclerView.Adapter {
    private List<MembersListBean.content.table> listbean = new ArrayList();
    public seletedsuccessful mseletedsuccessful;
    public static class ViewHolderContent extends RecyclerView.ViewHolder {
        public View rootView;


        TextView mumbermd;//门店
        TextView  mumbername;//会员名称
        TextView  mumberbirthday;//会员生日

        public ViewHolderContent(View rootView) {
            super(rootView);
            this.rootView = rootView;

            mumbermd = rootView.findViewById(R.id.mumbermd);

            mumbername = rootView.findViewById(R.id.mumbername);
            mumberbirthday = rootView.findViewById(R.id.mumberbirthday);


        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_memberslist, viewGroup, false);
        final RecyclerView.ViewHolder viewHolder = new ViewHolderContent(inflate);
        ((ViewHolderContent) viewHolder).rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
//
//
                mseletedsuccessful.onItemClick(position);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MembersListBean.content.table item = listbean.get(i);
        ((ViewHolderContent)viewHolder). mumbermd.setText(item.getMd());
        ((ViewHolderContent)viewHolder).mumberbirthday.setText(item.getStu_Date());
        ((ViewHolderContent)viewHolder).mumbername.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return listbean.size();
    }
    public  MembersListAdapter(List list){
        listbean = list;
    }
    public interface   seletedsuccessful{
        void  onItemClick(Object tag) ;

    }
    public  void  setseletedSuccessful(seletedsuccessful listener){
        mseletedsuccessful = listener;
    }

}
