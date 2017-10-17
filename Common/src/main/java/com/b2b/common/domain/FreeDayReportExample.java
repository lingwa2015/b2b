package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FreeDayReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FreeDayReportExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andSumDateIsNull() {
            addCriterion("sum_date is null");
            return (Criteria) this;
        }

        public Criteria andSumDateIsNotNull() {
            addCriterion("sum_date is not null");
            return (Criteria) this;
        }

        public Criteria andSumDateEqualTo(Date value) {
            addCriterionForJDBCDate("sum_date =", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("sum_date <>", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateGreaterThan(Date value) {
            addCriterionForJDBCDate("sum_date >", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sum_date >=", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateLessThan(Date value) {
            addCriterionForJDBCDate("sum_date <", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sum_date <=", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateIn(List<Date> values) {
            addCriterionForJDBCDate("sum_date in", values, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("sum_date not in", values, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sum_date between", value1, value2, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sum_date not between", value1, value2, "sumDate");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumIsNull() {
            addCriterion("open_shop_num is null");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumIsNotNull() {
            addCriterion("open_shop_num is not null");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumEqualTo(Integer value) {
            addCriterion("open_shop_num =", value, "openShopNum");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumNotEqualTo(Integer value) {
            addCriterion("open_shop_num <>", value, "openShopNum");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumGreaterThan(Integer value) {
            addCriterion("open_shop_num >", value, "openShopNum");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("open_shop_num >=", value, "openShopNum");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumLessThan(Integer value) {
            addCriterion("open_shop_num <", value, "openShopNum");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumLessThanOrEqualTo(Integer value) {
            addCriterion("open_shop_num <=", value, "openShopNum");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumIn(List<Integer> values) {
            addCriterion("open_shop_num in", values, "openShopNum");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumNotIn(List<Integer> values) {
            addCriterion("open_shop_num not in", values, "openShopNum");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumBetween(Integer value1, Integer value2) {
            addCriterion("open_shop_num between", value1, value2, "openShopNum");
            return (Criteria) this;
        }

        public Criteria andOpenShopNumNotBetween(Integer value1, Integer value2) {
            addCriterion("open_shop_num not between", value1, value2, "openShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumIsNull() {
            addCriterion("order_shop_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumIsNotNull() {
            addCriterion("order_shop_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumEqualTo(Integer value) {
            addCriterion("order_shop_num =", value, "orderShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumNotEqualTo(Integer value) {
            addCriterion("order_shop_num <>", value, "orderShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumGreaterThan(Integer value) {
            addCriterion("order_shop_num >", value, "orderShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_shop_num >=", value, "orderShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumLessThan(Integer value) {
            addCriterion("order_shop_num <", value, "orderShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumLessThanOrEqualTo(Integer value) {
            addCriterion("order_shop_num <=", value, "orderShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumIn(List<Integer> values) {
            addCriterion("order_shop_num in", values, "orderShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumNotIn(List<Integer> values) {
            addCriterion("order_shop_num not in", values, "orderShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumBetween(Integer value1, Integer value2) {
            addCriterion("order_shop_num between", value1, value2, "orderShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderShopNumNotBetween(Integer value1, Integer value2) {
            addCriterion("order_shop_num not between", value1, value2, "orderShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderFeeIsNull() {
            addCriterion("order_fee is null");
            return (Criteria) this;
        }

        public Criteria andOrderFeeIsNotNull() {
            addCriterion("order_fee is not null");
            return (Criteria) this;
        }

        public Criteria andOrderFeeEqualTo(Long value) {
            addCriterion("order_fee =", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeNotEqualTo(Long value) {
            addCriterion("order_fee <>", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeGreaterThan(Long value) {
            addCriterion("order_fee >", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("order_fee >=", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeLessThan(Long value) {
            addCriterion("order_fee <", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeLessThanOrEqualTo(Long value) {
            addCriterion("order_fee <=", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeIn(List<Long> values) {
            addCriterion("order_fee in", values, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeNotIn(List<Long> values) {
            addCriterion("order_fee not in", values, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeBetween(Long value1, Long value2) {
            addCriterion("order_fee between", value1, value2, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeNotBetween(Long value1, Long value2) {
            addCriterion("order_fee not between", value1, value2, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNull() {
            addCriterion("order_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("order_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(Integer value) {
            addCriterion("order_num =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(Integer value) {
            addCriterion("order_num <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(Integer value) {
            addCriterion("order_num >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_num >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(Integer value) {
            addCriterion("order_num <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("order_num <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<Integer> values) {
            addCriterion("order_num in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<Integer> values) {
            addCriterion("order_num not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("order_num between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("order_num not between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andRefundFeeIsNull() {
            addCriterion("refund_fee is null");
            return (Criteria) this;
        }

        public Criteria andRefundFeeIsNotNull() {
            addCriterion("refund_fee is not null");
            return (Criteria) this;
        }

        public Criteria andRefundFeeEqualTo(Long value) {
            addCriterion("refund_fee =", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeNotEqualTo(Long value) {
            addCriterion("refund_fee <>", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeGreaterThan(Long value) {
            addCriterion("refund_fee >", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("refund_fee >=", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeLessThan(Long value) {
            addCriterion("refund_fee <", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeLessThanOrEqualTo(Long value) {
            addCriterion("refund_fee <=", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeIn(List<Long> values) {
            addCriterion("refund_fee in", values, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeNotIn(List<Long> values) {
            addCriterion("refund_fee not in", values, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeBetween(Long value1, Long value2) {
            addCriterion("refund_fee between", value1, value2, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeNotBetween(Long value1, Long value2) {
            addCriterion("refund_fee not between", value1, value2, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundNumIsNull() {
            addCriterion("refund_num is null");
            return (Criteria) this;
        }

        public Criteria andRefundNumIsNotNull() {
            addCriterion("refund_num is not null");
            return (Criteria) this;
        }

        public Criteria andRefundNumEqualTo(Integer value) {
            addCriterion("refund_num =", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumNotEqualTo(Integer value) {
            addCriterion("refund_num <>", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumGreaterThan(Integer value) {
            addCriterion("refund_num >", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_num >=", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumLessThan(Integer value) {
            addCriterion("refund_num <", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumLessThanOrEqualTo(Integer value) {
            addCriterion("refund_num <=", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumIn(List<Integer> values) {
            addCriterion("refund_num in", values, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumNotIn(List<Integer> values) {
            addCriterion("refund_num not in", values, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumBetween(Integer value1, Integer value2) {
            addCriterion("refund_num between", value1, value2, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_num not between", value1, value2, "refundNum");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeIsNull() {
            addCriterion("sourcing_fee is null");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeIsNotNull() {
            addCriterion("sourcing_fee is not null");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeEqualTo(Long value) {
            addCriterion("sourcing_fee =", value, "sourcingFee");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeNotEqualTo(Long value) {
            addCriterion("sourcing_fee <>", value, "sourcingFee");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeGreaterThan(Long value) {
            addCriterion("sourcing_fee >", value, "sourcingFee");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("sourcing_fee >=", value, "sourcingFee");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeLessThan(Long value) {
            addCriterion("sourcing_fee <", value, "sourcingFee");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeLessThanOrEqualTo(Long value) {
            addCriterion("sourcing_fee <=", value, "sourcingFee");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeIn(List<Long> values) {
            addCriterion("sourcing_fee in", values, "sourcingFee");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeNotIn(List<Long> values) {
            addCriterion("sourcing_fee not in", values, "sourcingFee");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeBetween(Long value1, Long value2) {
            addCriterion("sourcing_fee between", value1, value2, "sourcingFee");
            return (Criteria) this;
        }

        public Criteria andSourcingFeeNotBetween(Long value1, Long value2) {
            addCriterion("sourcing_fee not between", value1, value2, "sourcingFee");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeIsNull() {
            addCriterion("before_sourcing_fee is null");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeIsNotNull() {
            addCriterion("before_sourcing_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeEqualTo(BigDecimal value) {
            addCriterion("before_sourcing_fee =", value, "beforeSourcingFee");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeNotEqualTo(BigDecimal value) {
            addCriterion("before_sourcing_fee <>", value, "beforeSourcingFee");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeGreaterThan(BigDecimal value) {
            addCriterion("before_sourcing_fee >", value, "beforeSourcingFee");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_sourcing_fee >=", value, "beforeSourcingFee");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeLessThan(BigDecimal value) {
            addCriterion("before_sourcing_fee <", value, "beforeSourcingFee");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_sourcing_fee <=", value, "beforeSourcingFee");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeIn(List<BigDecimal> values) {
            addCriterion("before_sourcing_fee in", values, "beforeSourcingFee");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeNotIn(List<BigDecimal> values) {
            addCriterion("before_sourcing_fee not in", values, "beforeSourcingFee");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_sourcing_fee between", value1, value2, "beforeSourcingFee");
            return (Criteria) this;
        }

        public Criteria andBeforeSourcingFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_sourcing_fee not between", value1, value2, "beforeSourcingFee");
            return (Criteria) this;
        }

        public Criteria andAvgFeeIsNull() {
            addCriterion("avg_fee is null");
            return (Criteria) this;
        }

        public Criteria andAvgFeeIsNotNull() {
            addCriterion("avg_fee is not null");
            return (Criteria) this;
        }

        public Criteria andAvgFeeEqualTo(Long value) {
            addCriterion("avg_fee =", value, "avgFee");
            return (Criteria) this;
        }

        public Criteria andAvgFeeNotEqualTo(Long value) {
            addCriterion("avg_fee <>", value, "avgFee");
            return (Criteria) this;
        }

        public Criteria andAvgFeeGreaterThan(Long value) {
            addCriterion("avg_fee >", value, "avgFee");
            return (Criteria) this;
        }

        public Criteria andAvgFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("avg_fee >=", value, "avgFee");
            return (Criteria) this;
        }

        public Criteria andAvgFeeLessThan(Long value) {
            addCriterion("avg_fee <", value, "avgFee");
            return (Criteria) this;
        }

        public Criteria andAvgFeeLessThanOrEqualTo(Long value) {
            addCriterion("avg_fee <=", value, "avgFee");
            return (Criteria) this;
        }

        public Criteria andAvgFeeIn(List<Long> values) {
            addCriterion("avg_fee in", values, "avgFee");
            return (Criteria) this;
        }

        public Criteria andAvgFeeNotIn(List<Long> values) {
            addCriterion("avg_fee not in", values, "avgFee");
            return (Criteria) this;
        }

        public Criteria andAvgFeeBetween(Long value1, Long value2) {
            addCriterion("avg_fee between", value1, value2, "avgFee");
            return (Criteria) this;
        }

        public Criteria andAvgFeeNotBetween(Long value1, Long value2) {
            addCriterion("avg_fee not between", value1, value2, "avgFee");
            return (Criteria) this;
        }

        public Criteria andReseauIdIsNull() {
            addCriterion("reseau_id is null");
            return (Criteria) this;
        }

        public Criteria andReseauIdIsNotNull() {
            addCriterion("reseau_id is not null");
            return (Criteria) this;
        }

        public Criteria andReseauIdEqualTo(Integer value) {
            addCriterion("reseau_id =", value, "reseauId");
            return (Criteria) this;
        }

        public Criteria andReseauIdNotEqualTo(Integer value) {
            addCriterion("reseau_id <>", value, "reseauId");
            return (Criteria) this;
        }

        public Criteria andReseauIdGreaterThan(Integer value) {
            addCriterion("reseau_id >", value, "reseauId");
            return (Criteria) this;
        }

        public Criteria andReseauIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reseau_id >=", value, "reseauId");
            return (Criteria) this;
        }

        public Criteria andReseauIdLessThan(Integer value) {
            addCriterion("reseau_id <", value, "reseauId");
            return (Criteria) this;
        }

        public Criteria andReseauIdLessThanOrEqualTo(Integer value) {
            addCriterion("reseau_id <=", value, "reseauId");
            return (Criteria) this;
        }

        public Criteria andReseauIdIn(List<Integer> values) {
            addCriterion("reseau_id in", values, "reseauId");
            return (Criteria) this;
        }

        public Criteria andReseauIdNotIn(List<Integer> values) {
            addCriterion("reseau_id not in", values, "reseauId");
            return (Criteria) this;
        }

        public Criteria andReseauIdBetween(Integer value1, Integer value2) {
            addCriterion("reseau_id between", value1, value2, "reseauId");
            return (Criteria) this;
        }

        public Criteria andReseauIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reseau_id not between", value1, value2, "reseauId");
            return (Criteria) this;
        }

        public Criteria andReseauNameIsNull() {
            addCriterion("reseau_name is null");
            return (Criteria) this;
        }

        public Criteria andReseauNameIsNotNull() {
            addCriterion("reseau_name is not null");
            return (Criteria) this;
        }

        public Criteria andReseauNameEqualTo(String value) {
            addCriterion("reseau_name =", value, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameNotEqualTo(String value) {
            addCriterion("reseau_name <>", value, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameGreaterThan(String value) {
            addCriterion("reseau_name >", value, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameGreaterThanOrEqualTo(String value) {
            addCriterion("reseau_name >=", value, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameLessThan(String value) {
            addCriterion("reseau_name <", value, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameLessThanOrEqualTo(String value) {
            addCriterion("reseau_name <=", value, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameLike(String value) {
            addCriterion("reseau_name like", value, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameNotLike(String value) {
            addCriterion("reseau_name not like", value, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameIn(List<String> values) {
            addCriterion("reseau_name in", values, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameNotIn(List<String> values) {
            addCriterion("reseau_name not in", values, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameBetween(String value1, String value2) {
            addCriterion("reseau_name between", value1, value2, "reseauName");
            return (Criteria) this;
        }

        public Criteria andReseauNameNotBetween(String value1, String value2) {
            addCriterion("reseau_name not between", value1, value2, "reseauName");
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