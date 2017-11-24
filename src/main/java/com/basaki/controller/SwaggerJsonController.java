package com.basaki.controller;

import com.basaki.config.SwaggerJson;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

/**
 * {@code SwaggerJsonController} is responsible for fulfilling Swagger/SpringFox
 * related requests .
 * <p/>
 *
 * @author Indra Basak
 * @since 11/23/17
 */
@RestController
@Slf4j
public class SwaggerJsonController {

    private static String swaggerResource = "[\n" +
            "  {\n" +
            "    \"name\": \"gson\",\n" +
            "    \"location\": \"/v2/api-docs?group=gson\",\n" +
            "    \"swaggerVersion\": \"2.0\"\n" +
            "  }\n" +
            "]";

    private static String uiConfiguration = "{\n" +
            "  \"docExpansion\": \"none\",\n" +
            "  \"apisSorter\": \"alpha\",\n" +
            "  \"defaultModelRendering\": \"schema\",\n" +
            "  \"supportedSubmitMethods\": [\n" +
            "    \"get\",\n" +
            "    \"post\",\n" +
            "    \"put\",\n" +
            "    \"delete\",\n" +
            "    \"patch\"\n" +
            "  ],\n" +
            "  \"jsonEditor\": false,\n" +
            "  \"showRequestHeaders\": true\n" +
            "}";

    private static String securityConfiguration = "{\n" +
            "  \"apiKeyName\": \"api_key\",\n" +
            "  \"scopeSeparator\": \",\",\n" +
            "  \"apiKeyVehicle\": \"header\"\n" +
            "}";

    private SwaggerJson swaggerJson;

    @Autowired
    public SwaggerJsonController(SwaggerJson swaggerJson) {
        this.swaggerJson = swaggerJson;
    }

    /**
     * Responsible for returning the Swagger JSON document.
     *
     * @param swaggerGroup
     * @param servletRequest
     * @return
     */
    @RequestMapping(
            value = {"/v2/api-docs"},
            method = {RequestMethod.GET},
            produces = {"application/json", "application/hal+json"}
    )
    @ResponseBody
    public ResponseEntity<Json> getDocumentation(
            @RequestParam(value = "group", required = false) String swaggerGroup,
            HttpServletRequest servletRequest) {

        return new ResponseEntity(swaggerJson.getJson(), HttpStatus.OK);
    }

    /**
     * Responsible for returning {@code SwaggerResource} when requested by
     * swagger-ui.html.
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = {"/swagger-resources"},
            method = {RequestMethod.GET},
            produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Json> getSwaggerResource(
            HttpServletRequest servletRequest) {
        return new ResponseEntity(swaggerResource, HttpStatus.OK);
    }

    /**
     * Responsible for returning {@code UIConfiguration} when requested by
     * swagger-ui.html.
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = {"/swagger-resources/configuration/ui"},
            method = {RequestMethod.GET},
            produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Json> getUIConfiguration(
            HttpServletRequest servletRequest) {

        return new ResponseEntity(uiConfiguration, HttpStatus.OK);
    }

    /**
     * Responsible for returning {@code SecurityConfiguration} when requested by
     * swagger-ui.html.
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = {"/swagger-resources/configuration/security"},
            method = {RequestMethod.GET},
            produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Json> getSecurityConfiguration(
            HttpServletRequest servletRequest) {

        return new ResponseEntity(securityConfiguration, HttpStatus.OK);
    }
}
