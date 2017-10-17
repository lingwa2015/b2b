package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MonthReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MonthReportExample() {
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

        public Criteria andOpenNumIsNull() {
            addCriterion("open_num is null");
            return (Criteria) this;
        }

        public Criteria andOpenNumIsNotNull() {
            addCriterion("open_num is not null");
            return (Criteria) this;
        }

        public Criteria andOpenNumEqualTo(Integer value) {
            addCriterion("open_num =", value, "openNum");
            return (Criteria) this;
        }

        public Criteria andOpenNumNotEqualTo(Integer value) {
            addCriterion("open_num <>", value, "openNum");
            return (Criteria) this;
        }

        public Criteria andOpenNumGreaterThan(Integer value) {
            addCriterion("open_num >", value, "openNum");
            return (Criteria) this;
        }

        public Criteria andOpenNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("open_num >=", value, "openNum");
            return (Criteria) this;
        }

        public Criteria andOpenNumLessThan(Integer value) {
            addCriterion("open_num <", value, "openNum");
            return (Criteria) this;
        }

        public Criteria andOpenNumLessThanOrEqualTo(Integer value) {
            addCriterion("open_num <=", value, "openNum");
            return (Criteria) this;
        }

        public Criteria andOpenNumIn(List<Integer> values) {
            addCriterion("open_num in", values, "openNum");
            return (Criteria) this;
        }

        public Criteria andOpenNumNotIn(List<Integer> values) {
            addCriterion("open_num not in", values, "openNum");
            return (Criteria) this;
        }

        public Criteria andOpenNumBetween(Integer value1, Integer value2) {
            addCriterion("open_num between", value1, value2, "openNum");
            return (Criteria) this;
        }

        public Criteria andOpenNumNotBetween(Integer value1, Integer value2) {
            addCriterion("open_num not between", value1, value2, "openNum");
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

        public Criteria andBeforeAvgFeeIsNull() {
            addCriterion("before_avg_fee is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeIsNotNull() {
            addCriterion("before_avg_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeEqualTo(BigDecimal value) {
            addCriterion("before_avg_fee =", value, "beforeAvgFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeNotEqualTo(BigDecimal value) {
            addCriterion("before_avg_fee <>", value, "beforeAvgFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeGreaterThan(BigDecimal value) {
            addCriterion("before_avg_fee >", value, "beforeAvgFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_avg_fee >=", value, "beforeAvgFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeLessThan(BigDecimal value) {
            addCriterion("before_avg_fee <", value, "beforeAvgFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_avg_fee <=", value, "beforeAvgFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeIn(List<BigDecimal> values) {
            addCriterion("before_avg_fee in", values, "beforeAvgFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeNotIn(List<BigDecimal> values) {
            addCriterion("before_avg_fee not in", values, "beforeAvgFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_avg_fee between", value1, value2, "beforeAvgFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_avg_fee not between", value1, value2, "beforeAvgFee");
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

        public Criteria andBeforeAvgDayFeeIsNull() {
            addCriterion("before_avg_day_fee is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeIsNotNull() {
            addCriterion("before_avg_day_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeEqualTo(BigDecimal value) {
            addCriterion("before_avg_day_fee =", value, "beforeAvgDayFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeNotEqualTo(BigDecimal value) {
            addCriterion("before_avg_day_fee <>", value, "beforeAvgDayFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeGreaterThan(BigDecimal value) {
            addCriterion("before_avg_day_fee >", value, "beforeAvgDayFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_avg_day_fee >=", value, "beforeAvgDayFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeLessThan(BigDecimal value) {
            addCriterion("before_avg_day_fee <", value, "beforeAvgDayFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_avg_day_fee <=", value, "beforeAvgDayFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeIn(List<BigDecimal> values) {
            addCriterion("before_avg_day_fee in", values, "beforeAvgDayFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeNotIn(List<BigDecimal> values) {
            addCriterion("before_avg_day_fee not in", values, "beforeAvgDayFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_avg_day_fee between", value1, value2, "beforeAvgDayFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgDayFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_avg_day_fee not between", value1, value2, "beforeAvgDayFee");
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

        public Criteria andBeforeConsumePenIsNull() {
            addCriterion("before_consume_pen is null");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenIsNotNull() {
            addCriterion("before_consume_pen is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenEqualTo(BigDecimal value) {
            addCriterion("before_consume_pen =", value, "beforeConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenNotEqualTo(BigDecimal value) {
            addCriterion("before_consume_pen <>", value, "beforeConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenGreaterThan(BigDecimal value) {
            addCriterion("before_consume_pen >", value, "beforeConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_consume_pen >=", value, "beforeConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenLessThan(BigDecimal value) {
            addCriterion("before_consume_pen <", value, "beforeConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_consume_pen <=", value, "beforeConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenIn(List<BigDecimal> values) {
            addCriterion("before_consume_pen in", values, "beforeConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenNotIn(List<BigDecimal> values) {
            addCriterion("before_consume_pen not in", values, "beforeConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_consume_pen between", value1, value2, "beforeConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumePenNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_consume_pen not between", value1, value2, "beforeConsumePen");
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

        public Criteria andBeforeConsumeNumIsNull() {
            addCriterion("before_consume_num is null");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumIsNotNull() {
            addCriterion("before_consume_num is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumEqualTo(BigDecimal value) {
            addCriterion("before_consume_num =", value, "beforeConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumNotEqualTo(BigDecimal value) {
            addCriterion("before_consume_num <>", value, "beforeConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumGreaterThan(BigDecimal value) {
            addCriterion("before_consume_num >", value, "beforeConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_consume_num >=", value, "beforeConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumLessThan(BigDecimal value) {
            addCriterion("before_consume_num <", value, "beforeConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_consume_num <=", value, "beforeConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumIn(List<BigDecimal> values) {
            addCriterion("before_consume_num in", values, "beforeConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumNotIn(List<BigDecimal> values) {
            addCriterion("before_consume_num not in", values, "beforeConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_consume_num between", value1, value2, "beforeConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeConsumeNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_consume_num not between", value1, value2, "beforeConsumeNum");
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

        public Criteria andBeforeOrderNumIsNull() {
            addCriterion("before_order_num is null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumIsNotNull() {
            addCriterion("before_order_num is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumEqualTo(BigDecimal value) {
            addCriterion("before_order_num =", value, "beforeOrderNum");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumNotEqualTo(BigDecimal value) {
            addCriterion("before_order_num <>", value, "beforeOrderNum");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumGreaterThan(BigDecimal value) {
            addCriterion("before_order_num >", value, "beforeOrderNum");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_order_num >=", value, "beforeOrderNum");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumLessThan(BigDecimal value) {
            addCriterion("before_order_num <", value, "beforeOrderNum");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_order_num <=", value, "beforeOrderNum");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumIn(List<BigDecimal> values) {
            addCriterion("before_order_num in", values, "beforeOrderNum");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumNotIn(List<BigDecimal> values) {
            addCriterion("before_order_num not in", values, "beforeOrderNum");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_order_num between", value1, value2, "beforeOrderNum");
            return (Criteria) this;
        }

        public Criteria andBeforeOrderNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_order_num not between", value1, value2, "beforeOrderNum");
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

        public Criteria andLossPercentIsNull() {
            addCriterion("loss_percent is null");
            return (Criteria) this;
        }

        public Criteria andLossPercentIsNotNull() {
            addCriterion("loss_percent is not null");
            return (Criteria) this;
        }

        public Criteria andLossPercentEqualTo(Integer value) {
            addCriterion("loss_percent =", value, "lossPercent");
            return (Criteria) this;
        }

        public Criteria andLossPercentNotEqualTo(Integer value) {
            addCriterion("loss_percent <>", value, "lossPercent");
            return (Criteria) this;
        }

        public Criteria andLossPercentGreaterThan(Integer value) {
            addCriterion("loss_percent >", value, "lossPercent");
            return (Criteria) this;
        }

        public Criteria andLossPercentGreaterThanOrEqualTo(Integer value) {
            addCriterion("loss_percent >=", value, "lossPercent");
            return (Criteria) this;
        }

        public Criteria andLossPercentLessThan(Integer value) {
            addCriterion("loss_percent <", value, "lossPercent");
            return (Criteria) this;
        }

        public Criteria andLossPercentLessThanOrEqualTo(Integer value) {
            addCriterion("loss_percent <=", value, "lossPercent");
            return (Criteria) this;
        }

        public Criteria andLossPercentIn(List<Integer> values) {
            addCriterion("loss_percent in", values, "lossPercent");
            return (Criteria) this;
        }

        public Criteria andLossPercentNotIn(List<Integer> values) {
            addCriterion("loss_percent not in", values, "lossPercent");
            return (Criteria) this;
        }

        public Criteria andLossPercentBetween(Integer value1, Integer value2) {
            addCriterion("loss_percent between", value1, value2, "lossPercent");
            return (Criteria) this;
        }

        public Criteria andLossPercentNotBetween(Integer value1, Integer value2) {
            addCriterion("loss_percent not between", value1, value2, "lossPercent");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentIsNull() {
            addCriterion("before_loss_percent is null");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentIsNotNull() {
            addCriterion("before_loss_percent is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentEqualTo(BigDecimal value) {
            addCriterion("before_loss_percent =", value, "beforeLossPercent");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentNotEqualTo(BigDecimal value) {
            addCriterion("before_loss_percent <>", value, "beforeLossPercent");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentGreaterThan(BigDecimal value) {
            addCriterion("before_loss_percent >", value, "beforeLossPercent");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_loss_percent >=", value, "beforeLossPercent");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentLessThan(BigDecimal value) {
            addCriterion("before_loss_percent <", value, "beforeLossPercent");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_loss_percent <=", value, "beforeLossPercent");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentIn(List<BigDecimal> values) {
            addCriterion("before_loss_percent in", values, "beforeLossPercent");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentNotIn(List<BigDecimal> values) {
            addCriterion("before_loss_percent not in", values, "beforeLossPercent");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_loss_percent between", value1, value2, "beforeLossPercent");
            return (Criteria) this;
        }

        public Criteria andBeforeLossPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_loss_percent not between", value1, value2, "beforeLossPercent");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockIsNull() {
            addCriterion("month_first_stock is null");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockIsNotNull() {
            addCriterion("month_first_stock is not null");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockEqualTo(Long value) {
            addCriterion("month_first_stock =", value, "monthFirstStock");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockNotEqualTo(Long value) {
            addCriterion("month_first_stock <>", value, "monthFirstStock");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockGreaterThan(Long value) {
            addCriterion("month_first_stock >", value, "monthFirstStock");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockGreaterThanOrEqualTo(Long value) {
            addCriterion("month_first_stock >=", value, "monthFirstStock");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockLessThan(Long value) {
            addCriterion("month_first_stock <", value, "monthFirstStock");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockLessThanOrEqualTo(Long value) {
            addCriterion("month_first_stock <=", value, "monthFirstStock");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockIn(List<Long> values) {
            addCriterion("month_first_stock in", values, "monthFirstStock");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockNotIn(List<Long> values) {
            addCriterion("month_first_stock not in", values, "monthFirstStock");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockBetween(Long value1, Long value2) {
            addCriterion("month_first_stock between", value1, value2, "monthFirstStock");
            return (Criteria) this;
        }

        public Criteria andMonthFirstStockNotBetween(Long value1, Long value2) {
            addCriterion("month_first_stock not between", value1, value2, "monthFirstStock");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockIsNull() {
            addCriterion("month_last_stock is null");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockIsNotNull() {
            addCriterion("month_last_stock is not null");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockEqualTo(Long value) {
            addCriterion("month_last_stock =", value, "monthLastStock");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockNotEqualTo(Long value) {
            addCriterion("month_last_stock <>", value, "monthLastStock");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockGreaterThan(Long value) {
            addCriterion("month_last_stock >", value, "monthLastStock");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockGreaterThanOrEqualTo(Long value) {
            addCriterion("month_last_stock >=", value, "monthLastStock");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockLessThan(Long value) {
            addCriterion("month_last_stock <", value, "monthLastStock");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockLessThanOrEqualTo(Long value) {
            addCriterion("month_last_stock <=", value, "monthLastStock");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockIn(List<Long> values) {
            addCriterion("month_last_stock in", values, "monthLastStock");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockNotIn(List<Long> values) {
            addCriterion("month_last_stock not in", values, "monthLastStock");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockBetween(Long value1, Long value2) {
            addCriterion("month_last_stock between", value1, value2, "monthLastStock");
            return (Criteria) this;
        }

        public Criteria andMonthLastStockNotBetween(Long value1, Long value2) {
            addCriterion("month_last_stock not between", value1, value2, "monthLastStock");
            return (Criteria) this;
        }

        public Criteria andExpandFeeIsNull() {
            addCriterion("expand_fee is null");
            return (Criteria) this;
        }

        public Criteria andExpandFeeIsNotNull() {
            addCriterion("expand_fee is not null");
            return (Criteria) this;
        }

        public Criteria andExpandFeeEqualTo(Long value) {
            addCriterion("expand_fee =", value, "expandFee");
            return (Criteria) this;
        }

        public Criteria andExpandFeeNotEqualTo(Long value) {
            addCriterion("expand_fee <>", value, "expandFee");
            return (Criteria) this;
        }

        public Criteria andExpandFeeGreaterThan(Long value) {
            addCriterion("expand_fee >", value, "expandFee");
            return (Criteria) this;
        }

        public Criteria andExpandFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("expand_fee >=", value, "expandFee");
            return (Criteria) this;
        }

        public Criteria andExpandFeeLessThan(Long value) {
            addCriterion("expand_fee <", value, "expandFee");
            return (Criteria) this;
        }

        public Criteria andExpandFeeLessThanOrEqualTo(Long value) {
            addCriterion("expand_fee <=", value, "expandFee");
            return (Criteria) this;
        }

        public Criteria andExpandFeeIn(List<Long> values) {
            addCriterion("expand_fee in", values, "expandFee");
            return (Criteria) this;
        }

        public Criteria andExpandFeeNotIn(List<Long> values) {
            addCriterion("expand_fee not in", values, "expandFee");
            return (Criteria) this;
        }

        public Criteria andExpandFeeBetween(Long value1, Long value2) {
            addCriterion("expand_fee between", value1, value2, "expandFee");
            return (Criteria) this;
        }

        public Criteria andExpandFeeNotBetween(Long value1, Long value2) {
            addCriterion("expand_fee not between", value1, value2, "expandFee");
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