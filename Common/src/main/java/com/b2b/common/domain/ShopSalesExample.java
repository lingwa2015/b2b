package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ShopSalesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopSalesExample() {
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

        public Criteria andPayBillWayIsNull() {
            addCriterion("pay_bill_way is null");
            return (Criteria) this;
        }

        public Criteria andPayBillWayIsNotNull() {
            addCriterion("pay_bill_way is not null");
            return (Criteria) this;
        }

        public Criteria andPayBillWayEqualTo(Integer value) {
            addCriterion("pay_bill_way =", value, "payBillWay");
            return (Criteria) this;
        }

        public Criteria andPayBillWayNotEqualTo(Integer value) {
            addCriterion("pay_bill_way <>", value, "payBillWay");
            return (Criteria) this;
        }

        public Criteria andPayBillWayGreaterThan(Integer value) {
            addCriterion("pay_bill_way >", value, "payBillWay");
            return (Criteria) this;
        }

        public Criteria andPayBillWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_bill_way >=", value, "payBillWay");
            return (Criteria) this;
        }

        public Criteria andPayBillWayLessThan(Integer value) {
            addCriterion("pay_bill_way <", value, "payBillWay");
            return (Criteria) this;
        }

        public Criteria andPayBillWayLessThanOrEqualTo(Integer value) {
            addCriterion("pay_bill_way <=", value, "payBillWay");
            return (Criteria) this;
        }

        public Criteria andPayBillWayIn(List<Integer> values) {
            addCriterion("pay_bill_way in", values, "payBillWay");
            return (Criteria) this;
        }

        public Criteria andPayBillWayNotIn(List<Integer> values) {
            addCriterion("pay_bill_way not in", values, "payBillWay");
            return (Criteria) this;
        }

        public Criteria andPayBillWayBetween(Integer value1, Integer value2) {
            addCriterion("pay_bill_way between", value1, value2, "payBillWay");
            return (Criteria) this;
        }

        public Criteria andPayBillWayNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_bill_way not between", value1, value2, "payBillWay");
            return (Criteria) this;
        }

        public Criteria andContractDateIsNull() {
            addCriterion("contract_date is null");
            return (Criteria) this;
        }

        public Criteria andContractDateIsNotNull() {
            addCriterion("contract_date is not null");
            return (Criteria) this;
        }

        public Criteria andContractDateEqualTo(Date value) {
            addCriterion("contract_date =", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotEqualTo(Date value) {
            addCriterion("contract_date <>", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateGreaterThan(Date value) {
            addCriterion("contract_date >", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateGreaterThanOrEqualTo(Date value) {
            addCriterion("contract_date >=", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateLessThan(Date value) {
            addCriterion("contract_date <", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateLessThanOrEqualTo(Date value) {
            addCriterion("contract_date <=", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateIn(List<Date> values) {
            addCriterion("contract_date in", values, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotIn(List<Date> values) {
            addCriterion("contract_date not in", values, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateBetween(Date value1, Date value2) {
            addCriterion("contract_date between", value1, value2, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotBetween(Date value1, Date value2) {
            addCriterion("contract_date not between", value1, value2, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractNumberIsNull() {
            addCriterion("contract_number is null");
            return (Criteria) this;
        }

        public Criteria andContractNumberIsNotNull() {
            addCriterion("contract_number is not null");
            return (Criteria) this;
        }

        public Criteria andContractNumberEqualTo(String value) {
            addCriterion("contract_number =", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotEqualTo(String value) {
            addCriterion("contract_number <>", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberGreaterThan(String value) {
            addCriterion("contract_number >", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberGreaterThanOrEqualTo(String value) {
            addCriterion("contract_number >=", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberLessThan(String value) {
            addCriterion("contract_number <", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberLessThanOrEqualTo(String value) {
            addCriterion("contract_number <=", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberLike(String value) {
            addCriterion("contract_number like", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotLike(String value) {
            addCriterion("contract_number not like", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberIn(List<String> values) {
            addCriterion("contract_number in", values, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotIn(List<String> values) {
            addCriterion("contract_number not in", values, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberBetween(String value1, String value2) {
            addCriterion("contract_number between", value1, value2, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotBetween(String value1, String value2) {
            addCriterion("contract_number not between", value1, value2, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andSumBaseIsNull() {
            addCriterion("sum_base is null");
            return (Criteria) this;
        }

        public Criteria andSumBaseIsNotNull() {
            addCriterion("sum_base is not null");
            return (Criteria) this;
        }

        public Criteria andSumBaseEqualTo(Long value) {
            addCriterion("sum_base =", value, "sumBase");
            return (Criteria) this;
        }

        public Criteria andSumBaseNotEqualTo(Long value) {
            addCriterion("sum_base <>", value, "sumBase");
            return (Criteria) this;
        }

        public Criteria andSumBaseGreaterThan(Long value) {
            addCriterion("sum_base >", value, "sumBase");
            return (Criteria) this;
        }

        public Criteria andSumBaseGreaterThanOrEqualTo(Long value) {
            addCriterion("sum_base >=", value, "sumBase");
            return (Criteria) this;
        }

        public Criteria andSumBaseLessThan(Long value) {
            addCriterion("sum_base <", value, "sumBase");
            return (Criteria) this;
        }

        public Criteria andSumBaseLessThanOrEqualTo(Long value) {
            addCriterion("sum_base <=", value, "sumBase");
            return (Criteria) this;
        }

        public Criteria andSumBaseIn(List<Long> values) {
            addCriterion("sum_base in", values, "sumBase");
            return (Criteria) this;
        }

        public Criteria andSumBaseNotIn(List<Long> values) {
            addCriterion("sum_base not in", values, "sumBase");
            return (Criteria) this;
        }

        public Criteria andSumBaseBetween(Long value1, Long value2) {
            addCriterion("sum_base between", value1, value2, "sumBase");
            return (Criteria) this;
        }

        public Criteria andSumBaseNotBetween(Long value1, Long value2) {
            addCriterion("sum_base not between", value1, value2, "sumBase");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientIsNull() {
            addCriterion("royalty_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientIsNotNull() {
            addCriterion("royalty_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientEqualTo(BigDecimal value) {
            addCriterion("royalty_coefficient =", value, "royaltyCoefficient");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientNotEqualTo(BigDecimal value) {
            addCriterion("royalty_coefficient <>", value, "royaltyCoefficient");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientGreaterThan(BigDecimal value) {
            addCriterion("royalty_coefficient >", value, "royaltyCoefficient");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("royalty_coefficient >=", value, "royaltyCoefficient");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientLessThan(BigDecimal value) {
            addCriterion("royalty_coefficient <", value, "royaltyCoefficient");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientLessThanOrEqualTo(BigDecimal value) {
            addCriterion("royalty_coefficient <=", value, "royaltyCoefficient");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientIn(List<BigDecimal> values) {
            addCriterion("royalty_coefficient in", values, "royaltyCoefficient");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientNotIn(List<BigDecimal> values) {
            addCriterion("royalty_coefficient not in", values, "royaltyCoefficient");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("royalty_coefficient between", value1, value2, "royaltyCoefficient");
            return (Criteria) this;
        }

        public Criteria andRoyaltyCoefficientNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("royalty_coefficient not between", value1, value2, "royaltyCoefficient");
            return (Criteria) this;
        }

        public Criteria andSumProportionIsNull() {
            addCriterion("sum_proportion is null");
            return (Criteria) this;
        }

        public Criteria andSumProportionIsNotNull() {
            addCriterion("sum_proportion is not null");
            return (Criteria) this;
        }

        public Criteria andSumProportionEqualTo(BigDecimal value) {
            addCriterion("sum_proportion =", value, "sumProportion");
            return (Criteria) this;
        }

        public Criteria andSumProportionNotEqualTo(BigDecimal value) {
            addCriterion("sum_proportion <>", value, "sumProportion");
            return (Criteria) this;
        }

        public Criteria andSumProportionGreaterThan(BigDecimal value) {
            addCriterion("sum_proportion >", value, "sumProportion");
            return (Criteria) this;
        }

        public Criteria andSumProportionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_proportion >=", value, "sumProportion");
            return (Criteria) this;
        }

        public Criteria andSumProportionLessThan(BigDecimal value) {
            addCriterion("sum_proportion <", value, "sumProportion");
            return (Criteria) this;
        }

        public Criteria andSumProportionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_proportion <=", value, "sumProportion");
            return (Criteria) this;
        }

        public Criteria andSumProportionIn(List<BigDecimal> values) {
            addCriterion("sum_proportion in", values, "sumProportion");
            return (Criteria) this;
        }

        public Criteria andSumProportionNotIn(List<BigDecimal> values) {
            addCriterion("sum_proportion not in", values, "sumProportion");
            return (Criteria) this;
        }

        public Criteria andSumProportionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_proportion between", value1, value2, "sumProportion");
            return (Criteria) this;
        }

        public Criteria andSumProportionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_proportion not between", value1, value2, "sumProportion");
            return (Criteria) this;
        }

        public Criteria andSumFeeIsNull() {
            addCriterion("sum_fee is null");
            return (Criteria) this;
        }

        public Criteria andSumFeeIsNotNull() {
            addCriterion("sum_fee is not null");
            return (Criteria) this;
        }

        public Criteria andSumFeeEqualTo(Long value) {
            addCriterion("sum_fee =", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeNotEqualTo(Long value) {
            addCriterion("sum_fee <>", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeGreaterThan(Long value) {
            addCriterion("sum_fee >", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("sum_fee >=", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeLessThan(Long value) {
            addCriterion("sum_fee <", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeLessThanOrEqualTo(Long value) {
            addCriterion("sum_fee <=", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeIn(List<Long> values) {
            addCriterion("sum_fee in", values, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeNotIn(List<Long> values) {
            addCriterion("sum_fee not in", values, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeBetween(Long value1, Long value2) {
            addCriterion("sum_fee between", value1, value2, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeNotBetween(Long value1, Long value2) {
            addCriterion("sum_fee not between", value1, value2, "sumFee");
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