package com.sxc.natasha.domain;

/**
 * Description: IPData 通过IP城市定位返回的信息
 *
 * @author:     zhengshutian
 * @version:    1.0
 * Filename:    GPSData.java
 * Create at:   2015-03-25
 *
 * Copyright:   Copyright (c)2015
 * Company:     songxiaocai
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-03-25      zhengshutian    1.0         1.0 Version
 */
public class IPData {

    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{

        private Address_detail address_detail; //地址信息

        public class Address_detail{

            private String province;  //省
            private String city;      //省
            private String district;  //区  通常获取不到

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

        }

        public Address_detail getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(Address_detail address_detail) {
            this.address_detail = address_detail;
        }

    }

}
