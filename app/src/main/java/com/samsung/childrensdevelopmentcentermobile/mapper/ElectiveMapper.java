package com.samsung.childrensdevelopmentcentermobile.mapper;

import com.samsung.childrensdevelopmentcentermobile.model.Direction;
import com.samsung.childrensdevelopmentcentermobile.model.Elective;
import com.samsung.childrensdevelopmentcentermobile.model.Tutor;
import org.json.JSONException;
import org.json.JSONObject;

public class ElectiveMapper {

    public Elective electiveFromJsonArray(JSONObject jsonObject, Direction direction, Tutor tutor) {

        Elective elective = null;

        try {
            elective = new Elective(
                    jsonObject.getLong("id"),
                    jsonObject.getString("name"),
                    direction,
                    tutor
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return elective;
    }

}