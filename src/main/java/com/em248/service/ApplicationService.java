package com.em248.service;

import com.em248.entity.CloudFoundryPage;
import org.cloudfoundry.client.v2.applications.ApplicationResource;
import org.cloudfoundry.client.v2.applications.CopyApplicationResponse;

import java.util.List;

/**
 * Created by tian on 2017/4/17.
 */
public interface ApplicationService {

    List<ApplicationResource> getApplicationBySpaceGuid(CloudFoundryPage page, String spaceGuid);

    CopyApplicationResponse copyApplication(String targetAppId, String sourceAppId);
}
