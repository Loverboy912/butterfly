package com.paypal.butterfly.api.exception;

import com.paypal.butterfly.extensions.api.exception.ButterflyException;

/**
 * This type represents an unexpected behavior or result
 * during transformation, which caused its abortion
 *
 * @author facarvalho
 */
public class TransformationException extends ButterflyException {

    public TransformationException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public TransformationException(String exceptionMessage, Throwable throwable) {
        super(exceptionMessage, throwable);
    }

}
