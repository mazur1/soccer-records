/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.exceptions.dao;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author radim
 */
public class DataAccessExceptions extends DataAccessException{
    
    public DataAccessExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessExceptions(String message) {
        super(message);
    }
    
}