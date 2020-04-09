package com.example.demo.repository;

import com.example.demo.entity.History;
import com.example.demo.entity.HistoryJpo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HistoryRepository implements HistoryRepositoryStore {

	private HistoryJpaRepository historyJpaRepository;
	
	public HistoryRepository(HistoryJpaRepository historyJpaRepository) {
		this.historyJpaRepository=historyJpaRepository;
	}
	
	@Override
	public void createHistory(History history) {
		HistoryJpo historyJpo = new HistoryJpo(history);
		this.historyJpaRepository.save(historyJpo);
		
	}


	@Override
	public List<History> retrieveMyHistoryByUserId(String userId) {

				
		return 	this.historyJpaRepository.findByUserIdOrderBySearchDateDesc(userId).stream().map(HistoryJpo::toModel).collect(Collectors.toList());

	}

	@Override
	public List<History> retrieveHistory() {
		
		return this.historyJpaRepository.findAll().stream().map(HistoryJpo::toModel).collect(Collectors.toList());
	}

}
