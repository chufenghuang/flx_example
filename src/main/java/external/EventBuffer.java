package external;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EventBuffer<T> {

	List<T> queue;

	public EventBuffer() {
		queue = Collections.synchronizedList(new LinkedList<T>());
	}

	public T remove() {
		while (queue.isEmpty()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return queue.remove(0);
	}

	public void add(T e) {
		queue.add(e);
	}
}