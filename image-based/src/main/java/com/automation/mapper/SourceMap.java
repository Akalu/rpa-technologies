package com.automation.mapper;

import java.net.URISyntaxException;

public class SourceMap {
    
    private final String basePath;

    public SourceMap(String resource) throws URISyntaxException {
        this.basePath = this.getClass().getClassLoader().getResource(resource).toURI().getPath();
    }

    public String target(String tgt) {
        return String.format("%s/%s", basePath, tgt);
    }

}
