package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class MyItemList implements Iterable<Item>, Serializable{

	private static MyItemList self;
	private ArrayList<Item> itemList;

	private MyItemList() {
		itemList = new ArrayList<Item>();
	}

	public static synchronized MyItemList getInstance() {
		if (self == null)
			self = new MyItemList();
		return self;
	}

	public void addItem(Item item) {
		itemList.add(item);
	}
	
	public Item getItem(Item item) {
		return itemList.get(itemList.indexOf(item));
	}
	
	public void removeItem(Item item) {
		itemList.remove(itemList.indexOf(item));
	}
	
	public int size() {
		return itemList.size();
	}

	@Override
	public Iterator<Item> iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator<Item> {

		private int currentIndex;

		public MyIterator() {
			currentIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return currentIndex < itemList.size();
		}

		@Override
		public Item next() {
			Item item = itemList.get(currentIndex);
			currentIndex++;
			return item;
		}
	}
}