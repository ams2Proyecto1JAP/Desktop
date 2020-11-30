package windows;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;

import duolingo.lib.model.*;
import duolingo.lib.hibernate.*;




public class WindowManageCourses extends JFrame {

	private JPanel contentPane;
	private List<LangModel> langsModel;
	private List<CrsModel> coursesModel;
	private List<CatModel> catsModel;
	private List<LvlModel> levelsModel;	
	
	
	
	/**
	 * Create the frame.
	 */
	public WindowManageCourses() {
		
		setFrame();
		langsModel = new ArrayList<LangModel>();
		coursesModel = new ArrayList<CrsModel>();
		catsModel = new ArrayList<CatModel>();
		levelsModel = new ArrayList<LvlModel>();
	}
	
	public void setFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitleManageCourses = new JLabel("Cursos existentes (filtrar por origen y/o destino)");
		lblTitleManageCourses.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JPanel panelFilters = new JPanel();
		
		JLabel lblCursos = new JLabel("Cursos");
		lblCursos.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JLabel lblCourseCat = new JLabel("Categorias del curso seleccionado");
		lblCourseCat.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JLabel lblLevelsCatCourse = new JLabel("Niveles de la categoria seleccionada");
		lblLevelsCatCourse.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JButton btnAddCat = new JButton("Anadir categoria");
		btnAddCat.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JButton btnAddLevel = new JButton("Anadir nivel");
		btnAddLevel.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JButton btnAddQuestion = new JButton("ANADIR PREGUNTA");
		btnAddQuestion.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JButton btnCheckQuestions = new JButton("VISUALIZAR PREGUNTAS");
		btnCheckQuestions.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JList listCrs = new JList();
		
		JList listCatsCrs = new JList();
		
		JList listLevelsCat = new JList();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitleManageCourses)
						.addComponent(btnCheckQuestions, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelFilters, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnAddQuestion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCursos)
										.addComponent(listCrs, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(39)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblCourseCat, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
												.addComponent(btnAddCat, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(listCatsCrs, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
									.addGap(40)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(listLevelsCat, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLevelsCatCourse, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnAddLevel))))
							.addGap(23)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitleManageCourses)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelFilters, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCursos, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLevelsCatCourse, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(listCrs, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addComponent(listCatsCrs, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addComponent(listLevelsCat, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblCourseCat, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddLevel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddCat, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddQuestion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCheckQuestions, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblOriginLanguage = new JLabel("Idioma de origen");
		lblOriginLanguage.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JLabel lblDestinyaLanguage = new JLabel("Idioma de destino");
		lblDestinyaLanguage.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JComboBox cmBxOriginLanguage = new JComboBox();
		
		JComboBox cmBxDestinyLanguage = new JComboBox();
		
		JButton btnAplicarFiltro = new JButton("Aplicar filtro");
		btnAplicarFiltro.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JButton btnCrearCurso = new JButton("Crear curso");
		btnCrearCurso.setFont(new Font("Dialog", Font.PLAIN, 8));
		GroupLayout gl_panelFilters = new GroupLayout(panelFilters);
		gl_panelFilters.setHorizontalGroup(
			gl_panelFilters.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFilters.createSequentialGroup()
					.addGroup(gl_panelFilters.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFilters.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelFilters.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cmBxOriginLanguage, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblOriginLanguage, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
							.addGap(45)
							.addGroup(gl_panelFilters.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelFilters.createSequentialGroup()
									.addComponent(lblDestinyaLanguage)
									.addGap(65))
								.addComponent(cmBxDestinyLanguage, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
							.addGap(47)
							.addComponent(btnAplicarFiltro, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelFilters.createSequentialGroup()
							.addGap(364)
							.addComponent(btnCrearCurso, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelFilters.setVerticalGroup(
			gl_panelFilters.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFilters.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFilters.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelFilters.createSequentialGroup()
							.addGroup(gl_panelFilters.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelFilters.createSequentialGroup()
									.addComponent(lblDestinyaLanguage, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmBxDestinyLanguage, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelFilters.createSequentialGroup()
									.addGap(4)
									.addComponent(lblOriginLanguage, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmBxOriginLanguage, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
							.addGap(13))
						.addGroup(gl_panelFilters.createSequentialGroup()
							.addComponent(btnAplicarFiltro)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCrearCurso, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(18))
		);
		panelFilters.setLayout(gl_panelFilters);
		contentPane.setLayout(gl_contentPane);
	}
	
	
}
