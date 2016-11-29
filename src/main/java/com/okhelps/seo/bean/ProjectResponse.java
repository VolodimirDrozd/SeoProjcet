package com.okhelps.seo.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.okhelps.seo.entity.Project;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectResponse {

	private boolean success;

	@JsonProperty("data")
	private List<Project> data;

	private String timestamp;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<Project> getData() {
		return data;
	}

	public void setData(List<Project> data) {
		this.data = data;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
