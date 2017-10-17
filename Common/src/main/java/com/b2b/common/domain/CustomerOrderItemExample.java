package com.b2b.common.domain;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderItemExample extends BaseExample{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerOrderItemExample() {
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

        public Criteria andCoiIdIsNull() {
            addCriterion("coi_id is null");
            return (Criteria) this;
        }

        public Criteria andCoiIdIsNotNull() {
            addCriterion("coi_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoiIdEqualTo(Integer value) {
            addCriterion("coi_id =", value, "coiId");
            return (Criteria) this;
        }

        public Criteria andCoiIdNotEqualTo(Integer value) {
            addCriterion("coi_id <>", value, "coiId");
            return (Criteria) this;
        }

        public Criteria andCoiIdGreaterThan(Integer value) {
            addCriterion("coi_id >", value, "coiId");
            return (Criteria) this;
        }

        public Criteria andCoiIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coi_id >=", value, "coiId");
            return (Criteria) this;
        }

        public Criteria andCoiIdLessThan(Integer value) {
            addCriterion("coi_id <", value, "coiId");
            return (Criteria) this;
        }

        public Criteria andCoiIdLessThanOrEqualTo(Integer value) {
            addCriterion("coi_id <=", value, "coiId");
            return (Criteria) this;
        }

        public Criteria andCoiIdIn(List<Integer> values) {
            addCriterion("coi_id in", values, "coiId");
            return (Criteria) this;
        }

        public Criteria andCoiIdNotIn(List<Integer> values) {
            addCriterion("coi_id not in", values, "coiId");
            return (Criteria) this;
        }

        public Criteria andCoiIdBetween(Integer value1, Integer value2) {
            addCriterion("coi_id between", value1, value2, "coiId");
            return (Criteria) this;
        }

        public Criteria andCoiIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coi_id not between", value1, value2, "coiId");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdIsNull() {
            addCriterion("customerorder_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdIsNotNull() {
            addCriterion("customerorder_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdEqualTo(Integer value) {
            addCriterion("customerorder_id =", value, "customerorderId");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdNotEqualTo(Integer value) {
            addCriterion("customerorder_id <>", value, "customerorderId");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdGreaterThan(Integer value) {
            addCriterion("customerorder_id >", value, "customerorderId");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customerorder_id >=", value, "customerorderId");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdLessThan(Integer value) {
            addCriterion("customerorder_id <", value, "customerorderId");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdLessThanOrEqualTo(Integer value) {
            addCriterion("customerorder_id <=", value, "customerorderId");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdIn(List<Integer> values) {
            addCriterion("customerorder_id in", values, "customerorderId");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdNotIn(List<Integer> values) {
            addCriterion("customerorder_id not in", values, "customerorderId");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdBetween(Integer value1, Integer value2) {
            addCriterion("customerorder_id between", value1, value2, "customerorderId");
            return (Criteria) this;
        }

        public Criteria andCustomerorderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customerorder_id not between", value1, value2, "customerorderId");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Integer value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Integer value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Integer value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Integer value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Integer> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Integer> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Integer value1, Integer value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(Long value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(Long value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(Long value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(Long value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(Long value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<Long> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<Long> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(Long value1, Long value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(Long value1, Long value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNull() {
            addCriterion("item_price is null");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNotNull() {
            addCriterion("item_price is not null");
            return (Criteria) this;
        }

        public Criteria andItemPriceEqualTo(Long value) {
            addCriterion("item_price =", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotEqualTo(Long value) {
            addCriterion("item_price <>", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThan(Long value) {
            addCriterion("item_price >", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("item_price >=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThan(Long value) {
            addCriterion("item_price <", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThanOrEqualTo(Long value) {
            addCriterion("item_price <=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceIn(List<Long> values) {
            addCriterion("item_price in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotIn(List<Long> values) {
            addCriterion("item_price not in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceBetween(Long value1, Long value2) {
            addCriterion("item_price between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotBetween(Long value1, Long value2) {
            addCriterion("item_price not between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemSizeIsNull() {
            addCriterion("item_size is null");
            return (Criteria) this;
        }

        public Criteria andItemSizeIsNotNull() {
            addCriterion("item_size is not null");
            return (Criteria) this;
        }

        public Criteria andItemSizeEqualTo(String value) {
            addCriterion("item_size =", value, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeNotEqualTo(String value) {
            addCriterion("item_size <>", value, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeGreaterThan(String value) {
            addCriterion("item_size >", value, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeGreaterThanOrEqualTo(String value) {
            addCriterion("item_size >=", value, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeLessThan(String value) {
            addCriterion("item_size <", value, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeLessThanOrEqualTo(String value) {
            addCriterion("item_size <=", value, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeLike(String value) {
            addCriterion("item_size like", value, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeNotLike(String value) {
            addCriterion("item_size not like", value, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeIn(List<String> values) {
            addCriterion("item_size in", values, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeNotIn(List<String> values) {
            addCriterion("item_size not in", values, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeBetween(String value1, String value2) {
            addCriterion("item_size between", value1, value2, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemSizeNotBetween(String value1, String value2) {
            addCriterion("item_size not between", value1, value2, "itemSize");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceIsNull() {
            addCriterion("item_cost_price is null");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceIsNotNull() {
            addCriterion("item_cost_price is not null");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceEqualTo(Long value) {
            addCriterion("item_cost_price =", value, "itemCostPrice");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceNotEqualTo(Long value) {
            addCriterion("item_cost_price <>", value, "itemCostPrice");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceGreaterThan(Long value) {
            addCriterion("item_cost_price >", value, "itemCostPrice");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("item_cost_price >=", value, "itemCostPrice");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceLessThan(Long value) {
            addCriterion("item_cost_price <", value, "itemCostPrice");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceLessThanOrEqualTo(Long value) {
            addCriterion("item_cost_price <=", value, "itemCostPrice");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceIn(List<Long> values) {
            addCriterion("item_cost_price in", values, "itemCostPrice");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceNotIn(List<Long> values) {
            addCriterion("item_cost_price not in", values, "itemCostPrice");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceBetween(Long value1, Long value2) {
            addCriterion("item_cost_price between", value1, value2, "itemCostPrice");
            return (Criteria) this;
        }

        public Criteria andItemCostPriceNotBetween(Long value1, Long value2) {
            addCriterion("item_cost_price not between", value1, value2, "itemCostPrice");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeIsNull() {
            addCriterion("item_size_type is null");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeIsNotNull() {
            addCriterion("item_size_type is not null");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeEqualTo(String value) {
            addCriterion("item_size_type =", value, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeNotEqualTo(String value) {
            addCriterion("item_size_type <>", value, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeGreaterThan(String value) {
            addCriterion("item_size_type >", value, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("item_size_type >=", value, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeLessThan(String value) {
            addCriterion("item_size_type <", value, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeLessThanOrEqualTo(String value) {
            addCriterion("item_size_type <=", value, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeLike(String value) {
            addCriterion("item_size_type like", value, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeNotLike(String value) {
            addCriterion("item_size_type not like", value, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeIn(List<String> values) {
            addCriterion("item_size_type in", values, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeNotIn(List<String> values) {
            addCriterion("item_size_type not in", values, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeBetween(String value1, String value2) {
            addCriterion("item_size_type between", value1, value2, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andItemSizeTypeNotBetween(String value1, String value2) {
            addCriterion("item_size_type not between", value1, value2, "itemSizeType");
            return (Criteria) this;
        }

        public Criteria andStockNumIsNull() {
            addCriterion("stock_num is null");
            return (Criteria) this;
        }

        public Criteria andStockNumIsNotNull() {
            addCriterion("stock_num is not null");
            return (Criteria) this;
        }

        public Criteria andStockNumEqualTo(Integer value) {
            addCriterion("stock_num =", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumNotEqualTo(Integer value) {
            addCriterion("stock_num <>", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumGreaterThan(Integer value) {
            addCriterion("stock_num >", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_num >=", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumLessThan(Integer value) {
            addCriterion("stock_num <", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumLessThanOrEqualTo(Integer value) {
            addCriterion("stock_num <=", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumIn(List<Integer> values) {
            addCriterion("stock_num in", values, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumNotIn(List<Integer> values) {
            addCriterion("stock_num not in", values, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumBetween(Integer value1, Integer value2) {
            addCriterion("stock_num between", value1, value2, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_num not between", value1, value2, "stockNum");
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