package com.powerup.realestate.properties.domain.exceptions;

import static com.powerup.realestate.properties.domain.utils.constants.VisitScheduleDomainConstants.UNAUTHORIZED_SELLER_MESSAGE;

public class UnauthorizedSellerException extends RuntimeException {
    public UnauthorizedSellerException() {
        super(UNAUTHORIZED_SELLER_MESSAGE);
    }
    
    public UnauthorizedSellerException(String message) {
        super(message);
    }

} 