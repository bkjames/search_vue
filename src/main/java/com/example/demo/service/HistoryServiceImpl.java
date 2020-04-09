package com.example.demo.service;

import com.example.demo.repository.HistoryRepositoryStore;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HistoryServiceImpl extends HistoryLogic {

	public HistoryServiceImpl(HistoryRepositoryStore historyRepositoryStore) {
		super(historyRepositoryStore);
	}

	
}
