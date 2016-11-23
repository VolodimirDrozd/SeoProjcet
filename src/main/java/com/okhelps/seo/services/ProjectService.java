package com.okhelps.seo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.okhelps.seo.api.SeoApi;
import com.okhelps.seo.bean.CategoryResponse;
import com.okhelps.seo.entity.Category;
import com.okhelps.seo.entity.Project;

@Service
public class ProjectService {

	final static Logger logger = Logger.getLogger(ProjectService.class);

	@Autowired
	private SeoApi seoApi;

	@Autowired
	MongoTemplate mongoTemplate;

	public List<Project> getProjects() {
		try {
			return seoApi.getProjects().execute().body().getData();
		} catch (IOException e) {
			logger.error("This is Error message", e);
		}
		return new ArrayList<Project>();
	}

	public List<Category> getCategories(ObjectId projectId) {
		try {
			return seoApi.getCategories(projectId).execute().body().getData();
		} catch (IOException e) {
			logger.error("This is Error message", e);
		}
		return new ArrayList<Category>();
	}

	public List<Category> getAllCategories(List<Project> listProjects) {
		setAllCategoriesProjectId();
		try {
			CategoryResponse categoryResponse = null;
			for (Project p : listProjects) {
				categoryResponse = seoApi.getCategories(p.getId()).execute()
						.body();
			}
			return fillCategories(categoryResponse.getData());
		} catch (IOException e) {
			logger.error("This is Error message", e);
			e.printStackTrace();
		}
		return new ArrayList<Category>();
	}

	public void saveListProjects(List<Project> listProjects) {
		mongoTemplate.save(listProjects);
	}

	public void saveListCategories(List<Category> listCategories) {
		mongoTemplate.save(listCategories);

	}

	private List<Category> fillCategories(List<Category> listCategories) {
		List<Category> result = new ArrayList<Category>();
		Map<Object, Category> categories = new HashMap<>();
		for (Category category : listCategories) {
			categories.put(category.getId(), category);
		}
		for (Category category : listCategories) {
			if (category.getParentId().equals(0)) {
				result.add(category);
			} else {
				categories.get(category.getParentId()).getChildren()
						.add(category);
			}
		}
		return result;
	}

	public void setAllCategoriesProjectId() {
		CategoryResponse categoryResponse = null;
		List<Project> listProject = getProjects();
		for (Project p : listProject) {
			try {
				categoryResponse = seoApi.getCategories(p.getId()).execute()
						.body();
				Iterator<Category> iterableCategory = categoryResponse
						.getData().iterator();
				while (iterableCategory.hasNext()) {
					Category category = iterableCategory.next();
					category.setProjectId(p.getId());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
