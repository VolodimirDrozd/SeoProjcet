package com.okhelps.seo.test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.okhelps.seo.configuration.SpringConfig;
import com.okhelps.seo.configuration.SpringMongoConfig;
import com.okhelps.seo.entity.Category;
import com.okhelps.seo.entity.Project;
import com.okhelps.seo.services.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =
{
        SpringConfig.class, SpringMongoConfig.class
})
public class TestProjectServise
{

    @Autowired
    ProjectService projectService;

    @Autowired
    MongoTemplate mongoTemplate;

    private List<Project> listProject;
    private List<Category> listCategories;
    private List<Category> allCategories;

    @Before
    public void testGetterProjects() throws IOException
    {
        listProject = projectService.getProjects();
        projectService.setProjectIdCategories();
        Assert.assertNotNull(listProject);

    }

    @Test
    public void testSetterProjectIdCategories() throws IOException
    {
        listCategories = projectService.getCategories(listProject.get(0).getId());
        Assert.assertNotNull(listCategories.get(0).getId());
    }

    @Test
    public void testGetterAllCategories()
    {
        allCategories = projectService.getAllCategories(listProject);
        Assert.assertNotNull(allCategories.size());
    }

    @Test
    public void saveListProjects()
    {
        projectService.saveListProjects(listProject);

        BasicQuery queryProjectId = new BasicQuery("{id:23}");
        mongoTemplate.findOne(queryProjectId, Project.class);
    }
    //
    //    @Test
    //    public void saveCategories()
    //    {
    //        listCategories = projectService.getCategories(listProject.get(0).getId());
    //        projectService.saveListCategories(listCategories);
    //        BasicQuery queryProjectId = new BasicQuery("{id:23}");
    //        mongoTemplate.findOne(queryProjectId, Project.class);
    //    }

}
