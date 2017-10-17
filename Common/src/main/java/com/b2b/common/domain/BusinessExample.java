package com.b2b.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessExample extends BaseExample {

	 protected String orderByClause;

	    protected boolean distinct;

	    protected List<Criteria> oredCriteria;

	    public BusinessExample() {
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


	        public Criteria andBusinessNameIsNull() {
	            addCriterion("business_name is null");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameIsNotNull() {
	            addCriterion("business_name is not null");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameEqualTo(String value) {
	            addCriterion("business_name =", value, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameNotEqualTo(String value) {
	            addCriterion("business_name <>", value, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameGreaterThan(String value) {
	            addCriterion("business_name >", value, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameGreaterThanOrEqualTo(String value) {
	            addCriterion("business_name >=", value, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameLessThan(String value) {
	            addCriterion("business_name <", value, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameLessThanOrEqualTo(String value) {
	            addCriterion("business_name <=", value, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameLike(String value) {
	            addCriterion("business_name like", value, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameNotLike(String value) {
	            addCriterion("business_name not like", value, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameIn(List<String> values) {
	            addCriterion("business_name in", values, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameNotIn(List<String> values) {
	            addCriterion("business_name not in", values, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameBetween(String value1, String value2) {
	            addCriterion("business_name between", value1, value2, "businessName");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessNameNotBetween(String value1, String value2) {
	            addCriterion("business_name not between", value1, value2, "businessName");
	            return (Criteria) this;
	        }
	        //
	        public Criteria andBusinessAddressIsNull() {
	            addCriterion("business_address is null");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressIsNotNull() {
	            addCriterion("business_address is not null");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressEqualTo(String value) {
	            addCriterion("business_address =", value, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressNotEqualTo(String value) {
	            addCriterion("business_address <>", value, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressGreaterThan(String value) {
	            addCriterion("business_address >", value, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressGreaterThanOrEqualTo(String value) {
	            addCriterion("business_address >=", value, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressLessThan(String value) {
	            addCriterion("business_address <", value, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressLessThanOrEqualTo(String value) {
	            addCriterion("business_address <=", value, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressLike(String value) {
	            addCriterion("business_address like", value, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressNotLike(String value) {
	            addCriterion("business_address not like", value, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressIn(List<String> values) {
	            addCriterion("business_address in", values, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressNotIn(List<String> values) {
	            addCriterion("business_address not in", values, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressBetween(String value1, String value2) {
	            addCriterion("business_address between", value1, value2, "businessAddress");
	            return (Criteria) this;
	        }

	        public Criteria andBusinessAddressNotBetween(String value1, String value2) {
	            addCriterion("business_address not between", value1, value2, "businessAddress");
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

	       
	        public Criteria andMobilePhoneIsNull() {
	            addCriterion("mobile_phone is null");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneIsNotNull() {
	            addCriterion("mobile_phone is not null");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneEqualTo(String value) {
	            addCriterion("mobile_phone =", value, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneNotEqualTo(String value) {
	            addCriterion("mobile_phone <>", value, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneGreaterThan(String value) {
	            addCriterion("mobile_phone >", value, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneGreaterThanOrEqualTo(String value) {
	            addCriterion("mobile_phone >=", value, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneLessThan(String value) {
	            addCriterion("mobile_phone <", value, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneLessThanOrEqualTo(String value) {
	            addCriterion("mobile_phone <=", value, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneLike(String value) {
	            addCriterion("mobile_phone like", value, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneNotLike(String value) {
	            addCriterion("mobile_phone not like", value, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneIn(List<String> values) {
	            addCriterion("mobile_phone in", values, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneNotIn(List<String> values) {
	            addCriterion("mobile_phone not in", values, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneBetween(String value1, String value2) {
	            addCriterion("mobile_phone between", value1, value2, "mobilePhone");
	            return (Criteria) this;
	        }

	        public Criteria andMobilePhoneNotBetween(String value1, String value2) {
	            addCriterion("mobile_phone not between", value1, value2, "mobilePhone");
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
