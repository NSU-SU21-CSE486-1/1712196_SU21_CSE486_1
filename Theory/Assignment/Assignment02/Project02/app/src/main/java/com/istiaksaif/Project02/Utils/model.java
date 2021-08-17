package com.istiaksaif.Project02.Utils;

import android.widget.LinearLayout;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

public class model {
    LinearLayout layout;
    TextInputEditText studentID;
    MaterialAutoCompleteTextView uniName,department,level;

    public model() {
    }

    public model(LinearLayout layout, TextInputEditText studentID, MaterialAutoCompleteTextView uniName, MaterialAutoCompleteTextView department, MaterialAutoCompleteTextView level) {
        this.layout = layout;
        this.studentID = studentID;
        this.uniName = uniName;
        this.department = department;
        this.level = level;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }

    public TextInputEditText getStudentID() {
        return studentID;
    }

    public void setStudentID(TextInputEditText studentID) {
        this.studentID = studentID;
    }

    public MaterialAutoCompleteTextView getUniName() {
        return uniName;
    }

    public void setUniName(MaterialAutoCompleteTextView uniName) {
        this.uniName = uniName;
    }

    public MaterialAutoCompleteTextView getDepartment() {
        return department;
    }

    public void setDepartment(MaterialAutoCompleteTextView department) {
        this.department = department;
    }

    public MaterialAutoCompleteTextView getLevel() {
        return level;
    }

    public void setLevel(MaterialAutoCompleteTextView level) {
        this.level = level;
    }
}
