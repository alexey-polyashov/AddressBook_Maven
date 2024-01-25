package com.example.adrbook.service;

import com.example.adrbook.entity.ServiceInfo;
import com.example.adrbook.repo.ServiceInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class ServiceInfoService {
    @Autowired
    ServiceInfoRepo serviceInfoRepo;

    public void setUpdateData(){
        ServiceInfo serviceInfoList = serviceInfoRepo.getFirstByOrderById();
        if(serviceInfoList==null){
            serviceInfoList = new ServiceInfo();
        }
        Date currentDate = new Date();
        serviceInfoList.setUpdateDate(new java.sql.Timestamp(currentDate.getTime()));
        serviceInfoRepo.save(serviceInfoList);
    }

    public String getUpdateData(String format){
        ServiceInfo serviceInfoList = serviceInfoRepo.getFirstByOrderById();
        Date updateDate = serviceInfoList.getUpdateDate();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(updateDate);
    }
}
