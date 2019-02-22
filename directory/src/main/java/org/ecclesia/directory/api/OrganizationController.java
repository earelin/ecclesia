package org.ecclesia.directory.api;

import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

  private final OrganizationService organizationService;

  public OrganizationController(OrganizationService organizationService) {
    this.organizationService = organizationService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Organization> findById(@PathVariable long id) {
    return ResponseEntity.of(organizationService.findById(id));
  }
}
