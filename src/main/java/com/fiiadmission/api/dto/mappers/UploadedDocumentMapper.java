package com.fiiadmission.api.dto.mappers;

import com.fiiadmission.api.dto.UploadedDocumentDTO;
import com.fiiadmission.domain.UploadedDocument;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UploadedDocumentMapper {

    UploadedDocumentMapper INSTANCE = Mappers.getMapper(UploadedDocumentMapper.class);

    UploadedDocumentDTO toUploadedDocumentDTO(UploadedDocument uploadedDocument);

    List<UploadedDocumentDTO> toUploadedDocumentDTOList(List<UploadedDocument> uploadedDocuments);
}
