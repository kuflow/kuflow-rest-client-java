package com.kuflow.rest.client.model;

import com.azure.core.util.BinaryData;

public class Document {

    private String fileName;

    private String contentType;

    private BinaryData fileContent;

    public String getFileName() {
        return this.fileName;
    }

    public Document setFileName(String fileName) {
        this.fileName = fileName;

        return this;
    }

    public String getContentType() {
        return this.contentType;
    }

    public Document setContentType(String contentType) {
        this.contentType = contentType;

        return this;
    }

    public BinaryData getFileContent() {
        return this.fileContent;
    }

    public Document setFileContent(BinaryData fileContent) {
        this.fileContent = fileContent;

        return this;
    }
}
