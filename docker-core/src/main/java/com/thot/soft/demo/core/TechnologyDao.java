package com.thot.soft.demo.core;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import javax.ejb.Singleton;
import java.net.UnknownHostException;

@Singleton
public class TechnologyDao extends BasicDAO<Technology, String> {

    public TechnologyDao() throws UnknownHostException {
        super(Technology.class, new MongoClient(System.getProperty("db.host")),
                new Morphia(), System.getProperty("db.name"));
    }

}
