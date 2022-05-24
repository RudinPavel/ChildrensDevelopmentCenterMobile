package com.samsung.childrensdevelopmentcentermobile.mapper;

import com.samsung.childrensdevelopmentcentermobile.model.Tutor;

import org.json.JSONException;
import org.json.JSONObject;

public class TutorMapper {

    public Tutor tutorFromElectiveJsonArray(JSONObject jsonObject) {

        Tutor tutor = null;
        try {

            tutor = new Tutor(
                    jsonObject.getJSONObject("tutorDto").getLong("id"),
                    jsonObject.getJSONObject("tutorDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return tutor;
    }

    public Tutor tutorFromJsonArray(JSONObject jsonObject) {

        Tutor tutor = null;
        try {

            tutor = new Tutor(
                    jsonObject.getLong("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return tutor;
    }

}