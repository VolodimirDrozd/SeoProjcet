package com.okhelps.seo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.okhelps.seo.api.SeoApi;
import com.okhelps.seo.bean.CategoryResponse;
import com.okhelps.seo.entity.Category;
import com.okhelps.seo.entity.Project;

@Service
public class ProjectService
{

    final static Logger logger = Logger.getLogger(ProjectService.class);

    @Autowired
    private SeoApi seoApi;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Project> getProjects()
    {
        try
        {
            return seoApi.getProjects().execute().body().getData();
        } catch (IOException e)
        {
            logger.error("This is Error message", e);
        }
        return new ArrayList<Project>();
    }

    public List<Category> getCategories(int projectId)
    {
        try
        {
            return seoApi.getCategories(projectId).execute().body().getData();
        } catch (IOException e)
        {
            logger.error("This is Error message", e);
        }
        return new ArrayList<Category>();
    }

    public List<Category> getAllCategories(List<Project> projectList)
    {
        try
        {
        List<Category> category = new ArrayList<>();
        for (int i = 0; i < projectList.size(); i++)
        {
            List<Category> listCategory = getCategories(projectList.get(i).getId());
            if (listCategory != null)
            {
                category.addAll(listCategory);
            }
        }
        return category;
        } catch (Exception e)
        {
            logger.error("This is Error message", e);
        }
        return new ArrayList<Category>();
    }

    public void saveListProjects(List<Project> listProjects)
    {
        mongoTemplate.save(listProjects);
    }

    public void saveListCategories(List<Category> listCategories)
    {
        mongoTemplate.save(listCategories);

    }

    public void setProjectIdCategories()
    {
        CategoryResponse categoryResponse = null;
        List<Project> listProject = getProjects();
        for (Project p : listProject)
        {
            try
            {
                categoryResponse = seoApi.getCategories(p.getId()).execute().body();
                Iterator<Category> iterableCategory = categoryResponse.getData().iterator();
                while (iterableCategory.hasNext())
                {
                    Category category = iterableCategory.next();
                    category.setProjectId(p.getId());
                }
            } catch (IOException e)
            {
                logger.error("This is Error message", e);
            }

        }
    }

}
