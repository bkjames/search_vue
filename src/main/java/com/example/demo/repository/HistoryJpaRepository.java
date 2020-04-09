package com.example.demo.repository;

import com.example.demo.entity.HistoryJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HistoryJpaRepository extends JpaRepository<HistoryJpo, String> {
	
	List<HistoryJpo> findByUserIdOrderBySearchDateDesc(String userId);

}
