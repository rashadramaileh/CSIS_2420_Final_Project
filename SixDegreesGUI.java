package sixDegrees;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * This is the main class for our program. It creates the GUI (Graphical User Interface) and runs
 * the main operations.
 * 
 * @author Rashad Ramaileh & Isaac Finley
 *
 */
public class SixDegreesGUI extends JFrame {

	private JPanel contentPane;
	private JLabel [] relationOutput = new JLabel[7];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SixDegreesGUI frame = new SixDegreesGUI();
					frame.setTitle("Six Degrees");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SixDegreesGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel titlePanel = createTitlePanel();
		contentPane.add(titlePanel, BorderLayout.NORTH);

		JPanel inputPanel = createInputPanel();
		contentPane.add(inputPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Creates an input panel that contains all of the JLabels that must be modified. The user also inputs
	 * their text here so the shortest paths can be calculated for two respective actors/actresses. 
	 * @return
	 */
	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(null);

		JLabel lblInputTitle = new JLabel("Please enter two names below:");
		lblInputTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputTitle.setBounds(197, 60, 489, 24);
		lblInputTitle.setFont(new Font("Eras Demi ITC", Font.PLAIN, 24));
		inputPanel.add(lblInputTitle);

		JLabel lblFormatInstr = new JLabel("Names must seperated by a comma and a space.    Format: Name Surname, Name Surname     Example: Kate Hudson, Julie Christie");
		lblFormatInstr.setFont(new Font("Eras Medium ITC", Font.PLAIN, 11));
		lblFormatInstr.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormatInstr.setBounds(99, 124, 663, 24);
		inputPanel.add(lblFormatInstr);

		JTextPane textInputField = new JTextPane();
		textInputField.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
		textInputField.setBounds(289, 99, 293, 24);
		inputPanel.add(textInputField);
		
		JLabel lblBaconNumberTitle = new JLabel("");
		lblBaconNumberTitle.setFont(new Font("Eras Bold ITC", Font.PLAIN, 24));
		lblBaconNumberTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaconNumberTitle.setBounds(91, 241, 709, 36);
		inputPanel.add(lblBaconNumberTitle);
		
		JLabel lblRelationship0 = new JLabel();
		lblRelationship0.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
		lblRelationship0.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationship0.setBounds(100, 292, 700, 18);
		relationOutput[0] = lblRelationship0;
		inputPanel.add(lblRelationship0);
		
		JLabel lblRelationship1 = new JLabel();
		lblRelationship1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationship1.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
		lblRelationship1.setBounds(100, 315, 700, 18);
		relationOutput[1] = lblRelationship1;
		inputPanel.add(lblRelationship1);
		
		JLabel lblRelationship2 = new JLabel();
		lblRelationship2.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationship2.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
		lblRelationship2.setBounds(100, 338, 700, 18);
		relationOutput[2] = lblRelationship2;
		inputPanel.add(lblRelationship2);
		
		JLabel lblRelationship3 = new JLabel();
		lblRelationship3.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationship3.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
		lblRelationship3.setBounds(100, 361, 700, 18);
		relationOutput[3] = lblRelationship3;
		inputPanel.add(lblRelationship3);
		
		JLabel lblRelationship4 = new JLabel();
		lblRelationship4.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationship4.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
		lblRelationship4.setBounds(100, 384, 700, 18);
		relationOutput[4] = lblRelationship4;
		inputPanel.add(lblRelationship4);
		
		JLabel lblRelationship5 = new JLabel();
		lblRelationship5.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationship5.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
		lblRelationship5.setBounds(100, 405, 700, 18);
		relationOutput[5] = lblRelationship5;
		inputPanel.add(lblRelationship5);
		
		JLabel lblRelationship6 = new JLabel();
		lblRelationship6.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationship6.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
		lblRelationship6.setBounds(100, 425, 700, 18);
		relationOutput[6] = lblRelationship6;
		inputPanel.add(lblRelationship6);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//sets relationship Jlabels text to empty
				for(int i = 0; i < relationOutput.length; i++) { 
					relationOutput[i].setText("");
				}
				
				//gets users input and stores it as a string
				String temp = textInputField.getText();
				
				//splits string into two names, adds it to an array
				String[] actor = temp.split(", ");
				
				//Input checker, if two actors are not given an error message in red is displayed
				if(actor.length < 2) {
					lblBaconNumberTitle.setForeground(Color.RED);
					lblBaconNumberTitle.setText("Invalid Input!");
				} else {
					//creates initial graph
					Graph graph = createGraph();
					int vertIndex = graph.computePaths(actor[0], actor[1]);
					int prevIndex;
					String relationship = "";
					if (vertIndex == -1) {
						lblBaconNumberTitle.setForeground(Color.BLACK);
						lblBaconNumberTitle.setText("No links were found!");
					}
					else if (vertIndex == -2) {
						lblBaconNumberTitle.setForeground(Color.BLACK);
						lblBaconNumberTitle.setText("The degree of separation is greater than 6.");
					} else {
						lblBaconNumberTitle.setForeground(Color.BLACK);
						lblBaconNumberTitle.setText(actor[0] +" -> " +  actor[1] + ": " + graph.adjLists[vertIndex].degree + " degrees of separation");
						prevIndex = graph.adjLists[vertIndex].whoBroughtYouIn;
						while (prevIndex!= -1) {		
							relationship += graph.getMovie(vertIndex, prevIndex) + ": " + graph.adjLists[prevIndex].actor + "; " + graph.adjLists[vertIndex].actor + "'\n";
							vertIndex = prevIndex;
							prevIndex = graph.adjLists[prevIndex].whoBroughtYouIn;
						}
						// Reversing relationship for output. Relation for Actor1->Actor2 and not Actor2->Actor1
						String [] relations = relationship.split("'\n");
					
						for (int i = relations.length - 1; i > -1; i-- ) {
							relationOutput[i].setText(relations[i]);
						}
					}
				}
			}	
		});
		btnSubmit.setBounds(387, 150, 100, 29);
		inputPanel.add(btnSubmit);
		
		return inputPanel;
	}
	
	/**
	 * Creates the main title for the GUI.
	 * @return
	 */
	private JPanel createTitlePanel() {
		JPanel titlePanel = new JPanel();
		JLabel lblTitle = new JLabel("Six Degrees of Kevin Bacon");
		lblTitle.setBorder(new EmptyBorder(15, 0, 10, 0));
		lblTitle.setFont(new Font("Eras Bold ITC", Font.PLAIN, 44));
		titlePanel.add(lblTitle);
		return titlePanel;
	}
	
	/**
	 * Reads in a file and creates a graph based off the contents.
	 * @return
	 */
	private Graph createGraph() {
		// Init Graph class
		Graph graph = new Graph(5000);
		
		// Open Movie File
		BufferedReader br = null;
		try {
	        InputStream inputStream = this.getClass().getResourceAsStream("MovieData.txt");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			br = new BufferedReader(inputStreamReader);
			String line, title;
			String [] actors;
			while ((line = br.readLine()) != null)
			{
				String [] words = line.split("[(]");
				int size = words.length;
				title = words[0];
				String actStr = words[size-1].replace(")", "");
				actors = actStr.split(",");
				// Ignore stand alone actors. This data is useless for finding  6 degrees of separation. 
				if (actors.length > 1)
					graph.makeEdge(actors[0].trim(), actors[1].trim(), title);
			}
			br.close();
		}
		catch (IOException d) {
			d.printStackTrace();
		}
		return graph;
	}
}
