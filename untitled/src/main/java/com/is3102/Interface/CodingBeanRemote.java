/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Interface;

import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.ICD10_Code;
import com.is3102.Exception.ExistException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author Ashish
 */import javax.ejb.Local;
@Local
public interface CodingBeanRemote {

    public List<ICD10_Code> getMatchingCodes(String description);

    public void updateDiagnosis(Long caseId, ICD10_Code icd10Code, String description) throws ExistException;

    public Collection<Diagnosis> listAllDiagnosis(long caseId) throws ExistException;

    public void addCode(String id, String Chapter, String block, String Disease, String name) throws ExistException;

    public Set<ICD10_Code> listAllCodes();

    public void removeDiagnosis(long diagnosisId)throws ExistException;
    
}
