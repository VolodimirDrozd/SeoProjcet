package com.okhelps.seo.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.okhelps.seo.entity.Category;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryResponse {

	private boolean success;

	private List<Category> data;

	private String timestamp;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<Category> getData() {
		return data;
	}

	public void setData(List<Category> data) {
		this.data = data;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
