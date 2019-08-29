package com.example.educationmanage.bean;

import java.io.Serializable;
import java.util.List;

public class PatriarchlistBean implements Serializable {

    public content getData() {
        return data;
    }

    public void setData(content data) {
        this.data = data;
    }

    content data;
    public  class content implements Serializable {
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
            String Id;//id
            String Name;//家长名字
            String Phone;//电话
            String Par_Add;//地址
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

            public String getPar_Add() {
                return Par_Add;
            }

            public void setPar_Add(String par_Add) {
                Par_Add = par_Add;
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
