package com.example.educationmanage.bean;

import java.io.Serializable;
import java.util.List;

public class AddBean {
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

        public String getDs() {
            return ds;
        }

        public void setDs(String ds) {
            this.ds = ds;
        }





    }
}
