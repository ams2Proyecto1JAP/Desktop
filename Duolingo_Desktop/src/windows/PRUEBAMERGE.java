package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

import duolingo.lib.model.*;
import duolingo.lib.dao.implementations.*;
import duolingo.lib.dao.interfaces.*;
import duolingo.lib.hibernate.*;

public class PRUEBAMERGE extends JFrame {

	private JPanel contentPane;
	private List<LangModel> langsModel;
	private List<CrsModel> coursesModel;
	private List<CatModel> catsModel;
	private List<LvlModel> levelsModel;
	
	// Components
	private JComboBox cmBxOriginLanguage;
	private JComboBox cmBxDestinyLanguage;	
	private JList listCrs;
	private JList listCatsCrs;
	private JList listLevelsCat;
	JButton btnCrearCurso;
	
	// DAO implements
	private ICrs crsDAO;
	private ICat catDAO;
	
	/**
	 * Create the frame.
	 */
	public PRUEBAMERGE() {
		langsModel = new ArrayList<LangModel>();
		coursesssModel = new ArrayList<CrsModel>();
		crsDAO = new CrsImpl();
		CrsModel crs = coursesModel.get(crsIndex);

		setFrame();

		System.out.println();
		System.out.println();
		catsModel = catDAO.getAllCategoriesByCrs(crs);	

		catsModel = new ArrayList<CatModel>();
		String row = cat.getName();
		listCatCrsModel.addElement(row);	
		levelsModel = new ArrayList<LvlModel>();
		
		setFrame();
		//loadLangs();
		//chargeLangsJComb();
		
		crsDAO = new CrsImpl();
		catDAO = new CatImpl();
		
	}
	

	public void loadCategoriesByCrs (int crsIndex)
	{
		CrsModel crs = coursesModel.get(crsIndex);
		
		catsModel = catDAO.getAllCategoriesByCrs(crs);	
		
	}
	
	public void updateCatListByModel() {
		listCatsCrs.removeAll();
		DefaultListModel listCatCrsModel = new DefaultListModel();
		
		for (CatModel cat : catsModel) 
		{
			String row = cat.getName();
			listCatCrsModel.addElement(row);	
		}
		listCatsCrs.setModel(listCatCrsModel);
	}
	

	public void createCrsByFilter(){
		coursesModel.clear();
		LangModel srcLang = langsModel.get(cmBxOriginLanguage.getSelectedIndex());
		LangModel dstLang = langsModel.get(cmBxDestinyLanguage.getSelectedIndex());
		CrsModel newCrs = new CrsModel(srcLang, dstLang);
		crsDAO.saveCrs(newCrs);
		coursesModel.add(newCrs);
		updateCrsListByModel();
		btnCrearCurso.setEnabled(false);
	}
	
	public void updateCrsListByModel() {
		listCrs.removeAll();
		DefaultListModel listCrsModel = new DefaultListModel();
		
		for (CrsModel crs : coursesModel) 
		{
			String row = crs.getLangOrigin().getNombre() + " --> " + crs.getLangDestiny().getNombre();
			listCrsModel.addElement(row);	
		}
		listCrs.setModel(listCrsModel);
	}
	
	public void loadLangs() {
		ILang langDAO = new LangImpl();
		langsModel=langDAO.getAllLang();
		LangModel def = new LangModel("Selecciona Un Idioma","");
		langsModel.add(0,def);
	}
	
	public void chargeLangsJComb() {
		for(LangModel l : langsModel) {
			cmBxOriginLanguage.addItem(l.getNombre());
			cmBxDestinyLanguage.addItem(l.getNombre());
		}
	}
	
