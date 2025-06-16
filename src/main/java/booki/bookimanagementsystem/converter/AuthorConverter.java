package booki.bookimanagementsystem.converter;

import booki.bookimanagementsystem.entity.AuthorEntity;
import booki.bookimanagementsystem.facade.AuthorFacade;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "authorConverter", managed = true)
public class AuthorConverter implements Converter<AuthorEntity> {

    @EJB
    private AuthorFacade authorFacade;

    @Override
    public AuthorEntity getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return authorFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, AuthorEntity author) {
        if (author == null) {
            return "";
        }
        return String.valueOf(author.getId());
    }

}
