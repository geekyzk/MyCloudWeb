package com.em248.service.impl;

import com.em248.dao.cloudfoundry.BuildpackDao;
import com.em248.service.BuildpackService;
import org.cloudfoundry.client.v2.buildpacks.BuildpackResource;

import java.util.List;

/**
 * Created by tian on 2017/4/17.
 */
public class BuildpackServiceImpl implements BuildpackService {

    BuildpackDao buildpackDao;

    public BuildpackServiceImpl(BuildpackDao buildpackDao) {
        this.buildpackDao = buildpackDao;
    }

    @Override
    public List<BuildpackResource> getBuildpacks() {
       return  buildpackDao.getBuildpacks();
    }
}
