package dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExerciceTranslateImpl implements IExerciceTranslate {
	@Override
    public ExerciceTranslate getExerciceTranslateByContent(String content) throws JSONException {
        String jsonString = content ;
        JSONObject obj = new JSONObject(jsonString);
        JSONObject translateExs = obj.getJSONObject("openTradExercise");
        String toTrans = translateExs.getString("phrToTranslate");

        JSONArray trans = translateExs.getJSONArray("options");
        String[] options = new String[trans.length()];
        for(int i =0; i<options.length;i++){
            options[i]=trans.getString(i);
        }

        ExerciceTranslate exs = new ExerciceTranslate(toTrans,options);
        exs.setType("Translate");
        return exs;
    }
}
