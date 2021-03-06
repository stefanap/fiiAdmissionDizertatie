package com.fiiadmission.repository;

import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.domain.UploadedDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface UploadedDocumentRepository extends CrudRepository<UploadedDocument, Long> {

    List<UploadedDocument> findByAdmissionData(AdmissionData admissionData);
}
