package org.earelin.ecclesia.service;

import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.service.impl.OrganizationServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * OrganizationServiceImpl unit test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-test-config.xml"})
public class OrganizationServiceTest {
    
    @Autowired
    private OrganizationService instance;
    
    @Before
    public void init() {
    }

    /**
     * Test of add method, of class OrganizationServiceImpl.
     */
    @Test
    public void createNewOrganization() {
    }

    /**
     * Test of update method, of class OrganizationServiceImpl.
     */
    @Test
    public void updateExistedOrganization() {
    }

    /**
     * Test of remove method, of class OrganizationServiceImpl.
     */
    @Test
    public void removeExistedOrganization() {
    }

    /**
     * Test of get method, of class OrganizationServiceImpl.
     */
    @Test
    public void getExistedOrganization() {
    }
    
}
