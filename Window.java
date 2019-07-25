// all the imports
import java.util.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.border.TitledBorder;

// class which creates the window 
public class Window extends JFrame{
	
	// initialize button groups
	ButtonGroup radioButtonGroup = new ButtonGroup();
	ButtonGroup ButtonGroup = new ButtonGroup();
	
	// Creating the frames
	private JFrame frame = new JFrame("Data Structures");
	
	// Creating the panels
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	private JPanel panel5 = new JPanel();
	
	// Creating the labels
	private JLabel label = new JLabel("Initialize Data Structure");
	private JLabel label2 = new JLabel("Sorted List: ");
	private JLabel label3 = new JLabel("");
	private JLabel label4 = new JLabel();
	
	// Creating the text fields
	private JTextField tf = new JTextField(10); 
	private JTextField tf2 = new JTextField(10); 
	
	// Creating the buttons
	private JButton add = new JButton("Add");
	private JButton clear = new JButton("Clear All");

	// Creating the different data structures
	List <Integer> storage = new ArrayList <Integer> ();
	List <Integer> choice1 = new LinkedList <Integer> ();
	Set <Integer> choice2 = new HashSet<Integer> ();
	Stack <Integer> choice3 = new Stack <Integer> ();
	Queue<Integer> choice4 = new LinkedList <Integer> ();
	List <Integer> choice5 = new ArrayList <Integer> ();
	
	// 	Creating the radio buttons
	JRadioButton ArrayList = new JRadioButton("ArrayList");
	JRadioButton LinkedList = new JRadioButton("LinkedList");
	JRadioButton Sets = new JRadioButton("Sets");
	JRadioButton Stacks = new JRadioButton("Stacks");
	JRadioButton Queues = new JRadioButton("Queues");
	
	// Creating the buttons
	JButton Add = new JButton("Add");
	JButton Remove = new JButton("Remove");
	JButton Find = new JButton("Binary Search");
	JButton Clear = new JButton("Clear");
	JButton Selection = new JButton("Selection Sort");
	
	// Initializing the integers
	private int temp = 0;
	private long duration = 0;
	
	// Function for selection sort in ArrayList
	private void SelectionSortArrayList(List<Integer> arr)
    {
        int n = arr.size();
 
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr.get(j) < arr.get(min_idx))
                    min_idx = j;
 
