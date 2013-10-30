/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationBean;

import com.is3102.EntityClass.Consent;
import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.Finding;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.DecisionMakingandPlaningRemote;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashish
 */
@Stateless
public class DecisionMakingandPlaning implements DecisionMakingandPlaningRemote{

  @PersistenceContext()
    EntityManager em;

    public void AddPlanedProcedure(Long CIN, String procedure_code, String procedure_name, String finding, String comments ) throws ExistException{
       System.out.println("In DMP Bean AddPlannedProcedure"); 
        mCase mcase = em.find(mCase.class, CIN);
        if(mcase==null){
            throw new ExistException("No such case exists");
        }
        System.out.println("Mcase found");
        Medical_Procedure procedure = new Medical_Procedure();
        System.out.println("created procedure");
        procedure.create(procedure_code, procedure_name, finding, comments);        
        System.out.println("created procedure 2");
        
        em.persist(procedure);
        
        mcase.addmedicaProcedure(procedure);
         System.out.println("added procedure");
        procedure.setMcase(mcase);
         System.out.println("set mcase");
         
        System.out.println("Medical Procedure " + procedure.getId() + 
                "added to case " + mcase.getCIN());
        
        em.persist(mcase);       
        em.flush();
    }
    
    public void GetConsent(Long procedureId, String patient_comment) throws ExistException {
        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedureId);
        if(procedure==null){
            throw new ExistException("No such procedure exists!");
        }
        
        Consent consent = new Consent();
        consent.create(patient_comment);
        procedure.setConsent(consent);        
        
    }
    
    public List<Medical_Procedure> RetrieveCarePlaning (Long CIN) throws ExistException{
        System.out.println("In DNP EJB");
        List<Medical_Procedure> procedures;
        
        mCase mcase = em.find(mCase.class, CIN);
        if(mcase==null){
            throw new ExistException("No such case exists!");
        }
        System.out.println("mCase found");
        procedures = mcase.getmProcedures();
        System.out.println("Returning procedures");
        return procedures;
    }

    @Override
    public void RetrieveMedicalKnowledge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    
}
