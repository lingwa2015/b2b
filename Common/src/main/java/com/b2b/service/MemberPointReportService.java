package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.MemberPointReport;

public interface MemberPointReportService {

	void save(MemberPointReport point);

	List<MemberPointReport> findByShopId(Integer userid);

	void deleteByID(MemberPointReport memberPointReport);

	MemberPointReport findById(Integer id);

}
