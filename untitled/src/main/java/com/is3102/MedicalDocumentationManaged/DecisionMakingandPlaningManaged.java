/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationManaged;

import com.is3102.EntityClass.Finding;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.DecisionMakingandPlaningRemote;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ashish
 */

@ManagedBean
public class DecisionMakingandPlaningManaged implements Serializable{
    @EJB
    private DecisionMakingandPlaningRemote dmp;
   
    //case identifaction number to search for a case
    private Long CIN;
    //Patient identification number to retrieve patient id
    private Long PIN;
    //Procedure code for doctors to enter when entering a new procedure
    private String procedure_code;
    //{rocedure name for them to enter when entering a new procedure
    private String procedure_name;
    //Finidng entity to create a new pro
    private String finding;
    //comments for doctor to add when adding a new procedure
    private String comments;
    //Procedure id to add patient consent to a procedure
    private Long procedureId;
    //Patient comment recorded at the time getting the consent. 
    private String patient_comment;
    //List of medical procedures associated with a Case. 
    private List<Medical_Procedure> medicalProcedures;

    public void doAddPlannedProcedure(){
        System.out.println("In managed bean DoAddPlannedProcedure");
        try{
            System.out.println("Enter try");
            dmp.AddPlanedProcedure(CIN, procedure_code, procedure_name, finding, comments);
            System.out.println("Leave try");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void doGetConsent(){
        try{
            dmp.GetConsent(procedureId, patient_comment);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void doRetrieveCarePlaning(){
        System.out.println("In Managed bean");
        try {
        System.out.println("in try");    
        List<Medical_Procedure> result = dmp.RetrieveCarePlaning(CIN);
                System.out.println("list returned");

        this.setMedicalProcedures(result);
                System.out.println("Resutls set");

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
       
    }

    public Long getCIN() {
        return CIN;
    }

    public void setCIN(Long CIN) {
        this.CIN = CIN;
    }

    public Long getPIN() {
        return PIN;
    }

    public void setPIN(Long PIN) {
        this.PIN = PIN;
    }

    public String getProcedure_code() {
        return procedure_code;
    }

    public void setProcedure_code(String procedure_code) {
        this.procedure_code = procedure_code;
    }

    public String getProcedure_name() {
        return procedure_name;
    }

    public void setProcedure_name(String procedure_name) {
        this.procedure_name = procedure_name;
    }

    public String getFinding() {
        return finding;
    }

    public void setFinding(String finding) {
        this.finding = finding;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public String getPatient_comment() {
        return patient_comment;
    }

    public void setPatient_comment(String patient_comment) {
        this.patient_comment = patient_comment;
    }
      
    public List<Medical_Procedure> getMedicalProcedures() {
        return medicalProcedures;
    }

    public void setMedicalProcedures(List<Medical_Procedure> medicalProcedures) {
        this.medicalProcedures = medicalProcedures;
    }
    
    
}
