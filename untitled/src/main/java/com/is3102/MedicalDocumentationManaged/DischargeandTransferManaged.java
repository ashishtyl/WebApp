/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationManaged;

import com.is3102.EntityClass.DischargeSummary;
import com.is3102.EntityClass.Transfer;
import com.is3102.Exception.CaseException;
import com.is3102.Interface.DischargeAndTransferBean1Remote;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ashish
 */
@ManagedBean
public class DischargeandTransferManaged implements Serializable{
  @EJB
  private DischargeAndTransferBean1Remote dt;
  
  //case id to search case
  private Long caseId;
  //Id for discharge entity
  private Long dischargeId;
  //final diagnosis to enter in the discharge summary
  private String diagnosis;
  //final findings
  private String findings;
  //Doctors recommendation at the time of discharge
  private String recommendation;
  //Patient's condition at the time of discharge
  private String patientState;
  //medical procedure at the time of discharge
  private String medicalProcedure;
  //doctor referred to for transfer patient
  private String referDoctor;
  //reason for transfer
  private String reason;
  //Transfer entity to retrieve transfer case
  private Transfer transfer;
  //
  private DischargeSummary dischargeSummary;
  //
  private Long transferid;

    
 
  public void DogenerateDischargeSummary(){
      try{
          dt.generateDischargeSummary(caseId, diagnosis, findings, recommendation, patientState, medicalProcedure);
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
  }
  
  public void DogetDischargeSummary(){
      try{
         this.setDischargeSummary(dt.getDischargeSummary(dischargeId));
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
  }
  
  public void DoDeleteSummary(){
      dt.deleteDischargeSummary(dischargeId);
  }
  
  public void DoRecordTransfer(){
      try{
      dt.recodeTransfer(caseId, referDoctor, reason);
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
  }
  
  public void DoGetTransfer(){
      this.setTransfer(dt.getTransfer(transferid));
  }
  
     public void DoDeleteTransfer(){
      dt.deleteTransfer(transferid);
  }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getMedicalProcedure() {
        return medicalProcedure;
    }

    public void setMedicalProcedure(String medicalProcedure) {
        this.medicalProcedure = medicalProcedure;
    }

    public String getReferDoctor() {
        return referDoctor;
    }

    public void setReferDoctor(String referDoctor) {
        this.referDoctor = referDoctor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public DischargeSummary getDischargeSummary() {
        return dischargeSummary;
    }

    public void setDischargeSummary(DischargeSummary dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }
    
    public Long getDischargeId() {
        return dischargeId;
    }

    public void setDischargeId(Long dischargeId) {
        this.dischargeId = dischargeId;
    }

}