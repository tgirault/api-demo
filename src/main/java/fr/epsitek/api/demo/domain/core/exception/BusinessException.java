package fr.epsitek.api.demo.domain.core.exception;

public class BusinessException extends Exception {
    private String code;
    private String message;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }
}
