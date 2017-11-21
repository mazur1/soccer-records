/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.services;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

/**
 *
 * @author Michaela Bocanova
 */
public interface BeanMappingService {

    Mapper getMapper();

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);
    
}
