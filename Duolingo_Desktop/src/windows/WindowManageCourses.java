package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
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

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.JScrollPane;

public class WindowManageCourses extends JPanel {

	//Attributes

	private List<LangModel> langsModel;
	private List<CrsModel> coursesModel;
	private List<CatModel> catsModel;
	private List<LvlModel> lvlsModel;
	private LvlModel selectedLvl;

	// Components
	private JPanel contentPane;
	private JComboBox cmBxOriginLanguage;
	private JComboBox cmBxDestinyLanguage;
	private JList listCrs;
	private JList listCatsCrs;
	private JList listLevelsCat;
	JButton btnCrearCurso;
	JButton btnAddLevel;
	JButton btnAddCat;
	JButton btnAddQuestion;
	private boolean ready;



	// DAO implements
	private ICrs crsDAO;
	private ICat catDAO;
	private ILvl lvlDAO;

	/**
	 * Create the frame.
	 */
	public WindowManageCourses() {
		ready = false;
		langsModel = new ArrayList<LangModel>();
		coursesModel = new ArrayList<CrsModel>();
		catsModel = new ArrayList<CatModel>();
		lvlsModel = new ArrayList<LvlModel>();

		setFrame();
		loadLangs();
		chargeLangsJComb();

		crsDAO = new CrsImpl();
		catDAO = new CatImpl();
		lvlDAO = new LvlImpl();

	}

	public void loadCategoriesByCrs(int crsIndex) {
		CrsModel crs = coursesModel.get(crsIndex);

		catsModel = catDAO.getAllCategoriesByCrs(crs);

	}


	public void loadLvlsByCat(int catIndex) {
		CatModel cat = catsModel.get(catIndex);
		lvlsModel = lvlDAO.getAllLevelsByCat(cat);
	}


	public void updateCatListByModel() {
		listCatsCrs.removeAll();
		DefaultListModel listCatCrsModel = new DefaultListModel();

		for (CatModel cat : catsModel) {
			String row = cat.getName();
			listCatCrsModel.addElement(row);
		}
		listCatsCrs.setModel(listCatCrsModel);
	}

	public void updateLevelListByModel() {
		listLevelsCat.removeAll();
		DefaultListModel listLvlsModel = new DefaultListModel();

		for (LvlModel lvl : lvlsModel) {
			String row = lvl.getName();
			listLvlsModel.addElement(row);
		}
		listLevelsCat.setModel(listLvlsModel);
	}

	public void createCrsByFilter() {
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

		for (CrsModel crs : coursesModel) {
			String row = crs.getLangOrigin().getNombre() + " --> " + crs.getLangDestiny().getNombre();
			listCrsModel.addElement(row);
		}
		listCrs.setModel(listCrsModel);
	}

	public void loadLangs() {
		ILang langDAO = new LangImpl();
		langsModel = langDAO.getAllLang();
		LangModel def = new LangModel("Selecciona Un Idioma", "");
		langsModel.add(0, def);
	}

	public void chargeLangsJComb() {
		for (LangModel l : langsModel) {
			cmBxOriginLanguage.addItem(l.getNombre());
			cmBxDestinyLanguage.addItem(l.getNombre());
		}
	}

