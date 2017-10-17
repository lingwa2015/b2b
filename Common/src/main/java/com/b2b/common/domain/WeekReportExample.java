package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WeekReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WeekReportExample() {
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

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date not between", value1, value2, "endDate");
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

        public Criteria andConsumeShopNumIsNull() {
            addCriterion("consume_shop_num is null");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumIsNotNull() {
            addCriterion("consume_shop_num is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumEqualTo(Integer value) {
            addCriterion("consume_shop_num =", value, "consumeShopNum");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumNotEqualTo(Integer value) {
            addCriterion("consume_shop_num <>", value, "consumeShopNum");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumGreaterThan(Integer value) {
            addCriterion("consume_shop_num >", value, "consumeShopNum");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("consume_shop_num >=", value, "consumeShopNum");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumLessThan(Integer value) {
            addCriterion("consume_shop_num <", value, "consumeShopNum");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumLessThanOrEqualTo(Integer value) {
            addCriterion("consume_shop_num <=", value, "consumeShopNum");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumIn(List<Integer> values) {
            addCriterion("consume_shop_num in", values, "consumeShopNum");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumNotIn(List<Integer> values) {
            addCriterion("consume_shop_num not in", values, "consumeShopNum");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumBetween(Integer value1, Integer value2) {
            addCriterion("consume_shop_num between", value1, value2, "consumeShopNum");
            return (Criteria) this;
        }

        public Criteria andConsumeShopNumNotBetween(Integer value1, Integer value2) {
            addCriterion("consume_shop_num not between", value1, value2, "consumeShopNum");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeIsNull() {
            addCriterion("consume_fee is null");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeIsNotNull() {
            addCriterion("consume_fee is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeEqualTo(Long value) {
            addCriterion("consume_fee =", value, "consumeFee");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeNotEqualTo(Long value) {
            addCriterion("consume_fee <>", value, "consumeFee");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeGreaterThan(Long value) {
            addCriterion("consume_fee >", value, "consumeFee");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("consume_fee >=", value, "consumeFee");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeLessThan(Long value) {
            addCriterion("consume_fee <", value, "consumeFee");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeLessThanOrEqualTo(Long value) {
            addCriterion("consume_fee <=", value, "consumeFee");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeIn(List<Long> values) {
            addCriterion("consume_fee in", values, "consumeFee");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeNotIn(List<Long> values) {
            addCriterion("consume_fee not in", values, "consumeFee");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeBetween(Long value1, Long value2) {
            addCriterion("consume_fee between", value1, value2, "consumeFee");
            return (Criteria) this;
        }

        public Criteria andConsumeFeeNotBetween(Long value1, Long value2) {
            addCriterion("consume_fee not between", value1, value2, "consumeFee");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeIsNull() {
            addCriterion("before_consume_fee is null");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeIsNotNull() {
            addCriterion("before_consume_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeEqualTo(BigDecimal value) {
            addCriterion("before_consume_fee =", value, "beforeConsumeFee");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeNotEqualTo(BigDecimal value) {
            addCriterion("before_consume_fee <>", value, "beforeConsumeFee");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeGreaterThan(BigDecimal value) {
            addCriterion("before_consume_fee >", value, "beforeConsumeFee");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_consume_fee >=", value, "beforeConsumeFee");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeLessThan(BigDecimal value) {
            addCriterion("before_consume_fee <", value, "beforeConsumeFee");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_consume_fee <=", value, "beforeConsumeFee");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeIn(List<BigDecimal> values) {
            addCriterion("before_consume_fee in", values, "beforeConsumeFee");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeNotIn(List<BigDecimal> values) {
            addCriterion("before_consume_fee not in", values, "beforeConsumeFee");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_consume_fee between", value1, value2, "beforeConsumeFee");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_consume_fee not between", value1, value2, "beforeConsumeFee");
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

        public Criteria andAvgDayFeeIsNull() {
            addCriterion("avg_day_fee is null");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeIsNotNull() {
            addCriterion("avg_day_fee is not null");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeEqualTo(Long value) {
            addCriterion("avg_day_fee =", value, "avgDayFee");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeNotEqualTo(Long value) {
            addCriterion("avg_day_fee <>", value, "avgDayFee");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeGreaterThan(Long value) {
            addCriterion("avg_day_fee >", value, "avgDayFee");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("avg_day_fee >=", value, "avgDayFee");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeLessThan(Long value) {
            addCriterion("avg_day_fee <", value, "avgDayFee");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeLessThanOrEqualTo(Long value) {
            addCriterion("avg_day_fee <=", value, "avgDayFee");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeIn(List<Long> values) {
            addCriterion("avg_day_fee in", values, "avgDayFee");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeNotIn(List<Long> values) {
            addCriterion("avg_day_fee not in", values, "avgDayFee");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeBetween(Long value1, Long value2) {
            addCriterion("avg_day_fee between", value1, value2, "avgDayFee");
            return (Criteria) this;
        }

        public Criteria andAvgDayFeeNotBetween(Long value1, Long value2) {
            addCriterion("avg_day_fee not between", value1, value2, "avgDayFee");
            return (Criteria) this;
        }

        public Criteria andConsumePenIsNull() {
            addCriterion("consume_pen is null");
            return (Criteria) this;
        }

        public Criteria andConsumePenIsNotNull() {
            addCriterion("consume_pen is not null");
            return (Criteria) this;
        }

        public Criteria andConsumePenEqualTo(Integer value) {
            addCriterion("consume_pen =", value, "consumePen");
            return (Criteria) this;
        }

        public Criteria andConsumePenNotEqualTo(Integer value) {
            addCriterion("consume_pen <>", value, "consumePen");
            return (Criteria) this;
        }

        public Criteria andConsumePenGreaterThan(Integer value) {
            addCriterion("consume_pen >", value, "consumePen");
            return (Criteria) this;
        }

        public Criteria andConsumePenGreaterThanOrEqualTo(Integer value) {
            addCriterion("consume_pen >=", value, "consumePen");
            return (Criteria) this;
        }

        public Criteria andConsumePenLessThan(Integer value) {
            addCriterion("consume_pen <", value, "consumePen");
            return (Criteria) this;
        }

        public Criteria andConsumePenLessThanOrEqualTo(Integer value) {
            addCriterion("consume_pen <=", value, "consumePen");
            return (Criteria) this;
        }

        public Criteria andConsumePenIn(List<Integer> values) {
            addCriterion("consume_pen in", values, "consumePen");
            return (Criteria) this;
        }

        public Criteria andConsumePenNotIn(List<Integer> values) {
            addCriterion("consume_pen not in", values, "consumePen");
            return (Criteria) this;
        }

        public Criteria andConsumePenBetween(Integer value1, Integer value2) {
            addCriterion("consume_pen between", value1, value2, "consumePen");
            return (Criteria) this;
        }

        public Criteria andConsumePenNotBetween(Integer value1, Integer value2) {
            addCriterion("consume_pen not between", value1, value2, "consumePen");
            return (Criteria) this;
        }

        public Criteria andConsumeNumIsNull() {
            addCriterion("consume_num is null");
            return (Criteria) this;
        }

        public Criteria andConsumeNumIsNotNull() {
            addCriterion("consume_num is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeNumEqualTo(Integer value) {
            addCriterion("consume_num =", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumNotEqualTo(Integer value) {
            addCriterion("consume_num <>", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumGreaterThan(Integer value) {
            addCriterion("consume_num >", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("consume_num >=", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumLessThan(Integer value) {
            addCriterion("consume_num <", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumLessThanOrEqualTo(Integer value) {
            addCriterion("consume_num <=", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumIn(List<Integer> values) {
            addCriterion("consume_num in", values, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumNotIn(List<Integer> values) {
            addCriterion("consume_num not in", values, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumBetween(Integer value1, Integer value2) {
            addCriterion("consume_num between", value1, value2, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("consume_num not between", value1, value2, "consumeNum");
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