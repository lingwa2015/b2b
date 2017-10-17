package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PurchaseItemExample() {
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

        public Criteria andPurchaseIdIsNull() {
            addCriterion("purchase_id is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdIsNotNull() {
            addCriterion("purchase_id is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdEqualTo(String value) {
            addCriterion("purchase_id =", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdNotEqualTo(String value) {
            addCriterion("purchase_id <>", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdGreaterThan(String value) {
            addCriterion("purchase_id >", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("purchase_id >=", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdLessThan(String value) {
            addCriterion("purchase_id <", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdLessThanOrEqualTo(String value) {
            addCriterion("purchase_id <=", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdLike(String value) {
            addCriterion("purchase_id like", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdNotLike(String value) {
            addCriterion("purchase_id not like", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdIn(List<String> values) {
            addCriterion("purchase_id in", values, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdNotIn(List<String> values) {
            addCriterion("purchase_id not in", values, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdBetween(String value1, String value2) {
            addCriterion("purchase_id between", value1, value2, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdNotBetween(String value1, String value2) {
            addCriterion("purchase_id not between", value1, value2, "purchaseId");
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIsNull() {
            addCriterion("category_name is null");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIsNotNull() {
            addCriterion("category_name is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryNameEqualTo(String value) {
            addCriterion("category_name =", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotEqualTo(String value) {
            addCriterion("category_name <>", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameGreaterThan(String value) {
            addCriterion("category_name >", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("category_name >=", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLessThan(String value) {
            addCriterion("category_name <", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("category_name <=", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLike(String value) {
            addCriterion("category_name like", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotLike(String value) {
            addCriterion("category_name not like", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIn(List<String> values) {
            addCriterion("category_name in", values, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotIn(List<String> values) {
            addCriterion("category_name not in", values, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameBetween(String value1, String value2) {
            addCriterion("category_name between", value1, value2, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotBetween(String value1, String value2) {
            addCriterion("category_name not between", value1, value2, "categoryName");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(String value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(String value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(String value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(String value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(String value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(String value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLike(String value) {
            addCriterion("size like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotLike(String value) {
            addCriterion("size not like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<String> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<String> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(String value1, String value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(String value1, String value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Long value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Long value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Long value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Long value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Long value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Long> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Long> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Long value1, Long value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Long value1, Long value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNull() {
            addCriterion("cost_price is null");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNotNull() {
            addCriterion("cost_price is not null");
            return (Criteria) this;
        }

        public Criteria andCostPriceEqualTo(Long value) {
            addCriterion("cost_price =", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotEqualTo(Long value) {
            addCriterion("cost_price <>", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThan(Long value) {
            addCriterion("cost_price >", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("cost_price >=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThan(Long value) {
            addCriterion("cost_price <", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThanOrEqualTo(Long value) {
            addCriterion("cost_price <=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceIn(List<Long> values) {
            addCriterion("cost_price in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotIn(List<Long> values) {
            addCriterion("cost_price not in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceBetween(Long value1, Long value2) {
            addCriterion("cost_price between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotBetween(Long value1, Long value2) {
            addCriterion("cost_price not between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andConvertNumIsNull() {
            addCriterion("convert_num is null");
            return (Criteria) this;
        }

        public Criteria andConvertNumIsNotNull() {
            addCriterion("convert_num is not null");
            return (Criteria) this;
        }

        public Criteria andConvertNumEqualTo(Integer value) {
            addCriterion("convert_num =", value, "convertNum");
            return (Criteria) this;
        }

        public Criteria andConvertNumNotEqualTo(Integer value) {
            addCriterion("convert_num <>", value, "convertNum");
            return (Criteria) this;
        }

        public Criteria andConvertNumGreaterThan(Integer value) {
            addCriterion("convert_num >", value, "convertNum");
            return (Criteria) this;
        }

        public Criteria andConvertNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("convert_num >=", value, "convertNum");
            return (Criteria) this;
        }

        public Criteria andConvertNumLessThan(Integer value) {
            addCriterion("convert_num <", value, "convertNum");
            return (Criteria) this;
        }

        public Criteria andConvertNumLessThanOrEqualTo(Integer value) {
            addCriterion("convert_num <=", value, "convertNum");
            return (Criteria) this;
        }

        public Criteria andConvertNumIn(List<Integer> values) {
            addCriterion("convert_num in", values, "convertNum");
            return (Criteria) this;
        }

        public Criteria andConvertNumNotIn(List<Integer> values) {
            addCriterion("convert_num not in", values, "convertNum");
            return (Criteria) this;
        }

        public Criteria andConvertNumBetween(Integer value1, Integer value2) {
            addCriterion("convert_num between", value1, value2, "convertNum");
            return (Criteria) this;
        }

        public Criteria andConvertNumNotBetween(Integer value1, Integer value2) {
            addCriterion("convert_num not between", value1, value2, "convertNum");
            return (Criteria) this;
        }

        public Criteria andItemWeightIsNull() {
            addCriterion("item_weight is null");
            return (Criteria) this;
        }

        public Criteria andItemWeightIsNotNull() {
            addCriterion("item_weight is not null");
            return (Criteria) this;
        }

        public Criteria andItemWeightEqualTo(BigDecimal value) {
            addCriterion("item_weight =", value, "itemWeight");
            return (Criteria) this;
        }

        public Criteria andItemWeightNotEqualTo(BigDecimal value) {
            addCriterion("item_weight <>", value, "itemWeight");
            return (Criteria) this;
        }

        public Criteria andItemWeightGreaterThan(BigDecimal value) {
            addCriterion("item_weight >", value, "itemWeight");
            return (Criteria) this;
        }

        public Criteria andItemWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("item_weight >=", value, "itemWeight");
            return (Criteria) this;
        }

        public Criteria andItemWeightLessThan(BigDecimal value) {
            addCriterion("item_weight <", value, "itemWeight");
            return (Criteria) this;
        }

        public Criteria andItemWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("item_weight <=", value, "itemWeight");
            return (Criteria) this;
        }

        public Criteria andItemWeightIn(List<BigDecimal> values) {
            addCriterion("item_weight in", values, "itemWeight");
            return (Criteria) this;
        }

        public Criteria andItemWeightNotIn(List<BigDecimal> values) {
            addCriterion("item_weight not in", values, "itemWeight");
            return (Criteria) this;
        }

        public Criteria andItemWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_weight between", value1, value2, "itemWeight");
            return (Criteria) this;
        }

        public Criteria andItemWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_weight not between", value1, value2, "itemWeight");
            return (Criteria) this;
        }

        public Criteria andShelfLifeIsNull() {
            addCriterion("shelf_life is null");
            return (Criteria) this;
        }

        public Criteria andShelfLifeIsNotNull() {
            addCriterion("shelf_life is not null");
            return (Criteria) this;
        }

        public Criteria andShelfLifeEqualTo(Integer value) {
            addCriterion("shelf_life =", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeNotEqualTo(Integer value) {
            addCriterion("shelf_life <>", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeGreaterThan(Integer value) {
            addCriterion("shelf_life >", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeGreaterThanOrEqualTo(Integer value) {
            addCriterion("shelf_life >=", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeLessThan(Integer value) {
            addCriterion("shelf_life <", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeLessThanOrEqualTo(Integer value) {
            addCriterion("shelf_life <=", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeIn(List<Integer> values) {
            addCriterion("shelf_life in", values, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeNotIn(List<Integer> values) {
            addCriterion("shelf_life not in", values, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeBetween(Integer value1, Integer value2) {
            addCriterion("shelf_life between", value1, value2, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeNotBetween(Integer value1, Integer value2) {
            addCriterion("shelf_life not between", value1, value2, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumIsNull() {
            addCriterion("purchase_num is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumIsNotNull() {
            addCriterion("purchase_num is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumEqualTo(Integer value) {
            addCriterion("purchase_num =", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumNotEqualTo(Integer value) {
            addCriterion("purchase_num <>", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumGreaterThan(Integer value) {
            addCriterion("purchase_num >", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_num >=", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumLessThan(Integer value) {
            addCriterion("purchase_num <", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_num <=", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumIn(List<Integer> values) {
            addCriterion("purchase_num in", values, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumNotIn(List<Integer> values) {
            addCriterion("purchase_num not in", values, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumBetween(Integer value1, Integer value2) {
            addCriterion("purchase_num between", value1, value2, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_num not between", value1, value2, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andItemRemarkIsNull() {
            addCriterion("item_remark is null");
            return (Criteria) this;
        }

        public Criteria andItemRemarkIsNotNull() {
            addCriterion("item_remark is not null");
            return (Criteria) this;
        }

        public Criteria andItemRemarkEqualTo(String value) {
            addCriterion("item_remark =", value, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkNotEqualTo(String value) {
            addCriterion("item_remark <>", value, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkGreaterThan(String value) {
            addCriterion("item_remark >", value, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("item_remark >=", value, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkLessThan(String value) {
            addCriterion("item_remark <", value, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkLessThanOrEqualTo(String value) {
            addCriterion("item_remark <=", value, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkLike(String value) {
            addCriterion("item_remark like", value, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkNotLike(String value) {
            addCriterion("item_remark not like", value, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkIn(List<String> values) {
            addCriterion("item_remark in", values, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkNotIn(List<String> values) {
            addCriterion("item_remark not in", values, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkBetween(String value1, String value2) {
            addCriterion("item_remark between", value1, value2, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andItemRemarkNotBetween(String value1, String value2) {
            addCriterion("item_remark not between", value1, value2, "itemRemark");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumIsNull() {
            addCriterion("purchased_num is null");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumIsNotNull() {
            addCriterion("purchased_num is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumEqualTo(Integer value) {
            addCriterion("purchased_num =", value, "purchasedNum");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumNotEqualTo(Integer value) {
            addCriterion("purchased_num <>", value, "purchasedNum");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumGreaterThan(Integer value) {
            addCriterion("purchased_num >", value, "purchasedNum");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchased_num >=", value, "purchasedNum");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumLessThan(Integer value) {
            addCriterion("purchased_num <", value, "purchasedNum");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumLessThanOrEqualTo(Integer value) {
            addCriterion("purchased_num <=", value, "purchasedNum");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumIn(List<Integer> values) {
            addCriterion("purchased_num in", values, "purchasedNum");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumNotIn(List<Integer> values) {
            addCriterion("purchased_num not in", values, "purchasedNum");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumBetween(Integer value1, Integer value2) {
            addCriterion("purchased_num between", value1, value2, "purchasedNum");
            return (Criteria) this;
        }

        public Criteria andPurchasedNumNotBetween(Integer value1, Integer value2) {
            addCriterion("purchased_num not between", value1, value2, "purchasedNum");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNull() {
            addCriterion("total_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNotNull() {
            addCriterion("total_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceEqualTo(Integer value) {
            addCriterion("total_price =", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotEqualTo(Integer value) {
            addCriterion("total_price <>", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThan(Integer value) {
            addCriterion("total_price >", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_price >=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThan(Integer value) {
            addCriterion("total_price <", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThanOrEqualTo(Integer value) {
            addCriterion("total_price <=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIn(List<Integer> values) {
            addCriterion("total_price in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotIn(List<Integer> values) {
            addCriterion("total_price not in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBetween(Integer value1, Integer value2) {
            addCriterion("total_price between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("total_price not between", value1, value2, "totalPrice");
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

        public Criteria andSupplierNameIsNull() {
            addCriterion("supplier_name is null");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIsNotNull() {
            addCriterion("supplier_name is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierNameEqualTo(String value) {
            addCriterion("supplier_name =", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotEqualTo(String value) {
            addCriterion("supplier_name <>", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameGreaterThan(String value) {
            addCriterion("supplier_name >", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_name >=", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLessThan(String value) {
            addCriterion("supplier_name <", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLessThanOrEqualTo(String value) {
            addCriterion("supplier_name <=", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLike(String value) {
            addCriterion("supplier_name like", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotLike(String value) {
            addCriterion("supplier_name not like", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIn(List<String> values) {
            addCriterion("supplier_name in", values, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotIn(List<String> values) {
            addCriterion("supplier_name not in", values, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameBetween(String value1, String value2) {
            addCriterion("supplier_name between", value1, value2, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotBetween(String value1, String value2) {
            addCriterion("supplier_name not between", value1, value2, "supplierName");
            return (Criteria) this;
        }

        public Criteria andUseNumIsNull() {
            addCriterion("use_num is null");
            return (Criteria) this;
        }

        public Criteria andUseNumIsNotNull() {
            addCriterion("use_num is not null");
            return (Criteria) this;
        }

        public Criteria andUseNumEqualTo(Integer value) {
            addCriterion("use_num =", value, "useNum");
            return (Criteria) this;
        }

        public Criteria andUseNumNotEqualTo(Integer value) {
            addCriterion("use_num <>", value, "useNum");
            return (Criteria) this;
        }

        public Criteria andUseNumGreaterThan(Integer value) {
            addCriterion("use_num >", value, "useNum");
            return (Criteria) this;
        }

        public Criteria andUseNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_num >=", value, "useNum");
            return (Criteria) this;
        }

        public Criteria andUseNumLessThan(Integer value) {
            addCriterion("use_num <", value, "useNum");
            return (Criteria) this;
        }

        public Criteria andUseNumLessThanOrEqualTo(Integer value) {
            addCriterion("use_num <=", value, "useNum");
            return (Criteria) this;
        }

        public Criteria andUseNumIn(List<Integer> values) {
            addCriterion("use_num in", values, "useNum");
            return (Criteria) this;
        }

        public Criteria andUseNumNotIn(List<Integer> values) {
            addCriterion("use_num not in", values, "useNum");
            return (Criteria) this;
        }

        public Criteria andUseNumBetween(Integer value1, Integer value2) {
            addCriterion("use_num between", value1, value2, "useNum");
            return (Criteria) this;
        }

        public Criteria andUseNumNotBetween(Integer value1, Integer value2) {
            addCriterion("use_num not between", value1, value2, "useNum");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNull() {
            addCriterion("barcode is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNotNull() {
            addCriterion("barcode is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeEqualTo(String value) {
            addCriterion("barcode =", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotEqualTo(String value) {
            addCriterion("barcode <>", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThan(String value) {
            addCriterion("barcode >", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("barcode >=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThan(String value) {
            addCriterion("barcode <", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThanOrEqualTo(String value) {
            addCriterion("barcode <=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLike(String value) {
            addCriterion("barcode like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotLike(String value) {
            addCriterion("barcode not like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeIn(List<String> values) {
            addCriterion("barcode in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotIn(List<String> values) {
            addCriterion("barcode not in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeBetween(String value1, String value2) {
            addCriterion("barcode between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotBetween(String value1, String value2) {
            addCriterion("barcode not between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andStockFlagIsNull() {
            addCriterion("stock_flag is null");
            return (Criteria) this;
        }

        public Criteria andStockFlagIsNotNull() {
            addCriterion("stock_flag is not null");
            return (Criteria) this;
        }

        public Criteria andStockFlagEqualTo(Integer value) {
            addCriterion("stock_flag =", value, "stockFlag");
            return (Criteria) this;
        }

        public Criteria andStockFlagNotEqualTo(Integer value) {
            addCriterion("stock_flag <>", value, "stockFlag");
            return (Criteria) this;
        }

        public Criteria andStockFlagGreaterThan(Integer value) {
            addCriterion("stock_flag >", value, "stockFlag");
            return (Criteria) this;
        }

        public Criteria andStockFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_flag >=", value, "stockFlag");
            return (Criteria) this;
        }

        public Criteria andStockFlagLessThan(Integer value) {
            addCriterion("stock_flag <", value, "stockFlag");
            return (Criteria) this;
        }

        public Criteria andStockFlagLessThanOrEqualTo(Integer value) {
            addCriterion("stock_flag <=", value, "stockFlag");
            return (Criteria) this;
        }

        public Criteria andStockFlagIn(List<Integer> values) {
            addCriterion("stock_flag in", values, "stockFlag");
            return (Criteria) this;
        }

        public Criteria andStockFlagNotIn(List<Integer> values) {
            addCriterion("stock_flag not in", values, "stockFlag");
            return (Criteria) this;
        }

        public Criteria andStockFlagBetween(Integer value1, Integer value2) {
            addCriterion("stock_flag between", value1, value2, "stockFlag");
            return (Criteria) this;
        }

        public Criteria andStockFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_flag not between", value1, value2, "stockFlag");
            return (Criteria) this;
        }

        public Criteria andStockedNumIsNull() {
            addCriterion("stocked_num is null");
            return (Criteria) this;
        }

        public Criteria andStockedNumIsNotNull() {
            addCriterion("stocked_num is not null");
            return (Criteria) this;
        }

        public Criteria andStockedNumEqualTo(Integer value) {
            addCriterion("stocked_num =", value, "stockedNum");
            return (Criteria) this;
        }

        public Criteria andStockedNumNotEqualTo(Integer value) {
            addCriterion("stocked_num <>", value, "stockedNum");
            return (Criteria) this;
        }

        public Criteria andStockedNumGreaterThan(Integer value) {
            addCriterion("stocked_num >", value, "stockedNum");
            return (Criteria) this;
        }

        public Criteria andStockedNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("stocked_num >=", value, "stockedNum");
            return (Criteria) this;
        }

        public Criteria andStockedNumLessThan(Integer value) {
            addCriterion("stocked_num <", value, "stockedNum");
            return (Criteria) this;
        }

        public Criteria andStockedNumLessThanOrEqualTo(Integer value) {
            addCriterion("stocked_num <=", value, "stockedNum");
            return (Criteria) this;
        }

        public Criteria andStockedNumIn(List<Integer> values) {
            addCriterion("stocked_num in", values, "stockedNum");
            return (Criteria) this;
        }

        public Criteria andStockedNumNotIn(List<Integer> values) {
            addCriterion("stocked_num not in", values, "stockedNum");
            return (Criteria) this;
        }

        public Criteria andStockedNumBetween(Integer value1, Integer value2) {
            addCriterion("stocked_num between", value1, value2, "stockedNum");
            return (Criteria) this;
        }

        public Criteria andStockedNumNotBetween(Integer value1, Integer value2) {
            addCriterion("stocked_num not between", value1, value2, "stockedNum");
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

        public Criteria andStockedTimeIsNull() {
            addCriterion("stocked_time is null");
            return (Criteria) this;
        }

        public Criteria andStockedTimeIsNotNull() {
            addCriterion("stocked_time is not null");
            return (Criteria) this;
        }

        public Criteria andStockedTimeEqualTo(Date value) {
            addCriterion("stocked_time =", value, "stockedTime");
            return (Criteria) this;
        }

        public Criteria andStockedTimeNotEqualTo(Date value) {
            addCriterion("stocked_time <>", value, "stockedTime");
            return (Criteria) this;
        }

        public Criteria andStockedTimeGreaterThan(Date value) {
            addCriterion("stocked_time >", value, "stockedTime");
            return (Criteria) this;
        }

        public Criteria andStockedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("stocked_time >=", value, "stockedTime");
            return (Criteria) this;
        }

        public Criteria andStockedTimeLessThan(Date value) {
            addCriterion("stocked_time <", value, "stockedTime");
            return (Criteria) this;
        }

        public Criteria andStockedTimeLessThanOrEqualTo(Date value) {
            addCriterion("stocked_time <=", value, "stockedTime");
            return (Criteria) this;
        }

        public Criteria andStockedTimeIn(List<Date> values) {
            addCriterion("stocked_time in", values, "stockedTime");
            return (Criteria) this;
        }

        public Criteria andStockedTimeNotIn(List<Date> values) {
            addCriterion("stocked_time not in", values, "stockedTime");
            return (Criteria) this;
        }

        public Criteria andStockedTimeBetween(Date value1, Date value2) {
            addCriterion("stocked_time between", value1, value2, "stockedTime");
            return (Criteria) this;
        }

        public Criteria andStockedTimeNotBetween(Date value1, Date value2) {
            addCriterion("stocked_time not between", value1, value2, "stockedTime");
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

        public Criteria andIsdownIsNull() {
            addCriterion("isDown is null");
            return (Criteria) this;
        }

        public Criteria andIsdownIsNotNull() {
            addCriterion("isDown is not null");
            return (Criteria) this;
        }

        public Criteria andIsdownEqualTo(Integer value) {
            addCriterion("isDown =", value, "isdown");
            return (Criteria) this;
        }

        public Criteria andIsdownNotEqualTo(Integer value) {
            addCriterion("isDown <>", value, "isdown");
            return (Criteria) this;
        }

        public Criteria andIsdownGreaterThan(Integer value) {
            addCriterion("isDown >", value, "isdown");
            return (Criteria) this;
        }

        public Criteria andIsdownGreaterThanOrEqualTo(Integer value) {
            addCriterion("isDown >=", value, "isdown");
            return (Criteria) this;
        }

        public Criteria andIsdownLessThan(Integer value) {
            addCriterion("isDown <", value, "isdown");
            return (Criteria) this;
        }

        public Criteria andIsdownLessThanOrEqualTo(Integer value) {
            addCriterion("isDown <=", value, "isdown");
            return (Criteria) this;
        }

        public Criteria andIsdownIn(List<Integer> values) {
            addCriterion("isDown in", values, "isdown");
            return (Criteria) this;
        }

        public Criteria andIsdownNotIn(List<Integer> values) {
            addCriterion("isDown not in", values, "isdown");
            return (Criteria) this;
        }

        public Criteria andIsdownBetween(Integer value1, Integer value2) {
            addCriterion("isDown between", value1, value2, "isdown");
            return (Criteria) this;
        }

        public Criteria andIsdownNotBetween(Integer value1, Integer value2) {
            addCriterion("isDown not between", value1, value2, "isdown");
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