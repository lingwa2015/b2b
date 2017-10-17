package com.b2b.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockMoneyMonitorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockMoneyMonitorExample() {
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

        public Criteria andStockMoneyIsNull() {
            addCriterion("stock_money is null");
            return (Criteria) this;
        }

        public Criteria andStockMoneyIsNotNull() {
            addCriterion("stock_money is not null");
            return (Criteria) this;
        }

        public Criteria andStockMoneyEqualTo(Long value) {
            addCriterion("stock_money =", value, "stockMoney");
            return (Criteria) this;
        }

        public Criteria andStockMoneyNotEqualTo(Long value) {
            addCriterion("stock_money <>", value, "stockMoney");
            return (Criteria) this;
        }

        public Criteria andStockMoneyGreaterThan(Long value) {
            addCriterion("stock_money >", value, "stockMoney");
            return (Criteria) this;
        }

        public Criteria andStockMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("stock_money >=", value, "stockMoney");
            return (Criteria) this;
        }

        public Criteria andStockMoneyLessThan(Long value) {
            addCriterion("stock_money <", value, "stockMoney");
            return (Criteria) this;
        }

        public Criteria andStockMoneyLessThanOrEqualTo(Long value) {
            addCriterion("stock_money <=", value, "stockMoney");
            return (Criteria) this;
        }

        public Criteria andStockMoneyIn(List<Long> values) {
            addCriterion("stock_money in", values, "stockMoney");
            return (Criteria) this;
        }

        public Criteria andStockMoneyNotIn(List<Long> values) {
            addCriterion("stock_money not in", values, "stockMoney");
            return (Criteria) this;
        }

        public Criteria andStockMoneyBetween(Long value1, Long value2) {
            addCriterion("stock_money between", value1, value2, "stockMoney");
            return (Criteria) this;
        }

        public Criteria andStockMoneyNotBetween(Long value1, Long value2) {
            addCriterion("stock_money not between", value1, value2, "stockMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyIsNull() {
            addCriterion("change_money is null");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyIsNotNull() {
            addCriterion("change_money is not null");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyEqualTo(Long value) {
            addCriterion("change_money =", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyNotEqualTo(Long value) {
            addCriterion("change_money <>", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyGreaterThan(Long value) {
            addCriterion("change_money >", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("change_money >=", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyLessThan(Long value) {
            addCriterion("change_money <", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyLessThanOrEqualTo(Long value) {
            addCriterion("change_money <=", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyIn(List<Long> values) {
            addCriterion("change_money in", values, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyNotIn(List<Long> values) {
            addCriterion("change_money not in", values, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyBetween(Long value1, Long value2) {
            addCriterion("change_money between", value1, value2, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyNotBetween(Long value1, Long value2) {
            addCriterion("change_money not between", value1, value2, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyIsNull() {
            addCriterion("in_stock_money is null");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyIsNotNull() {
            addCriterion("in_stock_money is not null");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyEqualTo(Long value) {
            addCriterion("in_stock_money =", value, "inStockMoney");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyNotEqualTo(Long value) {
            addCriterion("in_stock_money <>", value, "inStockMoney");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyGreaterThan(Long value) {
            addCriterion("in_stock_money >", value, "inStockMoney");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("in_stock_money >=", value, "inStockMoney");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyLessThan(Long value) {
            addCriterion("in_stock_money <", value, "inStockMoney");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyLessThanOrEqualTo(Long value) {
            addCriterion("in_stock_money <=", value, "inStockMoney");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyIn(List<Long> values) {
            addCriterion("in_stock_money in", values, "inStockMoney");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyNotIn(List<Long> values) {
            addCriterion("in_stock_money not in", values, "inStockMoney");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyBetween(Long value1, Long value2) {
            addCriterion("in_stock_money between", value1, value2, "inStockMoney");
            return (Criteria) this;
        }

        public Criteria andInStockMoneyNotBetween(Long value1, Long value2) {
            addCriterion("in_stock_money not between", value1, value2, "inStockMoney");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyIsNull() {
            addCriterion("out_stock_money is null");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyIsNotNull() {
            addCriterion("out_stock_money is not null");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyEqualTo(Long value) {
            addCriterion("out_stock_money =", value, "outStockMoney");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyNotEqualTo(Long value) {
            addCriterion("out_stock_money <>", value, "outStockMoney");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyGreaterThan(Long value) {
            addCriterion("out_stock_money >", value, "outStockMoney");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("out_stock_money >=", value, "outStockMoney");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyLessThan(Long value) {
            addCriterion("out_stock_money <", value, "outStockMoney");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyLessThanOrEqualTo(Long value) {
            addCriterion("out_stock_money <=", value, "outStockMoney");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyIn(List<Long> values) {
            addCriterion("out_stock_money in", values, "outStockMoney");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyNotIn(List<Long> values) {
            addCriterion("out_stock_money not in", values, "outStockMoney");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyBetween(Long value1, Long value2) {
            addCriterion("out_stock_money between", value1, value2, "outStockMoney");
            return (Criteria) this;
        }

        public Criteria andOutStockMoneyNotBetween(Long value1, Long value2) {
            addCriterion("out_stock_money not between", value1, value2, "outStockMoney");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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