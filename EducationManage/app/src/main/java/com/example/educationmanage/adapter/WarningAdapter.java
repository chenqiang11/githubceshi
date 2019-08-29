package com.example.educationmanage.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.educationmanage.R;
import com.example.educationmanage.bean.WarningBean;

import java.util.ArrayList;
import java.util.List;

public class WarningAdapter extends RecyclerView.Adapter {
    private List<WarningBean.content.table> listbean = new ArrayList();
    public seletedsuccessful mseletedsuccessful;
    public static class ViewHolderContent extends RecyclerView.ViewHolder {
        public View rootView;

        TextView restclass;//剩余课程
        TextView classname;//课程名字
        TextView  mumbername;//会员名称
        TextView  mumberid;//会员编号

        public ViewHolderContent(View rootView) {
            super(rootView);
            this.rootView = rootView;

            restclass = rootView.findViewById(R.id.restclass);
            classname = rootView.findViewById(R.id.classname);
            mumbername = rootView.findViewById(R.id.mumbername);
            mumberid = rootView.findViewById(R.id.mumberid);


        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_warning, viewGroup, false);
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
         WarningBean.content.table item = listbean.get(i);
        ((ViewHolderContent)viewHolder).restclass.setText(item.getSyks());
        ((ViewHolderContent)viewHolder).classname.setText(item.getKcmc());
        ((ViewHolderContent)viewHolder).mumbername.setText(item.getHymc());
        ((ViewHolderContent)viewHolder).mumberid.setText(item.getHybh());
    }

    @Override
    public int getItemCount() {
        return listbean.size();
    }
    public  WarningAdapter(List list){
            listbean = list;
    }
    public interface   seletedsuccessful{
        void  onItemClick(Object tag) ;

    }
    public  void  setseletedSuccessful(seletedsuccessful listener){
        mseletedsuccessful = listener;
    }

}
