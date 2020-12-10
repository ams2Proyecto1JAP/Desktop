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

import windows.WindowManageCourses;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

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
	
	public void startServer() {
		
	}
	
}


