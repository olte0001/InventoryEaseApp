package com.group17.inventoryease.ums.controllers;

/*
* Source: https://rahulrockin.medium.com/building-user-authentication-android-application-using-android-studio-and-spring-boot-backend-480676f858e
*         https://medium.com/@mariorodrguezgalicia/what-is-a-dto-in-spring-boot-and-why-should-you-use-it-97651506e516
*         https://www.baeldung.com/java-optional
*         https://www.baeldung.com/spring-response-entity
* */

import com.group17.inventoryease.ums.context.TenantContext;
import com.group17.inventoryease.ums.services.SchemaService;
import com.group17.inventoryease.ums.beans.TenantIdentifierResolver;
import com.group17.inventoryease.ums.services.AuthenticationService;
import com.group17.inventoryease.ums.services.JwtService;
import com.group17.inventoryease.ums.dtos.CompanyIdRequest;
import com.group17.inventoryease.ums.dtos.CompanyIdResponse;
import com.group17.inventoryease.ums.dtos.LoginRequest;
import com.group17.inventoryease.ums.dtos.LoginResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ums")
public class UmsController {

    @Autowired
    private SchemaService schemaService;

    @Autowired
    private TenantIdentifierResolver tenantIdentifierResolver;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    private static final Logger log = LoggerFactory.getLogger(UmsController.class);

    @PostMapping("/validate-company-identifier")
    public ResponseEntity<CompanyIdResponse> validateCompany(@RequestBody CompanyIdRequest request){
        log.debug("Validating company ID: {}", request.getCompanyId());
        log.debug("Raw company ID string: '{}'", request.getCompanyId());
        return schemaService.getSchemaByCompanyId(Long.valueOf(request.getCompanyId()))
                // If a schema is found, set it has the current one and respond to the client with the company name (200 OK).
                .map(schema -> {
                    String companyName = schemaService.getCompanyNameByCompanyId(Long.valueOf(request.getCompanyId()))
                            .orElse("");
                    log.debug("Company found: {}", companyName);
                    return ResponseEntity.ok(new CompanyIdResponse(true, companyName, schema));
                })
                // If a schema is not found, respond to the client with an error HTTP status (404 NOT FOUND).
                .orElseGet(() -> {
                        log.warn("Company ID not found: {}", request.getCompanyId());
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CompanyIdResponse(false, "", ""));
                });
    }

    // Source: https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request){
        TenantContext.setCurrentTenant(request.getSchemaName());
        return authenticationService.authenticate(request)
                .map(authenticatedUser -> {
                    String jwtToken = jwtService.generateToken(authenticatedUser);
                    return ResponseEntity.ok(new LoginResponse(jwtToken, jwtService.getExpirationTime()));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}