package cl.slash.minesave.exception;

import java.io.IOError;

/**
 * Created by waltercool on 9/13/16.
 */
public class NotEnoughRemoteSpaceException extends IOError {
    /**
     * Constructs a new instance of IOError with the specified cause. The
     * IOError is created with the detail message of
     * <tt>(cause==null ? null : cause.toString())</tt> (which typically
     * contains the class and detail message of cause).
     *
     * @param cause The cause of this error, or <tt>null</tt> if the cause
     *              is not known
     */
    public NotEnoughRemoteSpaceException(Throwable cause) {
        super(cause);
    }
}
