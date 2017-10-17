package com.b2b.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RedPacketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RedPacketExample() {
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

        public Criteria andRedNameIsNull() {
            addCriterion("red_name is null");
            return (Criteria) this;
        }

        public Criteria andRedNameIsNotNull() {
            addCriterion("red_name is not null");
            return (Criteria) this;
        }

        public Criteria andRedNameEqualTo(String value) {
            addCriterion("red_name =", value, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameNotEqualTo(String value) {
            addCriterion("red_name <>", value, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameGreaterThan(String value) {
            addCriterion("red_name >", value, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameGreaterThanOrEqualTo(String value) {
            addCriterion("red_name >=", value, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameLessThan(String value) {
            addCriterion("red_name <", value, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameLessThanOrEqualTo(String value) {
            addCriterion("red_name <=", value, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameLike(String value) {
            addCriterion("red_name like", value, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameNotLike(String value) {
            addCriterion("red_name not like", value, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameIn(List<String> values) {
            addCriterion("red_name in", values, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameNotIn(List<String> values) {
            addCriterion("red_name not in", values, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameBetween(String value1, String value2) {
            addCriterion("red_name between", value1, value2, "redName");
            return (Criteria) this;
        }

        public Criteria andRedNameNotBetween(String value1, String value2) {
            addCriterion("red_name not between", value1, value2, "redName");
            return (Criteria) this;
        }

        public Criteria andRedBudgetIsNull() {
            addCriterion("red_budget is null");
            return (Criteria) this;
        }

        public Criteria andRedBudgetIsNotNull() {
            addCriterion("red_budget is not null");
            return (Criteria) this;
        }

        public Criteria andRedBudgetEqualTo(Long value) {
            addCriterion("red_budget =", value, "redBudget");
            return (Criteria) this;
        }

        public Criteria andRedBudgetNotEqualTo(Long value) {
            addCriterion("red_budget <>", value, "redBudget");
            return (Criteria) this;
        }

        public Criteria andRedBudgetGreaterThan(Long value) {
            addCriterion("red_budget >", value, "redBudget");
            return (Criteria) this;
        }

        public Criteria andRedBudgetGreaterThanOrEqualTo(Long value) {
            addCriterion("red_budget >=", value, "redBudget");
            return (Criteria) this;
        }

        public Criteria andRedBudgetLessThan(Long value) {
            addCriterion("red_budget <", value, "redBudget");
            return (Criteria) this;
        }

        public Criteria andRedBudgetLessThanOrEqualTo(Long value) {
            addCriterion("red_budget <=", value, "redBudget");
            return (Criteria) this;
        }

        public Criteria andRedBudgetIn(List<Long> values) {
            addCriterion("red_budget in", values, "redBudget");
            return (Criteria) this;
        }

        public Criteria andRedBudgetNotIn(List<Long> values) {
            addCriterion("red_budget not in", values, "redBudget");
            return (Criteria) this;
        }

        public Criteria andRedBudgetBetween(Long value1, Long value2) {
            addCriterion("red_budget between", value1, value2, "redBudget");
            return (Criteria) this;
        }

        public Criteria andRedBudgetNotBetween(Long value1, Long value2) {
            addCriterion("red_budget not between", value1, value2, "redBudget");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumIsNull() {
            addCriterion("base_day_num is null");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumIsNotNull() {
            addCriterion("base_day_num is not null");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumEqualTo(Integer value) {
            addCriterion("base_day_num =", value, "baseDayNum");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumNotEqualTo(Integer value) {
            addCriterion("base_day_num <>", value, "baseDayNum");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumGreaterThan(Integer value) {
            addCriterion("base_day_num >", value, "baseDayNum");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("base_day_num >=", value, "baseDayNum");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumLessThan(Integer value) {
            addCriterion("base_day_num <", value, "baseDayNum");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumLessThanOrEqualTo(Integer value) {
            addCriterion("base_day_num <=", value, "baseDayNum");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumIn(List<Integer> values) {
            addCriterion("base_day_num in", values, "baseDayNum");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumNotIn(List<Integer> values) {
            addCriterion("base_day_num not in", values, "baseDayNum");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumBetween(Integer value1, Integer value2) {
            addCriterion("base_day_num between", value1, value2, "baseDayNum");
            return (Criteria) this;
        }

        public Criteria andBaseDayNumNotBetween(Integer value1, Integer value2) {
            addCriterion("base_day_num not between", value1, value2, "baseDayNum");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("count is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("count is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("count =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("count <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("count >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("count >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("count <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("count <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("count in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("count not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("count between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("count not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andSkipIsNull() {
            addCriterion("skip is null");
            return (Criteria) this;
        }

        public Criteria andSkipIsNotNull() {
            addCriterion("skip is not null");
            return (Criteria) this;
        }

        public Criteria andSkipEqualTo(Boolean value) {
            addCriterion("skip =", value, "skip");
            return (Criteria) this;
        }

        public Criteria andSkipNotEqualTo(Boolean value) {
            addCriterion("skip <>", value, "skip");
            return (Criteria) this;
        }

        public Criteria andSkipGreaterThan(Boolean value) {
            addCriterion("skip >", value, "skip");
            return (Criteria) this;
        }

        public Criteria andSkipGreaterThanOrEqualTo(Boolean value) {
            addCriterion("skip >=", value, "skip");
            return (Criteria) this;
        }

        public Criteria andSkipLessThan(Boolean value) {
            addCriterion("skip <", value, "skip");
            return (Criteria) this;
        }

        public Criteria andSkipLessThanOrEqualTo(Boolean value) {
            addCriterion("skip <=", value, "skip");
            return (Criteria) this;
        }

        public Criteria andSkipIn(List<Boolean> values) {
            addCriterion("skip in", values, "skip");
            return (Criteria) this;
        }

        public Criteria andSkipNotIn(List<Boolean> values) {
            addCriterion("skip not in", values, "skip");
            return (Criteria) this;
        }

        public Criteria andSkipBetween(Boolean value1, Boolean value2) {
            addCriterion("skip between", value1, value2, "skip");
            return (Criteria) this;
        }

        public Criteria andSkipNotBetween(Boolean value1, Boolean value2) {
            addCriterion("skip not between", value1, value2, "skip");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andRedStatusIsNull() {
            addCriterion("red_status is null");
            return (Criteria) this;
        }

        public Criteria andRedStatusIsNotNull() {
            addCriterion("red_status is not null");
            return (Criteria) this;
        }

        public Criteria andRedStatusEqualTo(Integer value) {
            addCriterion("red_status =", value, "redStatus");
            return (Criteria) this;
        }

        public Criteria andRedStatusNotEqualTo(Integer value) {
            addCriterion("red_status <>", value, "redStatus");
            return (Criteria) this;
        }

        public Criteria andRedStatusGreaterThan(Integer value) {
            addCriterion("red_status >", value, "redStatus");
            return (Criteria) this;
        }

        public Criteria andRedStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("red_status >=", value, "redStatus");
            return (Criteria) this;
        }

        public Criteria andRedStatusLessThan(Integer value) {
            addCriterion("red_status <", value, "redStatus");
            return (Criteria) this;
        }

        public Criteria andRedStatusLessThanOrEqualTo(Integer value) {
            addCriterion("red_status <=", value, "redStatus");
            return (Criteria) this;
        }

        public Criteria andRedStatusIn(List<Integer> values) {
            addCriterion("red_status in", values, "redStatus");
            return (Criteria) this;
        }

        public Criteria andRedStatusNotIn(List<Integer> values) {
            addCriterion("red_status not in", values, "redStatus");
            return (Criteria) this;
        }

        public Criteria andRedStatusBetween(Integer value1, Integer value2) {
            addCriterion("red_status between", value1, value2, "redStatus");
            return (Criteria) this;
        }

        public Criteria andRedStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("red_status not between", value1, value2, "redStatus");
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