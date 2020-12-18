package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.ieti.duolingoproyect.connection.ServerService;

import duolingo.lib.dao.implementations.ExsTypeImpl;
import duolingo.lib.dao.implementations.LangImpl;
import duolingo.lib.dao.interfaces.IExsType;
import duolingo.lib.dao.interfaces.ILang;
import duolingo.lib.model.ExsTypeModel;
import duolingo.lib.model.LangModel;
import utils.Constants;
import windows.WindowManageCourses;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Interface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setResizable(false);
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
	public Interface() {
		checkIntsertLanguages();
		checkInsertExsType();
		//Start server
		ServerService serverService = new ServerService();
		// Set iccon image of app
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/duolingo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// JMenuBar setter
		JMenuBar jMenuBar = new JMenuBar();
		setJMenuBar(jMenuBar);

		// JMenuBar Add Items
		JMenu jMenu = new JMenu("Menu");
		jMenuBar.add(jMenu);
		
		// JMenu Add Items
		JMenuItem menuItemAdminCurs = new JMenuItem(new AbstractAction("Administrar Cursos") {
		    public void actionPerformed(ActionEvent e) {
		    	openAdminCursos(contentPane);
		    	
		    }
		});
		
		jMenu.add(menuItemAdminCurs);
		//menuItemAdminCurs.addActionListener();
		jMenu.add("Altres");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void openAdminCursos(JPanel contentPane) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowManageCourses windowManagerCourses = new WindowManageCourses();
					contentPane.add(windowManagerCourses);
					Interface.this.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void checkInsertExsType() {
		IExsType exsTypeDAO = new ExsTypeImpl();
		
		if (exsTypeDAO.getExsTypeByType(Constants.EXS_TYPE_TEST) == null)
		{
			exsTypeDAO.saveExsType(new ExsTypeModel(Constants.EXS_TYPE_TEST));
		}
		
	}
	
	
	public void checkIntsertLanguages() {

		ILang langDAO = new LangImpl();
		if(langDAO.getLangById(1) == null) {
			String sCadena;
			FileReader fr;
			ArrayList<LangModel> langs = new ArrayList<LangModel>();
			try {
				fr = new FileReader("resources"+File.separator+"languages.txt");
				BufferedReader bf = new BufferedReader(fr);
				try {
					while ((sCadena = bf.readLine()) != null) {
						String[] languages = sCadena.split("\\s+");
						langs.add(new LangModel(languages[0], languages[1]));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (LangModel l : langs) {
					langDAO.saveLang(l);
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}


