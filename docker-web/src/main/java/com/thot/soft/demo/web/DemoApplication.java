package com.thot.soft.demo.web;

import com.thot.soft.demo.core.Technology;
import com.thot.soft.demo.core.TechnologyDao;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationPath("/")
@Path("/technology")
public class DemoApplication extends Application {

    @Inject
    private TechnologyDao service;

    @GET
    @Path("/echo")
    @Produces(MediaType.APPLICATION_JSON)
    public String echo() {
        return "echo";
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(Technology technology) {
        service.save(technology);
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Technology> list() {
        return service.find().asList();
    }
}
