package soccer.records.restapi.hateoas;

/*
import soccer.records.dto.CategoryDTO;
import soccer.records.restapi.controllers.CategoriesRestController;
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Assembles a HATEOS-compliant representation of a category from a CategoryDTO.
 *
 * @author Radim Vidlák
 */

/*
@Component
public class SampleResourceAssembler extends ResourceAssemblerSupport<CategoryDTO, CategoryResource> {

    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(SampleResourceAssembler.class);

    
    public CategoryResourceAssembler(@SuppressWarnings("SpringJavaAutowiringInspection") @Autowired EntityLinks entityLinks) {
        super(CategoriesRestController.class, CategoryResource.class);
        this.entityLinks = entityLinks;
    }

    @Override
    public CategoryResource toResource(CategoryDTO categoryDTO) {
        long id = categoryDTO.getId();
        CategoryResource categoryResource = new CategoryResource(categoryDTO);
        try {
            Link catLink = entityLinks.linkForSingleResource(CategoryDTO.class, id).withSelfRel();
            categoryResource.add(catLink);

            Link productsLink = entityLinks.linkForSingleResource(CategoryDTO.class, id).slash("/products").withRel("products");
            categoryResource.add(productsLink);

        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return categoryResource;
    }   
}

*/
