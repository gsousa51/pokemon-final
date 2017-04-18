package tests;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import model.HelixFossil;
import model.Item;
import model.MyItemList;
import model.Potion;
import model.SafariBall;

public class MyItemListTest {
	
	public Item potion = new Potion();
	public Item ball = new SafariBall();
	public Item fossil = new HelixFossil();

	@Test
	public void testAdd() {
		MyItemList list = MyItemList.getInstance();
		list.addItem(potion);
		list.addItem(ball);
		list.addItem(fossil);
		list.addItem(ball);
		list.addItem(ball);
		list.addItem(potion);
		assertEquals(6, list.size());
		assertEquals("Potion", list.getItem(potion).toString());
	}
	
	@Test
	public void testDelete() {
		MyItemList list = MyItemList.getInstance();
		list.addItem(potion);
		list.addItem(ball);
		int size = list.size();
		list.removeItem(potion);
		list.removeItem(ball);
		assertEquals(size-2, list.size());
	}
	
	@Test
	public void testIterator() {
		MyItemList list = MyItemList.getInstance();
		Iterator<Item> itr = list.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}
