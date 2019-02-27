package gui.frames;

import dao.TaskDao;
import gui.core.TodoList;

import javax.swing.*;

public class TodoListModel extends AbstractListModel<String> {
	private static final long serialVersionUID = 1L;
	
	private TodoList list;
	
	public TodoListModel(TodoList list) {
		this.list = list;
	}
	
	public void removeAt(int i) {
		//
		list.removeAt(i);
		fireContentsChanged(this, i, i);
	}
	
	public void add(String task) {
		//
		list.add(task);
		fireContentsChanged(this,
				list.size() - 1, list.size() - 1);
	}
	
	@Override
	public int getSize() {
		return list.size();
	}
	@Override
	public String getElementAt(int index) {
		return list.elementAt(index);
	}
}
