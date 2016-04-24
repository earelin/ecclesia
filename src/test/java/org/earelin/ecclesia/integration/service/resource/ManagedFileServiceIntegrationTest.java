package org.earelin.ecclesia.integration.service.resource;

import org.earelin.ecclesia.service.resource.ManagedFileService;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ManagedFileService integration test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class ManagedFileServiceIntegrationTest {
    
    @Autowired
    private ManagedFileService instance;

    
    
}
