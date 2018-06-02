package com.fiiadmission.api.controller;

import com.fiiadmission.api.exceptions.BadRequestException;
import com.fiiadmission.domain.User;
import com.fiiadmission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/fii/documents")
public class DocumentController {

    @Value("${documents.max-document-size}")
    private int maxDocumentSize;

    @Value("#{'${documents.allowed-mime-types}'.split(',')}")
    private List<String> allowedMimeTypes;

    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Boolean uploadFile(
            @RequestParam("file") MultipartFile uploadfile, Principal principal) throws BadRequestException {

        int fileSizeInMb = (int) (uploadfile.getSize() / (1024 * 1024));

        if (fileSizeInMb > maxDocumentSize) {
            throw new BadRequestException("File size is too big");
        }

        if (!allowedMimeTypes.contains(uploadfile.getContentType())) {
            throw new BadRequestException("Mime type is not allowed");
        }
        User user = userService.findByUsername(principal.getName());

        return true;
    }
}
