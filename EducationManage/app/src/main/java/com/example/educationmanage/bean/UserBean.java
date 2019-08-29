package com.example.educationmanage.bean;

import java.io.Serializable;

public class UserBean   implements Serializable {
    public content getData() {
        return data;
    }

    public void setData(content data) {
        this.data = data;
    }

    content data;
    public  class content implements Serializable{
        String ds;
        String userid;

        public String getDs() {
            return ds;
        }

        public void setDs(String ds) {
            this.ds = ds;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
