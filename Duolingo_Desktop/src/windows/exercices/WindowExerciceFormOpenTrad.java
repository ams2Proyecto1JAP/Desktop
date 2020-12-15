package windows.exercices;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.json.JSONArray;
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
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;


public class WindowExerciceFormOpenTrad {

	private JFrame frame;
	private JTextField txtToTranslate;

	private LvlModel lvl;
	private JTextField textField;
	
	private ArrayList<JTextField> fields;

	/**
	 * Create the application.
	 */
	public WindowExerciceFormOpenTrad(LvlModel lvl) {
		this.setLvl(lvl);
		fields = new ArrayList<JTextField>();
		initialize();
	}

	
	public void finishButtonClick() {
		boolean ok = true;
		for (JTextField jt : fields)
		{
			if (isStringVoid(jt.getText()))
			{
				ok = false;
			}
		}
			
		
		
		if (ok)
		{
			String json = generateJSON(txtToTranslate.getText(), fields);
			IExsType exsTypeDAO = new ExsTypeImpl();
			IExs exsDAO = new ExsImpl();
			
			int index = exsDAO.getAllExercicesByLvl(lvl).size();
			index++;
			
			
			ExsTypeModel exsType = exsTypeDAO.getExsTypeByType(Constants.EXS_TYPE_OPEN_TRAD);
			ExsModel exs = new ExsModel(lvl, index, exsType, json);
			
			exsDAO.saveExs(exs);
			frame.setVisible(false);
			frame.dispose();
		}
		else
		{
			
			JOptionPane.showMessageDialog(null, "Algun campo est� sin rellenar",
					"Comprobaci�n de datos", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
		
	}
	public String generateJSON(String toTranslate, ArrayList<JTextField> texts) {
		JSONObject file = new JSONObject();
		JSONObject typeTest = new JSONObject();
		
		typeTest.put("phrToTranslate", toTranslate);
		
		JSONArray options = new JSONArray();
		for (JTextField jt : texts)
		{
			options.put(jt.getText());
		}
		typeTest.put("options", options);
		
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
		
		JLabel lblToTranslate = new JLabel("Frase a traducir");
		lblToTranslate.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		txtToTranslate = new JTextField();
		
		JButton btnFinish = new JButton("Guardar");
		btnFinish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				finishButtonClick();
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(170)
					.addComponent(btnFinish, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
					.addGap(177))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(94)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
					.addComponent(btnFinish, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		
		JPanel fieldsPanel = new JPanel();
		scrollPane.setViewportView(fieldsPanel);
		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
		
		JLabel lblBadTranslated2 = new JLabel("Traducciones");
		fieldsPanel.add(lblBadTranslated2);
		lblBadTranslated2.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		JButton btnNewButton = new JButton("Añadir");
		fieldsPanel.add(btnNewButton);
		
		textField = new JTextField();
		
		fieldsPanel.add(textField);
		textField.setColumns(50);
		textField.setMaximumSize( textField.getPreferredSize() );
		fields.add(textField);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField jt = new JTextField();
				jt.setColumns(50);
				jt.setMaximumSize( jt.getPreferredSize() );
				fieldsPanel.add(jt);
				fields.add(jt);
				frame.revalidate();
				System.out.println("a");
			}
		});
		
		
		
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
