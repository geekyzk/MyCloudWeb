package com.em248.service.impl;

import com.em248.dao.cloudfoundry.ApplicationDao;
import com.em248.entity.CloudFoundryPage;
import com.em248.service.ApplicationService;
import org.cloudfoundry.client.v2.applications.ApplicationResource;
import org.cloudfoundry.client.v2.applications.CopyApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tian on 2017/4/17.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationDao applicationDao;

    @Autowired
    public ApplicationServiceImpl(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @Override
    public List<ApplicationResource> getApplicationBySpaceGuid(CloudFoundryPage page, String spaceGuid) {
        return applicationDao.getApplicationsBySpace(page,spaceGuid);
    }

    @Override
    public CopyApplicationResponse copyApplication(String targetAppId,String sourceAppId){
        return applicationDao.copyApplicationResource(targetAppId,sourceAppId);
    }
}
