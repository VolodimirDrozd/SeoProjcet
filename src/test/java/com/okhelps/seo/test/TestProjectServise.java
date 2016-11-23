package com.okhelps.seo.test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.okhelps.seo.configuration.SpringConfig;
import com.okhelps.seo.configuration.SpringMongoConfig;
import com.okhelps.seo.entity.Category;
import com.okhelps.seo.entity.Project;
import com.okhelps.seo.services.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class, SpringMongoConfig.class })
public class TestProjectServise {

	@Autowired
	ProjectService projectService;

	private List<Project> listProject ;
	private List<Category> listCategories ;
	private List<Category> allCategories;

	@Test
	public void getProjects() throws IOException {
		listProject = projectService.getProjects();
		Assert.assertNotNull(listProject);
	}

	@Test
	public void getCategories() throws IOException {
		this.listCategories = projectService.getCategories(listProject.get(0)
				.getId());
		Assert.assertNotNull(listCategories);
	}

	@Test
	public void getAllCategories() throws IOException {
		allCategories = projectService.getAllCategories(listProject);
		Assert.assertNotNull(allCategories);
	}

	@Test
	public void differentSizeAboutReturnCategoriesMethods() throws IOException {
		Assert.assertTrue(allCategories.size() > listCategories.size());
	}

//	@Test
//	public void saveListProjects() {
//		listProject = projectService.getProjects();
//	projectService.saveListProjects(listProject);
	
//	}

	@Test
	public void saveCategories() {
		listCategories = projectService.getCategories(listProject.get(0)
				.getId());
		projectService.saveListCategories(listCategories);
		// mongoTemplate.getCollection(collectionName)
	}
	
}
