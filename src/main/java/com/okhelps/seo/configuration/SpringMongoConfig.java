package com.okhelps.seo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration
{

    @Value("${mongodbName}")
    protected String mongodbName;

    @Value("${mongodbPort}")
    protected String mongodbPort;

    @Override
    protected String getDatabaseName()
    {
        return "dataBaseName";
    }

    @Override
    public Mongo mongo() throws Exception
    {
        return new MongoClient(mongodbPort, 27017);
    }
}
