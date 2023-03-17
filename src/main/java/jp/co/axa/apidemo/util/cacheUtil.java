package jp.co.axa.apidemo.util;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * cacheing utility
 * */
public class cacheUtil<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = -7602477555624411996L;
	static final int DEFAULT_INITIAL_CAPACITY = 16;
	static final float DEFAULT_LOAD_FACTOR = 0.75f;
	static final int DEFAULT_CACHE_SIZE = 1024;
	
	//true : LRU(Least Recently Used), false : FIFO(first in first out)
	static final boolean DEFAULT_LRU_OR_FIFO_SWITCH = true;

	private final int cacheSize;

	/*
	 * default constructor
	 */
	public cacheUtil() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_CACHE_SIZE, DEFAULT_LRU_OR_FIFO_SWITCH);
	}
	
	/*
	 * constructor for switching
	 * param boolean value 
	 * true : LRU(Least Recently Used) 
	 * false : FIFO (first in first  out) 
	 */
	public cacheUtil(boolean LRU_OR_FIFO) {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_CACHE_SIZE, LRU_OR_FIFO);
	}

	public cacheUtil(int capacity, float loadFactor, int cacheSize, boolean LRU_OR_FIFO) {
		super(capacity, loadFactor, LRU_OR_FIFO);
		this.cacheSize = cacheSize;
	}

	/*
	 * return the ture when size be over than limit to remove the oldest one
	 * */
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		return size() > cacheSize;
	}
}