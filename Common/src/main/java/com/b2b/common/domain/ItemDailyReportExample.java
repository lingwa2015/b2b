package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ItemDailyReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ItemDailyReportExample() {
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

        public Criteria andTurnoverRateIsNull() {
            addCriterion("turnover_rate is null");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateIsNotNull() {
            addCriterion("turnover_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateEqualTo(BigDecimal value) {
            addCriterion("turnover_rate =", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateNotEqualTo(BigDecimal value) {
            addCriterion("turnover_rate <>", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateGreaterThan(BigDecimal value) {
            addCriterion("turnover_rate >", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("turnover_rate >=", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateLessThan(BigDecimal value) {
            addCriterion("turnover_rate <", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("turnover_rate <=", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateIn(List<BigDecimal> values) {
            addCriterion("turnover_rate in", values, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateNotIn(List<BigDecimal> values) {
            addCriterion("turnover_rate not in", values, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("turnover_rate between", value1, value2, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("turnover_rate not between", value1, value2, "turnoverRate");
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

        public Criteria andCategorylevelIdIsNull() {
            addCriterion("categorylevel_id is null");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdIsNotNull() {
            addCriterion("categorylevel_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdEqualTo(Integer value) {
            addCriterion("categorylevel_id =", value, "categorylevelId");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdNotEqualTo(Integer value) {
            addCriterion("categorylevel_id <>", value, "categorylevelId");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdGreaterThan(Integer value) {
            addCriterion("categorylevel_id >", value, "categorylevelId");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("categorylevel_id >=", value, "categorylevelId");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdLessThan(Integer value) {
            addCriterion("categorylevel_id <", value, "categorylevelId");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("categorylevel_id <=", value, "categorylevelId");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdIn(List<Integer> values) {
            addCriterion("categorylevel_id in", values, "categorylevelId");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdNotIn(List<Integer> values) {
            addCriterion("categorylevel_id not in", values, "categorylevelId");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdBetween(Integer value1, Integer value2) {
            addCriterion("categorylevel_id between", value1, value2, "categorylevelId");
            return (Criteria) this;
        }

        public Criteria andCategorylevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("categorylevel_id not between", value1, value2, "categorylevelId");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameIsNull() {
            addCriterion("categorylevel_name is null");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameIsNotNull() {
            addCriterion("categorylevel_name is not null");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameEqualTo(String value) {
            addCriterion("categorylevel_name =", value, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameNotEqualTo(String value) {
            addCriterion("categorylevel_name <>", value, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameGreaterThan(String value) {
            addCriterion("categorylevel_name >", value, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("categorylevel_name >=", value, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameLessThan(String value) {
            addCriterion("categorylevel_name <", value, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameLessThanOrEqualTo(String value) {
            addCriterion("categorylevel_name <=", value, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameLike(String value) {
            addCriterion("categorylevel_name like", value, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameNotLike(String value) {
            addCriterion("categorylevel_name not like", value, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameIn(List<String> values) {
            addCriterion("categorylevel_name in", values, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameNotIn(List<String> values) {
            addCriterion("categorylevel_name not in", values, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameBetween(String value1, String value2) {
            addCriterion("categorylevel_name between", value1, value2, "categorylevelName");
            return (Criteria) this;
        }

        public Criteria andCategorylevelNameNotBetween(String value1, String value2) {
            addCriterion("categorylevel_name not between", value1, value2, "categorylevelName");
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

        public Criteria andOrderItemNumIsNull() {
            addCriterion("order_item_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumIsNotNull() {
            addCriterion("order_item_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumEqualTo(Integer value) {
            addCriterion("order_item_num =", value, "orderItemNum");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumNotEqualTo(Integer value) {
            addCriterion("order_item_num <>", value, "orderItemNum");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumGreaterThan(Integer value) {
            addCriterion("order_item_num >", value, "orderItemNum");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_item_num >=", value, "orderItemNum");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumLessThan(Integer value) {
            addCriterion("order_item_num <", value, "orderItemNum");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumLessThanOrEqualTo(Integer value) {
            addCriterion("order_item_num <=", value, "orderItemNum");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumIn(List<Integer> values) {
            addCriterion("order_item_num in", values, "orderItemNum");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumNotIn(List<Integer> values) {
            addCriterion("order_item_num not in", values, "orderItemNum");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumBetween(Integer value1, Integer value2) {
            addCriterion("order_item_num between", value1, value2, "orderItemNum");
            return (Criteria) this;
        }

        public Criteria andOrderItemNumNotBetween(Integer value1, Integer value2) {
            addCriterion("order_item_num not between", value1, value2, "orderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumIsNull() {
            addCriterion("shop_order_item_num is null");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumIsNotNull() {
            addCriterion("shop_order_item_num is not null");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumEqualTo(Integer value) {
            addCriterion("shop_order_item_num =", value, "shopOrderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumNotEqualTo(Integer value) {
            addCriterion("shop_order_item_num <>", value, "shopOrderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumGreaterThan(Integer value) {
            addCriterion("shop_order_item_num >", value, "shopOrderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_order_item_num >=", value, "shopOrderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumLessThan(Integer value) {
            addCriterion("shop_order_item_num <", value, "shopOrderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumLessThanOrEqualTo(Integer value) {
            addCriterion("shop_order_item_num <=", value, "shopOrderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumIn(List<Integer> values) {
            addCriterion("shop_order_item_num in", values, "shopOrderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumNotIn(List<Integer> values) {
            addCriterion("shop_order_item_num not in", values, "shopOrderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumBetween(Integer value1, Integer value2) {
            addCriterion("shop_order_item_num between", value1, value2, "shopOrderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemNumNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_order_item_num not between", value1, value2, "shopOrderItemNum");
            return (Criteria) this;
        }

        public Criteria andShopNumIsNull() {
            addCriterion("shop_num is null");
            return (Criteria) this;
        }

        public Criteria andShopNumIsNotNull() {
            addCriterion("shop_num is not null");
            return (Criteria) this;
        }

        public Criteria andShopNumEqualTo(Integer value) {
            addCriterion("shop_num =", value, "shopNum");
            return (Criteria) this;
        }

        public Criteria andShopNumNotEqualTo(Integer value) {
            addCriterion("shop_num <>", value, "shopNum");
            return (Criteria) this;
        }

        public Criteria andShopNumGreaterThan(Integer value) {
            addCriterion("shop_num >", value, "shopNum");
            return (Criteria) this;
        }

        public Criteria andShopNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_num >=", value, "shopNum");
            return (Criteria) this;
        }

        public Criteria andShopNumLessThan(Integer value) {
            addCriterion("shop_num <", value, "shopNum");
            return (Criteria) this;
        }

        public Criteria andShopNumLessThanOrEqualTo(Integer value) {
            addCriterion("shop_num <=", value, "shopNum");
            return (Criteria) this;
        }

        public Criteria andShopNumIn(List<Integer> values) {
            addCriterion("shop_num in", values, "shopNum");
            return (Criteria) this;
        }

        public Criteria andShopNumNotIn(List<Integer> values) {
            addCriterion("shop_num not in", values, "shopNum");
            return (Criteria) this;
        }

        public Criteria andShopNumBetween(Integer value1, Integer value2) {
            addCriterion("shop_num between", value1, value2, "shopNum");
            return (Criteria) this;
        }

        public Criteria andShopNumNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_num not between", value1, value2, "shopNum");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalIsNull() {
            addCriterion("shop_order_item_total is null");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalIsNotNull() {
            addCriterion("shop_order_item_total is not null");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalEqualTo(Long value) {
            addCriterion("shop_order_item_total =", value, "shopOrderItemTotal");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalNotEqualTo(Long value) {
            addCriterion("shop_order_item_total <>", value, "shopOrderItemTotal");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalGreaterThan(Long value) {
            addCriterion("shop_order_item_total >", value, "shopOrderItemTotal");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_order_item_total >=", value, "shopOrderItemTotal");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalLessThan(Long value) {
            addCriterion("shop_order_item_total <", value, "shopOrderItemTotal");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalLessThanOrEqualTo(Long value) {
            addCriterion("shop_order_item_total <=", value, "shopOrderItemTotal");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalIn(List<Long> values) {
            addCriterion("shop_order_item_total in", values, "shopOrderItemTotal");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalNotIn(List<Long> values) {
            addCriterion("shop_order_item_total not in", values, "shopOrderItemTotal");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalBetween(Long value1, Long value2) {
            addCriterion("shop_order_item_total between", value1, value2, "shopOrderItemTotal");
            return (Criteria) this;
        }

        public Criteria andShopOrderItemTotalNotBetween(Long value1, Long value2) {
            addCriterion("shop_order_item_total not between", value1, value2, "shopOrderItemTotal");
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

        public Criteria andOrderShopTotalIsNull() {
            addCriterion("order_shop_total is null");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalIsNotNull() {
            addCriterion("order_shop_total is not null");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalEqualTo(Long value) {
            addCriterion("order_shop_total =", value, "orderShopTotal");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalNotEqualTo(Long value) {
            addCriterion("order_shop_total <>", value, "orderShopTotal");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalGreaterThan(Long value) {
            addCriterion("order_shop_total >", value, "orderShopTotal");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("order_shop_total >=", value, "orderShopTotal");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalLessThan(Long value) {
            addCriterion("order_shop_total <", value, "orderShopTotal");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalLessThanOrEqualTo(Long value) {
            addCriterion("order_shop_total <=", value, "orderShopTotal");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalIn(List<Long> values) {
            addCriterion("order_shop_total in", values, "orderShopTotal");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalNotIn(List<Long> values) {
            addCriterion("order_shop_total not in", values, "orderShopTotal");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalBetween(Long value1, Long value2) {
            addCriterion("order_shop_total between", value1, value2, "orderShopTotal");
            return (Criteria) this;
        }

        public Criteria andOrderShopTotalNotBetween(Long value1, Long value2) {
            addCriterion("order_shop_total not between", value1, value2, "orderShopTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumIsNull() {
            addCriterion("order_refund_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumIsNotNull() {
            addCriterion("order_refund_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumEqualTo(Integer value) {
            addCriterion("order_refund_num =", value, "orderRefundNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumNotEqualTo(Integer value) {
            addCriterion("order_refund_num <>", value, "orderRefundNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumGreaterThan(Integer value) {
            addCriterion("order_refund_num >", value, "orderRefundNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_refund_num >=", value, "orderRefundNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumLessThan(Integer value) {
            addCriterion("order_refund_num <", value, "orderRefundNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumLessThanOrEqualTo(Integer value) {
            addCriterion("order_refund_num <=", value, "orderRefundNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumIn(List<Integer> values) {
            addCriterion("order_refund_num in", values, "orderRefundNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumNotIn(List<Integer> values) {
            addCriterion("order_refund_num not in", values, "orderRefundNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumBetween(Integer value1, Integer value2) {
            addCriterion("order_refund_num between", value1, value2, "orderRefundNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundNumNotBetween(Integer value1, Integer value2) {
            addCriterion("order_refund_num not between", value1, value2, "orderRefundNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalIsNull() {
            addCriterion("order_refund_total is null");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalIsNotNull() {
            addCriterion("order_refund_total is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalEqualTo(Long value) {
            addCriterion("order_refund_total =", value, "orderRefundTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalNotEqualTo(Long value) {
            addCriterion("order_refund_total <>", value, "orderRefundTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalGreaterThan(Long value) {
            addCriterion("order_refund_total >", value, "orderRefundTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("order_refund_total >=", value, "orderRefundTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalLessThan(Long value) {
            addCriterion("order_refund_total <", value, "orderRefundTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalLessThanOrEqualTo(Long value) {
            addCriterion("order_refund_total <=", value, "orderRefundTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalIn(List<Long> values) {
            addCriterion("order_refund_total in", values, "orderRefundTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalNotIn(List<Long> values) {
            addCriterion("order_refund_total not in", values, "orderRefundTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalBetween(Long value1, Long value2) {
            addCriterion("order_refund_total between", value1, value2, "orderRefundTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundTotalNotBetween(Long value1, Long value2) {
            addCriterion("order_refund_total not between", value1, value2, "orderRefundTotal");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumIsNull() {
            addCriterion("order_refund_shop_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumIsNotNull() {
            addCriterion("order_refund_shop_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumEqualTo(Integer value) {
            addCriterion("order_refund_shop_num =", value, "orderRefundShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumNotEqualTo(Integer value) {
            addCriterion("order_refund_shop_num <>", value, "orderRefundShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumGreaterThan(Integer value) {
            addCriterion("order_refund_shop_num >", value, "orderRefundShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_refund_shop_num >=", value, "orderRefundShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumLessThan(Integer value) {
            addCriterion("order_refund_shop_num <", value, "orderRefundShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumLessThanOrEqualTo(Integer value) {
            addCriterion("order_refund_shop_num <=", value, "orderRefundShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumIn(List<Integer> values) {
            addCriterion("order_refund_shop_num in", values, "orderRefundShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumNotIn(List<Integer> values) {
            addCriterion("order_refund_shop_num not in", values, "orderRefundShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumBetween(Integer value1, Integer value2) {
            addCriterion("order_refund_shop_num between", value1, value2, "orderRefundShopNum");
            return (Criteria) this;
        }

        public Criteria andOrderRefundShopNumNotBetween(Integer value1, Integer value2) {
            addCriterion("order_refund_shop_num not between", value1, value2, "orderRefundShopNum");
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

        public Criteria andShelfSalesRateIsNull() {
            addCriterion("shelf_sales_rate is null");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateIsNotNull() {
            addCriterion("shelf_sales_rate is not null");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateEqualTo(BigDecimal value) {
            addCriterion("shelf_sales_rate =", value, "shelfSalesRate");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateNotEqualTo(BigDecimal value) {
            addCriterion("shelf_sales_rate <>", value, "shelfSalesRate");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateGreaterThan(BigDecimal value) {
            addCriterion("shelf_sales_rate >", value, "shelfSalesRate");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shelf_sales_rate >=", value, "shelfSalesRate");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateLessThan(BigDecimal value) {
            addCriterion("shelf_sales_rate <", value, "shelfSalesRate");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shelf_sales_rate <=", value, "shelfSalesRate");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateIn(List<BigDecimal> values) {
            addCriterion("shelf_sales_rate in", values, "shelfSalesRate");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateNotIn(List<BigDecimal> values) {
            addCriterion("shelf_sales_rate not in", values, "shelfSalesRate");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shelf_sales_rate between", value1, value2, "shelfSalesRate");
            return (Criteria) this;
        }

        public Criteria andShelfSalesRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shelf_sales_rate not between", value1, value2, "shelfSalesRate");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumIsNull() {
            addCriterion("unsalable_shop_num is null");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumIsNotNull() {
            addCriterion("unsalable_shop_num is not null");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumEqualTo(Integer value) {
            addCriterion("unsalable_shop_num =", value, "unsalableShopNum");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumNotEqualTo(Integer value) {
            addCriterion("unsalable_shop_num <>", value, "unsalableShopNum");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumGreaterThan(Integer value) {
            addCriterion("unsalable_shop_num >", value, "unsalableShopNum");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("unsalable_shop_num >=", value, "unsalableShopNum");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumLessThan(Integer value) {
            addCriterion("unsalable_shop_num <", value, "unsalableShopNum");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumLessThanOrEqualTo(Integer value) {
            addCriterion("unsalable_shop_num <=", value, "unsalableShopNum");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumIn(List<Integer> values) {
            addCriterion("unsalable_shop_num in", values, "unsalableShopNum");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumNotIn(List<Integer> values) {
            addCriterion("unsalable_shop_num not in", values, "unsalableShopNum");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumBetween(Integer value1, Integer value2) {
            addCriterion("unsalable_shop_num between", value1, value2, "unsalableShopNum");
            return (Criteria) this;
        }

        public Criteria andUnsalableShopNumNotBetween(Integer value1, Integer value2) {
            addCriterion("unsalable_shop_num not between", value1, value2, "unsalableShopNum");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateIsNull() {
            addCriterion("unsalable_rate is null");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateIsNotNull() {
            addCriterion("unsalable_rate is not null");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateEqualTo(BigDecimal value) {
            addCriterion("unsalable_rate =", value, "unsalableRate");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateNotEqualTo(BigDecimal value) {
            addCriterion("unsalable_rate <>", value, "unsalableRate");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateGreaterThan(BigDecimal value) {
            addCriterion("unsalable_rate >", value, "unsalableRate");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unsalable_rate >=", value, "unsalableRate");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateLessThan(BigDecimal value) {
            addCriterion("unsalable_rate <", value, "unsalableRate");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unsalable_rate <=", value, "unsalableRate");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateIn(List<BigDecimal> values) {
            addCriterion("unsalable_rate in", values, "unsalableRate");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateNotIn(List<BigDecimal> values) {
            addCriterion("unsalable_rate not in", values, "unsalableRate");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unsalable_rate between", value1, value2, "unsalableRate");
            return (Criteria) this;
        }

        public Criteria andUnsalableRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unsalable_rate not between", value1, value2, "unsalableRate");
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

        public Criteria andReseauIsNull() {
            addCriterion("reseau is null");
            return (Criteria) this;
        }

        public Criteria andReseauIsNotNull() {
            addCriterion("reseau is not null");
            return (Criteria) this;
        }

        public Criteria andReseauEqualTo(String value) {
            addCriterion("reseau =", value, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauNotEqualTo(String value) {
            addCriterion("reseau <>", value, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauGreaterThan(String value) {
            addCriterion("reseau >", value, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauGreaterThanOrEqualTo(String value) {
            addCriterion("reseau >=", value, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauLessThan(String value) {
            addCriterion("reseau <", value, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauLessThanOrEqualTo(String value) {
            addCriterion("reseau <=", value, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauLike(String value) {
            addCriterion("reseau like", value, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauNotLike(String value) {
            addCriterion("reseau not like", value, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauIn(List<String> values) {
            addCriterion("reseau in", values, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauNotIn(List<String> values) {
            addCriterion("reseau not in", values, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauBetween(String value1, String value2) {
            addCriterion("reseau between", value1, value2, "reseau");
            return (Criteria) this;
        }

        public Criteria andReseauNotBetween(String value1, String value2) {
            addCriterion("reseau not between", value1, value2, "reseau");
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

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNull() {
            addCriterion("region_id is null");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNotNull() {
            addCriterion("region_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegionIdEqualTo(Integer value) {
            addCriterion("region_id =", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotEqualTo(Integer value) {
            addCriterion("region_id <>", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThan(Integer value) {
            addCriterion("region_id >", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("region_id >=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThan(Integer value) {
            addCriterion("region_id <", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThanOrEqualTo(Integer value) {
            addCriterion("region_id <=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdIn(List<Integer> values) {
            addCriterion("region_id in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotIn(List<Integer> values) {
            addCriterion("region_id not in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdBetween(Integer value1, Integer value2) {
            addCriterion("region_id between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("region_id not between", value1, value2, "regionId");
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

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
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