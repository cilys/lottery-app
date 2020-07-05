package com.cily.lottery.bean;

import java.io.Serializable;
import java.util.List;

public class OrderBean implements Serializable {
    private List<ItemBean> list;
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private int totalRow;
    private boolean firstPage;
    private boolean lastPage;

    public List<ItemBean> getList() {
        return list;
    }

    public void setList(List<ItemBean> list) {
        this.list = list;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public static class ItemBean implements Serializable {
        private String bonusStatus;
        private String orderStatus;
        private String schemeId;
        private String orderOperator;
        private String updateTime;
        private String operatorName;
        private String operator;
        private String cusertomerName;
        private String payedRate;
        private String payType;
        private String bonusMoney;
        private String payOperatorName;
        private String money;
        private String createTime;
        private String customerId;
        private String id;

        public String getBonusStatus() {
            return bonusStatus;
        }

        public void setBonusStatus(String bonusStatus) {
            this.bonusStatus = bonusStatus;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getSchemeId() {
            return schemeId;
        }

        public void setSchemeId(String schemeId) {
            this.schemeId = schemeId;
        }

        public String getOrderOperator() {
            return orderOperator;
        }

        public void setOrderOperator(String orderOperator) {
            this.orderOperator = orderOperator;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getCusertomerName() {
            return cusertomerName;
        }

        public void setCusertomerName(String cusertomerName) {
            this.cusertomerName = cusertomerName;
        }

        public String getPayedRate() {
            return payedRate;
        }

        public void setPayedRate(String payedRate) {
            this.payedRate = payedRate;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getBonusMoney() {
            return bonusMoney;
        }

        public void setBonusMoney(String bonusMoney) {
            this.bonusMoney = bonusMoney;
        }

        public String getPayOperatorName() {
            return payOperatorName;
        }

        public void setPayOperatorName(String payOperatorName) {
            this.payOperatorName = payOperatorName;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
