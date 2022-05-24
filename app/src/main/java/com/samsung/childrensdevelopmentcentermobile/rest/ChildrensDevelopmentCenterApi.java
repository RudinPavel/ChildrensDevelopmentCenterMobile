package com.samsung.childrensdevelopmentcentermobile.rest;

import com.samsung.childrensdevelopmentcentermobile.model.Elective;

public interface ChildrensDevelopmentCenterApi {

    void fillElectiveList();

    void fillDirectionList();

    void fillTutorList();

    void newElective(Elective elective);

    void updateElective(Long id, String newElectiveName, String newDirectionName, String newTutorName);

    void deleteElective(Long id);
}
