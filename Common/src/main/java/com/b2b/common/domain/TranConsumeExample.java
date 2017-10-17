package com.b2b.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TranConsumeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TranConsumeExample() {
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

        public Criteria andSourcingNoIsNull() {
            addCriterion("sourcing_no is null");
            return (Criteria) this;
        }

        public Criteria andSourcingNoIsNotNull() {
            addCriterion("sourcing_no is not null");
            return (Criteria) this;
        }

        public Criteria andSourcingNoEqualTo(Long value) {
            addCriterion("sourcing_no =", value, "sourcingNo");
            return (Criteria) this;
        }

        public Criteria andSourcingNoNotEqualTo(Long value) {
            addCriterion("sourcing_no <>", value, "sourcingNo");
            return (Criteria) this;
        }

        public Criteria andSourcingNoGreaterThan(Long value) {
            addCriterion("sourcing_no >", value, "sourcingNo");
            return (Criteria) this;
        }

        public Criteria andSourcingNoGreaterThanOrEqualTo(Long value) {
            addCriterion("sourcing_no >=", value, "sourcingNo");
            return (Criteria) this;
        }

        public Criteria andSourcingNoLessThan(Long value) {
            addCriterion("sourcing_no <", value, "sourcingNo");
            return (Criteria) this;
        }

        public Criteria andSourcingNoLessThanOrEqualTo(Long value) {
            addCriterion("sourcing_no <=", value, "sourcingNo");
            return (Criteria) this;
        }

        public Criteria andSourcingNoIn(List<Long> values) {
            addCriterion("sourcing_no in", values, "sourcingNo");
            return (Criteria) this;
        }

        public Criteria andSourcingNoNotIn(List<Long> values) {
            addCriterion("sourcing_no not in", values, "sourcingNo");
            return (Criteria) this;
        }

        public Criteria andSourcingNoBetween(Long value1, Long value2) {
            addCriterion("sourcing_no between", value1, value2, "sourcingNo");
            return (Criteria) this;
        }

        public Criteria andSourcingNoNotBetween(Long value1, Long value2) {
            addCriterion("sourcing_no not between", value1, value2, "sourcingNo");
            return (Criteria) this;
        }

        public Criteria andSourcingIsNull() {
            addCriterion("sourcing is null");
            return (Criteria) this;
        }

        public Criteria andSourcingIsNotNull() {
            addCriterion("sourcing is not null");
            return (Criteria) this;
        }

        public Criteria andSourcingEqualTo(Long value) {
            addCriterion("sourcing =", value, "sourcing");
            return (Criteria) this;
        }

        public Criteria andSourcingNotEqualTo(Long value) {
            addCriterion("sourcing <>", value, "sourcing");
            return (Criteria) this;
        }

        public Criteria andSourcingGreaterThan(Long value) {
            addCriterion("sourcing >", value, "sourcing");
            return (Criteria) this;
        }

        public Criteria andSourcingGreaterThanOrEqualTo(Long value) {
            addCriterion("sourcing >=", value, "sourcing");
            return (Criteria) this;
        }

        public Criteria andSourcingLessThan(Long value) {
            addCriterion("sourcing <", value, "sourcing");
            return (Criteria) this;
        }

        public Criteria andSourcingLessThanOrEqualTo(Long value) {
            addCriterion("sourcing <=", value, "sourcing");
            return (Criteria) this;
        }

        public Criteria andSourcingIn(List<Long> values) {
            addCriterion("sourcing in", values, "sourcing");
            return (Criteria) this;
        }

        public Criteria andSourcingNotIn(List<Long> values) {
            addCriterion("sourcing not in", values, "sourcing");
            return (Criteria) this;
        }

        public Criteria andSourcingBetween(Long value1, Long value2) {
            addCriterion("sourcing between", value1, value2, "sourcing");
            return (Criteria) this;
        }

        public Criteria andSourcingNotBetween(Long value1, Long value2) {
            addCriterion("sourcing not between", value1, value2, "sourcing");
            return (Criteria) this;
        }

        public Criteria andConsumeIsNull() {
            addCriterion("consume is null");
            return (Criteria) this;
        }

        public Criteria andConsumeIsNotNull() {
            addCriterion("consume is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeEqualTo(Long value) {
            addCriterion("consume =", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeNotEqualTo(Long value) {
            addCriterion("consume <>", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeGreaterThan(Long value) {
            addCriterion("consume >", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeGreaterThanOrEqualTo(Long value) {
            addCriterion("consume >=", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeLessThan(Long value) {
            addCriterion("consume <", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeLessThanOrEqualTo(Long value) {
            addCriterion("consume <=", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeIn(List<Long> values) {
            addCriterion("consume in", values, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeNotIn(List<Long> values) {
            addCriterion("consume not in", values, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeBetween(Long value1, Long value2) {
            addCriterion("consume between", value1, value2, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeNotBetween(Long value1, Long value2) {
            addCriterion("consume not between", value1, value2, "consume");
            return (Criteria) this;
        }

        public Criteria andRedFeeIsNull() {
            addCriterion("red_fee is null");
            return (Criteria) this;
        }

        public Criteria andRedFeeIsNotNull() {
            addCriterion("red_fee is not null");
            return (Criteria) this;
        }

        public Criteria andRedFeeEqualTo(Long value) {
            addCriterion("red_fee =", value, "redFee");
            return (Criteria) this;
        }

        public Criteria andRedFeeNotEqualTo(Long value) {
            addCriterion("red_fee <>", value, "redFee");
            return (Criteria) this;
        }

        public Criteria andRedFeeGreaterThan(Long value) {
            addCriterion("red_fee >", value, "redFee");
            return (Criteria) this;
        }

        public Criteria andRedFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("red_fee >=", value, "redFee");
            return (Criteria) this;
        }

        public Criteria andRedFeeLessThan(Long value) {
            addCriterion("red_fee <", value, "redFee");
            return (Criteria) this;
        }

        public Criteria andRedFeeLessThanOrEqualTo(Long value) {
            addCriterion("red_fee <=", value, "redFee");
            return (Criteria) this;
        }

        public Criteria andRedFeeIn(List<Long> values) {
            addCriterion("red_fee in", values, "redFee");
            return (Criteria) this;
        }

        public Criteria andRedFeeNotIn(List<Long> values) {
            addCriterion("red_fee not in", values, "redFee");
            return (Criteria) this;
        }

        public Criteria andRedFeeBetween(Long value1, Long value2) {
            addCriterion("red_fee between", value1, value2, "redFee");
            return (Criteria) this;
        }

        public Criteria andRedFeeNotBetween(Long value1, Long value2) {
            addCriterion("red_fee not between", value1, value2, "redFee");
            return (Criteria) this;
        }

        public Criteria andActualConsumeIsNull() {
            addCriterion("actual_consume is null");
            return (Criteria) this;
        }

        public Criteria andActualConsumeIsNotNull() {
            addCriterion("actual_consume is not null");
            return (Criteria) this;
        }

        public Criteria andActualConsumeEqualTo(Long value) {
            addCriterion("actual_consume =", value, "actualConsume");
            return (Criteria) this;
        }

        public Criteria andActualConsumeNotEqualTo(Long value) {
            addCriterion("actual_consume <>", value, "actualConsume");
            return (Criteria) this;
        }

        public Criteria andActualConsumeGreaterThan(Long value) {
            addCriterion("actual_consume >", value, "actualConsume");
            return (Criteria) this;
        }

        public Criteria andActualConsumeGreaterThanOrEqualTo(Long value) {
            addCriterion("actual_consume >=", value, "actualConsume");
            return (Criteria) this;
        }

        public Criteria andActualConsumeLessThan(Long value) {
            addCriterion("actual_consume <", value, "actualConsume");
            return (Criteria) this;
        }

        public Criteria andActualConsumeLessThanOrEqualTo(Long value) {
            addCriterion("actual_consume <=", value, "actualConsume");
            return (Criteria) this;
        }

        public Criteria andActualConsumeIn(List<Long> values) {
            addCriterion("actual_consume in", values, "actualConsume");
            return (Criteria) this;
        }

        public Criteria andActualConsumeNotIn(List<Long> values) {
            addCriterion("actual_consume not in", values, "actualConsume");
            return (Criteria) this;
        }

        public Criteria andActualConsumeBetween(Long value1, Long value2) {
            addCriterion("actual_consume between", value1, value2, "actualConsume");
            return (Criteria) this;
        }

        public Criteria andActualConsumeNotBetween(Long value1, Long value2) {
            addCriterion("actual_consume not between", value1, value2, "actualConsume");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyIsNull() {
            addCriterion("month_stock_money is null");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyIsNotNull() {
            addCriterion("month_stock_money is not null");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyEqualTo(Long value) {
            addCriterion("month_stock_money =", value, "monthStockMoney");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyNotEqualTo(Long value) {
            addCriterion("month_stock_money <>", value, "monthStockMoney");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyGreaterThan(Long value) {
            addCriterion("month_stock_money >", value, "monthStockMoney");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("month_stock_money >=", value, "monthStockMoney");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyLessThan(Long value) {
            addCriterion("month_stock_money <", value, "monthStockMoney");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyLessThanOrEqualTo(Long value) {
            addCriterion("month_stock_money <=", value, "monthStockMoney");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyIn(List<Long> values) {
            addCriterion("month_stock_money in", values, "monthStockMoney");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyNotIn(List<Long> values) {
            addCriterion("month_stock_money not in", values, "monthStockMoney");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyBetween(Long value1, Long value2) {
            addCriterion("month_stock_money between", value1, value2, "monthStockMoney");
            return (Criteria) this;
        }

        public Criteria andMonthStockMoneyNotBetween(Long value1, Long value2) {
            addCriterion("month_stock_money not between", value1, value2, "monthStockMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyIsNull() {
            addCriterion("goods_money is null");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyIsNotNull() {
            addCriterion("goods_money is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyEqualTo(Long value) {
            addCriterion("goods_money =", value, "goodsMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyNotEqualTo(Long value) {
            addCriterion("goods_money <>", value, "goodsMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyGreaterThan(Long value) {
            addCriterion("goods_money >", value, "goodsMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_money >=", value, "goodsMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyLessThan(Long value) {
            addCriterion("goods_money <", value, "goodsMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyLessThanOrEqualTo(Long value) {
            addCriterion("goods_money <=", value, "goodsMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyIn(List<Long> values) {
            addCriterion("goods_money in", values, "goodsMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyNotIn(List<Long> values) {
            addCriterion("goods_money not in", values, "goodsMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyBetween(Long value1, Long value2) {
            addCriterion("goods_money between", value1, value2, "goodsMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsMoneyNotBetween(Long value1, Long value2) {
            addCriterion("goods_money not between", value1, value2, "goodsMoney");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
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

        public Criteria andLwFeeOneIsNull() {
            addCriterion("lw_fee_one is null");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneIsNotNull() {
            addCriterion("lw_fee_one is not null");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneEqualTo(Long value) {
            addCriterion("lw_fee_one =", value, "lwFeeOne");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneNotEqualTo(Long value) {
            addCriterion("lw_fee_one <>", value, "lwFeeOne");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneGreaterThan(Long value) {
            addCriterion("lw_fee_one >", value, "lwFeeOne");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneGreaterThanOrEqualTo(Long value) {
            addCriterion("lw_fee_one >=", value, "lwFeeOne");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneLessThan(Long value) {
            addCriterion("lw_fee_one <", value, "lwFeeOne");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneLessThanOrEqualTo(Long value) {
            addCriterion("lw_fee_one <=", value, "lwFeeOne");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneIn(List<Long> values) {
            addCriterion("lw_fee_one in", values, "lwFeeOne");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneNotIn(List<Long> values) {
            addCriterion("lw_fee_one not in", values, "lwFeeOne");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneBetween(Long value1, Long value2) {
            addCriterion("lw_fee_one between", value1, value2, "lwFeeOne");
            return (Criteria) this;
        }

        public Criteria andLwFeeOneNotBetween(Long value1, Long value2) {
            addCriterion("lw_fee_one not between", value1, value2, "lwFeeOne");
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