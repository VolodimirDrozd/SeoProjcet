package com.okhelps.seo.api;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okhelps.seo.bean.CategoryResponse;
import com.okhelps.seo.bean.ProjectResponse;

@Component
public class SeoApi {

	@Autowired
	@Qualifier("mapper")
	private ObjectMapper objectMapper;

	@Value("${baseUrl}")
	private String baseUrl;

	public Call<ProjectResponse> getProjects() {
		return generateAPI().getProjects();
	}

	public Call<CategoryResponse> getCategories(ObjectId projectId) {
		return generateAPI().getCategories(projectId);
	}

	private API generateAPI() {
		Retrofit retofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(
						JacksonConverterFactory.create(objectMapper)).build();
		return retofit.create(API.class);
	}

	public interface API {

		@GET("api/get-projects/9efd67049aa3aa639b83b5a6c248e395")
		Call<ProjectResponse> getProjects();

		@GET("api/get-project/{categoryId}/9efd67049aa3aa639b83b5a6c248e395")
		Call<CategoryResponse> getCategories(
				@Path("categoryId") ObjectId categoryId);
	}

}
