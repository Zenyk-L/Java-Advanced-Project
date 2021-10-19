package ua.lviv.lgs.university.domain;

import java.util.HashMap;
import java.util.Map;

public class MarksMap {
	private Map<Subject, Integer> markMap = new HashMap<Subject, Integer>();
	

	public Map<Subject, Integer> getMarkMap() {
		return markMap;
	}

	public void setMarkMap(Map<Subject, Integer> markMap) {
		this.markMap = markMap;
	}

	@Override
	public String toString() {
		return "MarksMap [markMap=" + markMap + "]";
	}

	public void putIfAbsent(Subject subject, int i) {
		markMap.putIfAbsent(subject, i);
		
	}
	
	public Integer put(Subject subject, Integer mark) {
		return markMap.put(subject, mark);
	}
	
	public Integer get(Subject subject) {
		return markMap.get(subject);
	}
	

}
