package com.example.educationmanage.bean;

import java.io.Serializable;
import java.util.List;

public class MembersListBean implements Serializable{
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
         String name;//会员名字
         String Stu_Date;//出生日期
         String md;//门店

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStu_Date() {
                return Stu_Date;
            }

            public void setStu_Date(String stu_Date) {
                Stu_Date = stu_Date;
            }

            public String getMd() {
                return md;
            }

            public void setMd(String md) {
                this.md = md;
            }
        }


    }
}