	public void applyfilter(JComboBox cmBxOriginLanguage, JComboBox cmBxDestinyLanguage) {

		if (cmBxOriginLanguage.getSelectedIndex() == 0 || cmBxDestinyLanguage.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Comprobacion de daatos", "Has dejado sin seleccionar alguno de los idiomas", JOptionPane.WARNING_MESSAGE);
		}
		else if (cmBxDestinyLanguage.getSelectedIndex() == cmBxOriginLanguage.getSelectedIndex())
		{
			JOptionPane.showMessageDialog(null, "Comprobacion de daatos", "No puedes seleccionar el mismo idioma de origen que de destino", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			LangModel langOrigin = langsModel.get(cmBxOriginLanguage.getSelectedIndex());
			LangModel langDestiny = langsModel.get(cmBxDestinyLanguage.getSelectedIndex());
			coursesModel.clear();
			coursesModel = crsDAO.getCrsByLangFilter(langOrigin.getId(), langDestiny.getId());
			this.displayCrsSelected();
			if(coursesModel.isEmpty()) {
				btnCrearCurso.setEnabled(true);
			}
		}		
		
	}


	public void setFrame() {
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 750, 520);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblTitleManageCourses = new JLabel("Cursos existentes (filtrar por origen y/o destino)");
		lblTitleManageCourses.setFont(new Font("Dialog", Font.PLAIN, 13));

		JPanel panelFilters = new JPanel();

		JLabel lblCursos = new JLabel("Cursos");
		lblCursos.setFont(new Font("Dialog", Font.PLAIN, 13));

		JLabel lblCourseCat = new JLabel("Categorias del curso seleccionado");
		lblCourseCat.setFont(new Font("Dialog", Font.PLAIN, 13));

		JLabel lblLevelsCatCourse = new JLabel("Niveles de la categoria seleccionada");
		lblLevelsCatCourse.setFont(new Font("Dialog", Font.PLAIN, 13));

		JButton btnAddCat = new JButton("Anadir categoria");
		btnAddCat.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnAddCat.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				createCategory(JOptionPane.showInputDialog("Escribe el nombre de la categoria: "));
				
			}
		});
		JButton btnAddLevel = new JButton("Anadir nivel");
		btnAddLevel.setFont(new Font("Dialog", Font.PLAIN, 13));

		JButton btnAddQuestion = new JButton("ANADIR PREGUNTA");
		btnAddQuestion.setFont(new Font("Dialog", Font.PLAIN, 13));

		JButton btnCheckQuestions = new JButton("VISUALIZAR PREGUNTAS");
		btnCheckQuestions.setFont(new Font("Dialog", Font.PLAIN, 13));

		listCrs = new JList();
		listCrs.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	if (listCrs.getSelectedIndex() != -1)
                	{
                		loadCategoriesByCrs(listCrs.getSelectedIndex());
                    	updateCatListByModel();
                	}
                	
                }
            }
        });
		
		listCatsCrs = new JList();
		listLevelsCat = new JList();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitleManageCourses, Alignment.TRAILING)
						.addComponent(panelFilters, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCursos)
								.addComponent(listCrs, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCourseCat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(54))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(75)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnAddCat, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
										.addComponent(listCatsCrs, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
									.addGap(14)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLevelsCatCourse, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(45)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnAddLevel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(listLevelsCat, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
							.addGap(404))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnCheckQuestions, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAddQuestion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitleManageCourses)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelFilters, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCursos, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCourseCat, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblLevelsCatCourse, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(listCrs, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.addComponent(listCatsCrs, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.addComponent(listLevelsCat, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddLevel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddCat, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(btnAddQuestion, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCheckQuestions, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);

		JLabel lblOriginLanguage = new JLabel("Idioma de origen");
		lblOriginLanguage.setFont(new Font("Dialog", Font.PLAIN, 13));

		JLabel lblDestinyaLanguage = new JLabel("Idioma de destino");
		lblDestinyaLanguage.setFont(new Font("Dialog", Font.PLAIN, 13));
		cmBxOriginLanguage = new JComboBox();

		cmBxDestinyLanguage = new JComboBox();

		JButton btnAplicarFiltro = new JButton("Aplicar filtro");
		btnAplicarFiltro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				applyfilter(cmBxOriginLanguage, cmBxDestinyLanguage);
			}
			
		});
		btnAplicarFiltro.setFont(new Font("Dialog", Font.PLAIN, 13));

		btnCrearCurso = new JButton("Crear curso");
		btnCrearCurso.setEnabled(false);
		btnCrearCurso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createCrsByFilter();
				
			}
		});
		
		btnCrearCurso.setFont(new Font("Dialog", Font.PLAIN, 13));
		GroupLayout gl_panelFilters = new GroupLayout(panelFilters);
		gl_panelFilters.setHorizontalGroup(
			gl_panelFilters.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFilters.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFilters.createParallelGroup(Alignment.LEADING)
						.addComponent(cmBxOriginLanguage, 0, 125, Short.MAX_VALUE)
						.addComponent(lblOriginLanguage, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(gl_panelFilters.createParallelGroup(Alignment.LEADING)
						.addComponent(cmBxDestinyLanguage, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDestinyaLanguage))
					.addGap(72)
					.addGroup(gl_panelFilters.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCrearCurso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAplicarFiltro, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(429, Short.MAX_VALUE))
		);
		gl_panelFilters.setVerticalGroup(
			gl_panelFilters.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFilters.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFilters.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelFilters.createSequentialGroup()
							.addGroup(gl_panelFilters.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelFilters.createSequentialGroup()
									.addComponent(lblDestinyaLanguage, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
									.addGap(19))
								.addGroup(gl_panelFilters.createSequentialGroup()
									.addGap(4)
									.addComponent(lblOriginLanguage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelFilters.createParallelGroup(Alignment.BASELINE)
										.addComponent(cmBxOriginLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(cmBxDestinyLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(13))
						.addGroup(gl_panelFilters.createSequentialGroup()
							.addComponent(btnAplicarFiltro, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCrearCurso, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(18))
		);
		panelFilters.setLayout(gl_panelFilters);
		contentPane.setLayout(gl_contentPane);
	}
	
	
}
