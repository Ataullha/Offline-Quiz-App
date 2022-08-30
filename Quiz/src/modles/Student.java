/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Md Ataullha
 */
public class Student {
    
    private SimpleStringProperty SName;
    private SimpleStringProperty SRegNo;

    public Student(String SName,String SRegNo) {
        this.SName = new SimpleStringProperty(SName);
        this.SRegNo = new SimpleStringProperty (SRegNo);
    }

    public String getSName() {
        return SName.get();
    }

    public String getSRegNo() {
        return SRegNo.get();
    }

    public void setSName(String SName) {
        this.SName = new SimpleStringProperty(SName);
    }

    public void setSRegNo(String SRegNo) {
        this.SRegNo =new SimpleStringProperty(SRegNo);
    }
    
    
    
}
