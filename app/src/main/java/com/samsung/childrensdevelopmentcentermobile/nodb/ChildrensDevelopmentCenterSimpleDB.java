package com.samsung.childrensdevelopmentcentermobile.nodb;

import com.samsung.childrensdevelopmentcentermobile.model.Direction;
import com.samsung.childrensdevelopmentcentermobile.model.Elective;
import com.samsung.childrensdevelopmentcentermobile.model.Tutor;

import java.util.ArrayList;
import java.util.List;

public class ChildrensDevelopmentCenterSimpleDB {

    private ChildrensDevelopmentCenterSimpleDB(){}

    public static final List<Elective> ELECTIVE_LIST = new ArrayList<>();

    public static final List<Direction> DIRECTION_LIST = new ArrayList<>();

    public static final List<Tutor> TUTOR_LIST = new ArrayList<>();
}