	public void applyfilter(JComboBox cmBxOriginLanguage, JComboBox cmBxDestinyLanguage) {

		if (cmBxOriginLanguage.getSelectedIndex() == 0 && cmBxDestinyLanguage.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Comprobacion de daatos",
					"Has dejado sin seleccionar alguno de los idiomas", JOptionPane.WARNING_MESSAGE);
		} else if (cmBxDestinyLanguage.getSelectedIndex() == cmBxOriginLanguage.getSelectedIndex()) {
			JOptionPane.showMessageDialog(null, "Comprobacion de daatos",
					"No puedes seleccionar el mismo idioma de origen que de destino", JOptionPane.WARNING_MESSAGE);
		} else {
			btnAddLevel.setEnabled(false);
			btnAddCat.setEnabled(false);
			btnAddQuestion.setEnabled(false);
			listCrs.setModel(new DefaultListModel());
			listCatsCrs.setModel(new DefaultListModel());
			listLevelsCat.setModel(new DefaultListModel());
			LangModel langOrigin = langsModel.get(cmBxOriginLanguage.getSelectedIndex());
			LangModel langDestiny = langsModel.get(cmBxDestinyLanguage.getSelectedIndex());
			coursesModel.clear();
			coursesModel = crsDAO.getCrsByLangFilter(langOrigin.getId(), langDestiny.getId());
			this.displayCrsSelected();
			if (coursesModel.isEmpty()) {
				btnCrearCurso.setEnabled(true);
			}
		}

	}

	public void displayCrsSelected() {
		listCrs.removeAll();
		DefaultListModel listCrsModel = new DefaultListModel();
		for (CrsModel crs : coursesModel) {
			String row = crs.getLangOrigin().getNombre() + " --> " + crs.getLangDestiny().getNombre();
			listCrsModel.addElement(row);
		}
		listCrs.setModel(listCrsModel);
	}

	public void createCategory(String catName) {
		if (catName != null && !catName.isBlank() && !catName.isEmpty()) {
			if (listCrs.getSelectedIndex() != -1) {
				CrsModel crsSelected = coursesModel.get(listCrs.getSelectedIndex());
				int catCount = catDAO.getAllCategoriesByCrs(crsSelected).size();
				catCount++;
				CatModel newCat = new CatModel(crsSelected, catCount, catName, "ruta/imagen");
				catDAO.saveCat(newCat);
				loadCategoriesByCrs(listCrs.getSelectedIndex());
				updateCatListByModel();

			} else {
				JOptionPane.showMessageDialog(null, "Comprobacion de datos", "No tienes ningun curso seleccionado",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Comprobacion de datos", "Nombre de categoria no valido",
					JOptionPane.WARNING_MESSAGE);
		}

	}


	public void createLevel(String lvlName) {
		if (lvlName != null && !lvlName.isBlank() && !lvlName.isEmpty()) {
			if (listCatsCrs.getSelectedIndex() != -1) {
				CatModel cat = catsModel.get(listCatsCrs.getSelectedIndex());
				ArrayList<LvlModel> lvls = lvlDAO.getAllLevelsByCat(cat);
				int lvlCount = lvls.size();
				lvlCount++;
				LvlModel newLvl = new LvlModel(cat, lvlCount, lvlName);
				lvlDAO.saveLvl(newLvl);
				loadLvlsByCat(listCatsCrs.getSelectedIndex());
				updateLevelListByModel();

			} else {
				JOptionPane.showMessageDialog(null, "Comprobacion de datos", "No tienes ninguna categoria seleccionada",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Comprobacion de datos", "Nombre de categoria no valido",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public void setFrame() {
		/*setDefaultCloseOperation(HIDE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/duolingo.png"));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);*/
		setBounds(0, 0, 3000, 3000);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1540, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
				);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		//setSize(2000, 2000);
		//setResizable(false);
		contentPane = new JPanel();
		panel.add(contentPane);
		contentPane.setBounds(0,0,3000,3000);
		contentPane.setBorder(new CompoundBorder());

		JLabel lblTitleManageCourses = new JLabel("Cursos existentes (filtrar por origen y/o destino)");
		lblTitleManageCourses.setFont(new Font("Dialog", Font.PLAIN, 13));

		JPanel panelFilters = new JPanel();

		JLabel lblCursos = new JLabel("Cursos");
		lblCursos.setFont(new Font("Dialog", Font.PLAIN, 13));

		JLabel lblCourseCat = new JLabel("Categorias del curso seleccionado");
		lblCourseCat.setFont(new Font("Dialog", Font.PLAIN, 13));

		JLabel lblLevelsCatCourse = new JLabel("Niveles de la categoria seleccionada");
		lblLevelsCatCourse.setFont(new Font("Dialog", Font.PLAIN, 13));

		btnAddCat = new JButton("Anadir categoria");
		btnAddCat.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnAddCat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				createCategory(JOptionPane.showInputDialog("Escribe el nombre de la categoria: "));

			}
		});
		btnAddCat.setEnabled(false);
		btnAddLevel = new JButton("Anadir nivel");
		btnAddLevel.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnAddLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createLevel(JOptionPane.showInputDialog("Escribe el nombre del nivel: "));
			}
		});
		btnAddLevel.setEnabled(false);

		btnAddQuestion = new JButton("ANADIR PREGUNTA");
		btnAddQuestion.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnAddQuestion.setEnabled(false);
		btnAddQuestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WindowAddExercise frame = new WindowAddExercise(selectedLvl);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}

		});		

		JButton btnCheckQuestions = new JButton("VISUALIZAR PREGUNTAS");
		btnCheckQuestions.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnCheckQuestions.setEnabled(false);
		btnCheckQuestions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WindowCheckExercices frame = new WindowCheckExercices(selectedLvl);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}

		});		

		listCrs = new JList();	
		listCrs.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					if (listCrs.getSelectedIndex() != -1)
					{
						loadCategoriesByCrs(listCrs.getSelectedIndex());
						updateCatListByModel();
						btnAddLevel.setEnabled(false);
						btnAddQuestion.setEnabled(false);
						btnAddCat.setEnabled(true);
						btnCheckQuestions.setEnabled(false);
					}

				}
			}
		});





		listCatsCrs = new JList();
		listCatsCrs.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					if (listCatsCrs.getSelectedIndex() != -1)
					{
						loadLvlsByCat(listCatsCrs.getSelectedIndex());
						updateLevelListByModel();            
						btnAddQuestion.setEnabled(false);
						btnAddLevel.setEnabled(true);
						btnCheckQuestions.setEnabled(false);
					}

				}
			}
		});
		listLevelsCat = new JList();
		listLevelsCat.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					if (listLevelsCat.getSelectedIndex() != -1)
					{
						selectedLvl = lvlsModel.get(listLevelsCat.getSelectedIndex());
						btnAddQuestion.setEnabled(true);
						btnCheckQuestions.setEnabled(true);
					}

				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()

						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()

										.addGap(25)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(panelFilters, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGap(43)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)

																.addComponent(lblCursos)
																.addComponent(listCrs, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))

														.addGap(185)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(btnAddCat, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(listCatsCrs, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
																.addComponent(lblCourseCat, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
														.addGap(163)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)

																.addComponent(lblLevelsCatCourse, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
																.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
																		.addComponent(listLevelsCat, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
																		.addComponent(btnAddLevel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
														.addGap(67))
												.addComponent(lblTitleManageCourses, Alignment.LEADING)))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addGap(68)
										.addComponent(btnAddQuestion, GroupLayout.DEFAULT_SIZE, 1427, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()

										.addGap(68)
										.addComponent(btnCheckQuestions, GroupLayout.DEFAULT_SIZE, 1427, Short.MAX_VALUE)))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblTitleManageCourses)
						.addGap(16)
						.addComponent(panelFilters, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)

						.addGap(26)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCourseCat, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCursos, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)

								.addComponent(lblLevelsCatCourse, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(listLevelsCat, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
								.addComponent(listCatsCrs, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
								.addComponent(listCrs, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE))
						.addGap(26)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddCat, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAddLevel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGap(41)
						.addComponent(btnAddQuestion, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnCheckQuestions, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addGap(45))
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
								.addComponent(lblOriginLanguage, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmBxOriginLanguage, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE))
						.addGap(76)
						.addGroup(gl_panelFilters.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDestinyaLanguage)
								.addComponent(cmBxDestinyLanguage, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
						.addGroup(gl_panelFilters.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnAplicarFiltro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCrearCurso, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
						.addGap(75))
				);
		gl_panelFilters.setVerticalGroup(
				gl_panelFilters.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFilters.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelFilters.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelFilters.createSequentialGroup()
										.addGroup(gl_panelFilters.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panelFilters.createSequentialGroup()
														.addComponent(btnAplicarFiltro, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
														.addGap(19))
												.addGroup(gl_panelFilters.createSequentialGroup()
														.addGap(4)
														.addGroup(gl_panelFilters.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblOriginLanguage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(lblDestinyaLanguage, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_panelFilters.createParallelGroup(Alignment.BASELINE)
																.addComponent(cmBxOriginLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(cmBxDestinyLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
										.addGap(13))
								.addGroup(gl_panelFilters.createSequentialGroup()
										.addComponent(btnCrearCurso, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
										.addGap(6)))
						.addGap(18))
				);
		panelFilters.setLayout(gl_panelFilters);
		contentPane.setLayout(gl_contentPane);
		setLayout(groupLayout);
		ready = true;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
