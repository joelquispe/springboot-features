package com.yoecode.features_springboot.exampleUploadAndGenerateArchives.controllers;

import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.models.DocumentData;
import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateDocument(@RequestBody DocumentData documentData, @RequestParam String format) {
        return documentService.handleCreateDocument(documentData, format);
    }


}

