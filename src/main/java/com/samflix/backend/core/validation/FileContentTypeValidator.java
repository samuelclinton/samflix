package com.samflix.backend.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;

import java.util.Arrays;
import java.util.List;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, FilePart> {

    private List<String> allowedContentTypes;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allowedContentTypes = Arrays.asList(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(FilePart value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        MediaType mediaType = value.headers().getContentType();
        return mediaType != null && this.allowedContentTypes.contains(mediaType.toString());
    }

}
