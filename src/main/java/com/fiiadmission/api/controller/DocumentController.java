package com.fiiadmission.api.controller;

import com.fiiadmission.api.dto.UploadedDocumentDTO;
import com.fiiadmission.api.dto.mappers.UploadedDocumentMapper;
import com.fiiadmission.api.exceptions.BadRequestException;
import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.domain.UploadedDocument;
import com.fiiadmission.domain.User;
import com.fiiadmission.service.AdmissionDataService;
import com.fiiadmission.service.UploadedDocumentService;
import com.fiiadmission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.MultipartConfigElement;

@RestController
public class DocumentController {

    @Value("${documents.max-document-size}")
    private int maxDocumentSize;

    @Value("#{'${documents.allowed-mime-types}'.split(',')}")
    private List<String> allowedMimeTypes;

    @Autowired
    UserService userService;

    @Autowired
    UploadedDocumentService uploadedDocumentService;

    @Autowired
    AdmissionDataService admissionDataService;
    
    @PostMapping
    @RequestMapping("/fii/documents")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UploadedDocumentDTO uploadFile(
            @RequestParam("file") MultipartFile uploadfile, @RequestParam("documentType") String documentType, Principal principal) throws BadRequestException, IOException {

        int fileSizeInMb = (int) (uploadfile.getSize() / (1024 * 1024));

        if (fileSizeInMb > maxDocumentSize) {
            throw new BadRequestException("File size is too big");
        }

        if (!allowedMimeTypes.contains(uploadfile.getContentType())) {
            throw new BadRequestException("Mime type is not allowed");
        }
        User user = userService.findByUsername(principal.getName());
        AdmissionData admissionData = user.getAdmissionData();

        UploadedDocument uploadedDocument = new UploadedDocument();
        uploadedDocument.setAdmissionData(admissionData);
        uploadedDocument.setUploadDate(new Timestamp(new Date().getTime()));
        uploadedDocument.setDocumentType(documentType);
        uploadedDocument.setMimeType(uploadfile.getContentType());
        uploadedDocument.setFilename(uploadfile.getOriginalFilename());
        uploadedDocument.setContent(uploadfile.getBytes());
        uploadedDocumentService.create(uploadedDocument);

        return UploadedDocumentMapper.INSTANCE.toUploadedDocumentDTO(uploadedDocument);
    }

    @GetMapping(value = "/{id}")
    public UploadedDocumentDTO get(@PathVariable Long id){
        return UploadedDocumentMapper.INSTANCE.toUploadedDocumentDTO(uploadedDocumentService.findById(id));
    }

    @GetMapping(value = "/users/{id}")
    public List<UploadedDocumentDTO> getAllDocuments(@PathVariable Long id){
        User user = userService.findById(id);
        List<UploadedDocument> documents = new ArrayList<>();
        if(user.getAdmissionData() != null){
            documents = user.getAdmissionData().getDocuments();
        }
        return UploadedDocumentMapper.INSTANCE.toUploadedDocumentDTOList(documents);
    }
}
