package edu.umd.cloud9.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrderedHashMapKIT<K extends Comparable> extends HashMapKI<K> {

	private static final long serialVersionUID = 8726031451L;

	/**
	 * Treats maps as if they were vectors and performs vector addition.
	 * 
	 * @param m
	 *            the other vector
	 */
	public void plus(OrderedHashMapKIT<K> m) {
		for (MapKI.Entry<K> e : m.entrySet()) {
			K key = e.getKey();

			if (this.containsKey(key)) {
				this.put(key, this.get(key) + e.getValue());
			} else {
				this.put(key, e.getValue());
			}
		}
	}

	/**
	 * Treats maps as if they were vectors and computes the dot product.
	 * 
	 * @param m
	 *            the other vector
	 */
	public int dot(OrderedHashMapKIT<K> m) {
		int s = 0;

		for (MapKI.Entry<K> e : m.entrySet()) {
			K key = e.getKey();

			if (this.containsKey(key)) {
				s += this.get(key) * e.getValue();
			}
		}

		return s;
	}

	/**
	 * Returns entries sorted by descending value. Ties broken by the natural
	 * sort order of the feature.
	 * 
	 * @return entries sorted by descending value
	 */
	public SortedSet<MapKI.Entry<K>> getEntriesSortedByValue() {
		SortedSet<MapKI.Entry<K>> entries = new TreeSet<MapKI.Entry<K>>(
				new Comparator<MapKI.Entry<K>>() {
					@SuppressWarnings("unchecked")
					public int compare(MapKI.Entry<K> e1, MapKI.Entry<K> e2) {
						if (e1.getValue() > e2.getValue()) {
							return -1;
						} else if (e1.getValue() < e2.getValue()) {
							return 1;
						}
						return e1.getKey().compareTo(e2.getKey());
					}
				});

		for (MapKI.Entry<K> entry : this.entrySet()) {
			entries.add(entry);
		}

		return Collections.unmodifiableSortedSet(entries);
	}

	/**
	 * Returns top <i>n</i> entries sorted by descending value. Ties broken by
	 * the natural sort order of the feature.
	 * 
	 * @param n
	 *            number of entries to return
	 * @return top <i>n</i> entries sorted by descending value
	 */
	public SortedSet<MapKI.Entry<K>> getEntriesSortedByValue(int n) {
		// TODO: this should be rewritten to use a Fibonacci heap

		SortedSet<MapKI.Entry<K>> entries = new TreeSet<MapKI.Entry<K>>(
				new Comparator<MapKI.Entry<K>>() {
					@SuppressWarnings("unchecked")
					public int compare(MapKI.Entry<K> e1, MapKI.Entry<K> e2) {
						if (e1.getValue() > e2.getValue()) {
							return -1;
						} else if (e1.getValue() < e2.getValue()) {
							return 1;
						}
						return e1.getKey().compareTo(e2.getKey());
					}
				});

		int cnt = 0;
		for (MapKI.Entry<K> entry : getEntriesSortedByValue()) {
			entries.add(entry);
			cnt++;
			if (cnt >= n)
				break;
		}

		return Collections.unmodifiableSortedSet(entries);
	}

}