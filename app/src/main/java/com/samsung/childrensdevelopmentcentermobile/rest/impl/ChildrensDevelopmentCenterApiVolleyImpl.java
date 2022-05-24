package com.samsung.childrensdevelopmentcentermobile.rest.impl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.samsung.childrensdevelopmentcentermobile.MainActivity;
import com.samsung.childrensdevelopmentcentermobile.mapper.DirectionMapper;
import com.samsung.childrensdevelopmentcentermobile.mapper.ElectiveMapper;
import com.samsung.childrensdevelopmentcentermobile.mapper.TutorMapper;
import com.samsung.childrensdevelopmentcentermobile.model.Direction;
import com.samsung.childrensdevelopmentcentermobile.model.Elective;
import com.samsung.childrensdevelopmentcentermobile.model.Tutor;
import com.samsung.childrensdevelopmentcentermobile.nodb.ChildrensDevelopmentCenterSimpleDB;
import com.samsung.childrensdevelopmentcentermobile.rest.ChildrensDevelopmentCenterApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChildrensDevelopmentCenterApiVolleyImpl implements ChildrensDevelopmentCenterApi {

    public static final String BASE_URL = "http://192.168.123.65:8080";
    private final Context context;

    private Response.ErrorListener errorListener;

    public ChildrensDevelopmentCenterApiVolleyImpl(Context context) {

        this.context = context;
        errorListener = new ErrorListenerImpl();
    }

    @Override
    public void fillElectiveList() {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/electives";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            ChildrensDevelopmentCenterSimpleDB.ELECTIVE_LIST.clear();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Tutor tutor = new TutorMapper().tutorFromElectiveJsonArray(jsonObject);

                                Direction direction = new DirectionMapper().directionFromElectiveJsonArray(jsonObject);

                                Elective elective = new ElectiveMapper().electiveFromJsonArray(jsonObject, direction, tutor);
                                ChildrensDevelopmentCenterSimpleDB.ELECTIVE_LIST.add(elective);
                            }
                            Log.d("ELECTIVES_LIST", ChildrensDevelopmentCenterSimpleDB.ELECTIVE_LIST.toString());
                            ((MainActivity) context).update();
                        } catch (JSONException e) {

                            Log.d("ELECTIVES_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillTutorList() {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/tutors";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Tutor tutor = new TutorMapper().tutorFromJsonArray(jsonObject);

                                ChildrensDevelopmentCenterSimpleDB.TUTOR_LIST.add(tutor);
                            }
                            Log.d("TUTOR_LIST", ChildrensDevelopmentCenterSimpleDB.TUTOR_LIST.toString());
                        } catch (JSONException e) {

                            Log.d("TUTOR_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillDirectionList() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/directions";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Direction direction = new DirectionMapper().directionFromJsonArray(jsonObject);

                                ChildrensDevelopmentCenterSimpleDB.DIRECTION_LIST.add(direction);
                            }
                            Log.d("DIRECTIONS_LIST", ChildrensDevelopmentCenterSimpleDB.DIRECTION_LIST.toString());
                        } catch (JSONException e) {

                            Log.d("DIRECTIONS_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void newElective(Elective elective) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/electives";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response);

                        // выгрузка заново плохо?
                        fillElectiveList();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("electiveName", elective.getName());
                params.put("directionName", elective.getDirection().getName());
                params.put("tutorName", elective.getTutor().getName());

                return params;
            }
        };

        queue.add(postRequest);
    }

    @Override
    public void updateElective(Long id, String newElectiveName, String newDirectionName, String newTutorName) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/electives/" + id + "/";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response);

                        //стоит обновлять локально
                        //но пока так
                        fillElectiveList();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("newElectiveName", newElectiveName);
                params.put("newDirectionName", newDirectionName);
                params.put("newTutorName", newTutorName);
                params.put("id", String.valueOf(id));

                return params;
            }
        };

        queue.add(postRequest);
    }

    @Override
    public void deleteElective(Long id) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/electives/" + id;

        StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        fillElectiveList();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));

                return params;
            }
        };

        queue.add(dr);
    }

    private class ErrorListenerImpl implements Response.ErrorListener {


        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        }
    }
}
