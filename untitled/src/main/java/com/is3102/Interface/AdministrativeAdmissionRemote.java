/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Interface;

import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.mCase;
import javax.ejb.Remote;
import com.is3102.Exception.ExistException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Swarit
 */
@Remote
public interface AdministrativeAdmissionRemote {

    public String addPatient(String NRIC_PIN, String name, String birthday, String address, String cNumber) throws ExistException, ParseException, Exception;

    public long makeAppointment(String NRIC_PIN, String appDate, String place, String docId) throws ExistException, ParseException;
    
    public long createCase(String bedNo, String appId) throws ExistException;

    public List<Bed> getAvailBeds();

    public mCase getmCase(String CIN);

    public List<mCase> getmCases();
}