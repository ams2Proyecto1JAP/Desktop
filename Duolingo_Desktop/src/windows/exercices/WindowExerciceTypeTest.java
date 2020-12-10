package windows.exercices;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.json.JSONObject;

import duolingo.lib.dao.implementations.ExsImpl;
import duolingo.lib.dao.implementations.ExsTypeImpl;
import duolingo.lib.dao.interfaces.IExs;
import duolingo.lib.dao.interfaces.IExsType;
import duolingo.lib.model.ExsModel;
import duolingo.lib.model.ExsTypeModel;
import duolingo.lib.model.LvlModel;
import utils.Constants;

import javax.swing.JButton;


public class WindowExerciceTypeTest {

	private JFrame frame;
	private JTextField txtToTranslate;
	private JTextField txtTranslated;
	private JTextField txtBadTranslated1;
	private JTextField txtBadTranslated2;

	private LvlModel lvl;

	/**
	 * Create the application.
	 */
	public WindowExerciceTypeTest(LvlModel lvl) {
		this.setLvl(lvl);
		initialize();
	}

	
	public void finishButtonClick() {
		if (isStringVoid(txtToTranslate.getText()) ||
			isStringVoid(txtBadTranslated2 .getText()) ||
			isStringVoid(txtBadTranslated1.getText()) ||
			isStringVoid(txtTranslated.getText())) 
		{
			JOptionPane.showMessageDialog(null, "Algun campo está sin rellenar",
					"Comprobación de datos", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			String json = generateJSON(txtToTranslate.getText(), txtBadTranslated2 .getText(),
					txtBadTranslated1.getText(),txtTranslated.getText());
			IExsType exsTypeDAO = new ExsTypeImpl();
			IExs exsDAO = new ExsImpl();
			
			int index = exsDAO.getAllExercicesByLvl(lvl).size();
			index++;
			
			
			ExsTypeModel exsType = exsTypeDAO.getExsTypeByType(Constants.EXS_TYPE_TEST);
			ExsModel exs = new ExsModel(lvl, index, exsType, json);
			
			exsDAO.saveExs(exs);
		}
		txtToTranslate.setText("");
		txtBadTranslated2.setText("");
		txtBadTranslated1.setText("");
		txtTranslated.setText("");
		
	}
	public String generateJSON(String toTranslate, String translated, String badTranslated1, String badTranslated2) {
		JSONObject file = new JSONObject();
		JSONObject typeTest = new JSONObject();
		
		typeTest.put("phrToTranslate", toTranslate);
		typeTest.put("phrTranslated", translated);
		typeTest.put("phrBadTranslated1", badTranslated1);
		typeTest.put("phrBadTranslated2", badTranslated2);
		
		file.put("testExercise", typeTest);
		return file.toString();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 500, 420);
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblToTranslate = new JLabel("Frase a traducir");
		lblToTranslate.setFont(new Font("Dialog", Font.PLAIN, 18));
		JLabel lblTranslated = new JLabel("Frase traducida");
		lblTranslated.setFont(new Font("Dialog", Font.PLAIN, 18));
		JLabel lblBadTranslated1 = new JLabel("Frase incorrecta 1");
		lblBadTranslated1.setFont(new Font("Dialog", Font.PLAIN, 18));
		JLabel lblBadTranslated2 = new JLabel("Frase incorrecta 2");
		lblBadTranslated2.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		txtToTranslate = new JTextField();
		txtBadTranslated2 = new JTextField();
		txtBadTranslated1 = new JTextField();
		txtTranslated = new JTextField();
		
		
		
		txtTranslated.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 244, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTranslated, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
						.addComponent(txtTranslated, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(lblTranslated, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtTranslated, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_1_1 = new JPanel();
		
		
		
		txtBadTranslated1.setColumns(10);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 244, Short.MAX_VALUE)
				.addGap(0, 244, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBadTranslated1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
						.addComponent(txtBadTranslated1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_1_1.createSequentialGroup()
					.addGap(5)
					.addComponent(lblBadTranslated1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtBadTranslated1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1_1.setLayout(gl_panel_1_1);
		
		JPanel panel_1_1_1 = new JPanel();
		
		
		
		txtBadTranslated2.setColumns(10);
		GroupLayout gl_panel_1_1_1 = new GroupLayout(panel_1_1_1);
		gl_panel_1_1_1.setHorizontalGroup(
			gl_panel_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 244, Short.MAX_VALUE)
				.addGap(0, 244, Short.MAX_VALUE)
				.addGap(0, 244, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBadTranslated2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
						.addComponent(txtBadTranslated2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1_1_1.setVerticalGroup(
			gl_panel_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGap(0, 56, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_1_1_1.createSequentialGroup()
					.addGap(5)
					.addComponent(lblBadTranslated2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtBadTranslated2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1_1_1.setLayout(gl_panel_1_1_1);
		
		JButton btnFinish = new JButton("Guardar");
		btnFinish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				finishButtonClick();
			}
			
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(96)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
						.addComponent(panel_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
					.addGap(94))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(170)
					.addComponent(btnFinish, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
					.addGap(177))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1_1_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addComponent(btnFinish, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		
		
		
		txtToTranslate.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblToTranslate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
						.addComponent(txtToTranslate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblToTranslate, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtToTranslate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
	public boolean isStringVoid(String str)
	{
		if (str != null && !str.isBlank() && !str.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}


	public LvlModel getLvl() {
		return lvl;
	}


	public void setLvl(LvlModel lvl) {
		this.lvl = lvl;
	}
}
