package com.nowui.cloud.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config")
public class Config {
    
    private String tableSchema;
    
    private String codeGenerateUrl;

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getCodeGenerateUrl() {
        return codeGenerateUrl;
    }

    public void setCodeGenerateUrl(String codeGenerateUrl) {
        this.codeGenerateUrl = codeGenerateUrl;
    }
    
}
