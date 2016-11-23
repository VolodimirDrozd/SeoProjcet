package com.okhelps.seo.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okhelps.seo.entity.Category;
import com.okhelps.seo.entity.Project;
import com.okhelps.seo.services.ProjectService;

@RestController
@RequestMapping("/api/seo")
public class SeoDataController {

	@Autowired
	ProjectService projectService;

	@GetMapping("/categories/project")
	public List<Category> getCategoriesByProjectId(
			@RequestParam ObjectId projectId) {
		return projectService.getCategories(projectId);
	}

	@GetMapping("/project/all")
	public List<Project> getProjects() {
		return projectService.getProjects();
	}

	@GetMapping("/categories/all")
	public List<Category> getCategories() {
		return projectService.getAllCategories(projectService.getProjects());

	}
}
