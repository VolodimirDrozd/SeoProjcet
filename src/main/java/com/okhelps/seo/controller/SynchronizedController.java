package com.okhelps.seo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.okhelps.seo.entity.Category;
import com.okhelps.seo.entity.Project;
import com.okhelps.seo.services.ProjectService;

@RequestMapping(name = "/synchronize")
public class SynchronizedController
{

    @Autowired
    ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    public void updateProjectData()
    {
        List<Project> listProjects = projectService.getProjects();
        projectService.setProjectIdCategories();
        List<Category> listCategories = projectService.getAllCategories(listProjects);
        projectService.saveListProjects(listProjects);
        projectService.saveListCategories(listCategories);

    }
}
