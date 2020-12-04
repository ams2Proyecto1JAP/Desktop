package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import duolingo.lib.model.CatModel;

import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class WindowAddExercise extends JFrame {

	private JPanel contentPane;

	private CatModel cat;
	
	private String lvlName;
	
	JLabel lblLangSrc; 
	
	JLabel lblLangDst; 
	
	JLabel lblCat; 

	JLabel lblLvl; 

	/**
	 * Create the frame.
	 */
	public WindowAddExercise(CatModel cat, String lvlName) {
		setFrame();
		this.setCat(cat);
		setLabels();
		
	}
	public void setLabels() {
		lblLangSrc.setText("Idioma origen: " + cat.getCrs().getLangOrigin().getNombre());
		lblLangDst.setText("Idioma origen: " + cat.getCrs().getLangDestiny().getNombre());
		lblCat.setText("Categoría: " + cat.getName());
		lblLvl.setText("Nivel: " + lvlName);
	}
	
	public void setFrame(){
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 750, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(null);
		leftPanel.setSize((int)(contentPane.getWidth()/100f*30f), contentPane.getHeight());
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(null);
		rightPanel.setSize((int)(contentPane.getWidth()/100f*30f), contentPane.getHeight());
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
				.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
		);
		
		JButton btnNewButton = new JButton("");
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_2 = new JButton("");
		
		JButton btnNewButton_1_1 = new JButton("");
		
		JButton btnNewButton_1_1_1 = new JButton("");
		
		JButton btnNewButton_3 = new JButton("");
		
		JButton btnNewButton_4_1 = new JButton("");
		
		JButton btnNewButton_1_2 = new JButton("");
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addGap(44)
					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addGap(42))
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnNewButton_1_2, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_4_1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_4_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		rightPanel.setLayout(gl_rightPanel);
		
		JPanel leftUpPanel = new JPanel();
		
		JPanel leftDownPanel = new JPanel();
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(leftUpPanel, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
				.addComponent(leftDownPanel, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addComponent(leftUpPanel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(leftDownPanel, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
		);
		
		lblLangSrc = new JLabel("New label");
		
		lblLangDst = new JLabel("New label");
		
		lblCat = new JLabel("New label");
		
		lblLvl = new JLabel("New label");
		GroupLayout gl_leftUpPanel = new GroupLayout(leftUpPanel);
		gl_leftUpPanel.setHorizontalGroup(
			gl_leftUpPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftUpPanel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_leftUpPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_leftUpPanel.createSequentialGroup()
							.addComponent(lblLvl, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_leftUpPanel.createSequentialGroup()
							.addGroup(gl_leftUpPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCat, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
								.addComponent(lblLangDst, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLangSrc, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
							.addGap(20))))
		);
		gl_leftUpPanel.setVerticalGroup(
			gl_leftUpPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftUpPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLangSrc, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLangDst, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCat, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLvl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		leftUpPanel.setLayout(gl_leftUpPanel);
		leftPanel.setLayout(gl_leftPanel);
		contentPane.setLayout(gl_contentPane);
	}

	public CatModel getCat() {
		return cat;
	}

	public void setCat(CatModel cat) {
		this.cat = cat;
	}
}
