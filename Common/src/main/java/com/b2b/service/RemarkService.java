package com.b2b.service;

import com.b2b.common.domain.Remark;

public interface RemarkService {

	Remark findByCondition(Integer id, String years, String months);

	void save(Remark remark2);

	void update(Remark dto);

}
