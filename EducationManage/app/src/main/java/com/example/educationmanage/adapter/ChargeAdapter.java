package com.example.educationmanage.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.educationmanage.R;
import com.example.educationmanage.bean.ChargeBean;

import java.util.ArrayList;
import java.util.List;

public class ChargeAdapter extends RecyclerView.Adapter {

    private List<ChargeBean.content.table> listbean = new ArrayList();
    public seletedsuccessful mseletedsuccessful;
    public static class ViewHolderContent extends RecyclerView.ViewHolder {
        public View rootView;


        TextView chargeclassname;//课程名字
        TextView  chargeCou_Per;//课时
        TextView  chargePrice;//价格
        TextView chargeCou_Bvar1;//机构
        public ViewHolderContent(View rootView) {
            super(rootView);
            this.rootView = rootView;

            chargeclassname = rootView.findViewById(R.id.chargeclassname);

            chargeCou_Per = rootView.findViewById(R.id.chargeCou_Per);
            chargePrice = rootView.findViewById(R.id.chargePrice);
            chargeCou_Bvar1 = rootView.findViewById(R.id.chargeCou_Bvar1);


        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_charge, viewGroup, false);
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
        ChargeBean.content.table item = listbean.get(i);
        ((ViewHolderContent)viewHolder).chargeclassname.setText("课程名称："+item.getName());
        ((ViewHolderContent)viewHolder). chargeCou_Per.setText("课时："+item.getCou_Per());
        ((ViewHolderContent)viewHolder).chargePrice.setText("价格："+item.getPrice());
        ((ViewHolderContent)viewHolder).chargeCou_Bvar1.setText("机构名称："+item.getCou_Bvar1());

    }

    @Override
    public int getItemCount() {
        return listbean.size();
    }
    public  ChargeAdapter(List list){
        listbean = list;
    }
    public interface   seletedsuccessful{
        void  onItemClick(Object tag) ;

    }
    public  void  setseletedSuccessful(seletedsuccessful listener){
        mseletedsuccessful = listener;
    }
}
