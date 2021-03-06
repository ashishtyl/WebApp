/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationBean;


import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.ICD10_Code;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.CodingBeanRemote;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ashish
 */
@Stateless
public class CodingBean  implements CodingBeanRemote{
    @PersistenceContext
    EntityManager em;    

  public List<ICD10_Code> getMatchingCodes (String description){
      
      List<ICD10_Code> matchingCodes = (List<ICD10_Code>) em.createNativeQuery(""
              + "SELECT code, name"
              + "FROM ICD10_Code"
              + "WHERE (name LIKE '%description%')"
              ,com.is3102.EntityClass.ICD10_Code.class).getResultList();
      
      return matchingCodes;
  }
  
  public void removeDiagnosis(long diagnosisId )throws ExistException{
      
      Diagnosis diagnosis = em.find(Diagnosis.class, diagnosisId);
      if(diagnosis==null){
          throw new ExistException("No such Diagnosis entitiy exists!");
      }
      em.remove(diagnosis);
      System.out.println("Diagnosis "+ diagnosisId + " removed.");
      
  }
  //also works as add Diagnosis
  
  public void updateDiagnosis(Long caseId, ICD10_Code icd10Code,  String description) throws ExistException{
      mCase mcase = em.find(mCase.class, caseId);
      if(mcase==null){
          em.flush();
          throw new ExistException("No such case exists!");
      }
      Diagnosis diagnosis = new Diagnosis();
      diagnosis.create(icd10Code, description);
      mcase.getDiagnosis().add(diagnosis);
      em.persist(mcase);
 
  }
  
  public Collection<Diagnosis> listAllDiagnosis (long caseId) throws ExistException{
     
      mCase mcase = em.find(mCase.class, caseId);
      if(mcase==null){
          em.flush();
          throw new ExistException("No such case exists!");
      }
        return mcase.getDiagnosis();
     
  }
  
  public void addCode (String id, String Chapter, String block, String Disease, String name ) throws ExistException{
      ICD10_Code code = em.find(ICD10_Code.class, id);
      if(code != null){
          throw new ExistException("Such a disease code already exists!");
      }
      
      code = new ICD10_Code();
      code.create(id, Chapter, block, Disease, name);
      em.persist(code);
      System.out.println("ICD 10 Code "+code.getCode()+" "+ code.getName());
  }
  
  public Set<ICD10_Code> listAllCodes (){
      Query q = em.createQuery("SELECT c FROM ICD10_Code c");
      Set codeset = new HashSet<ICD10_Code>();
      
      for(Object o: q.getResultList()){
          ICD10_Code code = (ICD10_Code)o;
          codeset.add(code);
                  
      }
      
      return codeset;
  }
  
}