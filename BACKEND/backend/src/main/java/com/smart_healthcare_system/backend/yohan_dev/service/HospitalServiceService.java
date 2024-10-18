package com.smart_healthcare_system.backend.yohan_dev.service;

import com.smart_healthcare_system.backend.yohan_dev.dto.HospitalServiceReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.HospitalServiceRes;

import java.util.List;

public interface HospitalServiceService {
    void createService(HospitalServiceReq hospitalServiceReq);
    List<HospitalServiceRes> getAllServices();
    HospitalServiceRes getServiceById(String id);
    void updateService(String id, HospitalServiceReq hospitalServiceReq);
    void deleteService(String id);
}
