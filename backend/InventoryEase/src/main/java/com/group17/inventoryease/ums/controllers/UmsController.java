package com.group17.inventoryease.ums.controllers;

/*
* Source: https://rahulrockin.medium.com/building-user-authentication-android-application-using-android-studio-and-spring-boot-backend-480676f858e
*         https://medium.com/@mariorodrguezgalicia/what-is-a-dto-in-spring-boot-and-why-should-you-use-it-97651506e516
*         https://www.baeldung.com/java-optional
*         https://www.baeldung.com/spring-response-entity
* */
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ums")
public class UmsController {

    @Autowired
    private SchemaService schemaService;

    @Autowired
    private TenantIdentifierResolver tenantIdentifierResolver;

    @PostMapping("/validate-company-identifier")
    public ResponseEntity<CompanyIdResponse> validateCompany(@RequestBody CompanyIdRequest request){
        return schemaService.getSchemaByCompanyId(request.getCompanyId())
                // If a schema is found, set it has the current one and respond to the client with the company name (200 OK).
                .map(schema -> {
                    tenantIdentifierResolver.setCurrentTenant(schema);
                    String companyName = schemaService.getCompanyNameByCompanyId(request.getCompanyId());
                    return ResponseEntity.ok(new CompanyIdResponse(true, companyName))
                })
                // If a schema is not found, respond to the client with an error HTTP status (404 NOT FOUND).
                .orElseGet(() -> ResponseEntiy.status(HttpStatus.NOT_FOUND).body(new CompanyIdResponse(false, "")));
    }

    // Source: https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request){
        return authenticationService.authenticate(request)
                .map(authenticatedUser -> {
                    jwtService.generateToken(authenticatedUser);
                    return ResponseEntity.ok(new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime()));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND));
    }
}