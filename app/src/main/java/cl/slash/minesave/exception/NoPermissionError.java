package cl.slash.minesave.exception;

import java.io.IOError;

/**
 * Created by waltercool on 9/13/16.
 */
public class NoPermissionError extends IOError {

    public NoPermissionError(Throwable cause) {
        super(cause);
    }
}
