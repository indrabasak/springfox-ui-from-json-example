package com.basaki.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * {@code SwaggerJsonConfiguration} is responsible for reading the swagger.json
 * file for classpath or file sytstem.
 * <p/>
 *
 * @author Indra Basak
 * @since 11/23/17
 */
@Configuration
@Slf4j
public class SwaggerJsonConfiguration {

    @Bean
    public SwaggerJson swaggerConfiguration(
            @Value("${swagger.json.location}") String location,
            ResourceLoader loader) throws IOException {

        InputStream istream = getResource(location, loader);
        if (istream == null) {
            throw new FileNotFoundException(
                    "Scope configuration file " + location + " not found.");
        }

        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader
                     = new BufferedReader(new InputStreamReader(istream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        }
        String json = builder.toString();

        SwaggerJson swaggerJson = new SwaggerJson();
        swaggerJson.setJson(json);

        return swaggerJson;
    }

    /**
     * Looks for a resource at different locations in order of preference.
     *
     * @param location location of the resourcve
     * @param loader   the resource loader
     * @return input stream of the resource, null if not found
     */
    private InputStream getResource(String location, ResourceLoader loader) {

        InputStream istream = null;
        try {
            istream = loader.getResource(location).getInputStream();
        } catch (IOException e) {
            log.debug(
                    "Searching for swagger JSON. Not found in " + location);
        }

        if (istream == null) {
            Set<String> schemes = new LinkedHashSet<>();
            schemes.add("file:./config/");
            schemes.add("file:./");
            schemes.add("classpath:/config/");
            schemes.add("classpath:/");
            for (String scheme : schemes) {
                try {
                    istream =
                            loader.getResource(
                                    scheme + location).getInputStream();
                } catch (IOException e) {
                    log.debug(
                            "Searching for swagger JSON. Not found in " + scheme);
                }
                if (istream != null) {
                    return istream;
                }
            }
        }

        return null;
    }
}
