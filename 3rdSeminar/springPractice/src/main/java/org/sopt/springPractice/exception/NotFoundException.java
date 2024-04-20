package org.sopt.springPractice.exception;

import org.sopt.springPractice.common.dto.ErrorMessage;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
