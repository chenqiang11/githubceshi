package com.example.educationmanage.bean;

import java.io.Serializable;
import java.util.List;

public class ChargeBean implements Serializable{


    public content getData() {
        return data;
    }

    public void setData(content data) {
        this.data = data;
    }

    content data;


    public  class content implements Serializable {
        String ds;
        String sm;

        public String getSm() {
            return sm;
        }

        public void setSm(String sm) {
            this.sm = sm;
        }
        public List<table> getTable1() {
            return table1;
        }

        public void setTable1(List<table> table1) {
            this.table1 = table1;
        }

        List<table> table1;
        public String getDs() {
            return ds;
        }

        public void setDs(String ds) {
            this.ds = ds;
        }

        public class table implements Serializable{
            String Id;//课程id
           String Name;//课程名称
            String Cou_Per;//课时
            String Price;//价格
            String Cou_Time;//时间
            String Cou_JG;
            String Sto_id;//门店id
            String Beizhu;
            String Cou_Bvar1;//所属机构名

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getCou_Per() {
                return Cou_Per;
            }

            public void setCou_Per(String cou_Per) {
                Cou_Per = cou_Per;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String price) {
                Price = price;
            }

            public String getCou_Time() {
                return Cou_Time;
            }

            public void setCou_Time(String cou_Time) {
                Cou_Time = cou_Time;
            }

            public String getCou_JG() {
                return Cou_JG;
            }

            public void setCou_JG(String cou_JG) {
                Cou_JG = cou_JG;
            }

            public String getSto_id() {
                return Sto_id;
            }

            public void setSto_id(String sto_id) {
                Sto_id = sto_id;
            }

            public String getBeizhu() {
                return Beizhu;
            }

            public void setBeizhu(String beizhu) {
                Beizhu = beizhu;
            }

            public String getCou_Bvar1() {
                return Cou_Bvar1;
            }

            public void setCou_Bvar1(String cou_Bvar1) {
                Cou_Bvar1 = cou_Bvar1;
            }
        }


    }
}
