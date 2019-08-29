package com.example.educationmanage.bean;

import java.io.Serializable;
import java.util.List;

public class ShopNameBean implements Serializable {

    public content getData() {
        return data;
    }

    public void setData(content data) {
        this.data = data;
    }

    content data;
    public  class content implements Serializable{
        String ds;

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
            String Id;//店面id
            String Name;//店面名称
            String Phone;//电话
            String Sto_Add;//地址
            String Beizhu;//备注

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

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String phone) {
                Phone = phone;
            }

            public String getSto_Add() {
                return Sto_Add;
            }

            public void setSto_Add(String sto_Add) {
                Sto_Add = sto_Add;
            }

            public String getBeizhu() {
                return Beizhu;
            }

            public void setBeizhu(String beizhu) {
                Beizhu = beizhu;
            }
        }


    }

}
