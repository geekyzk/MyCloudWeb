package com.em248.service;

import org.cloudfoundry.client.v2.buildpacks.BuildpackResource;

import java.util.List;

/**
 * Created by tian on 2017/4/17.
 */
public interface BuildpackService {


    List<BuildpackResource>  getBuildpacks();
}
