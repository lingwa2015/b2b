package com.b2b.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerWiseExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerWiseExample() {
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

        public Criteria andWiseIdIsNull() {
            addCriterion("wise_id is null");
            return (Criteria) this;
        }

        public Criteria andWiseIdIsNotNull() {
            addCriterion("wise_id is not null");
            return (Criteria) this;
        }

        public Criteria andWiseIdEqualTo(Integer value) {
            addCriterion("wise_id =", value, "wiseId");
            return (Criteria) this;
        }

        public Criteria andWiseIdNotEqualTo(Integer value) {
            addCriterion("wise_id <>", value, "wiseId");
            return (Criteria) this;
        }

        public Criteria andWiseIdGreaterThan(Integer value) {
            addCriterion("wise_id >", value, "wiseId");
            return (Criteria) this;
        }

        public Criteria andWiseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wise_id >=", value, "wiseId");
            return (Criteria) this;
        }

        public Criteria andWiseIdLessThan(Integer value) {
            addCriterion("wise_id <", value, "wiseId");
            return (Criteria) this;
        }

        public Criteria andWiseIdLessThanOrEqualTo(Integer value) {
            addCriterion("wise_id <=", value, "wiseId");
            return (Criteria) this;
        }

        public Criteria andWiseIdIn(List<Integer> values) {
            addCriterion("wise_id in", values, "wiseId");
            return (Criteria) this;
        }

        public Criteria andWiseIdNotIn(List<Integer> values) {
            addCriterion("wise_id not in", values, "wiseId");
            return (Criteria) this;
        }

        public Criteria andWiseIdBetween(Integer value1, Integer value2) {
            addCriterion("wise_id between", value1, value2, "wiseId");
            return (Criteria) this;
        }

        public Criteria andWiseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wise_id not between", value1, value2, "wiseId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andBudgetIsNull() {
            addCriterion("budget is null");
            return (Criteria) this;
        }

        public Criteria andBudgetIsNotNull() {
            addCriterion("budget is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetEqualTo(Long value) {
            addCriterion("budget =", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetNotEqualTo(Long value) {
            addCriterion("budget <>", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetGreaterThan(Long value) {
            addCriterion("budget >", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetGreaterThanOrEqualTo(Long value) {
            addCriterion("budget >=", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetLessThan(Long value) {
            addCriterion("budget <", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetLessThanOrEqualTo(Long value) {
            addCriterion("budget <=", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetIn(List<Long> values) {
            addCriterion("budget in", values, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetNotIn(List<Long> values) {
            addCriterion("budget not in", values, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetBetween(Long value1, Long value2) {
            addCriterion("budget between", value1, value2, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetNotBetween(Long value1, Long value2) {
            addCriterion("budget not between", value1, value2, "budget");
            return (Criteria) this;
        }

        public Criteria andStartpriceIsNull() {
            addCriterion("startprice is null");
            return (Criteria) this;
        }

        public Criteria andStartpriceIsNotNull() {
            addCriterion("startprice is not null");
            return (Criteria) this;
        }

        public Criteria andStartpriceEqualTo(Integer value) {
            addCriterion("startprice =", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceNotEqualTo(Integer value) {
            addCriterion("startprice <>", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceGreaterThan(Integer value) {
            addCriterion("startprice >", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("startprice >=", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceLessThan(Integer value) {
            addCriterion("startprice <", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceLessThanOrEqualTo(Integer value) {
            addCriterion("startprice <=", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceIn(List<Integer> values) {
            addCriterion("startprice in", values, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceNotIn(List<Integer> values) {
            addCriterion("startprice not in", values, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceBetween(Integer value1, Integer value2) {
            addCriterion("startprice between", value1, value2, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceNotBetween(Integer value1, Integer value2) {
            addCriterion("startprice not between", value1, value2, "startprice");
            return (Criteria) this;
        }

        public Criteria andEndpriceIsNull() {
            addCriterion("endprice is null");
            return (Criteria) this;
        }

        public Criteria andEndpriceIsNotNull() {
            addCriterion("endprice is not null");
            return (Criteria) this;
        }

        public Criteria andEndpriceEqualTo(Integer value) {
            addCriterion("endprice =", value, "endprice");
            return (Criteria) this;
        }

        public Criteria andEndpriceNotEqualTo(Integer value) {
            addCriterion("endprice <>", value, "endprice");
            return (Criteria) this;
        }

        public Criteria andEndpriceGreaterThan(Integer value) {
            addCriterion("endprice >", value, "endprice");
            return (Criteria) this;
        }

        public Criteria andEndpriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("endprice >=", value, "endprice");
            return (Criteria) this;
        }

        public Criteria andEndpriceLessThan(Integer value) {
            addCriterion("endprice <", value, "endprice");
            return (Criteria) this;
        }

        public Criteria andEndpriceLessThanOrEqualTo(Integer value) {
            addCriterion("endprice <=", value, "endprice");
            return (Criteria) this;
        }

        public Criteria andEndpriceIn(List<Integer> values) {
            addCriterion("endprice in", values, "endprice");
            return (Criteria) this;
        }

        public Criteria andEndpriceNotIn(List<Integer> values) {
            addCriterion("endprice not in", values, "endprice");
            return (Criteria) this;
        }

        public Criteria andEndpriceBetween(Integer value1, Integer value2) {
            addCriterion("endprice between", value1, value2, "endprice");
            return (Criteria) this;
        }

        public Criteria andEndpriceNotBetween(Integer value1, Integer value2) {
            addCriterion("endprice not between", value1, value2, "endprice");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetIsNull() {
            addCriterion("issuper_budget is null");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetIsNotNull() {
            addCriterion("issuper_budget is not null");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetEqualTo(Integer value) {
            addCriterion("issuper_budget =", value, "issuperBudget");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetNotEqualTo(Integer value) {
            addCriterion("issuper_budget <>", value, "issuperBudget");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetGreaterThan(Integer value) {
            addCriterion("issuper_budget >", value, "issuperBudget");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetGreaterThanOrEqualTo(Integer value) {
            addCriterion("issuper_budget >=", value, "issuperBudget");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetLessThan(Integer value) {
            addCriterion("issuper_budget <", value, "issuperBudget");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetLessThanOrEqualTo(Integer value) {
            addCriterion("issuper_budget <=", value, "issuperBudget");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetIn(List<Integer> values) {
            addCriterion("issuper_budget in", values, "issuperBudget");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetNotIn(List<Integer> values) {
            addCriterion("issuper_budget not in", values, "issuperBudget");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetBetween(Integer value1, Integer value2) {
            addCriterion("issuper_budget between", value1, value2, "issuperBudget");
            return (Criteria) this;
        }

        public Criteria andIssuperBudgetNotBetween(Integer value1, Integer value2) {
            addCriterion("issuper_budget not between", value1, value2, "issuperBudget");
            return (Criteria) this;
        }

        public Criteria andMondayIsNull() {
            addCriterion("monday is null");
            return (Criteria) this;
        }

        public Criteria andMondayIsNotNull() {
            addCriterion("monday is not null");
            return (Criteria) this;
        }

        public Criteria andMondayEqualTo(Integer value) {
            addCriterion("monday =", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayNotEqualTo(Integer value) {
            addCriterion("monday <>", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayGreaterThan(Integer value) {
            addCriterion("monday >", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayGreaterThanOrEqualTo(Integer value) {
            addCriterion("monday >=", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayLessThan(Integer value) {
            addCriterion("monday <", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayLessThanOrEqualTo(Integer value) {
            addCriterion("monday <=", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayIn(List<Integer> values) {
            addCriterion("monday in", values, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayNotIn(List<Integer> values) {
            addCriterion("monday not in", values, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayBetween(Integer value1, Integer value2) {
            addCriterion("monday between", value1, value2, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayNotBetween(Integer value1, Integer value2) {
            addCriterion("monday not between", value1, value2, "monday");
            return (Criteria) this;
        }

        public Criteria andTuesdayIsNull() {
            addCriterion("tuesday is null");
            return (Criteria) this;
        }

        public Criteria andTuesdayIsNotNull() {
            addCriterion("tuesday is not null");
            return (Criteria) this;
        }

        public Criteria andTuesdayEqualTo(Integer value) {
            addCriterion("tuesday =", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayNotEqualTo(Integer value) {
            addCriterion("tuesday <>", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayGreaterThan(Integer value) {
            addCriterion("tuesday >", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("tuesday >=", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayLessThan(Integer value) {
            addCriterion("tuesday <", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayLessThanOrEqualTo(Integer value) {
            addCriterion("tuesday <=", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayIn(List<Integer> values) {
            addCriterion("tuesday in", values, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayNotIn(List<Integer> values) {
            addCriterion("tuesday not in", values, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayBetween(Integer value1, Integer value2) {
            addCriterion("tuesday between", value1, value2, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayNotBetween(Integer value1, Integer value2) {
            addCriterion("tuesday not between", value1, value2, "tuesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayIsNull() {
            addCriterion("wednesday is null");
            return (Criteria) this;
        }

        public Criteria andWednesdayIsNotNull() {
            addCriterion("wednesday is not null");
            return (Criteria) this;
        }

        public Criteria andWednesdayEqualTo(Integer value) {
            addCriterion("wednesday =", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayNotEqualTo(Integer value) {
            addCriterion("wednesday <>", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayGreaterThan(Integer value) {
            addCriterion("wednesday >", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("wednesday >=", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayLessThan(Integer value) {
            addCriterion("wednesday <", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayLessThanOrEqualTo(Integer value) {
            addCriterion("wednesday <=", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayIn(List<Integer> values) {
            addCriterion("wednesday in", values, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayNotIn(List<Integer> values) {
            addCriterion("wednesday not in", values, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayBetween(Integer value1, Integer value2) {
            addCriterion("wednesday between", value1, value2, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayNotBetween(Integer value1, Integer value2) {
            addCriterion("wednesday not between", value1, value2, "wednesday");
            return (Criteria) this;
        }

        public Criteria andThursdayIsNull() {
            addCriterion("thursday is null");
            return (Criteria) this;
        }

        public Criteria andThursdayIsNotNull() {
            addCriterion("thursday is not null");
            return (Criteria) this;
        }

        public Criteria andThursdayEqualTo(Integer value) {
            addCriterion("thursday =", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayNotEqualTo(Integer value) {
            addCriterion("thursday <>", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayGreaterThan(Integer value) {
            addCriterion("thursday >", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("thursday >=", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayLessThan(Integer value) {
            addCriterion("thursday <", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayLessThanOrEqualTo(Integer value) {
            addCriterion("thursday <=", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayIn(List<Integer> values) {
            addCriterion("thursday in", values, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayNotIn(List<Integer> values) {
            addCriterion("thursday not in", values, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayBetween(Integer value1, Integer value2) {
            addCriterion("thursday between", value1, value2, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayNotBetween(Integer value1, Integer value2) {
            addCriterion("thursday not between", value1, value2, "thursday");
            return (Criteria) this;
        }

        public Criteria andFridayIsNull() {
            addCriterion("friday is null");
            return (Criteria) this;
        }

        public Criteria andFridayIsNotNull() {
            addCriterion("friday is not null");
            return (Criteria) this;
        }

        public Criteria andFridayEqualTo(Integer value) {
            addCriterion("friday =", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayNotEqualTo(Integer value) {
            addCriterion("friday <>", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayGreaterThan(Integer value) {
            addCriterion("friday >", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayGreaterThanOrEqualTo(Integer value) {
            addCriterion("friday >=", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayLessThan(Integer value) {
            addCriterion("friday <", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayLessThanOrEqualTo(Integer value) {
            addCriterion("friday <=", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayIn(List<Integer> values) {
            addCriterion("friday in", values, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayNotIn(List<Integer> values) {
            addCriterion("friday not in", values, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayBetween(Integer value1, Integer value2) {
            addCriterion("friday between", value1, value2, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayNotBetween(Integer value1, Integer value2) {
            addCriterion("friday not between", value1, value2, "friday");
            return (Criteria) this;
        }

        public Criteria andSaturdayIsNull() {
            addCriterion("saturday is null");
            return (Criteria) this;
        }

        public Criteria andSaturdayIsNotNull() {
            addCriterion("saturday is not null");
            return (Criteria) this;
        }

        public Criteria andSaturdayEqualTo(Integer value) {
            addCriterion("saturday =", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayNotEqualTo(Integer value) {
            addCriterion("saturday <>", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayGreaterThan(Integer value) {
            addCriterion("saturday >", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("saturday >=", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayLessThan(Integer value) {
            addCriterion("saturday <", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayLessThanOrEqualTo(Integer value) {
            addCriterion("saturday <=", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayIn(List<Integer> values) {
            addCriterion("saturday in", values, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayNotIn(List<Integer> values) {
            addCriterion("saturday not in", values, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayBetween(Integer value1, Integer value2) {
            addCriterion("saturday between", value1, value2, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayNotBetween(Integer value1, Integer value2) {
            addCriterion("saturday not between", value1, value2, "saturday");
            return (Criteria) this;
        }

        public Criteria andSundayIsNull() {
            addCriterion("sunday is null");
            return (Criteria) this;
        }

        public Criteria andSundayIsNotNull() {
            addCriterion("sunday is not null");
            return (Criteria) this;
        }

        public Criteria andSundayEqualTo(Integer value) {
            addCriterion("sunday =", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayNotEqualTo(Integer value) {
            addCriterion("sunday <>", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayGreaterThan(Integer value) {
            addCriterion("sunday >", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayGreaterThanOrEqualTo(Integer value) {
            addCriterion("sunday >=", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayLessThan(Integer value) {
            addCriterion("sunday <", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayLessThanOrEqualTo(Integer value) {
            addCriterion("sunday <=", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayIn(List<Integer> values) {
            addCriterion("sunday in", values, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayNotIn(List<Integer> values) {
            addCriterion("sunday not in", values, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayBetween(Integer value1, Integer value2) {
            addCriterion("sunday between", value1, value2, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayNotBetween(Integer value1, Integer value2) {
            addCriterion("sunday not between", value1, value2, "sunday");
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