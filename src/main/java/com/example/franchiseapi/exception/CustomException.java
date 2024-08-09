package com.example.franchiseapi.exception;

public class CustomException extends RuntimeException {

    private final static String NOT_FOUND = "not found";

    private final static String ALREADY_EXISTS = "already exists";

    private final static String FRANCHISE = "franchise ";

    private final static String BRAND = "brand ";

    private final static String PRODUCT = "product ";

    public static class FranchiseAlreadyExistsException extends RuntimeException {
        public FranchiseAlreadyExistsException() {
            super(FRANCHISE + ALREADY_EXISTS);
        }
    }

    public static class FranchiseIdNotFoundException extends RuntimeException {
        public FranchiseIdNotFoundException() {
            super(FRANCHISE + NOT_FOUND);
        }
    }

    public static class BranchAlreadyExistsException extends RuntimeException {
        public BranchAlreadyExistsException() {
            super(BRAND + ALREADY_EXISTS);
        }
    }

    public static class BranchIdNotFoundException extends RuntimeException {
        public BranchIdNotFoundException() {
            super(BRAND + NOT_FOUND);
        }
    }

    public static class ProductAlreadyExistsException extends RuntimeException {
        public ProductAlreadyExistsException() {
            super(PRODUCT + ALREADY_EXISTS);
        }
    }

    public static class ProductIdNotFoundException extends RuntimeException {
        public ProductIdNotFoundException() {
            super(PRODUCT + NOT_FOUND);
        }
    }

    public static class BrandBelongToFranchiseException extends RuntimeException {
        public BrandBelongToFranchiseException() {
            super("Branch does not belong to the given franchise");
        }
    }
}