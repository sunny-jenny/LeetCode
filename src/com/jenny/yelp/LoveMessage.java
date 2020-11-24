package com.jenny.yelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

public class LoveMessage {
	
	public String getReceiverWithMostLoves(List<Message> messages) {
		Map<String, List<Message>> receiverMap = new HashMap<>();
		Set<String> uniqueSet = new HashSet<>();
		for (Message m: messages) {
			populateMap(receiverMap, m, m.receiver, uniqueSet);
		}
		
		String[] result = getPersonWithMostOrLestLoves(receiverMap);
		return result[0];
	}
	
	public List<String> getTopKReceivers(List<Message> messages, int k) {
		Map<String, List<Message>> map = new HashMap<>();
		Set<String> uniqueSet = new HashSet<>();
		for (Message m: messages) {
			populateMap(map, m, m.receiver, uniqueSet);
		}
		
		// minHeap
		PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> a.equals(b) ? b.compareTo(a) :  map.get(a).size() - map.get(b).size());
		for (Entry<String, List<Message>> entry: map.entrySet()) {
			if (pq.size() < k || entry.getValue().size() >= map.get(pq.peek()).size()) {
				pq.offer(entry.getKey());
			}
			if (pq.size() > k) pq.poll();
		}
		List<String> res = new ArrayList<>();
		while (!pq.isEmpty()) {
			res.add(0, pq.poll());
		}
		return res;
	}
	
	// remove duplicates
	private void populateMap(Map<String, List<Message>> map, Message message, String personName, Set<String> uniqueSet) {
		if (uniqueSet.contains(message.sender + "#" + message.receiver + "#" + message.content)) return;
		map.putIfAbsent(personName, new ArrayList<>());
		map.get(personName).add(message);
		uniqueSet.add(message.sender + "#" + message.receiver + "#" + message.content);
	}
	
	private String[] getPersonWithMostOrLestLoves(Map<String, List<Message>> map) {
		String[] result = new String[2]; // String[0] is for most loves, String[1] is for least loves
		int countMost = 0;
		int countLeast = Integer.MAX_VALUE;
		for (Entry<String, List<Message>> entry: map.entrySet()) {
			if (entry.getValue().size() > countMost) {
				countMost = entry.getValue().size();
				result[0] = entry.getKey();
			}
			if (entry.getValue().size() < countLeast) {
				countLeast = entry.getValue().size();
				result[1] = entry.getKey();
			}
		}
		return result;
	}
	
	public class Message {
		private String sender;
		private String receiver;
		private String content;
		
		public Message(String sender, String receiver, String content) {
			this.sender = sender;
			this.receiver = receiver;
			this.content = content;
		}

		public String getSender() {
			return sender;
		}

		public void setSender(String sender) {
			this.sender = sender;
		}

		public String getReceiver() {
			return receiver;
		}

		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
}
