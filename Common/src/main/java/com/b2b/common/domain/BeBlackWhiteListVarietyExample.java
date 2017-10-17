package com.b2b.common.domain;

import java.util.ArrayList;
import java.util.List;

public class BeBlackWhiteListVarietyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BeBlackWhiteListVarietyExample() {
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

        public Criteria andBbwlvIdIsNull() {
            addCriterion("bbwlv_id is null");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdIsNotNull() {
            addCriterion("bbwlv_id is not null");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdEqualTo(Integer value) {
            addCriterion("bbwlv_id =", value, "bbwlvId");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdNotEqualTo(Integer value) {
            addCriterion("bbwlv_id <>", value, "bbwlvId");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdGreaterThan(Integer value) {
            addCriterion("bbwlv_id >", value, "bbwlvId");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bbwlv_id >=", value, "bbwlvId");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdLessThan(Integer value) {
            addCriterion("bbwlv_id <", value, "bbwlvId");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdLessThanOrEqualTo(Integer value) {
            addCriterion("bbwlv_id <=", value, "bbwlvId");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdIn(List<Integer> values) {
            addCriterion("bbwlv_id in", values, "bbwlvId");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdNotIn(List<Integer> values) {
            addCriterion("bbwlv_id not in", values, "bbwlvId");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdBetween(Integer value1, Integer value2) {
            addCriterion("bbwlv_id between", value1, value2, "bbwlvId");
            return (Criteria) this;
        }

        public Criteria andBbwlvIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bbwlv_id not between", value1, value2, "bbwlvId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdIsNull() {
            addCriterion("variety_id is null");
            return (Criteria) this;
        }

        public Criteria andVarietyIdIsNotNull() {
            addCriterion("variety_id is not null");
            return (Criteria) this;
        }

        public Criteria andVarietyIdEqualTo(Integer value) {
            addCriterion("variety_id =", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdNotEqualTo(Integer value) {
            addCriterion("variety_id <>", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdGreaterThan(Integer value) {
            addCriterion("variety_id >", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("variety_id >=", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdLessThan(Integer value) {
            addCriterion("variety_id <", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdLessThanOrEqualTo(Integer value) {
            addCriterion("variety_id <=", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdIn(List<Integer> values) {
            addCriterion("variety_id in", values, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdNotIn(List<Integer> values) {
            addCriterion("variety_id not in", values, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdBetween(Integer value1, Integer value2) {
            addCriterion("variety_id between", value1, value2, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("variety_id not between", value1, value2, "varietyId");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdIsNull() {
            addCriterion("be_blackwhite_id is null");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdIsNotNull() {
            addCriterion("be_blackwhite_id is not null");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdEqualTo(Integer value) {
            addCriterion("be_blackwhite_id =", value, "beBlackwhiteId");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdNotEqualTo(Integer value) {
            addCriterion("be_blackwhite_id <>", value, "beBlackwhiteId");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdGreaterThan(Integer value) {
            addCriterion("be_blackwhite_id >", value, "beBlackwhiteId");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("be_blackwhite_id >=", value, "beBlackwhiteId");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdLessThan(Integer value) {
            addCriterion("be_blackwhite_id <", value, "beBlackwhiteId");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("be_blackwhite_id <=", value, "beBlackwhiteId");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdIn(List<Integer> values) {
            addCriterion("be_blackwhite_id in", values, "beBlackwhiteId");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdNotIn(List<Integer> values) {
            addCriterion("be_blackwhite_id not in", values, "beBlackwhiteId");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdBetween(Integer value1, Integer value2) {
            addCriterion("be_blackwhite_id between", value1, value2, "beBlackwhiteId");
            return (Criteria) this;
        }

        public Criteria andBeBlackwhiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("be_blackwhite_id not between", value1, value2, "beBlackwhiteId");
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