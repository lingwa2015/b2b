package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DailyReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DailyReportExample() {
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

        public Criteria andBeforeLastWeekNumIsNull() {
            addCriterion("before_last_week_num is null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumIsNotNull() {
            addCriterion("before_last_week_num is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumEqualTo(Integer value) {
            addCriterion("before_last_week_num =", value, "beforeLastWeekNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumNotEqualTo(Integer value) {
            addCriterion("before_last_week_num <>", value, "beforeLastWeekNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumGreaterThan(Integer value) {
            addCriterion("before_last_week_num >", value, "beforeLastWeekNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("before_last_week_num >=", value, "beforeLastWeekNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumLessThan(Integer value) {
            addCriterion("before_last_week_num <", value, "beforeLastWeekNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumLessThanOrEqualTo(Integer value) {
            addCriterion("before_last_week_num <=", value, "beforeLastWeekNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumIn(List<Integer> values) {
            addCriterion("before_last_week_num in", values, "beforeLastWeekNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumNotIn(List<Integer> values) {
            addCriterion("before_last_week_num not in", values, "beforeLastWeekNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumBetween(Integer value1, Integer value2) {
            addCriterion("before_last_week_num between", value1, value2, "beforeLastWeekNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekNumNotBetween(Integer value1, Integer value2) {
            addCriterion("before_last_week_num not between", value1, value2, "beforeLastWeekNum");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeIsNull() {
            addCriterion("total_consume is null");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeIsNotNull() {
            addCriterion("total_consume is not null");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeEqualTo(Long value) {
            addCriterion("total_consume =", value, "totalConsume");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeNotEqualTo(Long value) {
            addCriterion("total_consume <>", value, "totalConsume");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeGreaterThan(Long value) {
            addCriterion("total_consume >", value, "totalConsume");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeGreaterThanOrEqualTo(Long value) {
            addCriterion("total_consume >=", value, "totalConsume");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeLessThan(Long value) {
            addCriterion("total_consume <", value, "totalConsume");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeLessThanOrEqualTo(Long value) {
            addCriterion("total_consume <=", value, "totalConsume");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeIn(List<Long> values) {
            addCriterion("total_consume in", values, "totalConsume");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeNotIn(List<Long> values) {
            addCriterion("total_consume not in", values, "totalConsume");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeBetween(Long value1, Long value2) {
            addCriterion("total_consume between", value1, value2, "totalConsume");
            return (Criteria) this;
        }

        public Criteria andTotalConsumeNotBetween(Long value1, Long value2) {
            addCriterion("total_consume not between", value1, value2, "totalConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeIsNull() {
            addCriterion("before_last_week_consume is null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeIsNotNull() {
            addCriterion("before_last_week_consume is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume =", value, "beforeLastWeekConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNotEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume <>", value, "beforeLastWeekConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeGreaterThan(BigDecimal value) {
            addCriterion("before_last_week_consume >", value, "beforeLastWeekConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume >=", value, "beforeLastWeekConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeLessThan(BigDecimal value) {
            addCriterion("before_last_week_consume <", value, "beforeLastWeekConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume <=", value, "beforeLastWeekConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeIn(List<BigDecimal> values) {
            addCriterion("before_last_week_consume in", values, "beforeLastWeekConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNotIn(List<BigDecimal> values) {
            addCriterion("before_last_week_consume not in", values, "beforeLastWeekConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_last_week_consume between", value1, value2, "beforeLastWeekConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_last_week_consume not between", value1, value2, "beforeLastWeekConsume");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeIsNull() {
            addCriterion("avg_consume is null");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeIsNotNull() {
            addCriterion("avg_consume is not null");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeEqualTo(Long value) {
            addCriterion("avg_consume =", value, "avgConsume");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeNotEqualTo(Long value) {
            addCriterion("avg_consume <>", value, "avgConsume");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeGreaterThan(Long value) {
            addCriterion("avg_consume >", value, "avgConsume");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeGreaterThanOrEqualTo(Long value) {
            addCriterion("avg_consume >=", value, "avgConsume");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeLessThan(Long value) {
            addCriterion("avg_consume <", value, "avgConsume");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeLessThanOrEqualTo(Long value) {
            addCriterion("avg_consume <=", value, "avgConsume");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeIn(List<Long> values) {
            addCriterion("avg_consume in", values, "avgConsume");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeNotIn(List<Long> values) {
            addCriterion("avg_consume not in", values, "avgConsume");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeBetween(Long value1, Long value2) {
            addCriterion("avg_consume between", value1, value2, "avgConsume");
            return (Criteria) this;
        }

        public Criteria andAvgConsumeNotBetween(Long value1, Long value2) {
            addCriterion("avg_consume not between", value1, value2, "avgConsume");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgIsNull() {
            addCriterion("before_last_week_avg is null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgIsNotNull() {
            addCriterion("before_last_week_avg is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgEqualTo(BigDecimal value) {
            addCriterion("before_last_week_avg =", value, "beforeLastWeekAvg");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgNotEqualTo(BigDecimal value) {
            addCriterion("before_last_week_avg <>", value, "beforeLastWeekAvg");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgGreaterThan(BigDecimal value) {
            addCriterion("before_last_week_avg >", value, "beforeLastWeekAvg");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_last_week_avg >=", value, "beforeLastWeekAvg");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgLessThan(BigDecimal value) {
            addCriterion("before_last_week_avg <", value, "beforeLastWeekAvg");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_last_week_avg <=", value, "beforeLastWeekAvg");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgIn(List<BigDecimal> values) {
            addCriterion("before_last_week_avg in", values, "beforeLastWeekAvg");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgNotIn(List<BigDecimal> values) {
            addCriterion("before_last_week_avg not in", values, "beforeLastWeekAvg");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_last_week_avg between", value1, value2, "beforeLastWeekAvg");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekAvgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_last_week_avg not between", value1, value2, "beforeLastWeekAvg");
            return (Criteria) this;
        }

        public Criteria andTotalExpendIsNull() {
            addCriterion("total_expend is null");
            return (Criteria) this;
        }

        public Criteria andTotalExpendIsNotNull() {
            addCriterion("total_expend is not null");
            return (Criteria) this;
        }

        public Criteria andTotalExpendEqualTo(Long value) {
            addCriterion("total_expend =", value, "totalExpend");
            return (Criteria) this;
        }

        public Criteria andTotalExpendNotEqualTo(Long value) {
            addCriterion("total_expend <>", value, "totalExpend");
            return (Criteria) this;
        }

        public Criteria andTotalExpendGreaterThan(Long value) {
            addCriterion("total_expend >", value, "totalExpend");
            return (Criteria) this;
        }

        public Criteria andTotalExpendGreaterThanOrEqualTo(Long value) {
            addCriterion("total_expend >=", value, "totalExpend");
            return (Criteria) this;
        }

        public Criteria andTotalExpendLessThan(Long value) {
            addCriterion("total_expend <", value, "totalExpend");
            return (Criteria) this;
        }

        public Criteria andTotalExpendLessThanOrEqualTo(Long value) {
            addCriterion("total_expend <=", value, "totalExpend");
            return (Criteria) this;
        }

        public Criteria andTotalExpendIn(List<Long> values) {
            addCriterion("total_expend in", values, "totalExpend");
            return (Criteria) this;
        }

        public Criteria andTotalExpendNotIn(List<Long> values) {
            addCriterion("total_expend not in", values, "totalExpend");
            return (Criteria) this;
        }

        public Criteria andTotalExpendBetween(Long value1, Long value2) {
            addCriterion("total_expend between", value1, value2, "totalExpend");
            return (Criteria) this;
        }

        public Criteria andTotalExpendNotBetween(Long value1, Long value2) {
            addCriterion("total_expend not between", value1, value2, "totalExpend");
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

        public Criteria andBeforeLastWeekConsumeNumIsNull() {
            addCriterion("before_last_week_consume_num is null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumIsNotNull() {
            addCriterion("before_last_week_consume_num is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume_num =", value, "beforeLastWeekConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumNotEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume_num <>", value, "beforeLastWeekConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumGreaterThan(BigDecimal value) {
            addCriterion("before_last_week_consume_num >", value, "beforeLastWeekConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume_num >=", value, "beforeLastWeekConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumLessThan(BigDecimal value) {
            addCriterion("before_last_week_consume_num <", value, "beforeLastWeekConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume_num <=", value, "beforeLastWeekConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumIn(List<BigDecimal> values) {
            addCriterion("before_last_week_consume_num in", values, "beforeLastWeekConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumNotIn(List<BigDecimal> values) {
            addCriterion("before_last_week_consume_num not in", values, "beforeLastWeekConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_last_week_consume_num between", value1, value2, "beforeLastWeekConsumeNum");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumeNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_last_week_consume_num not between", value1, value2, "beforeLastWeekConsumeNum");
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

        public Criteria andBeforeLastWeekConsumePenIsNull() {
            addCriterion("before_last_week_consume_pen is null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenIsNotNull() {
            addCriterion("before_last_week_consume_pen is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume_pen =", value, "beforeLastWeekConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenNotEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume_pen <>", value, "beforeLastWeekConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenGreaterThan(BigDecimal value) {
            addCriterion("before_last_week_consume_pen >", value, "beforeLastWeekConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume_pen >=", value, "beforeLastWeekConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenLessThan(BigDecimal value) {
            addCriterion("before_last_week_consume_pen <", value, "beforeLastWeekConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_last_week_consume_pen <=", value, "beforeLastWeekConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenIn(List<BigDecimal> values) {
            addCriterion("before_last_week_consume_pen in", values, "beforeLastWeekConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenNotIn(List<BigDecimal> values) {
            addCriterion("before_last_week_consume_pen not in", values, "beforeLastWeekConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_last_week_consume_pen between", value1, value2, "beforeLastWeekConsumePen");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekConsumePenNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_last_week_consume_pen not between", value1, value2, "beforeLastWeekConsumePen");
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