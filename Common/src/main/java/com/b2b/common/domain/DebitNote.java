package com.b2b.common.domain;

import java.util.Date;

public class DebitNote {
    private String id;

    private Integer companyId;

    private Long debitnoteAmount;

    private String years;

    private String months;

    private Integer deleteStatus;

    private String remark;

    private Integer createUser;

    private Date createDate;
    
    private Integer flag;
    
    private Long subsidy;
    
    private Integer invoiceMonth;
    
    private Integer debitMonth;
    
    private Integer num5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Long getDebitnoteAmount() {
        return debitnoteAmount;
    }

    public void setDebitnoteAmount(Long debitnoteAmount) {
        this.debitnoteAmount = debitnoteAmount;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months == null ? null : months.trim();
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Long getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(Long subsidy) {
		this.subsidy = subsidy;
	}

	public Integer getInvoiceMonth() {
		return invoiceMonth;
	}

	public void setInvoiceMonth(Integer invoiceMonth) {
		this.invoiceMonth = invoiceMonth;
	}

	public Integer getDebitMonth() {
		return debitMonth;
	}

	public void setDebitMonth(Integer debitMonth) {
		this.debitMonth = debitMonth;
	}

	public Integer getNum5() {
		return num5;
	}

	public void setNum5(Integer num5) {
		this.num5 = num5;
	}
	
	
}