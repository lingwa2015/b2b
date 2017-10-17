package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemExample extends BaseExample{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ItemExample() {
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

        public Criteria andImgPathIsNull() {
            addCriterion("img_path is null");
            return (Criteria) this;
        }

        public Criteria andImgPathIsNotNull() {
            addCriterion("img_path is not null");
            return (Criteria) this;
        }

        public Criteria andImgPathEqualTo(String value) {
            addCriterion("img_path =", value, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathNotEqualTo(String value) {
            addCriterion("img_path <>", value, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathGreaterThan(String value) {
            addCriterion("img_path >", value, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathGreaterThanOrEqualTo(String value) {
            addCriterion("img_path >=", value, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathLessThan(String value) {
            addCriterion("img_path <", value, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathLessThanOrEqualTo(String value) {
            addCriterion("img_path <=", value, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathLike(String value) {
            addCriterion("img_path like", value, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathNotLike(String value) {
            addCriterion("img_path not like", value, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathIn(List<String> values) {
            addCriterion("img_path in", values, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathNotIn(List<String> values) {
            addCriterion("img_path not in", values, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathBetween(String value1, String value2) {
            addCriterion("img_path between", value1, value2, "imgPath");
            return (Criteria) this;
        }

        public Criteria andImgPathNotBetween(String value1, String value2) {
            addCriterion("img_path not between", value1, value2, "imgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathIsNull() {
            addCriterion("big_img_path is null");
            return (Criteria) this;
        }

        public Criteria andBigImgPathIsNotNull() {
            addCriterion("big_img_path is not null");
            return (Criteria) this;
        }

        public Criteria andBigImgPathEqualTo(String value) {
            addCriterion("big_img_path =", value, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathNotEqualTo(String value) {
            addCriterion("big_img_path <>", value, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathGreaterThan(String value) {
            addCriterion("big_img_path >", value, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathGreaterThanOrEqualTo(String value) {
            addCriterion("big_img_path >=", value, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathLessThan(String value) {
            addCriterion("big_img_path <", value, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathLessThanOrEqualTo(String value) {
            addCriterion("big_img_path <=", value, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathLike(String value) {
            addCriterion("big_img_path like", value, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathNotLike(String value) {
            addCriterion("big_img_path not like", value, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathIn(List<String> values) {
            addCriterion("big_img_path in", values, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathNotIn(List<String> values) {
            addCriterion("big_img_path not in", values, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathBetween(String value1, String value2) {
            addCriterion("big_img_path between", value1, value2, "bigImgPath");
            return (Criteria) this;
        }

        public Criteria andBigImgPathNotBetween(String value1, String value2) {
            addCriterion("big_img_path not between", value1, value2, "bigImgPath");
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

        public Criteria andNotaxInclusiveCostPriceIsNull() {
            addCriterion("notax_inclusive_cost_price is null");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceIsNotNull() {
            addCriterion("notax_inclusive_cost_price is not null");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceEqualTo(Long value) {
            addCriterion("notax_inclusive_cost_price =", value, "notaxInclusiveCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceNotEqualTo(Long value) {
            addCriterion("notax_inclusive_cost_price <>", value, "notaxInclusiveCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceGreaterThan(Long value) {
            addCriterion("notax_inclusive_cost_price >", value, "notaxInclusiveCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("notax_inclusive_cost_price >=", value, "notaxInclusiveCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceLessThan(Long value) {
            addCriterion("notax_inclusive_cost_price <", value, "notaxInclusiveCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceLessThanOrEqualTo(Long value) {
            addCriterion("notax_inclusive_cost_price <=", value, "notaxInclusiveCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceIn(List<Long> values) {
            addCriterion("notax_inclusive_cost_price in", values, "notaxInclusiveCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceNotIn(List<Long> values) {
            addCriterion("notax_inclusive_cost_price not in", values, "notaxInclusiveCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceBetween(Long value1, Long value2) {
            addCriterion("notax_inclusive_cost_price between", value1, value2, "notaxInclusiveCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveCostPriceNotBetween(Long value1, Long value2) {
            addCriterion("notax_inclusive_cost_price not between", value1, value2, "notaxInclusiveCostPrice");
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

        public Criteria andBuyPriceIsNull() {
            addCriterion("buy_price is null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIsNotNull() {
            addCriterion("buy_price is not null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceEqualTo(Long value) {
            addCriterion("buy_price =", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotEqualTo(Long value) {
            addCriterion("buy_price <>", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceGreaterThan(Long value) {
            addCriterion("buy_price >", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("buy_price >=", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLessThan(Long value) {
            addCriterion("buy_price <", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLessThanOrEqualTo(Long value) {
            addCriterion("buy_price <=", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIn(List<Long> values) {
            addCriterion("buy_price in", values, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotIn(List<Long> values) {
            addCriterion("buy_price not in", values, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceBetween(Long value1, Long value2) {
            addCriterion("buy_price between", value1, value2, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotBetween(Long value1, Long value2) {
            addCriterion("buy_price not between", value1, value2, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceIsNull() {
            addCriterion("notax_inclusive_buy_price is null");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceIsNotNull() {
            addCriterion("notax_inclusive_buy_price is not null");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceEqualTo(Long value) {
            addCriterion("notax_inclusive_buy_price =", value, "notaxInclusiveBuyPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceNotEqualTo(Long value) {
            addCriterion("notax_inclusive_buy_price <>", value, "notaxInclusiveBuyPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceGreaterThan(Long value) {
            addCriterion("notax_inclusive_buy_price >", value, "notaxInclusiveBuyPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("notax_inclusive_buy_price >=", value, "notaxInclusiveBuyPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceLessThan(Long value) {
            addCriterion("notax_inclusive_buy_price <", value, "notaxInclusiveBuyPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceLessThanOrEqualTo(Long value) {
            addCriterion("notax_inclusive_buy_price <=", value, "notaxInclusiveBuyPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceIn(List<Long> values) {
            addCriterion("notax_inclusive_buy_price in", values, "notaxInclusiveBuyPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceNotIn(List<Long> values) {
            addCriterion("notax_inclusive_buy_price not in", values, "notaxInclusiveBuyPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceBetween(Long value1, Long value2) {
            addCriterion("notax_inclusive_buy_price between", value1, value2, "notaxInclusiveBuyPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveBuyPriceNotBetween(Long value1, Long value2) {
            addCriterion("notax_inclusive_buy_price not between", value1, value2, "notaxInclusiveBuyPrice");
            return (Criteria) this;
        }

        public Criteria andBuySizeIsNull() {
            addCriterion("buy_size is null");
            return (Criteria) this;
        }

        public Criteria andBuySizeIsNotNull() {
            addCriterion("buy_size is not null");
            return (Criteria) this;
        }

        public Criteria andBuySizeEqualTo(String value) {
            addCriterion("buy_size =", value, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeNotEqualTo(String value) {
            addCriterion("buy_size <>", value, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeGreaterThan(String value) {
            addCriterion("buy_size >", value, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeGreaterThanOrEqualTo(String value) {
            addCriterion("buy_size >=", value, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeLessThan(String value) {
            addCriterion("buy_size <", value, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeLessThanOrEqualTo(String value) {
            addCriterion("buy_size <=", value, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeLike(String value) {
            addCriterion("buy_size like", value, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeNotLike(String value) {
            addCriterion("buy_size not like", value, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeIn(List<String> values) {
            addCriterion("buy_size in", values, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeNotIn(List<String> values) {
            addCriterion("buy_size not in", values, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeBetween(String value1, String value2) {
            addCriterion("buy_size between", value1, value2, "buySize");
            return (Criteria) this;
        }

        public Criteria andBuySizeNotBetween(String value1, String value2) {
            addCriterion("buy_size not between", value1, value2, "buySize");
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

        public Criteria andProfitIsNull() {
            addCriterion("profit is null");
            return (Criteria) this;
        }

        public Criteria andProfitIsNotNull() {
            addCriterion("profit is not null");
            return (Criteria) this;
        }

        public Criteria andProfitEqualTo(BigDecimal value) {
            addCriterion("profit =", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotEqualTo(BigDecimal value) {
            addCriterion("profit <>", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThan(BigDecimal value) {
            addCriterion("profit >", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("profit >=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThan(BigDecimal value) {
            addCriterion("profit <", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("profit <=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitIn(List<BigDecimal> values) {
            addCriterion("profit in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotIn(List<BigDecimal> values) {
            addCriterion("profit not in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit not between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNull() {
            addCriterion("sale_price is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNotNull() {
            addCriterion("sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceEqualTo(Long value) {
            addCriterion("sale_price =", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotEqualTo(Long value) {
            addCriterion("sale_price <>", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThan(Long value) {
            addCriterion("sale_price >", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("sale_price >=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThan(Long value) {
            addCriterion("sale_price <", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThanOrEqualTo(Long value) {
            addCriterion("sale_price <=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIn(List<Long> values) {
            addCriterion("sale_price in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotIn(List<Long> values) {
            addCriterion("sale_price not in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceBetween(Long value1, Long value2) {
            addCriterion("sale_price between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotBetween(Long value1, Long value2) {
            addCriterion("sale_price not between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSaleSizeIsNull() {
            addCriterion("sale_size is null");
            return (Criteria) this;
        }

        public Criteria andSaleSizeIsNotNull() {
            addCriterion("sale_size is not null");
            return (Criteria) this;
        }

        public Criteria andSaleSizeEqualTo(String value) {
            addCriterion("sale_size =", value, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNotEqualTo(String value) {
            addCriterion("sale_size <>", value, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeGreaterThan(String value) {
            addCriterion("sale_size >", value, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeGreaterThanOrEqualTo(String value) {
            addCriterion("sale_size >=", value, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeLessThan(String value) {
            addCriterion("sale_size <", value, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeLessThanOrEqualTo(String value) {
            addCriterion("sale_size <=", value, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeLike(String value) {
            addCriterion("sale_size like", value, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNotLike(String value) {
            addCriterion("sale_size not like", value, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeIn(List<String> values) {
            addCriterion("sale_size in", values, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNotIn(List<String> values) {
            addCriterion("sale_size not in", values, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeBetween(String value1, String value2) {
            addCriterion("sale_size between", value1, value2, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNotBetween(String value1, String value2) {
            addCriterion("sale_size not between", value1, value2, "saleSize");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumIsNull() {
            addCriterion("sale_size_num is null");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumIsNotNull() {
            addCriterion("sale_size_num is not null");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumEqualTo(Integer value) {
            addCriterion("sale_size_num =", value, "saleSizeNum");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumNotEqualTo(Integer value) {
            addCriterion("sale_size_num <>", value, "saleSizeNum");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumGreaterThan(Integer value) {
            addCriterion("sale_size_num >", value, "saleSizeNum");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_size_num >=", value, "saleSizeNum");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumLessThan(Integer value) {
            addCriterion("sale_size_num <", value, "saleSizeNum");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumLessThanOrEqualTo(Integer value) {
            addCriterion("sale_size_num <=", value, "saleSizeNum");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumIn(List<Integer> values) {
            addCriterion("sale_size_num in", values, "saleSizeNum");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumNotIn(List<Integer> values) {
            addCriterion("sale_size_num not in", values, "saleSizeNum");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumBetween(Integer value1, Integer value2) {
            addCriterion("sale_size_num between", value1, value2, "saleSizeNum");
            return (Criteria) this;
        }

        public Criteria andSaleSizeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_size_num not between", value1, value2, "saleSizeNum");
            return (Criteria) this;
        }

        public Criteria andKgNumIsNull() {
            addCriterion("kg_num is null");
            return (Criteria) this;
        }

        public Criteria andKgNumIsNotNull() {
            addCriterion("kg_num is not null");
            return (Criteria) this;
        }

        public Criteria andKgNumEqualTo(BigDecimal value) {
            addCriterion("kg_num =", value, "kgNum");
            return (Criteria) this;
        }

        public Criteria andKgNumNotEqualTo(BigDecimal value) {
            addCriterion("kg_num <>", value, "kgNum");
            return (Criteria) this;
        }

        public Criteria andKgNumGreaterThan(BigDecimal value) {
            addCriterion("kg_num >", value, "kgNum");
            return (Criteria) this;
        }

        public Criteria andKgNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("kg_num >=", value, "kgNum");
            return (Criteria) this;
        }

        public Criteria andKgNumLessThan(BigDecimal value) {
            addCriterion("kg_num <", value, "kgNum");
            return (Criteria) this;
        }

        public Criteria andKgNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("kg_num <=", value, "kgNum");
            return (Criteria) this;
        }

        public Criteria andKgNumIn(List<BigDecimal> values) {
            addCriterion("kg_num in", values, "kgNum");
            return (Criteria) this;
        }

        public Criteria andKgNumNotIn(List<BigDecimal> values) {
            addCriterion("kg_num not in", values, "kgNum");
            return (Criteria) this;
        }

        public Criteria andKgNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("kg_num between", value1, value2, "kgNum");
            return (Criteria) this;
        }

        public Criteria andKgNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("kg_num not between", value1, value2, "kgNum");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceIsNull() {
            addCriterion("sale_cost_price is null");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceIsNotNull() {
            addCriterion("sale_cost_price is not null");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceEqualTo(Long value) {
            addCriterion("sale_cost_price =", value, "saleCostPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceNotEqualTo(Long value) {
            addCriterion("sale_cost_price <>", value, "saleCostPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceGreaterThan(Long value) {
            addCriterion("sale_cost_price >", value, "saleCostPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("sale_cost_price >=", value, "saleCostPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceLessThan(Long value) {
            addCriterion("sale_cost_price <", value, "saleCostPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceLessThanOrEqualTo(Long value) {
            addCriterion("sale_cost_price <=", value, "saleCostPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceIn(List<Long> values) {
            addCriterion("sale_cost_price in", values, "saleCostPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceNotIn(List<Long> values) {
            addCriterion("sale_cost_price not in", values, "saleCostPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceBetween(Long value1, Long value2) {
            addCriterion("sale_cost_price between", value1, value2, "saleCostPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCostPriceNotBetween(Long value1, Long value2) {
            addCriterion("sale_cost_price not between", value1, value2, "saleCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceIsNull() {
            addCriterion("notax_inclusive_sale_cost_price is null");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceIsNotNull() {
            addCriterion("notax_inclusive_sale_cost_price is not null");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceEqualTo(Long value) {
            addCriterion("notax_inclusive_sale_cost_price =", value, "notaxInclusiveSaleCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceNotEqualTo(Long value) {
            addCriterion("notax_inclusive_sale_cost_price <>", value, "notaxInclusiveSaleCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceGreaterThan(Long value) {
            addCriterion("notax_inclusive_sale_cost_price >", value, "notaxInclusiveSaleCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("notax_inclusive_sale_cost_price >=", value, "notaxInclusiveSaleCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceLessThan(Long value) {
            addCriterion("notax_inclusive_sale_cost_price <", value, "notaxInclusiveSaleCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceLessThanOrEqualTo(Long value) {
            addCriterion("notax_inclusive_sale_cost_price <=", value, "notaxInclusiveSaleCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceIn(List<Long> values) {
            addCriterion("notax_inclusive_sale_cost_price in", values, "notaxInclusiveSaleCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceNotIn(List<Long> values) {
            addCriterion("notax_inclusive_sale_cost_price not in", values, "notaxInclusiveSaleCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceBetween(Long value1, Long value2) {
            addCriterion("notax_inclusive_sale_cost_price between", value1, value2, "notaxInclusiveSaleCostPrice");
            return (Criteria) this;
        }

        public Criteria andNotaxInclusiveSaleCostPriceNotBetween(Long value1, Long value2) {
            addCriterion("notax_inclusive_sale_cost_price not between", value1, value2, "notaxInclusiveSaleCostPrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIsNull() {
            addCriterion("purchase_price is null");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIsNotNull() {
            addCriterion("purchase_price is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceEqualTo(Long value) {
            addCriterion("purchase_price =", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotEqualTo(Long value) {
            addCriterion("purchase_price <>", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceGreaterThan(Long value) {
            addCriterion("purchase_price >", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("purchase_price >=", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceLessThan(Long value) {
            addCriterion("purchase_price <", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceLessThanOrEqualTo(Long value) {
            addCriterion("purchase_price <=", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIn(List<Long> values) {
            addCriterion("purchase_price in", values, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotIn(List<Long> values) {
            addCriterion("purchase_price not in", values, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceBetween(Long value1, Long value2) {
            addCriterion("purchase_price between", value1, value2, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotBetween(Long value1, Long value2) {
            addCriterion("purchase_price not between", value1, value2, "purchasePrice");
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

        public Criteria andItemVarietyIsNull() {
            addCriterion("item_variety is null");
            return (Criteria) this;
        }

        public Criteria andItemVarietyIsNotNull() {
            addCriterion("item_variety is not null");
            return (Criteria) this;
        }

        public Criteria andItemVarietyEqualTo(Integer value) {
            addCriterion("item_variety =", value, "itemVariety");
            return (Criteria) this;
        }

        public Criteria andItemVarietyNotEqualTo(Integer value) {
            addCriterion("item_variety <>", value, "itemVariety");
            return (Criteria) this;
        }

        public Criteria andItemVarietyGreaterThan(Integer value) {
            addCriterion("item_variety >", value, "itemVariety");
            return (Criteria) this;
        }

        public Criteria andItemVarietyGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_variety >=", value, "itemVariety");
            return (Criteria) this;
        }

        public Criteria andItemVarietyLessThan(Integer value) {
            addCriterion("item_variety <", value, "itemVariety");
            return (Criteria) this;
        }

        public Criteria andItemVarietyLessThanOrEqualTo(Integer value) {
            addCriterion("item_variety <=", value, "itemVariety");
            return (Criteria) this;
        }

        public Criteria andItemVarietyIn(List<Integer> values) {
            addCriterion("item_variety in", values, "itemVariety");
            return (Criteria) this;
        }

        public Criteria andItemVarietyNotIn(List<Integer> values) {
            addCriterion("item_variety not in", values, "itemVariety");
            return (Criteria) this;
        }

        public Criteria andItemVarietyBetween(Integer value1, Integer value2) {
            addCriterion("item_variety between", value1, value2, "itemVariety");
            return (Criteria) this;
        }

        public Criteria andItemVarietyNotBetween(Integer value1, Integer value2) {
            addCriterion("item_variety not between", value1, value2, "itemVariety");
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

        public Criteria andWarehouseIdIsNull() {
            addCriterion("warehouse_id is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIsNotNull() {
            addCriterion("warehouse_id is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdEqualTo(Integer value) {
            addCriterion("warehouse_id =", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotEqualTo(Integer value) {
            addCriterion("warehouse_id <>", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThan(Integer value) {
            addCriterion("warehouse_id >", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("warehouse_id >=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThan(Integer value) {
            addCriterion("warehouse_id <", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("warehouse_id <=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIn(List<Integer> values) {
            addCriterion("warehouse_id in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotIn(List<Integer> values) {
            addCriterion("warehouse_id not in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdBetween(Integer value1, Integer value2) {
            addCriterion("warehouse_id between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("warehouse_id not between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridIsNull() {
            addCriterion("created_userid is null");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridIsNotNull() {
            addCriterion("created_userid is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridEqualTo(Integer value) {
            addCriterion("created_userid =", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridNotEqualTo(Integer value) {
            addCriterion("created_userid <>", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridGreaterThan(Integer value) {
            addCriterion("created_userid >", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("created_userid >=", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridLessThan(Integer value) {
            addCriterion("created_userid <", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridLessThanOrEqualTo(Integer value) {
            addCriterion("created_userid <=", value, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridIn(List<Integer> values) {
            addCriterion("created_userid in", values, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridNotIn(List<Integer> values) {
            addCriterion("created_userid not in", values, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridBetween(Integer value1, Integer value2) {
            addCriterion("created_userid between", value1, value2, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andCreatedUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("created_userid not between", value1, value2, "createdUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridIsNull() {
            addCriterion("updated_userid is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridIsNotNull() {
            addCriterion("updated_userid is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridEqualTo(Integer value) {
            addCriterion("updated_userid =", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridNotEqualTo(Integer value) {
            addCriterion("updated_userid <>", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridGreaterThan(Integer value) {
            addCriterion("updated_userid >", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("updated_userid >=", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridLessThan(Integer value) {
            addCriterion("updated_userid <", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridLessThanOrEqualTo(Integer value) {
            addCriterion("updated_userid <=", value, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridIn(List<Integer> values) {
            addCriterion("updated_userid in", values, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridNotIn(List<Integer> values) {
            addCriterion("updated_userid not in", values, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridBetween(Integer value1, Integer value2) {
            addCriterion("updated_userid between", value1, value2, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andUpdatedUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("updated_userid not between", value1, value2, "updatedUserid");
            return (Criteria) this;
        }

        public Criteria andPlaceIsNull() {
            addCriterion("place is null");
            return (Criteria) this;
        }

        public Criteria andPlaceIsNotNull() {
            addCriterion("place is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceEqualTo(String value) {
            addCriterion("place =", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotEqualTo(String value) {
            addCriterion("place <>", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceGreaterThan(String value) {
            addCriterion("place >", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("place >=", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLessThan(String value) {
            addCriterion("place <", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLessThanOrEqualTo(String value) {
            addCriterion("place <=", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLike(String value) {
            addCriterion("place like", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotLike(String value) {
            addCriterion("place not like", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceIn(List<String> values) {
            addCriterion("place in", values, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotIn(List<String> values) {
            addCriterion("place not in", values, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceBetween(String value1, String value2) {
            addCriterion("place between", value1, value2, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotBetween(String value1, String value2) {
            addCriterion("place not between", value1, value2, "place");
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

        public Criteria andMarketPriceIsNull() {
            addCriterion("market_price is null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNotNull() {
            addCriterion("market_price is not null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceEqualTo(Long value) {
            addCriterion("market_price =", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotEqualTo(Long value) {
            addCriterion("market_price <>", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThan(Long value) {
            addCriterion("market_price >", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("market_price >=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThan(Long value) {
            addCriterion("market_price <", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThanOrEqualTo(Long value) {
            addCriterion("market_price <=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIn(List<Long> values) {
            addCriterion("market_price in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotIn(List<Long> values) {
            addCriterion("market_price not in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceBetween(Long value1, Long value2) {
            addCriterion("market_price between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotBetween(Long value1, Long value2) {
            addCriterion("market_price not between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(String value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(String value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(String value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(String value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(String value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(String value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(String value) {
            addCriterion("grade like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(String value) {
            addCriterion("grade not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<String> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<String> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(String value1, String value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(String value1, String value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andStoreWayIsNull() {
            addCriterion("store_way is null");
            return (Criteria) this;
        }

        public Criteria andStoreWayIsNotNull() {
            addCriterion("store_way is not null");
            return (Criteria) this;
        }

        public Criteria andStoreWayEqualTo(String value) {
            addCriterion("store_way =", value, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayNotEqualTo(String value) {
            addCriterion("store_way <>", value, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayGreaterThan(String value) {
            addCriterion("store_way >", value, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayGreaterThanOrEqualTo(String value) {
            addCriterion("store_way >=", value, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayLessThan(String value) {
            addCriterion("store_way <", value, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayLessThanOrEqualTo(String value) {
            addCriterion("store_way <=", value, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayLike(String value) {
            addCriterion("store_way like", value, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayNotLike(String value) {
            addCriterion("store_way not like", value, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayIn(List<String> values) {
            addCriterion("store_way in", values, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayNotIn(List<String> values) {
            addCriterion("store_way not in", values, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayBetween(String value1, String value2) {
            addCriterion("store_way between", value1, value2, "storeWay");
            return (Criteria) this;
        }

        public Criteria andStoreWayNotBetween(String value1, String value2) {
            addCriterion("store_way not between", value1, value2, "storeWay");
            return (Criteria) this;
        }

        public Criteria andRecommendIsNull() {
            addCriterion("recommend is null");
            return (Criteria) this;
        }

        public Criteria andRecommendIsNotNull() {
            addCriterion("recommend is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendEqualTo(Integer value) {
            addCriterion("recommend =", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotEqualTo(Integer value) {
            addCriterion("recommend <>", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendGreaterThan(Integer value) {
            addCriterion("recommend >", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommend >=", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLessThan(Integer value) {
            addCriterion("recommend <", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLessThanOrEqualTo(Integer value) {
            addCriterion("recommend <=", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendIn(List<Integer> values) {
            addCriterion("recommend in", values, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotIn(List<Integer> values) {
            addCriterion("recommend not in", values, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendBetween(Integer value1, Integer value2) {
            addCriterion("recommend between", value1, value2, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotBetween(Integer value1, Integer value2) {
            addCriterion("recommend not between", value1, value2, "recommend");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

        public Criteria andFreeSpecialSupplyIsNull() {
            addCriterion("free_special_supply is null");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyIsNotNull() {
            addCriterion("free_special_supply is not null");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyEqualTo(Integer value) {
            addCriterion("free_special_supply =", value, "freeSpecialSupply");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyNotEqualTo(Integer value) {
            addCriterion("free_special_supply <>", value, "freeSpecialSupply");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyGreaterThan(Integer value) {
            addCriterion("free_special_supply >", value, "freeSpecialSupply");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyGreaterThanOrEqualTo(Integer value) {
            addCriterion("free_special_supply >=", value, "freeSpecialSupply");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyLessThan(Integer value) {
            addCriterion("free_special_supply <", value, "freeSpecialSupply");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyLessThanOrEqualTo(Integer value) {
            addCriterion("free_special_supply <=", value, "freeSpecialSupply");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyIn(List<Integer> values) {
            addCriterion("free_special_supply in", values, "freeSpecialSupply");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyNotIn(List<Integer> values) {
            addCriterion("free_special_supply not in", values, "freeSpecialSupply");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyBetween(Integer value1, Integer value2) {
            addCriterion("free_special_supply between", value1, value2, "freeSpecialSupply");
            return (Criteria) this;
        }

        public Criteria andFreeSpecialSupplyNotBetween(Integer value1, Integer value2) {
            addCriterion("free_special_supply not between", value1, value2, "freeSpecialSupply");
            return (Criteria) this;
        }

        public Criteria andJdPriceIsNull() {
            addCriterion("JD_price is null");
            return (Criteria) this;
        }

        public Criteria andJdPriceIsNotNull() {
            addCriterion("JD_price is not null");
            return (Criteria) this;
        }

        public Criteria andJdPriceEqualTo(Long value) {
            addCriterion("JD_price =", value, "jdPrice");
            return (Criteria) this;
        }

        public Criteria andJdPriceNotEqualTo(Long value) {
            addCriterion("JD_price <>", value, "jdPrice");
            return (Criteria) this;
        }

        public Criteria andJdPriceGreaterThan(Long value) {
            addCriterion("JD_price >", value, "jdPrice");
            return (Criteria) this;
        }

        public Criteria andJdPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("JD_price >=", value, "jdPrice");
            return (Criteria) this;
        }

        public Criteria andJdPriceLessThan(Long value) {
            addCriterion("JD_price <", value, "jdPrice");
            return (Criteria) this;
        }

        public Criteria andJdPriceLessThanOrEqualTo(Long value) {
            addCriterion("JD_price <=", value, "jdPrice");
            return (Criteria) this;
        }

        public Criteria andJdPriceIn(List<Long> values) {
            addCriterion("JD_price in", values, "jdPrice");
            return (Criteria) this;
        }

        public Criteria andJdPriceNotIn(List<Long> values) {
            addCriterion("JD_price not in", values, "jdPrice");
            return (Criteria) this;
        }

        public Criteria andJdPriceBetween(Long value1, Long value2) {
            addCriterion("JD_price between", value1, value2, "jdPrice");
            return (Criteria) this;
        }

        public Criteria andJdPriceNotBetween(Long value1, Long value2) {
            addCriterion("JD_price not between", value1, value2, "jdPrice");
            return (Criteria) this;
        }

        public Criteria andTmPriceIsNull() {
            addCriterion("TM_price is null");
            return (Criteria) this;
        }

        public Criteria andTmPriceIsNotNull() {
            addCriterion("TM_price is not null");
            return (Criteria) this;
        }

        public Criteria andTmPriceEqualTo(Long value) {
            addCriterion("TM_price =", value, "tmPrice");
            return (Criteria) this;
        }

        public Criteria andTmPriceNotEqualTo(Long value) {
            addCriterion("TM_price <>", value, "tmPrice");
            return (Criteria) this;
        }

        public Criteria andTmPriceGreaterThan(Long value) {
            addCriterion("TM_price >", value, "tmPrice");
            return (Criteria) this;
        }

        public Criteria andTmPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("TM_price >=", value, "tmPrice");
            return (Criteria) this;
        }

        public Criteria andTmPriceLessThan(Long value) {
            addCriterion("TM_price <", value, "tmPrice");
            return (Criteria) this;
        }

        public Criteria andTmPriceLessThanOrEqualTo(Long value) {
            addCriterion("TM_price <=", value, "tmPrice");
            return (Criteria) this;
        }

        public Criteria andTmPriceIn(List<Long> values) {
            addCriterion("TM_price in", values, "tmPrice");
            return (Criteria) this;
        }

        public Criteria andTmPriceNotIn(List<Long> values) {
            addCriterion("TM_price not in", values, "tmPrice");
            return (Criteria) this;
        }

        public Criteria andTmPriceBetween(Long value1, Long value2) {
            addCriterion("TM_price between", value1, value2, "tmPrice");
            return (Criteria) this;
        }

        public Criteria andTmPriceNotBetween(Long value1, Long value2) {
            addCriterion("TM_price not between", value1, value2, "tmPrice");
            return (Criteria) this;
        }

        public Criteria andCsPriceIsNull() {
            addCriterion("CS_price is null");
            return (Criteria) this;
        }

        public Criteria andCsPriceIsNotNull() {
            addCriterion("CS_price is not null");
            return (Criteria) this;
        }

        public Criteria andCsPriceEqualTo(Long value) {
            addCriterion("CS_price =", value, "csPrice");
            return (Criteria) this;
        }

        public Criteria andCsPriceNotEqualTo(Long value) {
            addCriterion("CS_price <>", value, "csPrice");
            return (Criteria) this;
        }

        public Criteria andCsPriceGreaterThan(Long value) {
            addCriterion("CS_price >", value, "csPrice");
            return (Criteria) this;
        }

        public Criteria andCsPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("CS_price >=", value, "csPrice");
            return (Criteria) this;
        }

        public Criteria andCsPriceLessThan(Long value) {
            addCriterion("CS_price <", value, "csPrice");
            return (Criteria) this;
        }

        public Criteria andCsPriceLessThanOrEqualTo(Long value) {
            addCriterion("CS_price <=", value, "csPrice");
            return (Criteria) this;
        }

        public Criteria andCsPriceIn(List<Long> values) {
            addCriterion("CS_price in", values, "csPrice");
            return (Criteria) this;
        }

        public Criteria andCsPriceNotIn(List<Long> values) {
            addCriterion("CS_price not in", values, "csPrice");
            return (Criteria) this;
        }

        public Criteria andCsPriceBetween(Long value1, Long value2) {
            addCriterion("CS_price between", value1, value2, "csPrice");
            return (Criteria) this;
        }

        public Criteria andCsPriceNotBetween(Long value1, Long value2) {
            addCriterion("CS_price not between", value1, value2, "csPrice");
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

        public Criteria andPropertyIsNull() {
            addCriterion("property is null");
            return (Criteria) this;
        }

        public Criteria andPropertyIsNotNull() {
            addCriterion("property is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyEqualTo(Integer value) {
            addCriterion("property =", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNotEqualTo(Integer value) {
            addCriterion("property <>", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyGreaterThan(Integer value) {
            addCriterion("property >", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyGreaterThanOrEqualTo(Integer value) {
            addCriterion("property >=", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyLessThan(Integer value) {
            addCriterion("property <", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyLessThanOrEqualTo(Integer value) {
            addCriterion("property <=", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyIn(List<Integer> values) {
            addCriterion("property in", values, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNotIn(List<Integer> values) {
            addCriterion("property not in", values, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyBetween(Integer value1, Integer value2) {
            addCriterion("property between", value1, value2, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNotBetween(Integer value1, Integer value2) {
            addCriterion("property not between", value1, value2, "property");
            return (Criteria) this;
        }

        public Criteria andKaNumIsNull() {
            addCriterion("ka_num is null");
            return (Criteria) this;
        }

        public Criteria andKaNumIsNotNull() {
            addCriterion("ka_num is not null");
            return (Criteria) this;
        }

        public Criteria andKaNumEqualTo(Integer value) {
            addCriterion("ka_num =", value, "kaNum");
            return (Criteria) this;
        }

        public Criteria andKaNumNotEqualTo(Integer value) {
            addCriterion("ka_num <>", value, "kaNum");
            return (Criteria) this;
        }

        public Criteria andKaNumGreaterThan(Integer value) {
            addCriterion("ka_num >", value, "kaNum");
            return (Criteria) this;
        }

        public Criteria andKaNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ka_num >=", value, "kaNum");
            return (Criteria) this;
        }

        public Criteria andKaNumLessThan(Integer value) {
            addCriterion("ka_num <", value, "kaNum");
            return (Criteria) this;
        }

        public Criteria andKaNumLessThanOrEqualTo(Integer value) {
            addCriterion("ka_num <=", value, "kaNum");
            return (Criteria) this;
        }

        public Criteria andKaNumIn(List<Integer> values) {
            addCriterion("ka_num in", values, "kaNum");
            return (Criteria) this;
        }

        public Criteria andKaNumNotIn(List<Integer> values) {
            addCriterion("ka_num not in", values, "kaNum");
            return (Criteria) this;
        }

        public Criteria andKaNumBetween(Integer value1, Integer value2) {
            addCriterion("ka_num between", value1, value2, "kaNum");
            return (Criteria) this;
        }

        public Criteria andKaNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ka_num not between", value1, value2, "kaNum");
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