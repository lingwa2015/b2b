package com.b2b.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaymentExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(Integer value) {
            addCriterion("supplier_id =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(Integer value) {
            addCriterion("supplier_id <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(Integer value) {
            addCriterion("supplier_id >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_id >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(Integer value) {
            addCriterion("supplier_id <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_id <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<Integer> values) {
            addCriterion("supplier_id in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<Integer> values) {
            addCriterion("supplier_id not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(Integer value1, Integer value2) {
            addCriterion("supplier_id between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(Integer value1, Integer value2) {
            addCriterion("supplier_id not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNull() {
            addCriterion("supplier is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNotNull() {
            addCriterion("supplier is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierEqualTo(String value) {
            addCriterion("supplier =", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotEqualTo(String value) {
            addCriterion("supplier <>", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThan(String value) {
            addCriterion("supplier >", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThanOrEqualTo(String value) {
            addCriterion("supplier >=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThan(String value) {
            addCriterion("supplier <", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThanOrEqualTo(String value) {
            addCriterion("supplier <=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLike(String value) {
            addCriterion("supplier like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotLike(String value) {
            addCriterion("supplier not like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierIn(List<String> values) {
            addCriterion("supplier in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotIn(List<String> values) {
            addCriterion("supplier not in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierBetween(String value1, String value2) {
            addCriterion("supplier between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotBetween(String value1, String value2) {
            addCriterion("supplier not between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeIsNull() {
            addCriterion("payment_time is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeIsNotNull() {
            addCriterion("payment_time is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeEqualTo(Date value) {
            addCriterion("payment_time =", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotEqualTo(Date value) {
            addCriterion("payment_time <>", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeGreaterThan(Date value) {
            addCriterion("payment_time >", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("payment_time >=", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeLessThan(Date value) {
            addCriterion("payment_time <", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeLessThanOrEqualTo(Date value) {
            addCriterion("payment_time <=", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeIn(List<Date> values) {
            addCriterion("payment_time in", values, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotIn(List<Date> values) {
            addCriterion("payment_time not in", values, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeBetween(Date value1, Date value2) {
            addCriterion("payment_time between", value1, value2, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotBetween(Date value1, Date value2) {
            addCriterion("payment_time not between", value1, value2, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeIsNull() {
            addCriterion("paymented_time is null");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeIsNotNull() {
            addCriterion("paymented_time is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeEqualTo(Date value) {
            addCriterion("paymented_time =", value, "paymentedTime");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeNotEqualTo(Date value) {
            addCriterion("paymented_time <>", value, "paymentedTime");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeGreaterThan(Date value) {
            addCriterion("paymented_time >", value, "paymentedTime");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("paymented_time >=", value, "paymentedTime");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeLessThan(Date value) {
            addCriterion("paymented_time <", value, "paymentedTime");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeLessThanOrEqualTo(Date value) {
            addCriterion("paymented_time <=", value, "paymentedTime");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeIn(List<Date> values) {
            addCriterion("paymented_time in", values, "paymentedTime");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeNotIn(List<Date> values) {
            addCriterion("paymented_time not in", values, "paymentedTime");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeBetween(Date value1, Date value2) {
            addCriterion("paymented_time between", value1, value2, "paymentedTime");
            return (Criteria) this;
        }

        public Criteria andPaymentedTimeNotBetween(Date value1, Date value2) {
            addCriterion("paymented_time not between", value1, value2, "paymentedTime");
            return (Criteria) this;
        }

        public Criteria andSumdateIsNull() {
            addCriterion("sumdate is null");
            return (Criteria) this;
        }

        public Criteria andSumdateIsNotNull() {
            addCriterion("sumdate is not null");
            return (Criteria) this;
        }

        public Criteria andSumdateEqualTo(Date value) {
            addCriterion("sumdate =", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateNotEqualTo(Date value) {
            addCriterion("sumdate <>", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateGreaterThan(Date value) {
            addCriterion("sumdate >", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateGreaterThanOrEqualTo(Date value) {
            addCriterion("sumdate >=", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateLessThan(Date value) {
            addCriterion("sumdate <", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateLessThanOrEqualTo(Date value) {
            addCriterion("sumdate <=", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateIn(List<Date> values) {
            addCriterion("sumdate in", values, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateNotIn(List<Date> values) {
            addCriterion("sumdate not in", values, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateBetween(Date value1, Date value2) {
            addCriterion("sumdate between", value1, value2, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateNotBetween(Date value1, Date value2) {
            addCriterion("sumdate not between", value1, value2, "sumdate");
            return (Criteria) this;
        }

        public Criteria andStockPriceIsNull() {
            addCriterion("stock_price is null");
            return (Criteria) this;
        }

        public Criteria andStockPriceIsNotNull() {
            addCriterion("stock_price is not null");
            return (Criteria) this;
        }

        public Criteria andStockPriceEqualTo(Long value) {
            addCriterion("stock_price =", value, "stockPrice");
            return (Criteria) this;
        }

        public Criteria andStockPriceNotEqualTo(Long value) {
            addCriterion("stock_price <>", value, "stockPrice");
            return (Criteria) this;
        }

        public Criteria andStockPriceGreaterThan(Long value) {
            addCriterion("stock_price >", value, "stockPrice");
            return (Criteria) this;
        }

        public Criteria andStockPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("stock_price >=", value, "stockPrice");
            return (Criteria) this;
        }

        public Criteria andStockPriceLessThan(Long value) {
            addCriterion("stock_price <", value, "stockPrice");
            return (Criteria) this;
        }

        public Criteria andStockPriceLessThanOrEqualTo(Long value) {
            addCriterion("stock_price <=", value, "stockPrice");
            return (Criteria) this;
        }

        public Criteria andStockPriceIn(List<Long> values) {
            addCriterion("stock_price in", values, "stockPrice");
            return (Criteria) this;
        }

        public Criteria andStockPriceNotIn(List<Long> values) {
            addCriterion("stock_price not in", values, "stockPrice");
            return (Criteria) this;
        }

        public Criteria andStockPriceBetween(Long value1, Long value2) {
            addCriterion("stock_price between", value1, value2, "stockPrice");
            return (Criteria) this;
        }

        public Criteria andStockPriceNotBetween(Long value1, Long value2) {
            addCriterion("stock_price not between", value1, value2, "stockPrice");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceIsNull() {
            addCriterion("outstock_price is null");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceIsNotNull() {
            addCriterion("outstock_price is not null");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceEqualTo(Long value) {
            addCriterion("outstock_price =", value, "outstockPrice");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceNotEqualTo(Long value) {
            addCriterion("outstock_price <>", value, "outstockPrice");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceGreaterThan(Long value) {
            addCriterion("outstock_price >", value, "outstockPrice");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("outstock_price >=", value, "outstockPrice");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceLessThan(Long value) {
            addCriterion("outstock_price <", value, "outstockPrice");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceLessThanOrEqualTo(Long value) {
            addCriterion("outstock_price <=", value, "outstockPrice");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceIn(List<Long> values) {
            addCriterion("outstock_price in", values, "outstockPrice");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceNotIn(List<Long> values) {
            addCriterion("outstock_price not in", values, "outstockPrice");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceBetween(Long value1, Long value2) {
            addCriterion("outstock_price between", value1, value2, "outstockPrice");
            return (Criteria) this;
        }

        public Criteria andOutstockPriceNotBetween(Long value1, Long value2) {
            addCriterion("outstock_price not between", value1, value2, "outstockPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceIsNull() {
            addCriterion("payment_price is null");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceIsNotNull() {
            addCriterion("payment_price is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceEqualTo(Long value) {
            addCriterion("payment_price =", value, "paymentPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceNotEqualTo(Long value) {
            addCriterion("payment_price <>", value, "paymentPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceGreaterThan(Long value) {
            addCriterion("payment_price >", value, "paymentPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("payment_price >=", value, "paymentPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceLessThan(Long value) {
            addCriterion("payment_price <", value, "paymentPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceLessThanOrEqualTo(Long value) {
            addCriterion("payment_price <=", value, "paymentPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceIn(List<Long> values) {
            addCriterion("payment_price in", values, "paymentPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceNotIn(List<Long> values) {
            addCriterion("payment_price not in", values, "paymentPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceBetween(Long value1, Long value2) {
            addCriterion("payment_price between", value1, value2, "paymentPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentPriceNotBetween(Long value1, Long value2) {
            addCriterion("payment_price not between", value1, value2, "paymentPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceIsNull() {
            addCriterion("paymenting_price is null");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceIsNotNull() {
            addCriterion("paymenting_price is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceEqualTo(Long value) {
            addCriterion("paymenting_price =", value, "paymentingPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceNotEqualTo(Long value) {
            addCriterion("paymenting_price <>", value, "paymentingPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceGreaterThan(Long value) {
            addCriterion("paymenting_price >", value, "paymentingPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("paymenting_price >=", value, "paymentingPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceLessThan(Long value) {
            addCriterion("paymenting_price <", value, "paymentingPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceLessThanOrEqualTo(Long value) {
            addCriterion("paymenting_price <=", value, "paymentingPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceIn(List<Long> values) {
            addCriterion("paymenting_price in", values, "paymentingPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceNotIn(List<Long> values) {
            addCriterion("paymenting_price not in", values, "paymentingPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceBetween(Long value1, Long value2) {
            addCriterion("paymenting_price between", value1, value2, "paymentingPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentingPriceNotBetween(Long value1, Long value2) {
            addCriterion("paymenting_price not between", value1, value2, "paymentingPrice");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceIsNull() {
            addCriterion("notpayment_price is null");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceIsNotNull() {
            addCriterion("notpayment_price is not null");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceEqualTo(Long value) {
            addCriterion("notpayment_price =", value, "notpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceNotEqualTo(Long value) {
            addCriterion("notpayment_price <>", value, "notpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceGreaterThan(Long value) {
            addCriterion("notpayment_price >", value, "notpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("notpayment_price >=", value, "notpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceLessThan(Long value) {
            addCriterion("notpayment_price <", value, "notpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceLessThanOrEqualTo(Long value) {
            addCriterion("notpayment_price <=", value, "notpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceIn(List<Long> values) {
            addCriterion("notpayment_price in", values, "notpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceNotIn(List<Long> values) {
            addCriterion("notpayment_price not in", values, "notpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceBetween(Long value1, Long value2) {
            addCriterion("notpayment_price between", value1, value2, "notpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andNotpaymentPriceNotBetween(Long value1, Long value2) {
            addCriterion("notpayment_price not between", value1, value2, "notpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceIsNull() {
            addCriterion("preferential_benefit_price is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceIsNotNull() {
            addCriterion("preferential_benefit_price is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceEqualTo(Long value) {
            addCriterion("preferential_benefit_price =", value, "preferentialBenefitPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceNotEqualTo(Long value) {
            addCriterion("preferential_benefit_price <>", value, "preferentialBenefitPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceGreaterThan(Long value) {
            addCriterion("preferential_benefit_price >", value, "preferentialBenefitPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("preferential_benefit_price >=", value, "preferentialBenefitPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceLessThan(Long value) {
            addCriterion("preferential_benefit_price <", value, "preferentialBenefitPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceLessThanOrEqualTo(Long value) {
            addCriterion("preferential_benefit_price <=", value, "preferentialBenefitPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceIn(List<Long> values) {
            addCriterion("preferential_benefit_price in", values, "preferentialBenefitPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceNotIn(List<Long> values) {
            addCriterion("preferential_benefit_price not in", values, "preferentialBenefitPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceBetween(Long value1, Long value2) {
            addCriterion("preferential_benefit_price between", value1, value2, "preferentialBenefitPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialBenefitPriceNotBetween(Long value1, Long value2) {
            addCriterion("preferential_benefit_price not between", value1, value2, "preferentialBenefitPrice");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceIsNull() {
            addCriterion("receipt_price is null");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceIsNotNull() {
            addCriterion("receipt_price is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceEqualTo(Long value) {
            addCriterion("receipt_price =", value, "receiptPrice");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceNotEqualTo(Long value) {
            addCriterion("receipt_price <>", value, "receiptPrice");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceGreaterThan(Long value) {
            addCriterion("receipt_price >", value, "receiptPrice");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("receipt_price >=", value, "receiptPrice");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceLessThan(Long value) {
            addCriterion("receipt_price <", value, "receiptPrice");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceLessThanOrEqualTo(Long value) {
            addCriterion("receipt_price <=", value, "receiptPrice");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceIn(List<Long> values) {
            addCriterion("receipt_price in", values, "receiptPrice");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceNotIn(List<Long> values) {
            addCriterion("receipt_price not in", values, "receiptPrice");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceBetween(Long value1, Long value2) {
            addCriterion("receipt_price between", value1, value2, "receiptPrice");
            return (Criteria) this;
        }

        public Criteria andReceiptPriceNotBetween(Long value1, Long value2) {
            addCriterion("receipt_price not between", value1, value2, "receiptPrice");
            return (Criteria) this;
        }

        public Criteria andApplicantIdIsNull() {
            addCriterion("applicant_id is null");
            return (Criteria) this;
        }

        public Criteria andApplicantIdIsNotNull() {
            addCriterion("applicant_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplicantIdEqualTo(Integer value) {
            addCriterion("applicant_id =", value, "applicantId");
            return (Criteria) this;
        }

        public Criteria andApplicantIdNotEqualTo(Integer value) {
            addCriterion("applicant_id <>", value, "applicantId");
            return (Criteria) this;
        }

        public Criteria andApplicantIdGreaterThan(Integer value) {
            addCriterion("applicant_id >", value, "applicantId");
            return (Criteria) this;
        }

        public Criteria andApplicantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("applicant_id >=", value, "applicantId");
            return (Criteria) this;
        }

        public Criteria andApplicantIdLessThan(Integer value) {
            addCriterion("applicant_id <", value, "applicantId");
            return (Criteria) this;
        }

        public Criteria andApplicantIdLessThanOrEqualTo(Integer value) {
            addCriterion("applicant_id <=", value, "applicantId");
            return (Criteria) this;
        }

        public Criteria andApplicantIdIn(List<Integer> values) {
            addCriterion("applicant_id in", values, "applicantId");
            return (Criteria) this;
        }

        public Criteria andApplicantIdNotIn(List<Integer> values) {
            addCriterion("applicant_id not in", values, "applicantId");
            return (Criteria) this;
        }

        public Criteria andApplicantIdBetween(Integer value1, Integer value2) {
            addCriterion("applicant_id between", value1, value2, "applicantId");
            return (Criteria) this;
        }

        public Criteria andApplicantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("applicant_id not between", value1, value2, "applicantId");
            return (Criteria) this;
        }

        public Criteria andApplicantIsNull() {
            addCriterion("applicant is null");
            return (Criteria) this;
        }

        public Criteria andApplicantIsNotNull() {
            addCriterion("applicant is not null");
            return (Criteria) this;
        }

        public Criteria andApplicantEqualTo(String value) {
            addCriterion("applicant =", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotEqualTo(String value) {
            addCriterion("applicant <>", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantGreaterThan(String value) {
            addCriterion("applicant >", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantGreaterThanOrEqualTo(String value) {
            addCriterion("applicant >=", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantLessThan(String value) {
            addCriterion("applicant <", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantLessThanOrEqualTo(String value) {
            addCriterion("applicant <=", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantLike(String value) {
            addCriterion("applicant like", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotLike(String value) {
            addCriterion("applicant not like", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantIn(List<String> values) {
            addCriterion("applicant in", values, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotIn(List<String> values) {
            addCriterion("applicant not in", values, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantBetween(String value1, String value2) {
            addCriterion("applicant between", value1, value2, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotBetween(String value1, String value2) {
            addCriterion("applicant not between", value1, value2, "applicant");
            return (Criteria) this;
        }

        public Criteria andRemakeIsNull() {
            addCriterion("remake is null");
            return (Criteria) this;
        }

        public Criteria andRemakeIsNotNull() {
            addCriterion("remake is not null");
            return (Criteria) this;
        }

        public Criteria andRemakeEqualTo(String value) {
            addCriterion("remake =", value, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeNotEqualTo(String value) {
            addCriterion("remake <>", value, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeGreaterThan(String value) {
            addCriterion("remake >", value, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeGreaterThanOrEqualTo(String value) {
            addCriterion("remake >=", value, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeLessThan(String value) {
            addCriterion("remake <", value, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeLessThanOrEqualTo(String value) {
            addCriterion("remake <=", value, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeLike(String value) {
            addCriterion("remake like", value, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeNotLike(String value) {
            addCriterion("remake not like", value, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeIn(List<String> values) {
            addCriterion("remake in", values, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeNotIn(List<String> values) {
            addCriterion("remake not in", values, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeBetween(String value1, String value2) {
            addCriterion("remake between", value1, value2, "remake");
            return (Criteria) this;
        }

        public Criteria andRemakeNotBetween(String value1, String value2) {
            addCriterion("remake not between", value1, value2, "remake");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andReceiptNoIsNull() {
            addCriterion("receipt_no is null");
            return (Criteria) this;
        }

        public Criteria andReceiptNoIsNotNull() {
            addCriterion("receipt_no is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptNoEqualTo(String value) {
            addCriterion("receipt_no =", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoNotEqualTo(String value) {
            addCriterion("receipt_no <>", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoGreaterThan(String value) {
            addCriterion("receipt_no >", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoGreaterThanOrEqualTo(String value) {
            addCriterion("receipt_no >=", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoLessThan(String value) {
            addCriterion("receipt_no <", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoLessThanOrEqualTo(String value) {
            addCriterion("receipt_no <=", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoLike(String value) {
            addCriterion("receipt_no like", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoNotLike(String value) {
            addCriterion("receipt_no not like", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoIn(List<String> values) {
            addCriterion("receipt_no in", values, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoNotIn(List<String> values) {
            addCriterion("receipt_no not in", values, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoBetween(String value1, String value2) {
            addCriterion("receipt_no between", value1, value2, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoNotBetween(String value1, String value2) {
            addCriterion("receipt_no not between", value1, value2, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Integer value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Integer value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Integer value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Integer value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Integer> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Integer> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Integer value1, Integer value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceIsNull() {
            addCriterion("before_month_notpayment_price is null");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceIsNotNull() {
            addCriterion("before_month_notpayment_price is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceEqualTo(Long value) {
            addCriterion("before_month_notpayment_price =", value, "beforeMonthNotpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceNotEqualTo(Long value) {
            addCriterion("before_month_notpayment_price <>", value, "beforeMonthNotpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceGreaterThan(Long value) {
            addCriterion("before_month_notpayment_price >", value, "beforeMonthNotpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("before_month_notpayment_price >=", value, "beforeMonthNotpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceLessThan(Long value) {
            addCriterion("before_month_notpayment_price <", value, "beforeMonthNotpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceLessThanOrEqualTo(Long value) {
            addCriterion("before_month_notpayment_price <=", value, "beforeMonthNotpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceIn(List<Long> values) {
            addCriterion("before_month_notpayment_price in", values, "beforeMonthNotpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceNotIn(List<Long> values) {
            addCriterion("before_month_notpayment_price not in", values, "beforeMonthNotpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceBetween(Long value1, Long value2) {
            addCriterion("before_month_notpayment_price between", value1, value2, "beforeMonthNotpaymentPrice");
            return (Criteria) this;
        }

        public Criteria andBeforeMonthNotpaymentPriceNotBetween(Long value1, Long value2) {
            addCriterion("before_month_notpayment_price not between", value1, value2, "beforeMonthNotpaymentPrice");
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