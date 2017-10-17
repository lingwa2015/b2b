package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonUserExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PersonUserExample() {
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

        public Criteria andPostIsNull() {
            addCriterion("post is null");
            return (Criteria) this;
        }

        public Criteria andPostIsNotNull() {
            addCriterion("post is not null");
            return (Criteria) this;
        }

        public Criteria andPostEqualTo(String value) {
            addCriterion("post =", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotEqualTo(String value) {
            addCriterion("post <>", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThan(String value) {
            addCriterion("post >", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThanOrEqualTo(String value) {
            addCriterion("post >=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThan(String value) {
            addCriterion("post <", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThanOrEqualTo(String value) {
            addCriterion("post <=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLike(String value) {
            addCriterion("post like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotLike(String value) {
            addCriterion("post not like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostIn(List<String> values) {
            addCriterion("post in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotIn(List<String> values) {
            addCriterion("post not in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostBetween(String value1, String value2) {
            addCriterion("post between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotBetween(String value1, String value2) {
            addCriterion("post not between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andWeixinnumIsNull() {
            addCriterion("weixinnum is null");
            return (Criteria) this;
        }

        public Criteria andWeixinnumIsNotNull() {
            addCriterion("weixinnum is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinnumEqualTo(String value) {
            addCriterion("weixinnum =", value, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumNotEqualTo(String value) {
            addCriterion("weixinnum <>", value, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumGreaterThan(String value) {
            addCriterion("weixinnum >", value, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumGreaterThanOrEqualTo(String value) {
            addCriterion("weixinnum >=", value, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumLessThan(String value) {
            addCriterion("weixinnum <", value, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumLessThanOrEqualTo(String value) {
            addCriterion("weixinnum <=", value, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumLike(String value) {
            addCriterion("weixinnum like", value, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumNotLike(String value) {
            addCriterion("weixinnum not like", value, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumIn(List<String> values) {
            addCriterion("weixinnum in", values, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumNotIn(List<String> values) {
            addCriterion("weixinnum not in", values, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumBetween(String value1, String value2) {
            addCriterion("weixinnum between", value1, value2, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinnumNotBetween(String value1, String value2) {
            addCriterion("weixinnum not between", value1, value2, "weixinnum");
            return (Criteria) this;
        }

        public Criteria andWeixinimgIsNull() {
            addCriterion("weixinimg is null");
            return (Criteria) this;
        }

        public Criteria andWeixinimgIsNotNull() {
            addCriterion("weixinimg is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinimgEqualTo(String value) {
            addCriterion("weixinimg =", value, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgNotEqualTo(String value) {
            addCriterion("weixinimg <>", value, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgGreaterThan(String value) {
            addCriterion("weixinimg >", value, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgGreaterThanOrEqualTo(String value) {
            addCriterion("weixinimg >=", value, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgLessThan(String value) {
            addCriterion("weixinimg <", value, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgLessThanOrEqualTo(String value) {
            addCriterion("weixinimg <=", value, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgLike(String value) {
            addCriterion("weixinimg like", value, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgNotLike(String value) {
            addCriterion("weixinimg not like", value, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgIn(List<String> values) {
            addCriterion("weixinimg in", values, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgNotIn(List<String> values) {
            addCriterion("weixinimg not in", values, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgBetween(String value1, String value2) {
            addCriterion("weixinimg between", value1, value2, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andWeixinimgNotBetween(String value1, String value2) {
            addCriterion("weixinimg not between", value1, value2, "weixinimg");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
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

        public Criteria andDingCityIdIsNull() {
            addCriterion("ding_city_id is null");
            return (Criteria) this;
        }

        public Criteria andDingCityIdIsNotNull() {
            addCriterion("ding_city_id is not null");
            return (Criteria) this;
        }

        public Criteria andDingCityIdEqualTo(Integer value) {
            addCriterion("ding_city_id =", value, "dingCityId");
            return (Criteria) this;
        }

        public Criteria andDingCityIdNotEqualTo(Integer value) {
            addCriterion("ding_city_id <>", value, "dingCityId");
            return (Criteria) this;
        }

        public Criteria andDingCityIdGreaterThan(Integer value) {
            addCriterion("ding_city_id >", value, "dingCityId");
            return (Criteria) this;
        }

        public Criteria andDingCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ding_city_id >=", value, "dingCityId");
            return (Criteria) this;
        }

        public Criteria andDingCityIdLessThan(Integer value) {
            addCriterion("ding_city_id <", value, "dingCityId");
            return (Criteria) this;
        }

        public Criteria andDingCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("ding_city_id <=", value, "dingCityId");
            return (Criteria) this;
        }

        public Criteria andDingCityIdIn(List<Integer> values) {
            addCriterion("ding_city_id in", values, "dingCityId");
            return (Criteria) this;
        }

        public Criteria andDingCityIdNotIn(List<Integer> values) {
            addCriterion("ding_city_id not in", values, "dingCityId");
            return (Criteria) this;
        }

        public Criteria andDingCityIdBetween(Integer value1, Integer value2) {
            addCriterion("ding_city_id between", value1, value2, "dingCityId");
            return (Criteria) this;
        }

        public Criteria andDingCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ding_city_id not between", value1, value2, "dingCityId");
            return (Criteria) this;
        }

        public Criteria andIsGmIsNull() {
            addCriterion("is_gm is null");
            return (Criteria) this;
        }

        public Criteria andIsGmIsNotNull() {
            addCriterion("is_gm is not null");
            return (Criteria) this;
        }

        public Criteria andIsGmEqualTo(Integer value) {
            addCriterion("is_gm =", value, "isGm");
            return (Criteria) this;
        }

        public Criteria andIsGmNotEqualTo(Integer value) {
            addCriterion("is_gm <>", value, "isGm");
            return (Criteria) this;
        }

        public Criteria andIsGmGreaterThan(Integer value) {
            addCriterion("is_gm >", value, "isGm");
            return (Criteria) this;
        }

        public Criteria andIsGmGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_gm >=", value, "isGm");
            return (Criteria) this;
        }

        public Criteria andIsGmLessThan(Integer value) {
            addCriterion("is_gm <", value, "isGm");
            return (Criteria) this;
        }

        public Criteria andIsGmLessThanOrEqualTo(Integer value) {
            addCriterion("is_gm <=", value, "isGm");
            return (Criteria) this;
        }

        public Criteria andIsGmIn(List<Integer> values) {
            addCriterion("is_gm in", values, "isGm");
            return (Criteria) this;
        }

        public Criteria andIsGmNotIn(List<Integer> values) {
            addCriterion("is_gm not in", values, "isGm");
            return (Criteria) this;
        }

        public Criteria andIsGmBetween(Integer value1, Integer value2) {
            addCriterion("is_gm between", value1, value2, "isGm");
            return (Criteria) this;
        }

        public Criteria andIsGmNotBetween(Integer value1, Integer value2) {
            addCriterion("is_gm not between", value1, value2, "isGm");
            return (Criteria) this;
        }

        public Criteria andGreadIsNull() {
            addCriterion("gread is null");
            return (Criteria) this;
        }

        public Criteria andGreadIsNotNull() {
            addCriterion("gread is not null");
            return (Criteria) this;
        }

        public Criteria andGreadEqualTo(Integer value) {
            addCriterion("gread =", value, "gread");
            return (Criteria) this;
        }

        public Criteria andGreadNotEqualTo(Integer value) {
            addCriterion("gread <>", value, "gread");
            return (Criteria) this;
        }

        public Criteria andGreadGreaterThan(Integer value) {
            addCriterion("gread >", value, "gread");
            return (Criteria) this;
        }

        public Criteria andGreadGreaterThanOrEqualTo(Integer value) {
            addCriterion("gread >=", value, "gread");
            return (Criteria) this;
        }

        public Criteria andGreadLessThan(Integer value) {
            addCriterion("gread <", value, "gread");
            return (Criteria) this;
        }

        public Criteria andGreadLessThanOrEqualTo(Integer value) {
            addCriterion("gread <=", value, "gread");
            return (Criteria) this;
        }

        public Criteria andGreadIn(List<Integer> values) {
            addCriterion("gread in", values, "gread");
            return (Criteria) this;
        }

        public Criteria andGreadNotIn(List<Integer> values) {
            addCriterion("gread not in", values, "gread");
            return (Criteria) this;
        }

        public Criteria andGreadBetween(Integer value1, Integer value2) {
            addCriterion("gread between", value1, value2, "gread");
            return (Criteria) this;
        }

        public Criteria andGreadNotBetween(Integer value1, Integer value2) {
            addCriterion("gread not between", value1, value2, "gread");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNull() {
            addCriterion("manager_id is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNotNull() {
            addCriterion("manager_id is not null");
            return (Criteria) this;
        }

        public Criteria andManagerIdEqualTo(Integer value) {
            addCriterion("manager_id =", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotEqualTo(Integer value) {
            addCriterion("manager_id <>", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThan(Integer value) {
            addCriterion("manager_id >", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("manager_id >=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThan(Integer value) {
            addCriterion("manager_id <", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThanOrEqualTo(Integer value) {
            addCriterion("manager_id <=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdIn(List<Integer> values) {
            addCriterion("manager_id in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotIn(List<Integer> values) {
            addCriterion("manager_id not in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdBetween(Integer value1, Integer value2) {
            addCriterion("manager_id between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("manager_id not between", value1, value2, "managerId");
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