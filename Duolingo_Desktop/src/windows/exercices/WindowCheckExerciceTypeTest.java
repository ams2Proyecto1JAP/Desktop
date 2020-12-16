package windows.exercices;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Exercice;
import dao.ExerciceTest;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class WindowCheckExerciceTypeTest extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblToTransTitle;
	private JLabel lblToTrans;
	private JLabel lblOptions;
	private JButton btnOption1;
	private JButton btnOption2;
	private JButton btnOption3;
	private JButton btnValidar;
	private JButton btnOptionSelected;
	private ArrayList<JButton> buttonsOption;
	private Exercice exerciceContent;
	

	/**
	 * Create the frame.
	 */
	public WindowCheckExerciceTypeTest(ExerciceTest exerciceContent) {
		
		setBounds(100, 100, 450, 350);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblToTransTitle = new JLabel("Frase a traducir :");		
		lblToTrans = new JLabel("New label");
		lblOptions = new JLabel("Opciones :");
		btnOption1 = new JButton("Option1");
		btnOption2 = new JButton("Option2");
		btnOption3 = new JButton("Option3");
		btnValidar = new JButton("Validar");
		buttonsOption = new ArrayList<JButton>();
		
		this.exerciceContent = exerciceContent;
		
		btnOption1.addActionListener(this);
		btnOption2.addActionListener(this);
		btnOption3.addActionListener(this);
		btnValidar.addActionListener(this);
		buttonsOption.add(btnOption1);
		buttonsOption.add(btnOption2);
		buttonsOption.add(btnOption3);
		btnOption1.setBackground(Color.LIGHT_GRAY);
		btnOption2.setBackground(Color.LIGHT_GRAY);
		btnOption3.setBackground(Color.LIGHT_GRAY);
		
		lblToTrans.setText(exerciceContent.getToTrans());
		
		randomizeOptions(exerciceContent);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblToTransTitle, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
								.addComponent(lblOptions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblToTrans, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(67)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnOption1, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
								.addComponent(btnOption2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnOption3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(160)
							.addComponent(btnValidar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblToTransTitle)
					.addGap(18)
					.addComponent(lblToTrans)
					.addGap(26)
					.addComponent(lblOptions)
					.addGap(18)
					.addComponent(btnOption1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOption2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOption3)
					.addGap(18)
					.addComponent(btnValidar)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
	public void randomizeOptions(ExerciceTest exerciceContent) {
		ArrayList<String> options = exerciceContent.getAllOptions();
		int randomIndex = 0;
		for(JButton btn : buttonsOption) {
			randomIndex = (int) (Math.random()*options.size());
			btn.setText(options.get(randomIndex));
			options.remove(randomIndex);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ExerciceTest ex = (ExerciceTest) WindowCheckExerciceTypeTest.this.exerciceContent;
		JButton btnSelected = (JButton) e.getSource();
		
		if(btnSelected == btnValidar) {
			
			if(btnOptionSelected.getText().equals(ex.getTrans())) {
				JOptionPane.showMessageDialog(this, "La opcion seleccionada es correcta!");
			}else {
				JOptionPane.showMessageDialog(this, "La opcion seleccionada no es correcta!");
			}
		}else if(btnSelected == btnOption1) {
			btnOptionSelected = btnSelected;
			btnOption1.setBackground(Color.GREEN);
			btnOption2.setBackground(Color.LIGHT_GRAY);
			btnOption3.setBackground(Color.LIGHT_GRAY);
		}else if(btnSelected == btnOption2) {
			btnOptionSelected = btnSelected;
			btnOption2.setBackground(Color.GREEN);
			btnOption1.setBackground(Color.LIGHT_GRAY);
			btnOption3.setBackground(Color.LIGHT_GRAY);
		}else if(btnSelected == btnOption3) {
			btnOptionSelected = btnSelected;
			btnOption3.setBackground(Color.GREEN);
			btnOption1.setBackground(Color.LIGHT_GRAY);
			btnOption2.setBackground(Color.LIGHT_GRAY);
		}
		
	}
}
