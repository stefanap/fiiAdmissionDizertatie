package com.fiiadmission.api.controller;

import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.domain.Setting;
import com.fiiadmission.service.AdmissionDataService;
import com.fiiadmission.service.SettingService;
import com.fiiadmission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/fii/statistics")
@PreAuthorize("hasAuthority('ADMIN_USER')")
public class StatisticsController {

    @Autowired
    private AdmissionDataService admissionDataService;

    @Autowired
    private SettingService settingService;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/registers-per-day")
    public Map<Long, Integer> studentsPerDay(){
        Map<Long, Integer> result = new HashMap<>();
        List<AdmissionData> admissionDataList = admissionDataService.findAll();
        Setting setting = settingService.findFirstBy();
        if(setting == null){
            return result;
        }

        Calendar cal = Calendar.getInstance();
        Long day;
        Integer count;
        for(AdmissionData admissionData : admissionDataList){
            //if(admissionData.getCreateDate().after(setting.getStartDate()) && admissionData.getCreateDate().before(setting.getEndDate())) {
                day = getDayMillisecons(admissionData.getCreateDate().getTime());
                count = result.get(day);
                if (count != null) {
                    count ++;
                    result.put(day, count);
                } else {
                    result.put(day, 1);
//                /}
            }
        }
        return result;
    }

    @GetMapping(value = "/registers-per-section")
    public Map<String, Integer> studentsPerSection(){
        Map<String, Integer> result = new HashMap<>();
        Integer count;
        List<AdmissionData> admissionDataList = admissionDataService.findAll();
        for(AdmissionData admissionData : admissionDataList){
            count = result.get(admissionData.getLanguage());
            if(count != null){
                count ++;
                result.put(admissionData.getLanguage(), count);
            }else{
                result.put(admissionData.getLanguage(), 1);
            }
        }
        return result;
    }

    @GetMapping(value = "/registers-per-admission-type")
    public Map<String, Integer> studentsPerAdmissionType(){
        Map<String, Integer> result = new HashMap<>();
        Integer count;
        List<AdmissionData> admissionDataList = admissionDataService.findAll();
        for(AdmissionData admissionData : admissionDataList){
            count = result.get(admissionData.getAdmissionType());
            if(count != null){
                count ++;
                result.put(admissionData.getAdmissionType(), count);
            }else{
                result.put(admissionData.getAdmissionType(), 1);
            }
        }
        return result;
    }

    private Long getDayMillisecons(long milliseconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

        //clear the hours, minutes, seconds, milliseconds in order to remain the day
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }
}
