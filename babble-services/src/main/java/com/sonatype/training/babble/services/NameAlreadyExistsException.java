package com.sonatype.training.babble.services;

/**
 * Created by IntelliJ IDEA.
 * User: johnsmart
 * Date: Mar 27, 2009
 * Time: 3:56:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class NameAlreadyExistsException extends Exception {

    public NameAlreadyExistsException() {
        super();
    }

    public NameAlreadyExistsException(String message) {
        super(message);
    }

    public NameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NameAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
