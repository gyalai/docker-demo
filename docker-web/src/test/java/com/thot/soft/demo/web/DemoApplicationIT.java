package com.thot.soft.demo.web;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbConfigurationBuilder;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.thot.soft.demo.core.Technology;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.jboss.shrinkwrap.resolver.api.maven.coordinate.MavenDependencies;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class DemoApplicationIT {

    @Inject
    private DemoApplication application;

    @Rule
    public MongoDbRule mongoDb = new MongoDbRule(MongoDbConfigurationBuilder.mongoDb()
            .host(System.getProperty("db.host", "localhost"))
            .databaseName(System.getProperty("db.name", "demo"))
            .build());

    @Deployment
    public static WebArchive getBasicWebArchive() {
        File[] dependencies = Maven.configureResolver().withMavenCentralRepo(false)
                .loadPomFromFile("pom.xml").importRuntimeAndTestDependencies()
                .addDependencies(
                        MavenDependencies.createDependency("com.lordofthejars:nosqlunit-mongodb:0.8.1",
                                ScopeType.COMPILE, false))
                .addDependencies(MavenDependencies.createDependency(
                        "org.jboss.shrinkwrap.resolver:shrinkwrap-resolver-impl-maven:2.2.0-beta-2",
                        ScopeType.COMPILE, false))
                .resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.thot.soft.demo.web")
                .addAsResource("test-data")
                .addAsLibraries(dependencies);
    }

    @Test
    public void testEcho() throws Exception {
        assertEquals("echo", application.echo());
    }

    @Test
    @UsingDataSet(locations = "/test-data/input01.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    @ShouldMatchDataSet(location = "/test-data/expected.json")
    public void testSave() throws Exception {
        Technology technology = new Technology();
        technology.setName("test");
        technology.setDescription("testDescription");
        technology.setUrl("testURL");
        application.save(technology);
    }

    @Test
    @UsingDataSet(locations = "/test-data/input02.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    @ShouldMatchDataSet(location = "/test-data/input02.json")
    public void testList() throws Exception {
        List<Technology> listResult = application.list();

        assertEquals(1, listResult.size());

        assertEquals("testInput", listResult.get(0).getName());
    }
}