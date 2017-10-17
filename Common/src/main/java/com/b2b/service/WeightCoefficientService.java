package com.b2b.service;

import com.b2b.common.domain.WeightCoefficient;
import com.b2b.page.Page;

public interface WeightCoefficientService {

	Page<WeightCoefficient> findPage(int currentPage, int pageSize);

	WeightCoefficient findById(Integer id);

	void update(WeightCoefficient dto);

	void create(WeightCoefficient dto);

	void delete(WeightCoefficient weightCoefficient);

}
