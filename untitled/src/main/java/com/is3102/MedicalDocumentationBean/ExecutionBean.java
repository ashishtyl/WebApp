/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationBean;

import com.is3102.EntityClass.Doctor;
import com.is3102.EntityClass.ExecutionLog;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.Nursing_Procedure;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.ExecutionRemote;
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
public class ExecutionBean implements ExecutionRemote {

    @PersistenceContext()
    EntityManager em;

    public void AddExecutionRecordMedical(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException {
        System.out.println("In ExcB: addMedicalExecution");
        Doctor doctor = em.find(Doctor.class, doctor_id);
        if (doctor == null) {
            em.clear();
            System.out.println("Doctor not found");
            throw new ExistException("Doctor not found!");
        }

        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedure_id);
        if (procedure == null) {
            em.clear();
            throw new ExistException("No such procedure created");
        }
        ExecutionLog eLog = new ExecutionLog();
        eLog.create(doctor, exeuction_comment);
        System.out.println("Log created");

        procedure.addExecutionLog(eLog);
        System.out.println("Log appended");

    }

    public void AddExecutionRecordNursing(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException {
        System.out.println("In ExcB: addNursingExecution");
        Doctor doctor = em.find(Doctor.class, doctor_id);
        if (doctor == null) {
            em.clear();
            System.out.println("Doctor not found");
            throw new ExistException("Doctor not found!");
        }

        Nursing_Procedure procedure = em.find(Nursing_Procedure.class, procedure_id);
        if (procedure == null) {
            em.clear();
            System.out.println("Nursing procedure not found");
            throw new ExistException("No such procedure created");
        }

        ExecutionLog eLog = new ExecutionLog();
        eLog.create(doctor, exeuction_comment);
        System.out.println("Log created");
        procedure.addExecutionLog(eLog);
        System.out.println("Log appended");

    }

    public List<ExecutionLog> CreateEvaluationReport(Long procedure_id) throws ExistException {
        System.out.println("In ExcB: createEvaluationReport");
        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedure_id);
        
        if (procedure == null) {
            em.flush();
            System.out.println("no preocdure found");
            throw new ExistException("No such procedure created!");
        }
        System.out.println("Procedure found");
        List<ExecutionLog> logs = procedure.getExecutionlogs();
        return logs;
    }
}
