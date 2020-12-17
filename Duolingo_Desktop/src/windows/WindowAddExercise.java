package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import duolingo.lib.model.CatModel;
import duolingo.lib.model.LvlModel;
import windows.exercices.WindowExerciceFormOpenTrad;
import windows.exercices.WindowExerciceTypeTest;

import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

public class WindowAddExercise extends JFrame {

	private JPanel contentPane;

	private LvlModel lvl;
	
	
	JLabel lblLangSrc; 
	
	JLabel lblLangDst; 
	
	JLabel lblCat; 

	JLabel lblLvl; 

	/**
	 * Create the frame.
	 */
	public WindowAddExercise(LvlModel lvl) {
		setFrame();
		this.lvl = lvl;
		setLabels();
		
	}
	public void setLabels() {
		lblLangSrc.setText("Idioma origen: " + lvl.getCat().getCrs().getLangOrigin().getNombre());
		lblLangDst.setText("Idioma destino: " + lvl.getCat().getCrs().getLangDestiny().getNombre());
		lblCat.setText("Categor�a: " + lvl.getCat().getName());
		lblLvl.setText("Nivel: " + lvl.getName());
	}
	
	public void setFrame(){
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 765, 520);
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
		
		JButton btnTestType = new JButton("");
		btnTestType.setIcon(new ImageIcon("resources/tipo_test.jpeg"));
		
		btnTestType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WindowExerciceTypeTest window = new WindowExerciceTypeTest(lvl);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		JButton btnTradOpen = new JButton("");
		btnTradOpen.setIcon(new ImageIcon("resources/traduccio_oberta.jpeg"));
		btnTradOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WindowExerciceFormOpenTrad window = new WindowExerciceFormOpenTrad(lvl);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JButton btnTradOrder = new JButton("");
		btnTradOrder.setIcon(new ImageIcon("resources/traduccio_ordena.jpeg"));		
		
		JButton btnListOpen = new JButton("");
		btnListOpen.setIcon(new ImageIcon("resources/listening_obert.jpeg"));
		
		
		JButton btnListReorder = new JButton("");
		btnListReorder.setIcon(new ImageIcon("resources/listening_reordena.jpeg"));
		
		JButton btnFillWord = new JButton("");
		btnFillWord.setIcon(new ImageIcon("resources/omple_paraula.jpeg"));
		
		JButton btnPair = new JButton("");
		btnPair.setIcon(new ImageIcon("resources/aparella_paraules.png"));
		
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnTestType, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnTradOpen, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnTradOrder, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnFillWord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnListOpen, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnPair, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnListReorder, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
					.addGap(42))
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnTradOrder, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addComponent(btnTradOpen, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addComponent(btnTestType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(32)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnListOpen, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnListReorder, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPair, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(btnFillWord, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
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
		lblLangSrc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblLangDst = new JLabel("New label");
		lblLangDst.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblCat = new JLabel("New label");
		lblCat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblLvl = new JLabel("New label");
		lblLvl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_leftUpPanel = new GroupLayout(leftUpPanel);
		gl_leftUpPanel.setHorizontalGroup(
			gl_leftUpPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftUpPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_leftUpPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_leftUpPanel.createSequentialGroup()
							.addGroup(gl_leftUpPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLangDst, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
								.addComponent(lblLangSrc, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_leftUpPanel.createSequentialGroup()
							.addGroup(gl_leftUpPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLvl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
								.addComponent(lblCat, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
							.addContainerGap())))
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

	public LvlModel getLvl() {
		return lvl;
	}
	public void setLvl(LvlModel lvl) {
		this.lvl = lvl;
	}
}
