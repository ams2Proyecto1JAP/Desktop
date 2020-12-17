package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Exercice;
import dao.ExerciceTest;
import dao.ExerciceTestI;
import dao.ExerciceTestImpl;
import duolingo.lib.dao.implementations.ExsImpl;
import duolingo.lib.dao.implementations.LvlImpl;
import duolingo.lib.dao.interfaces.IExs;
import duolingo.lib.dao.interfaces.ILvl;
import duolingo.lib.model.CatModel;
import duolingo.lib.model.ExsModel;
import duolingo.lib.model.LvlModel;
import windows.exercices.WindowCheckExerciceTypeTest;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowCheckExercices extends JFrame  implements ActionListener {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panelButton;
	private GridBagConstraints cons;
	
	private ArrayList<JButton> exercicesButtons;
	private ArrayList<ExsModel> exercices;
	private ArrayList<ExerciceTest> exercicesContent;
	
	private IExs exDAO;
	private ExerciceTestI ExerciceTestDAO;
	

	/**
	 * Create the frame.
	 */
	public WindowCheckExercices(LvlModel lvl) {
		
		exercicesButtons = new ArrayList<JButton>();
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelButton = new JPanel();
		scrollPane.setViewportView(panelButton);
		panelButton.setLayout(new GridBagLayout());
		cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		cons.gridx = 0;
		
		exDAO = new ExsImpl();
		ExerciceTestDAO = new ExerciceTestImpl();
		
		exercices = loadExsByLvl(lvl);
		exercicesContent = getExerciceContent(exercices);
		createButtons(exercicesContent);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public ArrayList<ExsModel> loadExsByLvl(LvlModel lvl) {

		return exDAO.getAllExercicesByLvl(lvl);

	}
	
	public ArrayList<ExerciceTest> getExerciceContent(ArrayList<ExsModel> exercices) {
		ArrayList<ExerciceTest> exercicesContent = new ArrayList<ExerciceTest>();
		for(ExsModel e : exercices) {
			exercicesContent.add(ExerciceTestDAO.getExerciceTextByContent(e.getContent()));
		}
		return exercicesContent;
	}
	
	public void createButtons(ArrayList<ExerciceTest> exercicesContent) {
		String exType = new String();
		String question = new String();
		for(int i = 0; i < exercicesContent.size(); i++) {
			exType = exercicesContent.get(i).getType();
			question = exercicesContent.get(i).getToTrans();
			
			JButton btn = new JButton(exType+" - "+question);
			btn.addActionListener(this);
			exercicesButtons.add(btn);
			panelButton.add(btn, cons);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btnSelected = (JButton) e.getSource();
		int index = exercicesButtons.lastIndexOf(btnSelected);
		
		WindowCheckExerciceTypeTest windowCheckExerciceTest = new WindowCheckExerciceTypeTest(exercicesContent.get(index));
	}
}
