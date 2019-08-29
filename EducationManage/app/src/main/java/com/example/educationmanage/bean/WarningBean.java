package com.example.educationmanage.bean;

import java.io.Serializable;
import java.util.List;

public class WarningBean implements Serializable {
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
      String  hyid;//会员id
      String kechengID;//课程id
      String syks;//剩余课时
      String kcmc;//课程名称
      String hybh;//会员编号
      String hymc;//会员名称

      public String getHymc() {
          return hymc;
      }

      public void setHymc(String hymc) {
          this.hymc = hymc;
      }

      public String getHyid() {
          return hyid;
      }

      public void setHyid(String hyid) {
          this.hyid = hyid;
      }

      public String getKechengID() {
          return kechengID;
      }

      public void setKechengID(String kechengID) {
          this.kechengID = kechengID;
      }

      public String getSyks() {
          return syks;
      }

      public void setSyks(String syks) {
          this.syks = syks;
      }

      public String getKcmc() {
          return kcmc;
      }

      public void setKcmc(String kcmc) {
          this.kcmc = kcmc;
      }

      public String getHybh() {
          return hybh;
      }

      public void setHybh(String hybh) {
          this.hybh = hybh;
      }
  }


    }
}
