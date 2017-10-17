package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ShopDailyReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopDailyReportExample() {
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

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Integer value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Integer value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Integer value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Integer value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Integer value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Integer> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Integer> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Integer value1, Integer value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
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
            addCriterionForJDBCDate("sumdate =", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("sumdate <>", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateGreaterThan(Date value) {
            addCriterionForJDBCDate("sumdate >", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sumdate >=", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateLessThan(Date value) {
            addCriterionForJDBCDate("sumdate <", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sumdate <=", value, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateIn(List<Date> values) {
            addCriterionForJDBCDate("sumdate in", values, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("sumdate not in", values, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sumdate between", value1, value2, "sumdate");
            return (Criteria) this;
        }

        public Criteria andSumdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sumdate not between", value1, value2, "sumdate");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(BigDecimal value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(BigDecimal value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(BigDecimal value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(BigDecimal value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<BigDecimal> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<BigDecimal> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount not between", value1, value2, "discount");
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

        public Criteria andBeforeLastWeekExpandIsNull() {
            addCriterion("before_last_week_expand is null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandIsNotNull() {
            addCriterion("before_last_week_expand is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandEqualTo(BigDecimal value) {
            addCriterion("before_last_week_expand =", value, "beforeLastWeekExpand");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandNotEqualTo(BigDecimal value) {
            addCriterion("before_last_week_expand <>", value, "beforeLastWeekExpand");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandGreaterThan(BigDecimal value) {
            addCriterion("before_last_week_expand >", value, "beforeLastWeekExpand");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_last_week_expand >=", value, "beforeLastWeekExpand");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandLessThan(BigDecimal value) {
            addCriterion("before_last_week_expand <", value, "beforeLastWeekExpand");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_last_week_expand <=", value, "beforeLastWeekExpand");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandIn(List<BigDecimal> values) {
            addCriterion("before_last_week_expand in", values, "beforeLastWeekExpand");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandNotIn(List<BigDecimal> values) {
            addCriterion("before_last_week_expand not in", values, "beforeLastWeekExpand");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_last_week_expand between", value1, value2, "beforeLastWeekExpand");
            return (Criteria) this;
        }

        public Criteria andBeforeLastWeekExpandNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_last_week_expand not between", value1, value2, "beforeLastWeekExpand");
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

        public Criteria andConsumePersonNumIsNull() {
            addCriterion("consume_person_num is null");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumIsNotNull() {
            addCriterion("consume_person_num is not null");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumEqualTo(Integer value) {
            addCriterion("consume_person_num =", value, "consumePersonNum");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumNotEqualTo(Integer value) {
            addCriterion("consume_person_num <>", value, "consumePersonNum");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumGreaterThan(Integer value) {
            addCriterion("consume_person_num >", value, "consumePersonNum");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("consume_person_num >=", value, "consumePersonNum");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumLessThan(Integer value) {
            addCriterion("consume_person_num <", value, "consumePersonNum");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumLessThanOrEqualTo(Integer value) {
            addCriterion("consume_person_num <=", value, "consumePersonNum");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumIn(List<Integer> values) {
            addCriterion("consume_person_num in", values, "consumePersonNum");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumNotIn(List<Integer> values) {
            addCriterion("consume_person_num not in", values, "consumePersonNum");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumBetween(Integer value1, Integer value2) {
            addCriterion("consume_person_num between", value1, value2, "consumePersonNum");
            return (Criteria) this;
        }

        public Criteria andConsumePersonNumNotBetween(Integer value1, Integer value2) {
            addCriterion("consume_person_num not between", value1, value2, "consumePersonNum");
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

        public Criteria andAvgExpendIsNull() {
            addCriterion("avg_expend is null");
            return (Criteria) this;
        }

        public Criteria andAvgExpendIsNotNull() {
            addCriterion("avg_expend is not null");
            return (Criteria) this;
        }

        public Criteria andAvgExpendEqualTo(Long value) {
            addCriterion("avg_expend =", value, "avgExpend");
            return (Criteria) this;
        }

        public Criteria andAvgExpendNotEqualTo(Long value) {
            addCriterion("avg_expend <>", value, "avgExpend");
            return (Criteria) this;
        }

        public Criteria andAvgExpendGreaterThan(Long value) {
            addCriterion("avg_expend >", value, "avgExpend");
            return (Criteria) this;
        }

        public Criteria andAvgExpendGreaterThanOrEqualTo(Long value) {
            addCriterion("avg_expend >=", value, "avgExpend");
            return (Criteria) this;
        }

        public Criteria andAvgExpendLessThan(Long value) {
            addCriterion("avg_expend <", value, "avgExpend");
            return (Criteria) this;
        }

        public Criteria andAvgExpendLessThanOrEqualTo(Long value) {
            addCriterion("avg_expend <=", value, "avgExpend");
            return (Criteria) this;
        }

        public Criteria andAvgExpendIn(List<Long> values) {
            addCriterion("avg_expend in", values, "avgExpend");
            return (Criteria) this;
        }

        public Criteria andAvgExpendNotIn(List<Long> values) {
            addCriterion("avg_expend not in", values, "avgExpend");
            return (Criteria) this;
        }

        public Criteria andAvgExpendBetween(Long value1, Long value2) {
            addCriterion("avg_expend between", value1, value2, "avgExpend");
            return (Criteria) this;
        }

        public Criteria andAvgExpendNotBetween(Long value1, Long value2) {
            addCriterion("avg_expend not between", value1, value2, "avgExpend");
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

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeIsNull() {
            addCriterion("onshelf_fee is null");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeIsNotNull() {
            addCriterion("onshelf_fee is not null");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeEqualTo(Long value) {
            addCriterion("onshelf_fee =", value, "onshelfFee");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeNotEqualTo(Long value) {
            addCriterion("onshelf_fee <>", value, "onshelfFee");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeGreaterThan(Long value) {
            addCriterion("onshelf_fee >", value, "onshelfFee");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("onshelf_fee >=", value, "onshelfFee");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeLessThan(Long value) {
            addCriterion("onshelf_fee <", value, "onshelfFee");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeLessThanOrEqualTo(Long value) {
            addCriterion("onshelf_fee <=", value, "onshelfFee");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeIn(List<Long> values) {
            addCriterion("onshelf_fee in", values, "onshelfFee");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeNotIn(List<Long> values) {
            addCriterion("onshelf_fee not in", values, "onshelfFee");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeBetween(Long value1, Long value2) {
            addCriterion("onshelf_fee between", value1, value2, "onshelfFee");
            return (Criteria) this;
        }

        public Criteria andOnshelfFeeNotBetween(Long value1, Long value2) {
            addCriterion("onshelf_fee not between", value1, value2, "onshelfFee");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindIsNull() {
            addCriterion("onshelf_kind is null");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindIsNotNull() {
            addCriterion("onshelf_kind is not null");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindEqualTo(Integer value) {
            addCriterion("onshelf_kind =", value, "onshelfKind");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindNotEqualTo(Integer value) {
            addCriterion("onshelf_kind <>", value, "onshelfKind");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindGreaterThan(Integer value) {
            addCriterion("onshelf_kind >", value, "onshelfKind");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindGreaterThanOrEqualTo(Integer value) {
            addCriterion("onshelf_kind >=", value, "onshelfKind");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindLessThan(Integer value) {
            addCriterion("onshelf_kind <", value, "onshelfKind");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindLessThanOrEqualTo(Integer value) {
            addCriterion("onshelf_kind <=", value, "onshelfKind");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindIn(List<Integer> values) {
            addCriterion("onshelf_kind in", values, "onshelfKind");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindNotIn(List<Integer> values) {
            addCriterion("onshelf_kind not in", values, "onshelfKind");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindBetween(Integer value1, Integer value2) {
            addCriterion("onshelf_kind between", value1, value2, "onshelfKind");
            return (Criteria) this;
        }

        public Criteria andOnshelfKindNotBetween(Integer value1, Integer value2) {
            addCriterion("onshelf_kind not between", value1, value2, "onshelfKind");
            return (Criteria) this;
        }

        public Criteria andMoveKindIsNull() {
            addCriterion("move_kind is null");
            return (Criteria) this;
        }

        public Criteria andMoveKindIsNotNull() {
            addCriterion("move_kind is not null");
            return (Criteria) this;
        }

        public Criteria andMoveKindEqualTo(Integer value) {
            addCriterion("move_kind =", value, "moveKind");
            return (Criteria) this;
        }

        public Criteria andMoveKindNotEqualTo(Integer value) {
            addCriterion("move_kind <>", value, "moveKind");
            return (Criteria) this;
        }

        public Criteria andMoveKindGreaterThan(Integer value) {
            addCriterion("move_kind >", value, "moveKind");
            return (Criteria) this;
        }

        public Criteria andMoveKindGreaterThanOrEqualTo(Integer value) {
            addCriterion("move_kind >=", value, "moveKind");
            return (Criteria) this;
        }

        public Criteria andMoveKindLessThan(Integer value) {
            addCriterion("move_kind <", value, "moveKind");
            return (Criteria) this;
        }

        public Criteria andMoveKindLessThanOrEqualTo(Integer value) {
            addCriterion("move_kind <=", value, "moveKind");
            return (Criteria) this;
        }

        public Criteria andMoveKindIn(List<Integer> values) {
            addCriterion("move_kind in", values, "moveKind");
            return (Criteria) this;
        }

        public Criteria andMoveKindNotIn(List<Integer> values) {
            addCriterion("move_kind not in", values, "moveKind");
            return (Criteria) this;
        }

        public Criteria andMoveKindBetween(Integer value1, Integer value2) {
            addCriterion("move_kind between", value1, value2, "moveKind");
            return (Criteria) this;
        }

        public Criteria andMoveKindNotBetween(Integer value1, Integer value2) {
            addCriterion("move_kind not between", value1, value2, "moveKind");
            return (Criteria) this;
        }

        public Criteria andMovePercentIsNull() {
            addCriterion("move_percent is null");
            return (Criteria) this;
        }

        public Criteria andMovePercentIsNotNull() {
            addCriterion("move_percent is not null");
            return (Criteria) this;
        }

        public Criteria andMovePercentEqualTo(BigDecimal value) {
            addCriterion("move_percent =", value, "movePercent");
            return (Criteria) this;
        }

        public Criteria andMovePercentNotEqualTo(BigDecimal value) {
            addCriterion("move_percent <>", value, "movePercent");
            return (Criteria) this;
        }

        public Criteria andMovePercentGreaterThan(BigDecimal value) {
            addCriterion("move_percent >", value, "movePercent");
            return (Criteria) this;
        }

        public Criteria andMovePercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("move_percent >=", value, "movePercent");
            return (Criteria) this;
        }

        public Criteria andMovePercentLessThan(BigDecimal value) {
            addCriterion("move_percent <", value, "movePercent");
            return (Criteria) this;
        }

        public Criteria andMovePercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("move_percent <=", value, "movePercent");
            return (Criteria) this;
        }

        public Criteria andMovePercentIn(List<BigDecimal> values) {
            addCriterion("move_percent in", values, "movePercent");
            return (Criteria) this;
        }

        public Criteria andMovePercentNotIn(List<BigDecimal> values) {
            addCriterion("move_percent not in", values, "movePercent");
            return (Criteria) this;
        }

        public Criteria andMovePercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("move_percent between", value1, value2, "movePercent");
            return (Criteria) this;
        }

        public Criteria andMovePercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("move_percent not between", value1, value2, "movePercent");
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