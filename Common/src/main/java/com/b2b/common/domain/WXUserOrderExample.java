package com.b2b.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WXUserOrderExample extends BaseExample{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WXUserOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andWxuserIdIsNull() {
            addCriterion("wxuser_id is null");
            return (Criteria) this;
        }

        public Criteria andWxuserIdIsNotNull() {
            addCriterion("wxuser_id is not null");
            return (Criteria) this;
        }

        public Criteria andWxuserIdEqualTo(Integer value) {
            addCriterion("wxuser_id =", value, "wxuserId");
            return (Criteria) this;
        }

        public Criteria andWxuserIdNotEqualTo(Integer value) {
            addCriterion("wxuser_id <>", value, "wxuserId");
            return (Criteria) this;
        }

        public Criteria andWxuserIdGreaterThan(Integer value) {
            addCriterion("wxuser_id >", value, "wxuserId");
            return (Criteria) this;
        }

        public Criteria andWxuserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wxuser_id >=", value, "wxuserId");
            return (Criteria) this;
        }

        public Criteria andWxuserIdLessThan(Integer value) {
            addCriterion("wxuser_id <", value, "wxuserId");
            return (Criteria) this;
        }

        public Criteria andWxuserIdLessThanOrEqualTo(Integer value) {
            addCriterion("wxuser_id <=", value, "wxuserId");
            return (Criteria) this;
        }

        public Criteria andWxuserIdIn(List<Integer> values) {
            addCriterion("wxuser_id in", values, "wxuserId");
            return (Criteria) this;
        }

        public Criteria andWxuserIdNotIn(List<Integer> values) {
            addCriterion("wxuser_id not in", values, "wxuserId");
            return (Criteria) this;
        }

        public Criteria andWxuserIdBetween(Integer value1, Integer value2) {
            addCriterion("wxuser_id between", value1, value2, "wxuserId");
            return (Criteria) this;
        }

        public Criteria andWxuserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wxuser_id not between", value1, value2, "wxuserId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdIsNull() {
            addCriterion("snackpackage_id is null");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdIsNotNull() {
            addCriterion("snackpackage_id is not null");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdEqualTo(Integer value) {
            addCriterion("snackpackage_id =", value, "snackpackageId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdNotEqualTo(Integer value) {
            addCriterion("snackpackage_id <>", value, "snackpackageId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdGreaterThan(Integer value) {
            addCriterion("snackpackage_id >", value, "snackpackageId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("snackpackage_id >=", value, "snackpackageId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdLessThan(Integer value) {
            addCriterion("snackpackage_id <", value, "snackpackageId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdLessThanOrEqualTo(Integer value) {
            addCriterion("snackpackage_id <=", value, "snackpackageId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdIn(List<Integer> values) {
            addCriterion("snackpackage_id in", values, "snackpackageId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdNotIn(List<Integer> values) {
            addCriterion("snackpackage_id not in", values, "snackpackageId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdBetween(Integer value1, Integer value2) {
            addCriterion("snackpackage_id between", value1, value2, "snackpackageId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("snackpackage_id not between", value1, value2, "snackpackageId");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumIsNull() {
            addCriterion("snackpackage_num is null");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumIsNotNull() {
            addCriterion("snackpackage_num is not null");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumEqualTo(Integer value) {
            addCriterion("snackpackage_num =", value, "snackpackageNum");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumNotEqualTo(Integer value) {
            addCriterion("snackpackage_num <>", value, "snackpackageNum");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumGreaterThan(Integer value) {
            addCriterion("snackpackage_num >", value, "snackpackageNum");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("snackpackage_num >=", value, "snackpackageNum");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumLessThan(Integer value) {
            addCriterion("snackpackage_num <", value, "snackpackageNum");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumLessThanOrEqualTo(Integer value) {
            addCriterion("snackpackage_num <=", value, "snackpackageNum");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumIn(List<Integer> values) {
            addCriterion("snackpackage_num in", values, "snackpackageNum");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumNotIn(List<Integer> values) {
            addCriterion("snackpackage_num not in", values, "snackpackageNum");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumBetween(Integer value1, Integer value2) {
            addCriterion("snackpackage_num between", value1, value2, "snackpackageNum");
            return (Criteria) this;
        }

        public Criteria andSnackpackageNumNotBetween(Integer value1, Integer value2) {
            addCriterion("snackpackage_num not between", value1, value2, "snackpackageNum");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeIsNull() {
            addCriterion("snackpackage_fee is null");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeIsNotNull() {
            addCriterion("snackpackage_fee is not null");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeEqualTo(Long value) {
            addCriterion("snackpackage_fee =", value, "snackpackageFee");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeNotEqualTo(Long value) {
            addCriterion("snackpackage_fee <>", value, "snackpackageFee");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeGreaterThan(Long value) {
            addCriterion("snackpackage_fee >", value, "snackpackageFee");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("snackpackage_fee >=", value, "snackpackageFee");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeLessThan(Long value) {
            addCriterion("snackpackage_fee <", value, "snackpackageFee");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeLessThanOrEqualTo(Long value) {
            addCriterion("snackpackage_fee <=", value, "snackpackageFee");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeIn(List<Long> values) {
            addCriterion("snackpackage_fee in", values, "snackpackageFee");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeNotIn(List<Long> values) {
            addCriterion("snackpackage_fee not in", values, "snackpackageFee");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeBetween(Long value1, Long value2) {
            addCriterion("snackpackage_fee between", value1, value2, "snackpackageFee");
            return (Criteria) this;
        }

        public Criteria andSnackpackageFeeNotBetween(Long value1, Long value2) {
            addCriterion("snackpackage_fee not between", value1, value2, "snackpackageFee");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalIsNull() {
            addCriterion("snackpackage_total is null");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalIsNotNull() {
            addCriterion("snackpackage_total is not null");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalEqualTo(Long value) {
            addCriterion("snackpackage_total =", value, "snackpackageTotal");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalNotEqualTo(Long value) {
            addCriterion("snackpackage_total <>", value, "snackpackageTotal");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalGreaterThan(Long value) {
            addCriterion("snackpackage_total >", value, "snackpackageTotal");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("snackpackage_total >=", value, "snackpackageTotal");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalLessThan(Long value) {
            addCriterion("snackpackage_total <", value, "snackpackageTotal");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalLessThanOrEqualTo(Long value) {
            addCriterion("snackpackage_total <=", value, "snackpackageTotal");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalIn(List<Long> values) {
            addCriterion("snackpackage_total in", values, "snackpackageTotal");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalNotIn(List<Long> values) {
            addCriterion("snackpackage_total not in", values, "snackpackageTotal");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalBetween(Long value1, Long value2) {
            addCriterion("snackpackage_total between", value1, value2, "snackpackageTotal");
            return (Criteria) this;
        }

        public Criteria andSnackpackageTotalNotBetween(Long value1, Long value2) {
            addCriterion("snackpackage_total not between", value1, value2, "snackpackageTotal");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusIsNull() {
            addCriterion("payfee_status is null");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusIsNotNull() {
            addCriterion("payfee_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusEqualTo(Integer value) {
            addCriterion("payfee_status =", value, "payfeeStatus");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusNotEqualTo(Integer value) {
            addCriterion("payfee_status <>", value, "payfeeStatus");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusGreaterThan(Integer value) {
            addCriterion("payfee_status >", value, "payfeeStatus");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("payfee_status >=", value, "payfeeStatus");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusLessThan(Integer value) {
            addCriterion("payfee_status <", value, "payfeeStatus");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("payfee_status <=", value, "payfeeStatus");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusIn(List<Integer> values) {
            addCriterion("payfee_status in", values, "payfeeStatus");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusNotIn(List<Integer> values) {
            addCriterion("payfee_status not in", values, "payfeeStatus");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusBetween(Integer value1, Integer value2) {
            addCriterion("payfee_status between", value1, value2, "payfeeStatus");
            return (Criteria) this;
        }

        public Criteria andPayfeeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("payfee_status not between", value1, value2, "payfeeStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIsNull() {
            addCriterion("invoice_status is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIsNotNull() {
            addCriterion("invoice_status is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusEqualTo(Integer value) {
            addCriterion("invoice_status =", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotEqualTo(Integer value) {
            addCriterion("invoice_status <>", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusGreaterThan(Integer value) {
            addCriterion("invoice_status >", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_status >=", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusLessThan(Integer value) {
            addCriterion("invoice_status <", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_status <=", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIn(List<Integer> values) {
            addCriterion("invoice_status in", values, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotIn(List<Integer> values) {
            addCriterion("invoice_status not in", values, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusBetween(Integer value1, Integer value2) {
            addCriterion("invoice_status between", value1, value2, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_status not between", value1, value2, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIsNull() {
            addCriterion("invoice_id is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIsNotNull() {
            addCriterion("invoice_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdEqualTo(Integer value) {
            addCriterion("invoice_id =", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotEqualTo(Integer value) {
            addCriterion("invoice_id <>", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdGreaterThan(Integer value) {
            addCriterion("invoice_id >", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_id >=", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdLessThan(Integer value) {
            addCriterion("invoice_id <", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_id <=", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIn(List<Integer> values) {
            addCriterion("invoice_id in", values, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotIn(List<Integer> values) {
            addCriterion("invoice_id not in", values, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdBetween(Integer value1, Integer value2) {
            addCriterion("invoice_id between", value1, value2, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_id not between", value1, value2, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNull() {
            addCriterion("address_id is null");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNotNull() {
            addCriterion("address_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIdEqualTo(Integer value) {
            addCriterion("address_id =", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotEqualTo(Integer value) {
            addCriterion("address_id <>", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThan(Integer value) {
            addCriterion("address_id >", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_id >=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThan(Integer value) {
            addCriterion("address_id <", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThanOrEqualTo(Integer value) {
            addCriterion("address_id <=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIn(List<Integer> values) {
            addCriterion("address_id in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotIn(List<Integer> values) {
            addCriterion("address_id not in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdBetween(Integer value1, Integer value2) {
            addCriterion("address_id between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("address_id not between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoIsNull() {
            addCriterion("fastexpress_no is null");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoIsNotNull() {
            addCriterion("fastexpress_no is not null");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoEqualTo(String value) {
            addCriterion("fastexpress_no =", value, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoNotEqualTo(String value) {
            addCriterion("fastexpress_no <>", value, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoGreaterThan(String value) {
            addCriterion("fastexpress_no >", value, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("fastexpress_no >=", value, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoLessThan(String value) {
            addCriterion("fastexpress_no <", value, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoLessThanOrEqualTo(String value) {
            addCriterion("fastexpress_no <=", value, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoLike(String value) {
            addCriterion("fastexpress_no like", value, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoNotLike(String value) {
            addCriterion("fastexpress_no not like", value, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoIn(List<String> values) {
            addCriterion("fastexpress_no in", values, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoNotIn(List<String> values) {
            addCriterion("fastexpress_no not in", values, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoBetween(String value1, String value2) {
            addCriterion("fastexpress_no between", value1, value2, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andFastexpressNoNotBetween(String value1, String value2) {
            addCriterion("fastexpress_no not between", value1, value2, "fastexpressNo");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andWxorderNumIsNull() {
            addCriterion("wxorder_num is null");
            return (Criteria) this;
        }

        public Criteria andWxorderNumIsNotNull() {
            addCriterion("wxorder_num is not null");
            return (Criteria) this;
        }

        public Criteria andWxorderNumEqualTo(String value) {
            addCriterion("wxorder_num =", value, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumNotEqualTo(String value) {
            addCriterion("wxorder_num <>", value, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumGreaterThan(String value) {
            addCriterion("wxorder_num >", value, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumGreaterThanOrEqualTo(String value) {
            addCriterion("wxorder_num >=", value, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumLessThan(String value) {
            addCriterion("wxorder_num <", value, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumLessThanOrEqualTo(String value) {
            addCriterion("wxorder_num <=", value, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumLike(String value) {
            addCriterion("wxorder_num like", value, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumNotLike(String value) {
            addCriterion("wxorder_num not like", value, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumIn(List<String> values) {
            addCriterion("wxorder_num in", values, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumNotIn(List<String> values) {
            addCriterion("wxorder_num not in", values, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumBetween(String value1, String value2) {
            addCriterion("wxorder_num between", value1, value2, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andWxorderNumNotBetween(String value1, String value2) {
            addCriterion("wxorder_num not between", value1, value2, "wxorderNum");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridIsNull() {
            addCriterion("updated_userid is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridIsNotNull() {
            addCriterion("updated_userid is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridEqualTo(Integer value) {
            addCriterion("updated_userid =", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridNotEqualTo(Integer value) {
            addCriterion("updated_userid <>", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridGreaterThan(Integer value) {
            addCriterion("updated_userid >", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("updated_userid >=", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridLessThan(Integer value) {
            addCriterion("updated_userid <", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridLessThanOrEqualTo(Integer value) {
            addCriterion("updated_userid <=", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridIn(List<Integer> values) {
            addCriterion("updated_userid in", values, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridNotIn(List<Integer> values) {
            addCriterion("updated_userid not in", values, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridBetween(Integer value1, Integer value2) {
            addCriterion("updated_userid between", value1, value2, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("updated_userid not between", value1, value2, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andWxnameIsNull() {
            addCriterion("wxname is null");
            return (Criteria) this;
        }

        public Criteria andWxnameIsNotNull() {
            addCriterion("wxname is not null");
            return (Criteria) this;
        }

        public Criteria andWxnameEqualTo(String value) {
            addCriterion("wxname =", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameNotEqualTo(String value) {
            addCriterion("wxname <>", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameGreaterThan(String value) {
            addCriterion("wxname >", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameGreaterThanOrEqualTo(String value) {
            addCriterion("wxname >=", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameLessThan(String value) {
            addCriterion("wxname <", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameLessThanOrEqualTo(String value) {
            addCriterion("wxname <=", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameLike(String value) {
            addCriterion("wxname like", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameNotLike(String value) {
            addCriterion("wxname not like", value, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameIn(List<String> values) {
            addCriterion("wxname in", values, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameNotIn(List<String> values) {
            addCriterion("wxname not in", values, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameBetween(String value1, String value2) {
            addCriterion("wxname between", value1, value2, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxnameNotBetween(String value1, String value2) {
            addCriterion("wxname not between", value1, value2, "wxname");
            return (Criteria) this;
        }

        public Criteria andWxphoneIsNull() {
            addCriterion("wxphone is null");
            return (Criteria) this;
        }

        public Criteria andWxphoneIsNotNull() {
            addCriterion("wxphone is not null");
            return (Criteria) this;
        }

        public Criteria andWxphoneEqualTo(String value) {
            addCriterion("wxphone =", value, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneNotEqualTo(String value) {
            addCriterion("wxphone <>", value, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneGreaterThan(String value) {
            addCriterion("wxphone >", value, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneGreaterThanOrEqualTo(String value) {
            addCriterion("wxphone >=", value, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneLessThan(String value) {
            addCriterion("wxphone <", value, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneLessThanOrEqualTo(String value) {
            addCriterion("wxphone <=", value, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneLike(String value) {
            addCriterion("wxphone like", value, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneNotLike(String value) {
            addCriterion("wxphone not like", value, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneIn(List<String> values) {
            addCriterion("wxphone in", values, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneNotIn(List<String> values) {
            addCriterion("wxphone not in", values, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneBetween(String value1, String value2) {
            addCriterion("wxphone between", value1, value2, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxphoneNotBetween(String value1, String value2) {
            addCriterion("wxphone not between", value1, value2, "wxphone");
            return (Criteria) this;
        }

        public Criteria andWxaddressIsNull() {
            addCriterion("wxaddress is null");
            return (Criteria) this;
        }

        public Criteria andWxaddressIsNotNull() {
            addCriterion("wxaddress is not null");
            return (Criteria) this;
        }

        public Criteria andWxaddressEqualTo(String value) {
            addCriterion("wxaddress =", value, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressNotEqualTo(String value) {
            addCriterion("wxaddress <>", value, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressGreaterThan(String value) {
            addCriterion("wxaddress >", value, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressGreaterThanOrEqualTo(String value) {
            addCriterion("wxaddress >=", value, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressLessThan(String value) {
            addCriterion("wxaddress <", value, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressLessThanOrEqualTo(String value) {
            addCriterion("wxaddress <=", value, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressLike(String value) {
            addCriterion("wxaddress like", value, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressNotLike(String value) {
            addCriterion("wxaddress not like", value, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressIn(List<String> values) {
            addCriterion("wxaddress in", values, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressNotIn(List<String> values) {
            addCriterion("wxaddress not in", values, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressBetween(String value1, String value2) {
            addCriterion("wxaddress between", value1, value2, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andWxaddressNotBetween(String value1, String value2) {
            addCriterion("wxaddress not between", value1, value2, "wxaddress");
            return (Criteria) this;
        }

        public Criteria andGroupsIsNull() {
            addCriterion("groups is null");
            return (Criteria) this;
        }

        public Criteria andGroupsIsNotNull() {
            addCriterion("groups is not null");
            return (Criteria) this;
        }

        public Criteria andGroupsEqualTo(Integer value) {
            addCriterion("groups =", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotEqualTo(Integer value) {
            addCriterion("groups <>", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsGreaterThan(Integer value) {
            addCriterion("groups >", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsGreaterThanOrEqualTo(Integer value) {
            addCriterion("groups >=", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsLessThan(Integer value) {
            addCriterion("groups <", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsLessThanOrEqualTo(Integer value) {
            addCriterion("groups <=", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsIn(List<Integer> values) {
            addCriterion("groups in", values, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotIn(List<Integer> values) {
            addCriterion("groups not in", values, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsBetween(Integer value1, Integer value2) {
            addCriterion("groups between", value1, value2, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotBetween(Integer value1, Integer value2) {
            addCriterion("groups not between", value1, value2, "groups");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}