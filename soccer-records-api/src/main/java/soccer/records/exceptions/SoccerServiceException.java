/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.exceptions;

/**
 *
 * @author Michaela Bocanova
 */
public class SoccerServiceException extends RuntimeException {
    
    public SoccerServiceException() {
	super();
    }

    public SoccerServiceException(String message) {
	super(message);
    }
    
    public SoccerServiceException(Throwable cause) {
	super(cause);
    }
    
    public SoccerServiceException(String message, Throwable cause) {
	super(message, cause);
    }
    
}
