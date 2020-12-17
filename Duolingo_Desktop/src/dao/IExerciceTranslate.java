package dao;

import org.json.JSONException;

public interface IExerciceTranslate {
	public ExerciceTranslate getExerciceTranslateByContent(String content) throws JSONException;
}
