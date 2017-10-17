package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerUserExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerUserExample() {
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

        public Criteria andPassWordIsNull() {
            addCriterion("pass_word is null");
            return (Criteria) this;
        }

        public Criteria andPassWordIsNotNull() {
            addCriterion("pass_word is not null");
            return (Criteria) this;
        }

        public Criteria andPassWordEqualTo(String value) {
            addCriterion("pass_word =", value, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordNotEqualTo(String value) {
            addCriterion("pass_word <>", value, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordGreaterThan(String value) {
            addCriterion("pass_word >", value, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordGreaterThanOrEqualTo(String value) {
            addCriterion("pass_word >=", value, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordLessThan(String value) {
            addCriterion("pass_word <", value, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordLessThanOrEqualTo(String value) {
            addCriterion("pass_word <=", value, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordLike(String value) {
            addCriterion("pass_word like", value, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordNotLike(String value) {
            addCriterion("pass_word not like", value, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordIn(List<String> values) {
            addCriterion("pass_word in", values, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordNotIn(List<String> values) {
            addCriterion("pass_word not in", values, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordBetween(String value1, String value2) {
            addCriterion("pass_word between", value1, value2, "passWord");
            return (Criteria) this;
        }

        public Criteria andPassWordNotBetween(String value1, String value2) {
            addCriterion("pass_word not between", value1, value2, "passWord");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
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

        public Criteria andIsadminIsNull() {
            addCriterion("isadmin is null");
            return (Criteria) this;
        }

        public Criteria andIsadminIsNotNull() {
            addCriterion("isadmin is not null");
            return (Criteria) this;
        }

        public Criteria andIsadminEqualTo(Integer value) {
            addCriterion("isadmin =", value, "isadmin");
            return (Criteria) this;
        }

        public Criteria andIsadminNotEqualTo(Integer value) {
            addCriterion("isadmin <>", value, "isadmin");
            return (Criteria) this;
        }

        public Criteria andIsadminGreaterThan(Integer value) {
            addCriterion("isadmin >", value, "isadmin");
            return (Criteria) this;
        }

        public Criteria andIsadminGreaterThanOrEqualTo(Integer value) {
            addCriterion("isadmin >=", value, "isadmin");
            return (Criteria) this;
        }

        public Criteria andIsadminLessThan(Integer value) {
            addCriterion("isadmin <", value, "isadmin");
            return (Criteria) this;
        }

        public Criteria andIsadminLessThanOrEqualTo(Integer value) {
            addCriterion("isadmin <=", value, "isadmin");
            return (Criteria) this;
        }

        public Criteria andIsadminIn(List<Integer> values) {
            addCriterion("isadmin in", values, "isadmin");
            return (Criteria) this;
        }

        public Criteria andIsadminNotIn(List<Integer> values) {
            addCriterion("isadmin not in", values, "isadmin");
            return (Criteria) this;
        }

        public Criteria andIsadminBetween(Integer value1, Integer value2) {
            addCriterion("isadmin between", value1, value2, "isadmin");
            return (Criteria) this;
        }

        public Criteria andIsadminNotBetween(Integer value1, Integer value2) {
            addCriterion("isadmin not between", value1, value2, "isadmin");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNull() {
            addCriterion("business_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNotNull() {
            addCriterion("business_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdEqualTo(Integer value) {
            addCriterion("business_id =", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotEqualTo(Integer value) {
            addCriterion("business_id <>", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThan(Integer value) {
            addCriterion("business_id >", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_id >=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThan(Integer value) {
            addCriterion("business_id <", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThanOrEqualTo(Integer value) {
            addCriterion("business_id <=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIn(List<Integer> values) {
            addCriterion("business_id in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotIn(List<Integer> values) {
            addCriterion("business_id not in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdBetween(Integer value1, Integer value2) {
            addCriterion("business_id between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("business_id not between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andManagershopidIsNull() {
            addCriterion("managerShopId is null");
            return (Criteria) this;
        }

        public Criteria andManagershopidIsNotNull() {
            addCriterion("managerShopId is not null");
            return (Criteria) this;
        }

        public Criteria andManagershopidEqualTo(Integer value) {
            addCriterion("managerShopId =", value, "managershopid");
            return (Criteria) this;
        }

        public Criteria andManagershopidNotEqualTo(Integer value) {
            addCriterion("managerShopId <>", value, "managershopid");
            return (Criteria) this;
        }

        public Criteria andManagershopidGreaterThan(Integer value) {
            addCriterion("managerShopId >", value, "managershopid");
            return (Criteria) this;
        }

        public Criteria andManagershopidGreaterThanOrEqualTo(Integer value) {
            addCriterion("managerShopId >=", value, "managershopid");
            return (Criteria) this;
        }

        public Criteria andManagershopidLessThan(Integer value) {
            addCriterion("managerShopId <", value, "managershopid");
            return (Criteria) this;
        }

        public Criteria andManagershopidLessThanOrEqualTo(Integer value) {
            addCriterion("managerShopId <=", value, "managershopid");
            return (Criteria) this;
        }

        public Criteria andManagershopidIn(List<Integer> values) {
            addCriterion("managerShopId in", values, "managershopid");
            return (Criteria) this;
        }

        public Criteria andManagershopidNotIn(List<Integer> values) {
            addCriterion("managerShopId not in", values, "managershopid");
            return (Criteria) this;
        }

        public Criteria andManagershopidBetween(Integer value1, Integer value2) {
            addCriterion("managerShopId between", value1, value2, "managershopid");
            return (Criteria) this;
        }

        public Criteria andManagershopidNotBetween(Integer value1, Integer value2) {
            addCriterion("managerShopId not between", value1, value2, "managershopid");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoIsNull() {
            addCriterion("company_memo is null");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoIsNotNull() {
            addCriterion("company_memo is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoEqualTo(String value) {
            addCriterion("company_memo =", value, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoNotEqualTo(String value) {
            addCriterion("company_memo <>", value, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoGreaterThan(String value) {
            addCriterion("company_memo >", value, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoGreaterThanOrEqualTo(String value) {
            addCriterion("company_memo >=", value, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoLessThan(String value) {
            addCriterion("company_memo <", value, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoLessThanOrEqualTo(String value) {
            addCriterion("company_memo <=", value, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoLike(String value) {
            addCriterion("company_memo like", value, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoNotLike(String value) {
            addCriterion("company_memo not like", value, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoIn(List<String> values) {
            addCriterion("company_memo in", values, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoNotIn(List<String> values) {
            addCriterion("company_memo not in", values, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoBetween(String value1, String value2) {
            addCriterion("company_memo between", value1, value2, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyMemoNotBetween(String value1, String value2) {
            addCriterion("company_memo not between", value1, value2, "companyMemo");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumIsNull() {
            addCriterion("company_personnum is null");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumIsNotNull() {
            addCriterion("company_personnum is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumEqualTo(Integer value) {
            addCriterion("company_personnum =", value, "companyPersonnum");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumNotEqualTo(Integer value) {
            addCriterion("company_personnum <>", value, "companyPersonnum");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumGreaterThan(Integer value) {
            addCriterion("company_personnum >", value, "companyPersonnum");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_personnum >=", value, "companyPersonnum");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumLessThan(Integer value) {
            addCriterion("company_personnum <", value, "companyPersonnum");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumLessThanOrEqualTo(Integer value) {
            addCriterion("company_personnum <=", value, "companyPersonnum");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumIn(List<Integer> values) {
            addCriterion("company_personnum in", values, "companyPersonnum");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumNotIn(List<Integer> values) {
            addCriterion("company_personnum not in", values, "companyPersonnum");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumBetween(Integer value1, Integer value2) {
            addCriterion("company_personnum between", value1, value2, "companyPersonnum");
            return (Criteria) this;
        }

        public Criteria andCompanyPersonnumNotBetween(Integer value1, Integer value2) {
            addCriterion("company_personnum not between", value1, value2, "companyPersonnum");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIsNull() {
            addCriterion("interface_person is null");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIsNotNull() {
            addCriterion("interface_person is not null");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonEqualTo(String value) {
            addCriterion("interface_person =", value, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonNotEqualTo(String value) {
            addCriterion("interface_person <>", value, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonGreaterThan(String value) {
            addCriterion("interface_person >", value, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonGreaterThanOrEqualTo(String value) {
            addCriterion("interface_person >=", value, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonLessThan(String value) {
            addCriterion("interface_person <", value, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonLessThanOrEqualTo(String value) {
            addCriterion("interface_person <=", value, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonLike(String value) {
            addCriterion("interface_person like", value, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonNotLike(String value) {
            addCriterion("interface_person not like", value, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIn(List<String> values) {
            addCriterion("interface_person in", values, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonNotIn(List<String> values) {
            addCriterion("interface_person not in", values, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonBetween(String value1, String value2) {
            addCriterion("interface_person between", value1, value2, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonNotBetween(String value1, String value2) {
            addCriterion("interface_person not between", value1, value2, "interfacePerson");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(BigDecimal value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(BigDecimal value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(BigDecimal value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(BigDecimal value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<BigDecimal> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<BigDecimal> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andIswxvipIsNull() {
            addCriterion("iswxvip is null");
            return (Criteria) this;
        }

        public Criteria andIswxvipIsNotNull() {
            addCriterion("iswxvip is not null");
            return (Criteria) this;
        }

        public Criteria andIswxvipEqualTo(Integer value) {
            addCriterion("iswxvip =", value, "iswxvip");
            return (Criteria) this;
        }

        public Criteria andIswxvipNotEqualTo(Integer value) {
            addCriterion("iswxvip <>", value, "iswxvip");
            return (Criteria) this;
        }

        public Criteria andIswxvipGreaterThan(Integer value) {
            addCriterion("iswxvip >", value, "iswxvip");
            return (Criteria) this;
        }

        public Criteria andIswxvipGreaterThanOrEqualTo(Integer value) {
            addCriterion("iswxvip >=", value, "iswxvip");
            return (Criteria) this;
        }

        public Criteria andIswxvipLessThan(Integer value) {
            addCriterion("iswxvip <", value, "iswxvip");
            return (Criteria) this;
        }

        public Criteria andIswxvipLessThanOrEqualTo(Integer value) {
            addCriterion("iswxvip <=", value, "iswxvip");
            return (Criteria) this;
        }

        public Criteria andIswxvipIn(List<Integer> values) {
            addCriterion("iswxvip in", values, "iswxvip");
            return (Criteria) this;
        }

        public Criteria andIswxvipNotIn(List<Integer> values) {
            addCriterion("iswxvip not in", values, "iswxvip");
            return (Criteria) this;
        }

        public Criteria andIswxvipBetween(Integer value1, Integer value2) {
            addCriterion("iswxvip between", value1, value2, "iswxvip");
            return (Criteria) this;
        }

        public Criteria andIswxvipNotBetween(Integer value1, Integer value2) {
            addCriterion("iswxvip not between", value1, value2, "iswxvip");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andLikemanIsNull() {
            addCriterion("likeman is null");
            return (Criteria) this;
        }

        public Criteria andLikemanIsNotNull() {
            addCriterion("likeman is not null");
            return (Criteria) this;
        }

        public Criteria andLikemanEqualTo(String value) {
            addCriterion("likeman =", value, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanNotEqualTo(String value) {
            addCriterion("likeman <>", value, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanGreaterThan(String value) {
            addCriterion("likeman >", value, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanGreaterThanOrEqualTo(String value) {
            addCriterion("likeman >=", value, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanLessThan(String value) {
            addCriterion("likeman <", value, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanLessThanOrEqualTo(String value) {
            addCriterion("likeman <=", value, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanLike(String value) {
            addCriterion("likeman like", value, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanNotLike(String value) {
            addCriterion("likeman not like", value, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanIn(List<String> values) {
            addCriterion("likeman in", values, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanNotIn(List<String> values) {
            addCriterion("likeman not in", values, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanBetween(String value1, String value2) {
            addCriterion("likeman between", value1, value2, "likeman");
            return (Criteria) this;
        }

        public Criteria andLikemanNotBetween(String value1, String value2) {
            addCriterion("likeman not between", value1, value2, "likeman");
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

        public Criteria andGoodsShelfNumIsNull() {
            addCriterion("goods_shelf_num is null");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumIsNotNull() {
            addCriterion("goods_shelf_num is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumEqualTo(Integer value) {
            addCriterion("goods_shelf_num =", value, "goodsShelfNum");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumNotEqualTo(Integer value) {
            addCriterion("goods_shelf_num <>", value, "goodsShelfNum");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumGreaterThan(Integer value) {
            addCriterion("goods_shelf_num >", value, "goodsShelfNum");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_shelf_num >=", value, "goodsShelfNum");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumLessThan(Integer value) {
            addCriterion("goods_shelf_num <", value, "goodsShelfNum");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumLessThanOrEqualTo(Integer value) {
            addCriterion("goods_shelf_num <=", value, "goodsShelfNum");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumIn(List<Integer> values) {
            addCriterion("goods_shelf_num in", values, "goodsShelfNum");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumNotIn(List<Integer> values) {
            addCriterion("goods_shelf_num not in", values, "goodsShelfNum");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumBetween(Integer value1, Integer value2) {
            addCriterion("goods_shelf_num between", value1, value2, "goodsShelfNum");
            return (Criteria) this;
        }

        public Criteria andGoodsShelfNumNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_shelf_num not between", value1, value2, "goodsShelfNum");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumIsNull() {
            addCriterion("popsicle_icebox_num is null");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumIsNotNull() {
            addCriterion("popsicle_icebox_num is not null");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumEqualTo(Integer value) {
            addCriterion("popsicle_icebox_num =", value, "popsicleIceboxNum");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumNotEqualTo(Integer value) {
            addCriterion("popsicle_icebox_num <>", value, "popsicleIceboxNum");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumGreaterThan(Integer value) {
            addCriterion("popsicle_icebox_num >", value, "popsicleIceboxNum");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("popsicle_icebox_num >=", value, "popsicleIceboxNum");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumLessThan(Integer value) {
            addCriterion("popsicle_icebox_num <", value, "popsicleIceboxNum");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumLessThanOrEqualTo(Integer value) {
            addCriterion("popsicle_icebox_num <=", value, "popsicleIceboxNum");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumIn(List<Integer> values) {
            addCriterion("popsicle_icebox_num in", values, "popsicleIceboxNum");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumNotIn(List<Integer> values) {
            addCriterion("popsicle_icebox_num not in", values, "popsicleIceboxNum");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumBetween(Integer value1, Integer value2) {
            addCriterion("popsicle_icebox_num between", value1, value2, "popsicleIceboxNum");
            return (Criteria) this;
        }

        public Criteria andPopsicleIceboxNumNotBetween(Integer value1, Integer value2) {
            addCriterion("popsicle_icebox_num not between", value1, value2, "popsicleIceboxNum");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumIsNull() {
            addCriterion("drink_icebox_num is null");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumIsNotNull() {
            addCriterion("drink_icebox_num is not null");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumEqualTo(Integer value) {
            addCriterion("drink_icebox_num =", value, "drinkIceboxNum");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumNotEqualTo(Integer value) {
            addCriterion("drink_icebox_num <>", value, "drinkIceboxNum");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumGreaterThan(Integer value) {
            addCriterion("drink_icebox_num >", value, "drinkIceboxNum");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("drink_icebox_num >=", value, "drinkIceboxNum");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumLessThan(Integer value) {
            addCriterion("drink_icebox_num <", value, "drinkIceboxNum");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumLessThanOrEqualTo(Integer value) {
            addCriterion("drink_icebox_num <=", value, "drinkIceboxNum");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumIn(List<Integer> values) {
            addCriterion("drink_icebox_num in", values, "drinkIceboxNum");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumNotIn(List<Integer> values) {
            addCriterion("drink_icebox_num not in", values, "drinkIceboxNum");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumBetween(Integer value1, Integer value2) {
            addCriterion("drink_icebox_num between", value1, value2, "drinkIceboxNum");
            return (Criteria) this;
        }

        public Criteria andDrinkIceboxNumNotBetween(Integer value1, Integer value2) {
            addCriterion("drink_icebox_num not between", value1, value2, "drinkIceboxNum");
            return (Criteria) this;
        }

        public Criteria andBuyWayIsNull() {
            addCriterion("buy_way is null");
            return (Criteria) this;
        }

        public Criteria andBuyWayIsNotNull() {
            addCriterion("buy_way is not null");
            return (Criteria) this;
        }

        public Criteria andBuyWayEqualTo(Integer value) {
            addCriterion("buy_way =", value, "buyWay");
            return (Criteria) this;
        }

        public Criteria andBuyWayNotEqualTo(Integer value) {
            addCriterion("buy_way <>", value, "buyWay");
            return (Criteria) this;
        }

        public Criteria andBuyWayGreaterThan(Integer value) {
            addCriterion("buy_way >", value, "buyWay");
            return (Criteria) this;
        }

        public Criteria andBuyWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_way >=", value, "buyWay");
            return (Criteria) this;
        }

        public Criteria andBuyWayLessThan(Integer value) {
            addCriterion("buy_way <", value, "buyWay");
            return (Criteria) this;
        }

        public Criteria andBuyWayLessThanOrEqualTo(Integer value) {
            addCriterion("buy_way <=", value, "buyWay");
            return (Criteria) this;
        }

        public Criteria andBuyWayIn(List<Integer> values) {
            addCriterion("buy_way in", values, "buyWay");
            return (Criteria) this;
        }

        public Criteria andBuyWayNotIn(List<Integer> values) {
            addCriterion("buy_way not in", values, "buyWay");
            return (Criteria) this;
        }

        public Criteria andBuyWayBetween(Integer value1, Integer value2) {
            addCriterion("buy_way between", value1, value2, "buyWay");
            return (Criteria) this;
        }

        public Criteria andBuyWayNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_way not between", value1, value2, "buyWay");
            return (Criteria) this;
        }

        public Criteria andListTimeIsNull() {
            addCriterion("list_time is null");
            return (Criteria) this;
        }

        public Criteria andListTimeIsNotNull() {
            addCriterion("list_time is not null");
            return (Criteria) this;
        }

        public Criteria andListTimeEqualTo(Date value) {
            addCriterion("list_time =", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeNotEqualTo(Date value) {
            addCriterion("list_time <>", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeGreaterThan(Date value) {
            addCriterion("list_time >", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("list_time >=", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeLessThan(Date value) {
            addCriterion("list_time <", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeLessThanOrEqualTo(Date value) {
            addCriterion("list_time <=", value, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeIn(List<Date> values) {
            addCriterion("list_time in", values, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeNotIn(List<Date> values) {
            addCriterion("list_time not in", values, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeBetween(Date value1, Date value2) {
            addCriterion("list_time between", value1, value2, "listTime");
            return (Criteria) this;
        }

        public Criteria andListTimeNotBetween(Date value1, Date value2) {
            addCriterion("list_time not between", value1, value2, "listTime");
            return (Criteria) this;
        }

        public Criteria andShopDiscountIsNull() {
            addCriterion("shop_discount is null");
            return (Criteria) this;
        }

        public Criteria andShopDiscountIsNotNull() {
            addCriterion("shop_discount is not null");
            return (Criteria) this;
        }

        public Criteria andShopDiscountEqualTo(BigDecimal value) {
            addCriterion("shop_discount =", value, "shopDiscount");
            return (Criteria) this;
        }

        public Criteria andShopDiscountNotEqualTo(BigDecimal value) {
            addCriterion("shop_discount <>", value, "shopDiscount");
            return (Criteria) this;
        }

        public Criteria andShopDiscountGreaterThan(BigDecimal value) {
            addCriterion("shop_discount >", value, "shopDiscount");
            return (Criteria) this;
        }

        public Criteria andShopDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shop_discount >=", value, "shopDiscount");
            return (Criteria) this;
        }

        public Criteria andShopDiscountLessThan(BigDecimal value) {
            addCriterion("shop_discount <", value, "shopDiscount");
            return (Criteria) this;
        }

        public Criteria andShopDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shop_discount <=", value, "shopDiscount");
            return (Criteria) this;
        }

        public Criteria andShopDiscountIn(List<BigDecimal> values) {
            addCriterion("shop_discount in", values, "shopDiscount");
            return (Criteria) this;
        }

        public Criteria andShopDiscountNotIn(List<BigDecimal> values) {
            addCriterion("shop_discount not in", values, "shopDiscount");
            return (Criteria) this;
        }

        public Criteria andShopDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shop_discount between", value1, value2, "shopDiscount");
            return (Criteria) this;
        }

        public Criteria andShopDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shop_discount not between", value1, value2, "shopDiscount");
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

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andSpecialIsNull() {
            addCriterion("special is null");
            return (Criteria) this;
        }

        public Criteria andSpecialIsNotNull() {
            addCriterion("special is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialEqualTo(String value) {
            addCriterion("special =", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialNotEqualTo(String value) {
            addCriterion("special <>", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialGreaterThan(String value) {
            addCriterion("special >", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialGreaterThanOrEqualTo(String value) {
            addCriterion("special >=", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialLessThan(String value) {
            addCriterion("special <", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialLessThanOrEqualTo(String value) {
            addCriterion("special <=", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialLike(String value) {
            addCriterion("special like", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialNotLike(String value) {
            addCriterion("special not like", value, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialIn(List<String> values) {
            addCriterion("special in", values, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialNotIn(List<String> values) {
            addCriterion("special not in", values, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialBetween(String value1, String value2) {
            addCriterion("special between", value1, value2, "special");
            return (Criteria) this;
        }

        public Criteria andSpecialNotBetween(String value1, String value2) {
            addCriterion("special not between", value1, value2, "special");
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

        public Criteria andInterfacePersonIdIsNull() {
            addCriterion("interface_person_id is null");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdIsNotNull() {
            addCriterion("interface_person_id is not null");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdEqualTo(Integer value) {
            addCriterion("interface_person_id =", value, "interfacePersonId");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdNotEqualTo(Integer value) {
            addCriterion("interface_person_id <>", value, "interfacePersonId");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdGreaterThan(Integer value) {
            addCriterion("interface_person_id >", value, "interfacePersonId");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("interface_person_id >=", value, "interfacePersonId");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdLessThan(Integer value) {
            addCriterion("interface_person_id <", value, "interfacePersonId");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdLessThanOrEqualTo(Integer value) {
            addCriterion("interface_person_id <=", value, "interfacePersonId");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdIn(List<Integer> values) {
            addCriterion("interface_person_id in", values, "interfacePersonId");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdNotIn(List<Integer> values) {
            addCriterion("interface_person_id not in", values, "interfacePersonId");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdBetween(Integer value1, Integer value2) {
            addCriterion("interface_person_id between", value1, value2, "interfacePersonId");
            return (Criteria) this;
        }

        public Criteria andInterfacePersonIdNotBetween(Integer value1, Integer value2) {
            addCriterion("interface_person_id not between", value1, value2, "interfacePersonId");
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

        public Criteria andDutyParagraphIsNull() {
            addCriterion("duty_paragraph is null");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphIsNotNull() {
            addCriterion("duty_paragraph is not null");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphEqualTo(String value) {
            addCriterion("duty_paragraph =", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphNotEqualTo(String value) {
            addCriterion("duty_paragraph <>", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphGreaterThan(String value) {
            addCriterion("duty_paragraph >", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphGreaterThanOrEqualTo(String value) {
            addCriterion("duty_paragraph >=", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphLessThan(String value) {
            addCriterion("duty_paragraph <", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphLessThanOrEqualTo(String value) {
            addCriterion("duty_paragraph <=", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphLike(String value) {
            addCriterion("duty_paragraph like", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphNotLike(String value) {
            addCriterion("duty_paragraph not like", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphIn(List<String> values) {
            addCriterion("duty_paragraph in", values, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphNotIn(List<String> values) {
            addCriterion("duty_paragraph not in", values, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphBetween(String value1, String value2) {
            addCriterion("duty_paragraph between", value1, value2, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphNotBetween(String value1, String value2) {
            addCriterion("duty_paragraph not between", value1, value2, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andCheckStrIsNull() {
            addCriterion("check_str is null");
            return (Criteria) this;
        }

        public Criteria andCheckStrIsNotNull() {
            addCriterion("check_str is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStrEqualTo(String value) {
            addCriterion("check_str =", value, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrNotEqualTo(String value) {
            addCriterion("check_str <>", value, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrGreaterThan(String value) {
            addCriterion("check_str >", value, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrGreaterThanOrEqualTo(String value) {
            addCriterion("check_str >=", value, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrLessThan(String value) {
            addCriterion("check_str <", value, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrLessThanOrEqualTo(String value) {
            addCriterion("check_str <=", value, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrLike(String value) {
            addCriterion("check_str like", value, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrNotLike(String value) {
            addCriterion("check_str not like", value, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrIn(List<String> values) {
            addCriterion("check_str in", values, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrNotIn(List<String> values) {
            addCriterion("check_str not in", values, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrBetween(String value1, String value2) {
            addCriterion("check_str between", value1, value2, "checkStr");
            return (Criteria) this;
        }

        public Criteria andCheckStrNotBetween(String value1, String value2) {
            addCriterion("check_str not between", value1, value2, "checkStr");
            return (Criteria) this;
        }


        public Criteria andDeliveryIdIsNull() {
            addCriterion("delivery_Id is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdIsNotNull() {
            addCriterion("delivery_Id is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdEqualTo(Integer value) {
            addCriterion("delivery_Id =", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdNotEqualTo(Integer value) {
            addCriterion("delivery_Id <>", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdGreaterThan(Integer value) {
            addCriterion("delivery_Id >", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_Id >=", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdLessThan(Integer value) {
            addCriterion("delivery_Id <", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_Id <=", value, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdIn(List<Integer> values) {
            addCriterion("delivery_Id in", values, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdNotIn(List<Integer> values) {
            addCriterion("delivery_Id not in", values, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdBetween(Integer value1, Integer value2) {
            addCriterion("delivery_Id between", value1, value2, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andDeliveryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_Id not between", value1, value2, "deliveryId");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submit_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Date value) {
            addCriterion("submit_time =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Date value) {
            addCriterion("submit_time <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Date value) {
            addCriterion("submit_time >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submit_time >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Date value) {
            addCriterion("submit_time <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("submit_time <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Date> values) {
            addCriterion("submit_time in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Date> values) {
            addCriterion("submit_time not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("submit_time between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("submit_time not between", value1, value2, "submitTime");
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