package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ShopMonthReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopMonthReportExample() {
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

        public Criteria andBeforeExpandFeeIsNull() {
            addCriterion("before_expand_fee is null");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeIsNotNull() {
            addCriterion("before_expand_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeEqualTo(BigDecimal value) {
            addCriterion("before_expand_fee =", value, "beforeExpandFee");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeNotEqualTo(BigDecimal value) {
            addCriterion("before_expand_fee <>", value, "beforeExpandFee");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeGreaterThan(BigDecimal value) {
            addCriterion("before_expand_fee >", value, "beforeExpandFee");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_expand_fee >=", value, "beforeExpandFee");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeLessThan(BigDecimal value) {
            addCriterion("before_expand_fee <", value, "beforeExpandFee");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_expand_fee <=", value, "beforeExpandFee");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeIn(List<BigDecimal> values) {
            addCriterion("before_expand_fee in", values, "beforeExpandFee");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeNotIn(List<BigDecimal> values) {
            addCriterion("before_expand_fee not in", values, "beforeExpandFee");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_expand_fee between", value1, value2, "beforeExpandFee");
            return (Criteria) this;
        }

        public Criteria andBeforeExpandFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_expand_fee not between", value1, value2, "beforeExpandFee");
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

        public Criteria andStockIsNull() {
            addCriterion("stock is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("stock is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(Long value) {
            addCriterion("stock =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(Long value) {
            addCriterion("stock <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(Long value) {
            addCriterion("stock >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(Long value) {
            addCriterion("stock >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(Long value) {
            addCriterion("stock <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(Long value) {
            addCriterion("stock <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<Long> values) {
            addCriterion("stock in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<Long> values) {
            addCriterion("stock not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(Long value1, Long value2) {
            addCriterion("stock between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(Long value1, Long value2) {
            addCriterion("stock not between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeIsNull() {
            addCriterion("avg_man_fee is null");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeIsNotNull() {
            addCriterion("avg_man_fee is not null");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeEqualTo(Long value) {
            addCriterion("avg_man_fee =", value, "avgManFee");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeNotEqualTo(Long value) {
            addCriterion("avg_man_fee <>", value, "avgManFee");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeGreaterThan(Long value) {
            addCriterion("avg_man_fee >", value, "avgManFee");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("avg_man_fee >=", value, "avgManFee");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeLessThan(Long value) {
            addCriterion("avg_man_fee <", value, "avgManFee");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeLessThanOrEqualTo(Long value) {
            addCriterion("avg_man_fee <=", value, "avgManFee");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeIn(List<Long> values) {
            addCriterion("avg_man_fee in", values, "avgManFee");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeNotIn(List<Long> values) {
            addCriterion("avg_man_fee not in", values, "avgManFee");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeBetween(Long value1, Long value2) {
            addCriterion("avg_man_fee between", value1, value2, "avgManFee");
            return (Criteria) this;
        }

        public Criteria andAvgManFeeNotBetween(Long value1, Long value2) {
            addCriterion("avg_man_fee not between", value1, value2, "avgManFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeIsNull() {
            addCriterion("before_avg_man_fee is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeIsNotNull() {
            addCriterion("before_avg_man_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeEqualTo(BigDecimal value) {
            addCriterion("before_avg_man_fee =", value, "beforeAvgManFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeNotEqualTo(BigDecimal value) {
            addCriterion("before_avg_man_fee <>", value, "beforeAvgManFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeGreaterThan(BigDecimal value) {
            addCriterion("before_avg_man_fee >", value, "beforeAvgManFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_avg_man_fee >=", value, "beforeAvgManFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeLessThan(BigDecimal value) {
            addCriterion("before_avg_man_fee <", value, "beforeAvgManFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_avg_man_fee <=", value, "beforeAvgManFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeIn(List<BigDecimal> values) {
            addCriterion("before_avg_man_fee in", values, "beforeAvgManFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeNotIn(List<BigDecimal> values) {
            addCriterion("before_avg_man_fee not in", values, "beforeAvgManFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_avg_man_fee between", value1, value2, "beforeAvgManFee");
            return (Criteria) this;
        }

        public Criteria andBeforeAvgManFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_avg_man_fee not between", value1, value2, "beforeAvgManFee");
            return (Criteria) this;
        }

        public Criteria andWastageIsNull() {
            addCriterion("wastage is null");
            return (Criteria) this;
        }

        public Criteria andWastageIsNotNull() {
            addCriterion("wastage is not null");
            return (Criteria) this;
        }

        public Criteria andWastageEqualTo(Long value) {
            addCriterion("wastage =", value, "wastage");
            return (Criteria) this;
        }

        public Criteria andWastageNotEqualTo(Long value) {
            addCriterion("wastage <>", value, "wastage");
            return (Criteria) this;
        }

        public Criteria andWastageGreaterThan(Long value) {
            addCriterion("wastage >", value, "wastage");
            return (Criteria) this;
        }

        public Criteria andWastageGreaterThanOrEqualTo(Long value) {
            addCriterion("wastage >=", value, "wastage");
            return (Criteria) this;
        }

        public Criteria andWastageLessThan(Long value) {
            addCriterion("wastage <", value, "wastage");
            return (Criteria) this;
        }

        public Criteria andWastageLessThanOrEqualTo(Long value) {
            addCriterion("wastage <=", value, "wastage");
            return (Criteria) this;
        }

        public Criteria andWastageIn(List<Long> values) {
            addCriterion("wastage in", values, "wastage");
            return (Criteria) this;
        }

        public Criteria andWastageNotIn(List<Long> values) {
            addCriterion("wastage not in", values, "wastage");
            return (Criteria) this;
        }

        public Criteria andWastageBetween(Long value1, Long value2) {
            addCriterion("wastage between", value1, value2, "wastage");
            return (Criteria) this;
        }

        public Criteria andWastageNotBetween(Long value1, Long value2) {
            addCriterion("wastage not between", value1, value2, "wastage");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouIsNull() {
            addCriterion("zhekoucaigou is null");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouIsNotNull() {
            addCriterion("zhekoucaigou is not null");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouEqualTo(Long value) {
            addCriterion("zhekoucaigou =", value, "zhekoucaigou");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouNotEqualTo(Long value) {
            addCriterion("zhekoucaigou <>", value, "zhekoucaigou");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouGreaterThan(Long value) {
            addCriterion("zhekoucaigou >", value, "zhekoucaigou");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouGreaterThanOrEqualTo(Long value) {
            addCriterion("zhekoucaigou >=", value, "zhekoucaigou");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouLessThan(Long value) {
            addCriterion("zhekoucaigou <", value, "zhekoucaigou");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouLessThanOrEqualTo(Long value) {
            addCriterion("zhekoucaigou <=", value, "zhekoucaigou");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouIn(List<Long> values) {
            addCriterion("zhekoucaigou in", values, "zhekoucaigou");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouNotIn(List<Long> values) {
            addCriterion("zhekoucaigou not in", values, "zhekoucaigou");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouBetween(Long value1, Long value2) {
            addCriterion("zhekoucaigou between", value1, value2, "zhekoucaigou");
            return (Criteria) this;
        }

        public Criteria andZhekoucaigouNotBetween(Long value1, Long value2) {
            addCriterion("zhekoucaigou not between", value1, value2, "zhekoucaigou");
            return (Criteria) this;
        }

        public Criteria andChengbenIsNull() {
            addCriterion("chengben is null");
            return (Criteria) this;
        }

        public Criteria andChengbenIsNotNull() {
            addCriterion("chengben is not null");
            return (Criteria) this;
        }

        public Criteria andChengbenEqualTo(Long value) {
            addCriterion("chengben =", value, "chengben");
            return (Criteria) this;
        }

        public Criteria andChengbenNotEqualTo(Long value) {
            addCriterion("chengben <>", value, "chengben");
            return (Criteria) this;
        }

        public Criteria andChengbenGreaterThan(Long value) {
            addCriterion("chengben >", value, "chengben");
            return (Criteria) this;
        }

        public Criteria andChengbenGreaterThanOrEqualTo(Long value) {
            addCriterion("chengben >=", value, "chengben");
            return (Criteria) this;
        }

        public Criteria andChengbenLessThan(Long value) {
            addCriterion("chengben <", value, "chengben");
            return (Criteria) this;
        }

        public Criteria andChengbenLessThanOrEqualTo(Long value) {
            addCriterion("chengben <=", value, "chengben");
            return (Criteria) this;
        }

        public Criteria andChengbenIn(List<Long> values) {
            addCriterion("chengben in", values, "chengben");
            return (Criteria) this;
        }

        public Criteria andChengbenNotIn(List<Long> values) {
            addCriterion("chengben not in", values, "chengben");
            return (Criteria) this;
        }

        public Criteria andChengbenBetween(Long value1, Long value2) {
            addCriterion("chengben between", value1, value2, "chengben");
            return (Criteria) this;
        }

        public Criteria andChengbenNotBetween(Long value1, Long value2) {
            addCriterion("chengben not between", value1, value2, "chengben");
            return (Criteria) this;
        }

        public Criteria andProfitIsNull() {
            addCriterion("profit is null");
            return (Criteria) this;
        }

        public Criteria andProfitIsNotNull() {
            addCriterion("profit is not null");
            return (Criteria) this;
        }

        public Criteria andProfitEqualTo(Long value) {
            addCriterion("profit =", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotEqualTo(Long value) {
            addCriterion("profit <>", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThan(Long value) {
            addCriterion("profit >", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThanOrEqualTo(Long value) {
            addCriterion("profit >=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThan(Long value) {
            addCriterion("profit <", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThanOrEqualTo(Long value) {
            addCriterion("profit <=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitIn(List<Long> values) {
            addCriterion("profit in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotIn(List<Long> values) {
            addCriterion("profit not in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitBetween(Long value1, Long value2) {
            addCriterion("profit between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotBetween(Long value1, Long value2) {
            addCriterion("profit not between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitRateIsNull() {
            addCriterion("profit_rate is null");
            return (Criteria) this;
        }

        public Criteria andProfitRateIsNotNull() {
            addCriterion("profit_rate is not null");
            return (Criteria) this;
        }

        public Criteria andProfitRateEqualTo(BigDecimal value) {
            addCriterion("profit_rate =", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateNotEqualTo(BigDecimal value) {
            addCriterion("profit_rate <>", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateGreaterThan(BigDecimal value) {
            addCriterion("profit_rate >", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_rate >=", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateLessThan(BigDecimal value) {
            addCriterion("profit_rate <", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_rate <=", value, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateIn(List<BigDecimal> values) {
            addCriterion("profit_rate in", values, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateNotIn(List<BigDecimal> values) {
            addCriterion("profit_rate not in", values, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_rate between", value1, value2, "profitRate");
            return (Criteria) this;
        }

        public Criteria andProfitRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_rate not between", value1, value2, "profitRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyIsNull() {
            addCriterion("subsidy is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyIsNotNull() {
            addCriterion("subsidy is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyEqualTo(Long value) {
            addCriterion("subsidy =", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyNotEqualTo(Long value) {
            addCriterion("subsidy <>", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyGreaterThan(Long value) {
            addCriterion("subsidy >", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyGreaterThanOrEqualTo(Long value) {
            addCriterion("subsidy >=", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyLessThan(Long value) {
            addCriterion("subsidy <", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyLessThanOrEqualTo(Long value) {
            addCriterion("subsidy <=", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyIn(List<Long> values) {
            addCriterion("subsidy in", values, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyNotIn(List<Long> values) {
            addCriterion("subsidy not in", values, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyBetween(Long value1, Long value2) {
            addCriterion("subsidy between", value1, value2, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyNotBetween(Long value1, Long value2) {
            addCriterion("subsidy not between", value1, value2, "subsidy");
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