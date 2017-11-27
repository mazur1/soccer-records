/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.exceptions.service;

public class SoccerServiceException extends RuntimeException{

    public SoccerServiceException() {
        super();
    }

    public SoccerServiceException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SoccerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SoccerServiceException(String message) {
        super(message);
    }

    public SoccerServiceException(Throwable cause) {
        super(cause);
    }

}
