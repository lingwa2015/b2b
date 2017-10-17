package com.b2b.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SalesmanPerformanceExample extends BaseExample{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SalesmanPerformanceExample() {
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

        public Criteria andInterfaceManIsNull() {
            addCriterion("interface_man is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceManIsNotNull() {
            addCriterion("interface_man is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceManEqualTo(String value) {
            addCriterion("interface_man =", value, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManNotEqualTo(String value) {
            addCriterion("interface_man <>", value, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManGreaterThan(String value) {
            addCriterion("interface_man >", value, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManGreaterThanOrEqualTo(String value) {
            addCriterion("interface_man >=", value, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManLessThan(String value) {
            addCriterion("interface_man <", value, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManLessThanOrEqualTo(String value) {
            addCriterion("interface_man <=", value, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManLike(String value) {
            addCriterion("interface_man like", value, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManNotLike(String value) {
            addCriterion("interface_man not like", value, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManIn(List<String> values) {
            addCriterion("interface_man in", values, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManNotIn(List<String> values) {
            addCriterion("interface_man not in", values, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManBetween(String value1, String value2) {
            addCriterion("interface_man between", value1, value2, "interfaceMan");
            return (Criteria) this;
        }

        public Criteria andInterfaceManNotBetween(String value1, String value2) {
            addCriterion("interface_man not between", value1, value2, "interfaceMan");
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

        public Criteria andRefundNumIsNull() {
            addCriterion("refund_num is null");
            return (Criteria) this;
        }

        public Criteria andRefundNumIsNotNull() {
            addCriterion("refund_num is not null");
            return (Criteria) this;
        }

        public Criteria andRefundNumEqualTo(Integer value) {
            addCriterion("refund_num =", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumNotEqualTo(Integer value) {
            addCriterion("refund_num <>", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumGreaterThan(Integer value) {
            addCriterion("refund_num >", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_num >=", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumLessThan(Integer value) {
            addCriterion("refund_num <", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumLessThanOrEqualTo(Integer value) {
            addCriterion("refund_num <=", value, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumIn(List<Integer> values) {
            addCriterion("refund_num in", values, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumNotIn(List<Integer> values) {
            addCriterion("refund_num not in", values, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumBetween(Integer value1, Integer value2) {
            addCriterion("refund_num between", value1, value2, "refundNum");
            return (Criteria) this;
        }

        public Criteria andRefundNumNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_num not between", value1, value2, "refundNum");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andConpanyNameIsNull() {
            addCriterion("conpany_name is null");
            return (Criteria) this;
        }

        public Criteria andConpanyNameIsNotNull() {
            addCriterion("conpany_name is not null");
            return (Criteria) this;
        }

        public Criteria andConpanyNameEqualTo(String value) {
            addCriterion("conpany_name =", value, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameNotEqualTo(String value) {
            addCriterion("conpany_name <>", value, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameGreaterThan(String value) {
            addCriterion("conpany_name >", value, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("conpany_name >=", value, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameLessThan(String value) {
            addCriterion("conpany_name <", value, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameLessThanOrEqualTo(String value) {
            addCriterion("conpany_name <=", value, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameLike(String value) {
            addCriterion("conpany_name like", value, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameNotLike(String value) {
            addCriterion("conpany_name not like", value, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameIn(List<String> values) {
            addCriterion("conpany_name in", values, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameNotIn(List<String> values) {
            addCriterion("conpany_name not in", values, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameBetween(String value1, String value2) {
            addCriterion("conpany_name between", value1, value2, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andConpanyNameNotBetween(String value1, String value2) {
            addCriterion("conpany_name not between", value1, value2, "conpanyName");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyIsNull() {
            addCriterion("after_marsale_money is null");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyIsNotNull() {
            addCriterion("after_marsale_money is not null");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyEqualTo(Long value) {
            addCriterion("after_marsale_money =", value, "afterMarsaleMoney");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyNotEqualTo(Long value) {
            addCriterion("after_marsale_money <>", value, "afterMarsaleMoney");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyGreaterThan(Long value) {
            addCriterion("after_marsale_money >", value, "afterMarsaleMoney");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("after_marsale_money >=", value, "afterMarsaleMoney");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyLessThan(Long value) {
            addCriterion("after_marsale_money <", value, "afterMarsaleMoney");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyLessThanOrEqualTo(Long value) {
            addCriterion("after_marsale_money <=", value, "afterMarsaleMoney");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyIn(List<Long> values) {
            addCriterion("after_marsale_money in", values, "afterMarsaleMoney");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyNotIn(List<Long> values) {
            addCriterion("after_marsale_money not in", values, "afterMarsaleMoney");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyBetween(Long value1, Long value2) {
            addCriterion("after_marsale_money between", value1, value2, "afterMarsaleMoney");
            return (Criteria) this;
        }

        public Criteria andAfterMarsaleMoneyNotBetween(Long value1, Long value2) {
            addCriterion("after_marsale_money not between", value1, value2, "afterMarsaleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyIsNull() {
            addCriterion("sale_money is null");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyIsNotNull() {
            addCriterion("sale_money is not null");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyEqualTo(Long value) {
            addCriterion("sale_money =", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyNotEqualTo(Long value) {
            addCriterion("sale_money <>", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyGreaterThan(Long value) {
            addCriterion("sale_money >", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("sale_money >=", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyLessThan(Long value) {
            addCriterion("sale_money <", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyLessThanOrEqualTo(Long value) {
            addCriterion("sale_money <=", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyIn(List<Long> values) {
            addCriterion("sale_money in", values, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyNotIn(List<Long> values) {
            addCriterion("sale_money not in", values, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyBetween(Long value1, Long value2) {
            addCriterion("sale_money between", value1, value2, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyNotBetween(Long value1, Long value2) {
            addCriterion("sale_money not between", value1, value2, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyIsNull() {
            addCriterion("new_sale_money is null");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyIsNotNull() {
            addCriterion("new_sale_money is not null");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyEqualTo(Long value) {
            addCriterion("new_sale_money =", value, "newSaleMoney");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyNotEqualTo(Long value) {
            addCriterion("new_sale_money <>", value, "newSaleMoney");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyGreaterThan(Long value) {
            addCriterion("new_sale_money >", value, "newSaleMoney");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("new_sale_money >=", value, "newSaleMoney");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyLessThan(Long value) {
            addCriterion("new_sale_money <", value, "newSaleMoney");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyLessThanOrEqualTo(Long value) {
            addCriterion("new_sale_money <=", value, "newSaleMoney");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyIn(List<Long> values) {
            addCriterion("new_sale_money in", values, "newSaleMoney");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyNotIn(List<Long> values) {
            addCriterion("new_sale_money not in", values, "newSaleMoney");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyBetween(Long value1, Long value2) {
            addCriterion("new_sale_money between", value1, value2, "newSaleMoney");
            return (Criteria) this;
        }

        public Criteria andNewSaleMoneyNotBetween(Long value1, Long value2) {
            addCriterion("new_sale_money not between", value1, value2, "newSaleMoney");
            return (Criteria) this;
        }

        public Criteria andBagMoneyIsNull() {
            addCriterion("bag_money is null");
            return (Criteria) this;
        }

        public Criteria andBagMoneyIsNotNull() {
            addCriterion("bag_money is not null");
            return (Criteria) this;
        }

        public Criteria andBagMoneyEqualTo(Long value) {
            addCriterion("bag_money =", value, "bagMoney");
            return (Criteria) this;
        }

        public Criteria andBagMoneyNotEqualTo(Long value) {
            addCriterion("bag_money <>", value, "bagMoney");
            return (Criteria) this;
        }

        public Criteria andBagMoneyGreaterThan(Long value) {
            addCriterion("bag_money >", value, "bagMoney");
            return (Criteria) this;
        }

        public Criteria andBagMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("bag_money >=", value, "bagMoney");
            return (Criteria) this;
        }

        public Criteria andBagMoneyLessThan(Long value) {
            addCriterion("bag_money <", value, "bagMoney");
            return (Criteria) this;
        }

        public Criteria andBagMoneyLessThanOrEqualTo(Long value) {
            addCriterion("bag_money <=", value, "bagMoney");
            return (Criteria) this;
        }

        public Criteria andBagMoneyIn(List<Long> values) {
            addCriterion("bag_money in", values, "bagMoney");
            return (Criteria) this;
        }

        public Criteria andBagMoneyNotIn(List<Long> values) {
            addCriterion("bag_money not in", values, "bagMoney");
            return (Criteria) this;
        }

        public Criteria andBagMoneyBetween(Long value1, Long value2) {
            addCriterion("bag_money between", value1, value2, "bagMoney");
            return (Criteria) this;
        }

        public Criteria andBagMoneyNotBetween(Long value1, Long value2) {
            addCriterion("bag_money not between", value1, value2, "bagMoney");
            return (Criteria) this;
        }

        public Criteria andCountDateIsNull() {
            addCriterion("count_date is null");
            return (Criteria) this;
        }

        public Criteria andCountDateIsNotNull() {
            addCriterion("count_date is not null");
            return (Criteria) this;
        }

        public Criteria andCountDateEqualTo(Date value) {
            addCriterionForJDBCDate("count_date =", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("count_date <>", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateGreaterThan(Date value) {
            addCriterionForJDBCDate("count_date >", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("count_date >=", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateLessThan(Date value) {
            addCriterionForJDBCDate("count_date <", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("count_date <=", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateIn(List<Date> values) {
            addCriterionForJDBCDate("count_date in", values, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("count_date not in", values, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("count_date between", value1, value2, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("count_date not between", value1, value2, "countDate");
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