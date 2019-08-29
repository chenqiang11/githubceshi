package com.example.educationmanage.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.educationmanage.R;
import com.example.educationmanage.bean.MembersListBean;
import com.example.educationmanage.bean.PatriarchlistBean;

import java.util.ArrayList;
import java.util.List;

public class PatriarchlistAdapter extends RecyclerView.Adapter {
    private List<PatriarchlistBean.content.table> listbean = new ArrayList();
    public seletedsuccessful mseletedsuccessful;
    public static class ViewHolderContent extends RecyclerView.ViewHolder {
        public View rootView;


        TextView patriarchname;//家长姓名
        TextView  patriarchphone;//家长电话
        TextView  patriarchaddress;//家庭地址
        TextView patriarchremark;//备注
        public ViewHolderContent(View rootView) {
            super(rootView);
            this.rootView = rootView;

            patriarchname = rootView.findViewById(R.id.patriarchname);

            patriarchphone = rootView.findViewById(R.id.patriarchphone);
            patriarchaddress = rootView.findViewById(R.id.patriarchaddress);
            patriarchremark = rootView.findViewById(R.id.patriarchremark);


        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_patriarchlist, viewGroup, false);
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
        PatriarchlistBean.content.table item = listbean.get(i);
        ((ViewHolderContent)viewHolder). patriarchname.setText("家长姓名："+item.getName());
        ((ViewHolderContent)viewHolder).patriarchphone.setText("家长电话："+item.getPhone());
        ((ViewHolderContent)viewHolder). patriarchaddress.setText("家长地址："+item.getPar_Add());
        ((ViewHolderContent)viewHolder). patriarchremark.setText("备注："+item.getBeizhu());

    }

    @Override
    public int getItemCount() {
        return listbean.size();
    }
    public  PatriarchlistAdapter(List list){
        listbean = list;
    }
    public interface   seletedsuccessful{
        void  onItemClick(Object tag) ;

    }
    public  void  setseletedSuccessful(seletedsuccessful listener){
        mseletedsuccessful = listener;
    }

}
