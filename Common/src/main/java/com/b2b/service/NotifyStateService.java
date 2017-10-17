package com.b2b.service;

import com.b2b.common.domain.NotifyState;

public interface NotifyStateService {

	void insert(NotifyState state);

	NotifyState findById(String string);

}
