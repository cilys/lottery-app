package com.cily.lottery.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SchemeBean implements Serializable {

    private List<SchemeBean.ItemBean> list;
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private int totalRow;
    private boolean firstPage;
    private boolean lastPage;


    public static class ItemBean implements Serializable {
        private int maxUsers;
        private String totalBonus;
        private String canUseBonus;
        private String totalMoney;
        private String creatorId;
        private String updateTime;
        private String outOfTime;
        private String selledMoney;
        private String payedMoney;
        private String name;
        private String id;
        private String bonusRate;
        private String descption;
        private String status;

        public int getMaxUsers() {
            return maxUsers;
        }

        public void setMaxUsers(int maxUsers) {
            this.maxUsers = maxUsers;
        }

        public String getTotalBonus() {
            return totalBonus;
        }

        public void setTotalBonus(String totalBonus) {
            this.totalBonus = totalBonus;
        }

        public String getCanUseBonus() {
            return canUseBonus;
        }

        public void setCanUseBonus(String canUseBonus) {
            this.canUseBonus = canUseBonus;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public String getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(String creatorId) {
            this.creatorId = creatorId;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getOutOfTime() {
            return outOfTime;
        }

        public void setOutOfTime(String outOfTime) {
            this.outOfTime = outOfTime;
        }

        public String getSelledMoney() {
            return selledMoney;
        }

        public void setSelledMoney(String selledMoney) {
            this.selledMoney = selledMoney;
        }

        public String getPayedMoney() {
            return payedMoney;
        }

        public void setPayedMoney(String payedMoney) {
            this.payedMoney = payedMoney;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBonusRate() {
            return bonusRate;
        }

        public void setBonusRate(String bonusRate) {
            this.bonusRate = bonusRate;
        }

        public String getDescption() {
            return descption;
        }

        public void setDescption(String descption) {
            this.descption = descption;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}