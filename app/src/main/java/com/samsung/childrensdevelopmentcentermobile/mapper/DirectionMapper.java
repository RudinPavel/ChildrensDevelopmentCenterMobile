package com.samsung.childrensdevelopmentcentermobile.mapper;

import com.samsung.childrensdevelopmentcentermobile.model.Direction;

import org.json.JSONException;
import org.json.JSONObject;

public class DirectionMapper {

    public Direction directionFromElectiveJsonArray(JSONObject jsonObject) {

        Direction direction = null;
        try {
            direction = new Direction(
                    jsonObject.getJSONObject("directionDto").getLong("id"),
                    jsonObject.getJSONObject("directionDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return direction;
    }

    public Direction directionFromJsonArray(JSONObject jsonObject) {

        Direction direction = null;
        try {

            direction = new Direction(
                    jsonObject.getLong("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return direction;
    }
}