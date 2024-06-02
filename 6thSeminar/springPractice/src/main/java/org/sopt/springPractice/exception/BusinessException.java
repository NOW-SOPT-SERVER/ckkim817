package org.sopt.springPractice.exception;

import lombok.Getter;
import org.sopt.springPractice.common.dto.ErrorMessage;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
