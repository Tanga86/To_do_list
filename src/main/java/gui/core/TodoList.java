package gui.core;

import dao.TaskDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TodoList implements Iterable<String>{

	private List<String> list = new ArrayList<>();
//	private  TaskDao taskDao;

//	public TodoList(TaskDao taskDao){
	//	this.taskDao=taskDao;}

	public void add(String item) {
		//taskDao.save(item);
		list.add(item);
	}

	public void removeAt(int i) {
		if (i >= 0 && i < list.size()) {
		//	taskDao.delete(i);
			list.remove(i);
		}
	}
	
	public int size() {
		return list.size();
	}
	
	public String elementAt(int i) {
		return list.get(i);
	}
	
	@Override
	public Iterator<String> iterator() {
		return list.iterator();
	}
}
