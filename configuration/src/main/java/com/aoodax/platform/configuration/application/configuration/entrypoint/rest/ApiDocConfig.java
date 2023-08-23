package com.aoodax.platform.configuration.application.configuration.entrypoint.rest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.core.DefaultLinkRelationProvider;
import org.springframework.util.StringUtils;

@Configuration
@PropertySource("classpath:api-docs.properties")
public class ApiDocConfig {

    private final String serverUrl;
    private final String defaultServerUrl;
    private final String apiVersion;
    private final String apiTitle;
    private final String contactName;
    private final String licenseName;
    private final String licenseUrl;
    private final String contactEmails;
    private final String termsOfService;

    ApiDocConfig(@Value("${openapi3.server.url:}") final String serverUrl,
                 @Value("http://127.0.0.1:${server.port:8080}") final String defaultServerUrl,
                 @Value("${api.version:v1}") final String apiVersion,
                 @Value("${api.title:[REST Api]}") final String apiTitle,
                 @Value("${api.term-of-service:[Terms of service]}") final String termsOfService,
                 @Value("${api.contact.name:AOODAX team}") final String contactName,
                 @Value("${api.contact.email:support@aoodax.com}") final String contactEmails,
                 @Value("${api.license.name:[Platform REST API license name]}") final String licenseName,
                 @Value("${api.license.url:[Platform REST API license url]}") final String licenseUrl
    ) {
        this.serverUrl = serverUrl;
        this.defaultServerUrl = defaultServerUrl;
        this.apiVersion = apiVersion;
        this.apiTitle = apiTitle;
        this.termsOfService = termsOfService;
        this.contactName = contactName;
        this.licenseName = licenseName;
        this.licenseUrl = licenseUrl;
        this.contactEmails = contactEmails;
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .addServersItem(
                        new Server()
                                .url(StringUtils.hasText(serverUrl) ? serverUrl : defaultServerUrl)
                )
                .info(new Info().title(apiTitle)
                        .version(apiVersion)
                        .termsOfService(termsOfService)
                        .contact(
                                new Contact().name(contactName)
                                        .email(contactEmails)
                        )
                        .license(
                                new License().name(licenseName)
                                        .url(licenseUrl)
                        )
                )
                ;
    }

    @Bean
    public LinkRelationProvider linkRelationProvider() {
        return new DefaultLinkRelationProvider();
    }
}