            // Swap the found minimum element with the first
            // element
            int temp = arr.get(min_idx);
            arr.set(min_idx, arr.get(i));
            arr.set(i, temp);
        }
    }
	
	// Function for selection sort in LinkedList
	private void SelectionSortLinkedList(List<Integer> arr)
    {
		// it is complicated to move the nodes around and to link them with other nodes would be inefficient
		// thus it made more sense to convert the linked list through list conversion		
		List<Integer> list = new LinkedList<Integer>(arr);
		System.out.println(list);
		SelectionSortArrayList(list);
    }
	
	// Function for selection sort in Set
	private void SelectionSortSet(Set<Integer> arr)
    {
		List<Integer> list = new ArrayList<Integer>(arr);
		SelectionSortArrayList(list);
    }

	// Function for selection sort in Stacks
	private void SelectionSortStack(Stack<Integer> stack0)
    {
	    for (int i = 0; i < stack0.size()-1; i++)
	    {
	        // Find the minimum element in unsorted array
	        int min_idx = i;
	        for (int j = i+1; j < stack0.size(); j++)
	            if (stack0.get(j) < stack0.get(min_idx))
	                min_idx = j;

	        // Swap the found minimum element with the first
	        // element
	        int temp = stack0.get(min_idx);
	        stack0.set(min_idx, stack0.get(i));
	        stack0.set(i, temp);   
	    }
    }
	
	// Function for finding the minimum index for selection sort in queues
	public static int minIndex(Queue<Integer> list, int sortIndex)
	 {
		 int min_index = -1;
		 int min_value = Integer.MAX_VALUE;
		 int s = list.size();	
		 for (int i = 0; i < s; i++)
		 {
			 int current = list.peek();
			 // This is dequeue() in Java STL
			 list.poll();
			 
			 if (current <= min_value && i <= sortIndex)
			 {
				 min_index = i;
				 min_value = current;
			 }
			 list.add(current); 
		 }
		 return min_index;
	 }

	// Moves given minimum element to rear of queue
	public static void insertMinToRear(Queue<Integer> list, int min_index)
	{
		int min_value = 0; 
		int s = list.size();
		for (int i = 0; i < s; i++)
		{
			int current = list.peek();
			list.poll();
			
			if (i != min_index)
				list.add(current);
			else
				min_value = current;
		}
		list.add(min_value);
	}

	// Function for for selection sort in queues
	public static void SelectionSortQueue(Queue<Integer> list)
	{

		for(int i = 1; i <= list.size(); i++)
		{
			int min_index = minIndex(list,list.size() - i);
			insertMinToRear(list, min_index);
		}
	}

	// Constructor for event object window
    public Window()
    {
    	// Set frame size 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

    	// Group the radio buttons.
    	radioButtonGroup.add(ArrayList);
    	radioButtonGroup.add(LinkedList);
    	radioButtonGroup.add(Sets);
    	radioButtonGroup.add(Stacks);
    	radioButtonGroup.add(Queues);

    	// Add buttons to ButtonGroup
    	ButtonGroup.add(Add);
    	ButtonGroup.add(Remove);
    	ButtonGroup.add(Find);
    	ButtonGroup.add(Clear);
    	
    	// Add action listeners to the radio buttons.
    	ArrayList.addActionListener(new RadioButtonListener());
    	LinkedList.addActionListener(new RadioButtonListener());
    	Sets.addActionListener(new RadioButtonListener());
    	Stacks.addActionListener(new RadioButtonListener());
    	Queues.addActionListener(new RadioButtonListener());
    	
    	// Add action listeners to the buttons.
    	Add.addActionListener(new RadioButtonListener());
    	Remove.addActionListener(new RadioButtonListener());
    	Find.addActionListener(new RadioButtonListener());
    	Clear.addActionListener(new RadioButtonListener());

    	// Add action listeners to buttons
        add.addActionListener(new TextListener());
        clear.addActionListener(new TextListener());
        tf.addActionListener(new TextListener());

    	Selection.addActionListener(new SortButtonListener());

    	// Create a panel and add the components to it
        panel.add(label); 
        panel.add(tf);
        panel.add(add);
        
    	// Create a panel and add the components to it 
    	panel2.add(ArrayList);
    	panel2.add(LinkedList);
    	panel2.add(Sets);
    	panel2.add(Stacks);
    	panel2.add(Queues);
    	panel2.setBorder(BorderFactory.createTitledBorder("Data Structures"));
    	
    	// Create a panel and add the components to it 
    	panel3.add(tf2);
    	panel3.add(Add);
    	panel3.add(Remove);
    	panel3.add(Find);
    	panel3.add(Clear);
    	panel3.setBorder(BorderFactory.createTitledBorder("Functions"));
    	
	   	// Create a panel and add the components to it
    	panel4.add(Selection);
    	panel4.add(label2);
    	panel4.add(label3);
    	panel4.add(label4);
    	panel4.setBorder(BorderFactory.createTitledBorder("Sorting"));
    	
    	// Create a panel and add the components to it
    	panel5.add(clear);
    	
    	// Set panel sizes
    	panel.setPreferredSize(new Dimension(100,50));
    	panel2.setPreferredSize(new Dimension(140,300));
    	panel3.setPreferredSize(new Dimension(121,300));
    	panel4.setPreferredSize(new Dimension(200, 300));
    	panel5.setPreferredSize(new Dimension(100,50));
    	
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.WEST, panel2);
        frame.getContentPane().add(BorderLayout.CENTER, panel3);
        frame.getContentPane().add(BorderLayout.EAST, panel4);
        frame.getContentPane().add(BorderLayout.SOUTH, panel5);
        frame.setVisible(true);
        
        // Add action listeners to mouse clicks for entering new text
        tf.addMouseListener(new MouseAdapter() {
        	  @Override
        	  public void mouseClicked(MouseEvent e) {
        	    tf.setText("");
        	  }
        	});
        
        tf2.addMouseListener(new MouseAdapter() {
      	  @Override
      	  public void mouseClicked(MouseEvent e) {
      	    tf2.setText("");
      	  }
      	});
    }
    
    // TextListener Class
  	private class TextListener implements ActionListener
    {

		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			if(actionCommand.equals("Add"))
			{
				storage.add(Integer.parseInt(tf.getText()));
		
			}

  			if(actionCommand.equals("Clear All"))
  			{
  				radioButtonGroup.clearSelection();
  				storage.clear();
  				choice1.clear();
  				choice2.clear();
  				choice3.clear();
  				choice4.clear();
  				choice5.clear();
  			}
  			
			System.out.println(storage);
		}	
    }
      	
    // SortButtonListener Class
  	private class SortButtonListener implements ActionListener
  	{
  		public void actionPerformed(ActionEvent e)
  		{
			String actionCommand = e.getActionCommand();
			
			// if selection sort button is pressed
  			if(actionCommand.equals("Selection Sort"))
  			{
  				label3.setText("");
  				
  				// if ArrayList button is pressed
  				if(ArrayList.isSelected())
  				{
  					long startTime = System.nanoTime();
  					SelectionSortArrayList(choice5);
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					for(int i: choice5)
						label3.setText(label3.getText() + "   " + Integer.toString(i));
					label4.setText(duration + " nanoseconds ");
					System.out.println(choice5);
  				}

  				// if LinkedList button is pressed
  				if(LinkedList.isSelected())
  				{
  					long startTime = System.nanoTime();
  					SelectionSortLinkedList(choice1);
  					System.out.println(choice1);
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					for(int i: choice1)
						label3.setText(label3.getText() + "   " + Integer.toString(i));
					label4.setText(duration + " nanoseconds ");
					System.out.println(choice1);
  				}
  				
  				// if Sets button is pressed
  				if(Sets.isSelected())
  				{
  					long startTime = System.nanoTime();
  					SelectionSortSet(choice2);
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					for(int i: choice2)
						label3.setText(label3.getText() + "   " + Integer.toString(i));
					label4.setText(duration + " nanoseconds ");
					System.out.println(choice2);
  				}
  				
  				// if Stacks button is pressed
  				if(Stacks.isSelected())
  				{
  					long startTime = System.nanoTime();
  					SelectionSortStack(choice3);
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					for(int i: choice3)
						label3.setText(label3.getText() + "   " + Integer.toString(i));
					label4.setText(duration + " nanoseconds ");
					System.out.println(choice3);
  				}
  				
  				// if Queues button is pressed
  				if(Queues.isSelected())
  				{
  					long startTime = System.nanoTime();
  					SelectionSortQueue(choice4);
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					for(int i: choice4)
						label3.setText(label3.getText() + "   " + Integer.toString(i));
					label4.setText(duration + " nanoseconds ");
					System.out.println(choice4);
  				}
  			}		
  		}
  	}
  	
  	// RadioButtonListener Class
    private class RadioButtonListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e) {
			
			// if ArrayList button is pressed, an ArrayList is created from the initial input 
			if(e.getSource() == ArrayList)
			{
//				JOptionPane.showMessageDialog(frame, "You have chosen ArrayList");
				choice5 = new ArrayList<Integer>(storage);
			}
			
			// if LinkedList button is pressed, a LinkedList is created from the initial input
			if(e.getSource() == LinkedList)
			{
//				JOptionPane.showMessageDialog(frame, "You have chosen LinkedList");
				choice1 = storage.stream().collect(Collectors.toCollection(LinkedList::new));
			}
			
			// if Sets button is pressed, a Set is created from the initial input
			if(e.getSource() == Sets)
			{
//				JOptionPane.showMessageDialog(frame, "You have chosen Sets");
				choice2 = new HashSet<Integer> (storage);
			}
			
			// if Stacks button is pressed, a Stack is created from the initial input
			if(e.getSource() == Stacks)
			{
//				JOptionPane.showMessageDialog(frame, "You have chosen Stacks");
				choice3.addAll(storage);
			}
			
			// if Queues button is pressed, a queue is created from the initial input
			if(e.getSource() == Queues)
			{
//				JOptionPane.showMessageDialog(frame, "You have chosen Queues");
				choice4 = new LinkedList<> (storage);
			}
			
			// if ArrayList button is pressed, all the operations are available for ArrayList
			if(ArrayList.isSelected())
			{
				if(e.getSource() == Add)
				{
					temp = Integer.parseInt(tf2.getText());
					long startTime = System.nanoTime();
					choice5.add(temp);
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, "That process took " + duration + " nanoseconds");
					System.out.println(choice5);
					System.out.println("That took " + duration + " ns");
				}
				
				if(e.getSource() == Remove)
				{	
					temp = Integer.parseInt(tf2.getText());
					long startTime = System.nanoTime();
					if(choice5.remove(Integer.valueOf(temp)))
					{
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, temp + " deleted. " + "That process took " + duration + " nanoseconds");
					System.out.println(choice5);
					System.out.println("That took " + duration + " ns");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Element not found, no element deleted.");
						System.out.println(choice5);
					}
						
				}
				
				if(e.getSource() == Find)
				{
					temp = Integer.parseInt(tf2.getText());
					String a;
					long startTime = System.nanoTime();
					int index = Collections.binarySearch(choice5, temp);
					if (index >= 0) {
						a = "Element found at index: " + Integer.toString(index);
						System.out.println(a);
					} else {
						a = "Element not found";
					    System.out.println(a);
					}
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, a + " and that took " + duration + " nanoseconds");
					System.out.println(choice5);
					System.out.println("That took " + duration + " ns");
				}
				
				if(e.getSource() == Clear)
				{
					long startTime = System.nanoTime();
					choice5.clear();
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, "That process took " + duration + " nanoseconds");
					System.out.println(choice5);
					System.out.println("That took " + duration + " ns");
				}
			
			}
			
			// if LinkedList button is pressed, all the operations are available for LinkedList
			if(LinkedList.isSelected())
			{
				if(e.getSource() == Add)
				{
					temp = Integer.parseInt(tf2.getText());
					long startTime = System.nanoTime();
					choice1.add(temp);
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, "That process took " + duration + " nanoseconds");
					System.out.println(choice1);
					System.out.println("That took " + duration + " ns");
				}
				
				if(e.getSource() == Remove)
				{		
					temp = Integer.parseInt(tf2.getText());
					long startTime = System.nanoTime();
					if(choice1.remove(Integer.valueOf(temp)))
					{
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, temp + " deleted. " + "That process took " + duration + " nanoseconds");
					System.out.println(choice1);
					System.out.println("That took " + duration + " ns");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Element not found, no element deleted.");
						System.out.println(choice1);
					}
				}
				
				if(e.getSource() == Find)
				{
					temp = Integer.parseInt(tf2.getText());
					String a;
					long startTime = System.nanoTime();
					int index = Collections.binarySearch(choice1, temp);
					if (index >= 0) {
						a = "Element found at index: " + Integer.toString(index);
						System.out.println(a);
					} else {
						a = "Element not found";
					    System.out.println(a);
					}
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, a + " and that took " + duration + " nanoseconds");
					System.out.println(choice1);
					System.out.println("That took " + duration + " ns");
				}
				
				if(e.getSource() == Clear)
				{
					long startTime = System.nanoTime();
					choice1.clear();
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, "That process took " + duration + " nanoseconds");
					System.out.println(choice1);
					System.out.println("That took " + duration + " ns");
				}
			
			}
			
			// if Sets button is pressed, all the operations are available for Sets
			if(Sets.isSelected())
			{
				if(e.getSource() == Add)
				{
					temp = Integer.parseInt(tf2.getText());
					long startTime = System.nanoTime();
					choice2.add(temp);
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, "That process took " + duration + " nanoseconds");
					System.out.println(choice2);
					System.out.println("That took " + duration + " ns");
				}
				
				if(e.getSource() == Remove)
				{					
					temp = Integer.parseInt(tf2.getText());
					long startTime = System.nanoTime();
					if(choice2.remove(Integer.valueOf(temp)))
					{
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, temp + " deleted. " + "That process took " + duration + " nanoseconds");
					System.out.println(choice2);
					System.out.println("That took " + duration + " ns");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Element not found, no element deleted.");
						System.out.println(choice2);
					}
				}
				
				if(e.getSource() == Find)
				{
					temp = Integer.parseInt(tf2.getText());
					String a;
					long startTime = System.nanoTime();
					List<Integer> tempList = new ArrayList<Integer>();
					tempList.addAll(choice2);
					int index = Collections.binarySearch(tempList, temp);
					if (index >= 0) {
						a = "Element found at index: " + Integer.toString(index);
						System.out.println(a);
					} else {
						a = "Element not found";
					    System.out.println(a);
					}
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, a + " and that took " + duration + " nanoseconds");
					System.out.println(choice2);
					System.out.println("That took " + duration + " ns");
				}
				
				if(e.getSource() == Clear)
				{
					long startTime = System.nanoTime();
					choice2.clear();
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, "That process took " + duration + " nanoseconds");
					System.out.println(choice2);
					System.out.println("That took " + duration + " ns");
				}
			
			}
			
			// if Stacks button is pressed, all the operations are available for Stacks
			if(Stacks.isSelected())
			{
				if(e.getSource() == Add)
				{
					temp = Integer.parseInt(tf2.getText());
					long startTime = System.nanoTime();
					choice3.add(temp);
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, "That process took " + duration + " nanoseconds");
					System.out.println(choice3);
					System.out.println("That took " + duration + " ns");
				}
				
				if(e.getSource() == Remove)
				{					
					temp = Integer.parseInt(tf2.getText());
					long startTime = System.nanoTime();
					if(choice3.remove(Integer.valueOf(temp)))
					{
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, temp + " deleted. " + "That process took " + duration + " nanoseconds");
					System.out.println(choice3);
					System.out.println("That took " + duration + " ns");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Element not found, no element deleted.");
						System.out.println(choice3);
					}
				}
				
				if(e.getSource() == Find)
				{
					temp = Integer.parseInt(tf2.getText());
					String a;
					long startTime = System.nanoTime();
					int index = Collections.binarySearch(choice3, temp);
					if (index >= 0) {
						a = "Element found at index: " + Integer.toString(index);
						System.out.println(a);
					} else {
						a = "Element not found";
					    System.out.println(a);
					}
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, a + " and that took " + duration + " nanoseconds");
					System.out.println(choice3);
					System.out.println("That took " + duration + " ns");
				}
				
				if(e.getSource() == Clear)
				{
					long startTime = System.nanoTime();
					choice3.clear();
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, "That process took " + duration + " nanoseconds");
					System.out.println(choice3);
					System.out.println("That took " + duration + " ns");
				}
			
			}
			
			// if Queues button is pressed, all the operations are available for Queues
			if(Queues.isSelected())
			{
				if(e.getSource() == Add)
				{
					temp = Integer.parseInt(tf2.getText());
					long startTime = System.nanoTime();
					choice4.add(temp);
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, "That process took " + duration + " nanoseconds");
					System.out.println(choice4);
					System.out.println("That took " + duration + " ns");
				}
				
				if(e.getSource() == Remove)
				{					
					temp = Integer.parseInt(tf2.getText());
					long startTime = System.nanoTime();
					if(choice4.remove(Integer.valueOf(temp)))
					{
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, temp + " deleted. " + "That process took " + duration + " nanoseconds");
					System.out.println(choice4);
					System.out.println("That took " + duration + " ns");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Element not found, no element deleted.");
						System.out.println(choice4);
					}
				}
				
				if(e.getSource() == Find)
				{
					temp = Integer.parseInt(tf2.getText());
					String a;
					long startTime = System.nanoTime();
					List<Integer> tempList = new ArrayList<Integer>();
					tempList.addAll(choice4);
					int index = Collections.binarySearch(tempList, temp);
					if (index >= 0) {
						a = "Element found at index: " + Integer.toString(index);
						System.out.println(a);
					} else {
						a = "Element not found";
					    System.out.println(a);
					}
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, a + " and that took " + duration + " nanoseconds");
					System.out.println(choice4);
					System.out.println("That took " + duration + " ns");
				}
				
				if(e.getSource() == Clear)
				{
					long startTime = System.nanoTime();
					choice4.clear();
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					JOptionPane.showMessageDialog(frame, duration + " nanoseconds");
					System.out.println(choice4);
					System.out.println("That took " + duration + " ns");
				}
			}
		}
    }
}
