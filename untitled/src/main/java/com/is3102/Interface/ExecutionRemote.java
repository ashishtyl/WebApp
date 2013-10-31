/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Interface;

import com.is3102.EntityClass.ExecutionLog;
import com.is3102.Exception.ExistException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ashish
 */
import javax.ejb.Local;
@Local
public interface ExecutionRemote {

    public void AddExecutionRecordMedical(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException;

    public void AddExecutionRecordNursing(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException;

    public List<ExecutionLog> CreateEvaluationReport(Long procedure_id) throws ExistException;

    
    
}
