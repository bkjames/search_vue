package com.example.demo.service;

import com.example.demo.entity.History;
import com.example.demo.repository.HistoryRepositoryStore;
import com.example.demo.util.ValueComparator;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class HistoryLogic implements HistoryService {

	private static final int KEY_WORD_N=10;
	
	private HistoryRepositoryStore historyRepositoryStore;

	public HistoryLogic(HistoryRepositoryStore historyRepositoryStor) {
		this.historyRepositoryStore = historyRepositoryStor;
	}

	@Override
	public void registerHistory(History history) {

		historyRepositoryStore.createHistory(history);
	}

	@Override
	public List<History> findMyHistoryByUserId(String userId) {

		return historyRepositoryStore.retrieveMyHistoryByUserId(userId);
	}

	@Override
	public List<HashMap<String, Object>> findKeyWordTop10() {

		List<History> histories = historyRepositoryStore.retrieveHistory();
		List<HashMap<String, Object>> resultKeyWordTop10 = new ArrayList<HashMap<String,Object>>();
		if(!histories.isEmpty()) {
			Map<String, Long> getHistoryByQueryCounting = histories.stream().collect(Collectors.groupingBy(History::getQuery, LinkedHashMap::new, Collectors.counting()));						
			
			List<Entry<String, Long>> greatest = findGreatest(getHistoryByQueryCounting, KEY_WORD_N);
			
			Map<String, Long> keyWordTop10= new HashMap<String, Long>();

			for (Entry<String, Long> entry : greatest)
	        {
				keyWordTop10.put(entry.getKey(), entry.getValue());
	        }
			
	        ValueComparator bvc =  new ValueComparator(keyWordTop10);
	        TreeMap<String,Long> sortedMap = new TreeMap<String,Long>(bvc);
	        sortedMap.putAll(keyWordTop10);
	       
	        
	        for(Entry<String, Long> entry: sortedMap.entrySet()) {
	        	HashMap<String, Object> rankResult = new HashMap<>();
	        	rankResult.put("keyword",entry.getKey());
	        	rankResult.put("cnt",entry.getValue());
	        	resultKeyWordTop10.add(rankResult);
	        }
		}
		
        
       
		
		return resultKeyWordTop10;
	}

	private static <K, V extends Comparable<? super V>> List<Entry<K, V>> findGreatest(Map<K, V> map, int n) {
		Comparator<? super Entry<K, V>> comparator = new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e0, Entry<K, V> e1) {
				V v0 = e0.getValue();
				V v1 = e1.getValue();
				return v0.compareTo(v1);
			}
		};
		PriorityQueue<Entry<K, V>> highest = new PriorityQueue<Entry<K, V>>(n, comparator);
		for (Entry<K, V> entry : map.entrySet()) {
			highest.offer(entry);
			while (highest.size() > n) {
				highest.poll();
			}
		}

		List<Entry<K, V>> result = new ArrayList<Entry<K, V>>();
		while (highest.size() > 0) {
			result.add(highest.poll());
		}
		return result;
	}

}
