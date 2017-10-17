package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeniorSetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SeniorSetExample() {
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

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Long value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Long value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Long value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Long value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Long value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Long> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Long> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Long value1, Long value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Long value1, Long value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
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

        public Criteria andStartIsNull() {
            addCriterion("start is null");
            return (Criteria) this;
        }

        public Criteria andStartIsNotNull() {
            addCriterion("start is not null");
            return (Criteria) this;
        }

        public Criteria andStartEqualTo(Integer value) {
            addCriterion("start =", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotEqualTo(Integer value) {
            addCriterion("start <>", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThan(Integer value) {
            addCriterion("start >", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThanOrEqualTo(Integer value) {
            addCriterion("start >=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThan(Integer value) {
            addCriterion("start <", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThanOrEqualTo(Integer value) {
            addCriterion("start <=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartIn(List<Integer> values) {
            addCriterion("start in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotIn(List<Integer> values) {
            addCriterion("start not in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartBetween(Integer value1, Integer value2) {
            addCriterion("start between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotBetween(Integer value1, Integer value2) {
            addCriterion("start not between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andShowPriceIsNull() {
            addCriterion("show_price is null");
            return (Criteria) this;
        }

        public Criteria andShowPriceIsNotNull() {
            addCriterion("show_price is not null");
            return (Criteria) this;
        }

        public Criteria andShowPriceEqualTo(Integer value) {
            addCriterion("show_price =", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceNotEqualTo(Integer value) {
            addCriterion("show_price <>", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceGreaterThan(Integer value) {
            addCriterion("show_price >", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_price >=", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceLessThan(Integer value) {
            addCriterion("show_price <", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceLessThanOrEqualTo(Integer value) {
            addCriterion("show_price <=", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceIn(List<Integer> values) {
            addCriterion("show_price in", values, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceNotIn(List<Integer> values) {
            addCriterion("show_price not in", values, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceBetween(Integer value1, Integer value2) {
            addCriterion("show_price between", value1, value2, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("show_price not between", value1, value2, "showPrice");
            return (Criteria) this;
        }

        public Criteria andFreeFeeIsNull() {
            addCriterion("free_fee is null");
            return (Criteria) this;
        }

        public Criteria andFreeFeeIsNotNull() {
            addCriterion("free_fee is not null");
            return (Criteria) this;
        }

        public Criteria andFreeFeeEqualTo(Long value) {
            addCriterion("free_fee =", value, "freeFee");
            return (Criteria) this;
        }

        public Criteria andFreeFeeNotEqualTo(Long value) {
            addCriterion("free_fee <>", value, "freeFee");
            return (Criteria) this;
        }

        public Criteria andFreeFeeGreaterThan(Long value) {
            addCriterion("free_fee >", value, "freeFee");
            return (Criteria) this;
        }

        public Criteria andFreeFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("free_fee >=", value, "freeFee");
            return (Criteria) this;
        }

        public Criteria andFreeFeeLessThan(Long value) {
            addCriterion("free_fee <", value, "freeFee");
            return (Criteria) this;
        }

        public Criteria andFreeFeeLessThanOrEqualTo(Long value) {
            addCriterion("free_fee <=", value, "freeFee");
            return (Criteria) this;
        }

        public Criteria andFreeFeeIn(List<Long> values) {
            addCriterion("free_fee in", values, "freeFee");
            return (Criteria) this;
        }

        public Criteria andFreeFeeNotIn(List<Long> values) {
            addCriterion("free_fee not in", values, "freeFee");
            return (Criteria) this;
        }

        public Criteria andFreeFeeBetween(Long value1, Long value2) {
            addCriterion("free_fee between", value1, value2, "freeFee");
            return (Criteria) this;
        }

        public Criteria andFreeFeeNotBetween(Long value1, Long value2) {
            addCriterion("free_fee not between", value1, value2, "freeFee");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthIsNull() {
            addCriterion("day_or_month is null");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthIsNotNull() {
            addCriterion("day_or_month is not null");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthEqualTo(Integer value) {
            addCriterion("day_or_month =", value, "dayOrMonth");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthNotEqualTo(Integer value) {
            addCriterion("day_or_month <>", value, "dayOrMonth");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthGreaterThan(Integer value) {
            addCriterion("day_or_month >", value, "dayOrMonth");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_or_month >=", value, "dayOrMonth");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthLessThan(Integer value) {
            addCriterion("day_or_month <", value, "dayOrMonth");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthLessThanOrEqualTo(Integer value) {
            addCriterion("day_or_month <=", value, "dayOrMonth");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthIn(List<Integer> values) {
            addCriterion("day_or_month in", values, "dayOrMonth");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthNotIn(List<Integer> values) {
            addCriterion("day_or_month not in", values, "dayOrMonth");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthBetween(Integer value1, Integer value2) {
            addCriterion("day_or_month between", value1, value2, "dayOrMonth");
            return (Criteria) this;
        }

        public Criteria andDayOrMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("day_or_month not between", value1, value2, "dayOrMonth");
            return (Criteria) this;
        }

        public Criteria andIslayerIsNull() {
            addCriterion("islayer is null");
            return (Criteria) this;
        }

        public Criteria andIslayerIsNotNull() {
            addCriterion("islayer is not null");
            return (Criteria) this;
        }

        public Criteria andIslayerEqualTo(Integer value) {
            addCriterion("islayer =", value, "islayer");
            return (Criteria) this;
        }

        public Criteria andIslayerNotEqualTo(Integer value) {
            addCriterion("islayer <>", value, "islayer");
            return (Criteria) this;
        }

        public Criteria andIslayerGreaterThan(Integer value) {
            addCriterion("islayer >", value, "islayer");
            return (Criteria) this;
        }

        public Criteria andIslayerGreaterThanOrEqualTo(Integer value) {
            addCriterion("islayer >=", value, "islayer");
            return (Criteria) this;
        }

        public Criteria andIslayerLessThan(Integer value) {
            addCriterion("islayer <", value, "islayer");
            return (Criteria) this;
        }

        public Criteria andIslayerLessThanOrEqualTo(Integer value) {
            addCriterion("islayer <=", value, "islayer");
            return (Criteria) this;
        }

        public Criteria andIslayerIn(List<Integer> values) {
            addCriterion("islayer in", values, "islayer");
            return (Criteria) this;
        }

        public Criteria andIslayerNotIn(List<Integer> values) {
            addCriterion("islayer not in", values, "islayer");
            return (Criteria) this;
        }

        public Criteria andIslayerBetween(Integer value1, Integer value2) {
            addCriterion("islayer between", value1, value2, "islayer");
            return (Criteria) this;
        }

        public Criteria andIslayerNotBetween(Integer value1, Integer value2) {
            addCriterion("islayer not between", value1, value2, "islayer");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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