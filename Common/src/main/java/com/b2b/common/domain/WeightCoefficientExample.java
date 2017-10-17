package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeightCoefficientExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WeightCoefficientExample() {
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

        public Criteria andWeightIdIsNull() {
            addCriterion("weight_id is null");
            return (Criteria) this;
        }

        public Criteria andWeightIdIsNotNull() {
            addCriterion("weight_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeightIdEqualTo(Integer value) {
            addCriterion("weight_id =", value, "weightId");
            return (Criteria) this;
        }

        public Criteria andWeightIdNotEqualTo(Integer value) {
            addCriterion("weight_id <>", value, "weightId");
            return (Criteria) this;
        }

        public Criteria andWeightIdGreaterThan(Integer value) {
            addCriterion("weight_id >", value, "weightId");
            return (Criteria) this;
        }

        public Criteria andWeightIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight_id >=", value, "weightId");
            return (Criteria) this;
        }

        public Criteria andWeightIdLessThan(Integer value) {
            addCriterion("weight_id <", value, "weightId");
            return (Criteria) this;
        }

        public Criteria andWeightIdLessThanOrEqualTo(Integer value) {
            addCriterion("weight_id <=", value, "weightId");
            return (Criteria) this;
        }

        public Criteria andWeightIdIn(List<Integer> values) {
            addCriterion("weight_id in", values, "weightId");
            return (Criteria) this;
        }

        public Criteria andWeightIdNotIn(List<Integer> values) {
            addCriterion("weight_id not in", values, "weightId");
            return (Criteria) this;
        }

        public Criteria andWeightIdBetween(Integer value1, Integer value2) {
            addCriterion("weight_id between", value1, value2, "weightId");
            return (Criteria) this;
        }

        public Criteria andWeightIdNotBetween(Integer value1, Integer value2) {
            addCriterion("weight_id not between", value1, value2, "weightId");
            return (Criteria) this;
        }

        public Criteria andNewitemDayIsNull() {
            addCriterion("newitem_day is null");
            return (Criteria) this;
        }

        public Criteria andNewitemDayIsNotNull() {
            addCriterion("newitem_day is not null");
            return (Criteria) this;
        }

        public Criteria andNewitemDayEqualTo(Integer value) {
            addCriterion("newitem_day =", value, "newitemDay");
            return (Criteria) this;
        }

        public Criteria andNewitemDayNotEqualTo(Integer value) {
            addCriterion("newitem_day <>", value, "newitemDay");
            return (Criteria) this;
        }

        public Criteria andNewitemDayGreaterThan(Integer value) {
            addCriterion("newitem_day >", value, "newitemDay");
            return (Criteria) this;
        }

        public Criteria andNewitemDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("newitem_day >=", value, "newitemDay");
            return (Criteria) this;
        }

        public Criteria andNewitemDayLessThan(Integer value) {
            addCriterion("newitem_day <", value, "newitemDay");
            return (Criteria) this;
        }

        public Criteria andNewitemDayLessThanOrEqualTo(Integer value) {
            addCriterion("newitem_day <=", value, "newitemDay");
            return (Criteria) this;
        }

        public Criteria andNewitemDayIn(List<Integer> values) {
            addCriterion("newitem_day in", values, "newitemDay");
            return (Criteria) this;
        }

        public Criteria andNewitemDayNotIn(List<Integer> values) {
            addCriterion("newitem_day not in", values, "newitemDay");
            return (Criteria) this;
        }

        public Criteria andNewitemDayBetween(Integer value1, Integer value2) {
            addCriterion("newitem_day between", value1, value2, "newitemDay");
            return (Criteria) this;
        }

        public Criteria andNewitemDayNotBetween(Integer value1, Integer value2) {
            addCriterion("newitem_day not between", value1, value2, "newitemDay");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightIsNull() {
            addCriterion("newitem_weight is null");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightIsNotNull() {
            addCriterion("newitem_weight is not null");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightEqualTo(Integer value) {
            addCriterion("newitem_weight =", value, "newitemWeight");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightNotEqualTo(Integer value) {
            addCriterion("newitem_weight <>", value, "newitemWeight");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightGreaterThan(Integer value) {
            addCriterion("newitem_weight >", value, "newitemWeight");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("newitem_weight >=", value, "newitemWeight");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightLessThan(Integer value) {
            addCriterion("newitem_weight <", value, "newitemWeight");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightLessThanOrEqualTo(Integer value) {
            addCriterion("newitem_weight <=", value, "newitemWeight");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightIn(List<Integer> values) {
            addCriterion("newitem_weight in", values, "newitemWeight");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightNotIn(List<Integer> values) {
            addCriterion("newitem_weight not in", values, "newitemWeight");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightBetween(Integer value1, Integer value2) {
            addCriterion("newitem_weight between", value1, value2, "newitemWeight");
            return (Criteria) this;
        }

        public Criteria andNewitemWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("newitem_weight not between", value1, value2, "newitemWeight");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffIsNull() {
            addCriterion("newitemweight_coeff is null");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffIsNotNull() {
            addCriterion("newitemweight_coeff is not null");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffEqualTo(BigDecimal value) {
            addCriterion("newitemweight_coeff =", value, "newitemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffNotEqualTo(BigDecimal value) {
            addCriterion("newitemweight_coeff <>", value, "newitemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffGreaterThan(BigDecimal value) {
            addCriterion("newitemweight_coeff >", value, "newitemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("newitemweight_coeff >=", value, "newitemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffLessThan(BigDecimal value) {
            addCriterion("newitemweight_coeff <", value, "newitemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffLessThanOrEqualTo(BigDecimal value) {
            addCriterion("newitemweight_coeff <=", value, "newitemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffIn(List<BigDecimal> values) {
            addCriterion("newitemweight_coeff in", values, "newitemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffNotIn(List<BigDecimal> values) {
            addCriterion("newitemweight_coeff not in", values, "newitemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("newitemweight_coeff between", value1, value2, "newitemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andNewitemweightCoeffNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("newitemweight_coeff not between", value1, value2, "newitemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andProfitWeightIsNull() {
            addCriterion("profit_weight is null");
            return (Criteria) this;
        }

        public Criteria andProfitWeightIsNotNull() {
            addCriterion("profit_weight is not null");
            return (Criteria) this;
        }

        public Criteria andProfitWeightEqualTo(Integer value) {
            addCriterion("profit_weight =", value, "profitWeight");
            return (Criteria) this;
        }

        public Criteria andProfitWeightNotEqualTo(Integer value) {
            addCriterion("profit_weight <>", value, "profitWeight");
            return (Criteria) this;
        }

        public Criteria andProfitWeightGreaterThan(Integer value) {
            addCriterion("profit_weight >", value, "profitWeight");
            return (Criteria) this;
        }

        public Criteria andProfitWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("profit_weight >=", value, "profitWeight");
            return (Criteria) this;
        }

        public Criteria andProfitWeightLessThan(Integer value) {
            addCriterion("profit_weight <", value, "profitWeight");
            return (Criteria) this;
        }

        public Criteria andProfitWeightLessThanOrEqualTo(Integer value) {
            addCriterion("profit_weight <=", value, "profitWeight");
            return (Criteria) this;
        }

        public Criteria andProfitWeightIn(List<Integer> values) {
            addCriterion("profit_weight in", values, "profitWeight");
            return (Criteria) this;
        }

        public Criteria andProfitWeightNotIn(List<Integer> values) {
            addCriterion("profit_weight not in", values, "profitWeight");
            return (Criteria) this;
        }

        public Criteria andProfitWeightBetween(Integer value1, Integer value2) {
            addCriterion("profit_weight between", value1, value2, "profitWeight");
            return (Criteria) this;
        }

        public Criteria andProfitWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("profit_weight not between", value1, value2, "profitWeight");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffIsNull() {
            addCriterion("profitweight_coeff is null");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffIsNotNull() {
            addCriterion("profitweight_coeff is not null");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffEqualTo(BigDecimal value) {
            addCriterion("profitweight_coeff =", value, "profitweightCoeff");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffNotEqualTo(BigDecimal value) {
            addCriterion("profitweight_coeff <>", value, "profitweightCoeff");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffGreaterThan(BigDecimal value) {
            addCriterion("profitweight_coeff >", value, "profitweightCoeff");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("profitweight_coeff >=", value, "profitweightCoeff");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffLessThan(BigDecimal value) {
            addCriterion("profitweight_coeff <", value, "profitweightCoeff");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffLessThanOrEqualTo(BigDecimal value) {
            addCriterion("profitweight_coeff <=", value, "profitweightCoeff");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffIn(List<BigDecimal> values) {
            addCriterion("profitweight_coeff in", values, "profitweightCoeff");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffNotIn(List<BigDecimal> values) {
            addCriterion("profitweight_coeff not in", values, "profitweightCoeff");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profitweight_coeff between", value1, value2, "profitweightCoeff");
            return (Criteria) this;
        }

        public Criteria andProfitweightCoeffNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profitweight_coeff not between", value1, value2, "profitweightCoeff");
            return (Criteria) this;
        }

        public Criteria andItemWeightsIsNull() {
            addCriterion("item_weights is null");
            return (Criteria) this;
        }

        public Criteria andItemWeightsIsNotNull() {
            addCriterion("item_weights is not null");
            return (Criteria) this;
        }

        public Criteria andItemWeightsEqualTo(Integer value) {
            addCriterion("item_weights =", value, "itemWeights");
            return (Criteria) this;
        }

        public Criteria andItemWeightsNotEqualTo(Integer value) {
            addCriterion("item_weights <>", value, "itemWeights");
            return (Criteria) this;
        }

        public Criteria andItemWeightsGreaterThan(Integer value) {
            addCriterion("item_weights >", value, "itemWeights");
            return (Criteria) this;
        }

        public Criteria andItemWeightsGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_weights >=", value, "itemWeights");
            return (Criteria) this;
        }

        public Criteria andItemWeightsLessThan(Integer value) {
            addCriterion("item_weights <", value, "itemWeights");
            return (Criteria) this;
        }

        public Criteria andItemWeightsLessThanOrEqualTo(Integer value) {
            addCriterion("item_weights <=", value, "itemWeights");
            return (Criteria) this;
        }

        public Criteria andItemWeightsIn(List<Integer> values) {
            addCriterion("item_weights in", values, "itemWeights");
            return (Criteria) this;
        }

        public Criteria andItemWeightsNotIn(List<Integer> values) {
            addCriterion("item_weights not in", values, "itemWeights");
            return (Criteria) this;
        }

        public Criteria andItemWeightsBetween(Integer value1, Integer value2) {
            addCriterion("item_weights between", value1, value2, "itemWeights");
            return (Criteria) this;
        }

        public Criteria andItemWeightsNotBetween(Integer value1, Integer value2) {
            addCriterion("item_weights not between", value1, value2, "itemWeights");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffIsNull() {
            addCriterion("itemweight_coeff is null");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffIsNotNull() {
            addCriterion("itemweight_coeff is not null");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffEqualTo(BigDecimal value) {
            addCriterion("itemweight_coeff =", value, "itemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffNotEqualTo(BigDecimal value) {
            addCriterion("itemweight_coeff <>", value, "itemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffGreaterThan(BigDecimal value) {
            addCriterion("itemweight_coeff >", value, "itemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("itemweight_coeff >=", value, "itemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffLessThan(BigDecimal value) {
            addCriterion("itemweight_coeff <", value, "itemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffLessThanOrEqualTo(BigDecimal value) {
            addCriterion("itemweight_coeff <=", value, "itemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffIn(List<BigDecimal> values) {
            addCriterion("itemweight_coeff in", values, "itemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffNotIn(List<BigDecimal> values) {
            addCriterion("itemweight_coeff not in", values, "itemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("itemweight_coeff between", value1, value2, "itemweightCoeff");
            return (Criteria) this;
        }

        public Criteria andItemweightCoeffNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("itemweight_coeff not between", value1, value2, "itemweightCoeff");
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