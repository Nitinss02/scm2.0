package com.scm.scm20.Helper;

public class ResourceIsNotFound extends RuntimeException {
    public ResourceIsNotFound(String massage) {
        super(massage);
    }

    public ResourceIsNotFound() {
        super("Resource is not Found");
    }
}
