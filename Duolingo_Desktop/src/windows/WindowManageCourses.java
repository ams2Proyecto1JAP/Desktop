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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class WindowManageCourses extends JFrame {

	private JPanel contentPane;
	
	
	
	/**
	 * Create the frame.
	 */
	public WindowManageCourses() {
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
		
		JLabel lblCourseCat = new JLabel("Categorías del curso seleccionado");
		lblCourseCat.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JLabel lblLevelsCatCourse = new JLabel("Niveles de la categoría seleccionada");
		lblLevelsCatCourse.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JPanel panelCourses = new JPanel();
		
		JPanel panelCatCourse = new JPanel();
		
		JPanel panelLevelsCatCourse = new JPanel();
		
		JButton btnAddCat = new JButton("Añadir categoría");
		btnAddCat.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JButton btnAddLevel = new JButton("Añadir nivel");
		btnAddLevel.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JButton btnAddQuestion = new JButton("AÑADIR PREGUNTA");
		btnAddQuestion.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JButton btnCheckQuestions = new JButton("VISUALIZAR PREGUNTAS");
		btnCheckQuestions.setFont(new Font("Dialog", Font.PLAIN, 8));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitleManageCourses)
						.addComponent(btnCheckQuestions, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelFilters, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnAddQuestion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCursos)
										.addComponent(panelCourses, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
									.addGap(39)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCourseCat, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
										.addComponent(btnAddCat, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
										.addComponent(panelCatCourse, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
									.addGap(40)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLevelsCatCourse, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(btnAddLevel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(panelLevelsCatCourse, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)))))
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panelCourses, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addComponent(panelLevelsCatCourse, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCourseCat, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelCatCourse, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddLevel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddCat, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddQuestion)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnCheckQuestions, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
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
					.addGroup(gl_panelFilters.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAplicarFiltro, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
						.addComponent(btnCrearCurso, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
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
									.addComponent(lblDestinyaLanguage, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmBxDestinyLanguage, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelFilters.createSequentialGroup()
									.addGap(4)
									.addComponent(lblOriginLanguage, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmBxOriginLanguage, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
							.addGap(13))
						.addGroup(gl_panelFilters.createSequentialGroup()
							.addComponent(btnAplicarFiltro)
							.addGap(18)))
					.addComponent(btnCrearCurso, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
		);
		panelFilters.setLayout(gl_panelFilters);
		contentPane.setLayout(gl_contentPane);
	}
}
