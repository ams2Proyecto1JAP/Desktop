package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import duolingo.lib.dao.implementations.LangImpl;
import duolingo.lib.dao.interfaces.ILang;
import duolingo.lib.hibernate.util.HibernateUtil;
import duolingo.lib.model.CatModel;
import duolingo.lib.model.CrsModel;
import duolingo.lib.model.LangModel;

public class CreateDemoDataBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sCadena;
		FileReader fr;
		ArrayList<LangModel> langs = new ArrayList<LangModel>();
		ILang langDAO = new LangImpl();

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
