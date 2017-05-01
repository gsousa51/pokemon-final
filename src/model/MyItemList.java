package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class MyItemList implements Iterable<Item>, Serializable {

	private static MyItemList self;
	private ArrayList<Item> itemList;

	private MyItemList() {
		itemList = new ArrayList<Item>();

		for (int i = 0; i < 30; i++) {
			itemList.add(new SafariBall());
		}
	}

	public static synchronized MyItemList getInstance() {
		if (self == null)
			self = new MyItemList();
		return self;
	}

	public void addItem(Item item) {
		itemList.add(item);
		//TODO: Remove this. Used for testing.
		for(Item i : itemList){
			System.out.println(i.toString());
		}
	}

	public Item getItem(Item item) {
		return itemList.get(itemList.indexOf(item));
	}

	public void removeItem(String itemName) {
		for (Item i : itemList) {
			if (i.toString().equals(itemName)) {
				itemList.remove(i);
				break;
			}
		}
	}

	public int size() {
		return itemList.size();
	}

	public int getItemCount(String itemName) {
		int count = 0;
		for (Item i : itemList) {
			if (i.toString().equals(itemName))
				count++;
		}
		return count;
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