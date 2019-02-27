package gui.frames;

import da.HibernateService;
import dao.TaskDao;
import enit.Task;
import gui.core.TodoList;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.Box.createVerticalStrut;

public class MainWindow extends JFrame{


	private TaskDao taskDao =new TaskDao(HibernateService.getSessionFactory());

	private static final long serialVersionUID = 1L;
	private JPanel mainContentPane;
	private JPanel newTaskControls;
	private JButton addTaskButton;
	private JTextField newTaskField;
	private JScrollPane taskListScrollPane;
	private JPanel taskListControls;
	private JButton deleteButton;
	private JList<String> taskList;
	private JLabel statusBar;
		
	private TodoList todoList;
	private TodoListModel todoListModel;

	public MainWindow(){
		todoList = new TodoList();
		todoListModel = new TodoListModel(todoList);
		
		setContentPane( getMainContentPane() );
		
		setTitle("Todo list");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(250, 250));
		pack();
	}

	private Container getMainContentPane() {
		if (mainContentPane == null) {
			mainContentPane = new JPanel();
			mainContentPane.setLayout(new BorderLayout());
			
			mainContentPane.add(getNewTaskControls(), BorderLayout.NORTH);
			mainContentPane.add(getTasksListScrollPane(), BorderLayout.CENTER);
			mainContentPane.add(getTasksListControls(), BorderLayout.EAST);
			mainContentPane.add(getStatusBar(), BorderLayout.SOUTH);
			
		}
		return mainContentPane;
	}

	private Component getNewTaskControls() {
		if (newTaskControls == null) {
			newTaskControls = new JPanel();
			
			BorderLayout layout = new BorderLayout();
			newTaskControls.setLayout(layout);
			layout.setHgap(5);
			newTaskControls.setBorder(createEmptyBorder(10,0,10,10));
			
			newTaskControls.add(getNewTaskField(), BorderLayout.CENTER);
			newTaskControls.add(getAddTaskButton(), BorderLayout.EAST);
		}
		
		return newTaskControls;
	}

	private JTextField getNewTaskField() {
		if (newTaskField == null) {
			newTaskField = new JTextField();
		}
		return newTaskField;
	}

	private Component getTasksListScrollPane() {
		if (taskListScrollPane == null) {
			taskListScrollPane = new JScrollPane(getTaskList());
		}
		return taskListScrollPane;
	}

	private JList<String> getTaskList() {
		if (taskList == null) {
			taskList = new JList<>();
			taskList.setModel(todoListModel);
		}
		return taskList;
	}

	private Component getTasksListControls() {
		if (taskListControls == null) {
			taskListControls = new JPanel();
			
			BoxLayout layout = new BoxLayout(taskListControls, BoxLayout.Y_AXIS);
			taskListControls.setLayout(layout);
			taskListControls.setBorder(createEmptyBorder(5, 5, 5, 5));
			
			taskListControls.add(createVerticalStrut(10));

			JButton button = getDeleteButton();
			button.setAlignmentX(CENTER_ALIGNMENT);
			taskListControls.add(button);
			
			taskListControls.add(createVerticalStrut(10));
		}
		
		return taskListControls;
	}

	private JButton getDeleteButton() {

		if (deleteButton == null) {
			deleteButton = new JButton("Delete");
			deleteButton.setIcon(createIcon("bin.png"));
			
			deleteButton.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					int remmoveIndex =getTaskList().getSelectedIndex();
					String task =todoListModel.getElementAt((remmoveIndex));
					todoListModel.removeAt(getTaskList().getSelectedIndex());
				taskDao.delete(task);
				}
			});
		}
		
		return deleteButton;
	}

	private JButton getAddTaskButton() {
		if (addTaskButton == null) {
			addTaskButton = new JButton("Add");
			addTaskButton.setIcon(createIcon("diary.png"));
			
			addTaskButton.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					if (getNewTaskField().getText().length() > 0) {
						String task = getNewTaskField().getText().trim();


						todoListModel.add(getNewTaskField().getText().trim());
						taskDao.save(task);
						getNewTaskField().setText("");
						
						getTaskList().setSelectedIndex(getTaskList().getModel().getSize()-1);
					}

				}
			});
		}
		
		return addTaskButton;
	}
	
	private JLabel getStatusBar() {
		if (statusBar == null) {
			statusBar = new JLabel("Number of tasks: 0");
			todoListModel.addListDataListener(new ListDataListener() {
				@Override
				public void contentsChanged(ListDataEvent e) {
					updateLabel(e);
				}
				
				private void updateLabel(ListDataEvent e) {
					getStatusBar().setText("Number of tasks: "+((TodoListModel)e.getSource()).getSize());
				}
				
				@Override
				public void intervalRemoved(ListDataEvent e) {}
				@Override
				public void intervalAdded(ListDataEvent e) {}
			});
		}
		
		return statusBar;
	}
	
	private Icon createIcon(String iconfilename) {
		return new ImageIcon(
				getClass().
				getResource("/"+iconfilename));
	}
	public void intilal(){
		List<Task> tasks=taskDao.getAll();
		for (Task task :tasks){
			todoListModel.add(task.getTask());
		}
	}


}
