package ua.epam.radchenko.presentation.util.validator;

import ua.epam.radchenko.persistence.entity.Exhibition;
import ua.epam.radchenko.presentation.command.impl.admin.PostCreateExhibitionCommand;
import ua.epam.radchenko.presentation.util.constants.Attributes;
import ua.epam.radchenko.presentation.util.validator.impl.DescriptionValidator;
import ua.epam.radchenko.presentation.util.validator.impl.ExhibitionPriceValidator;
import ua.epam.radchenko.presentation.util.validator.impl.TitleValidator;

import java.io.Serializable;
import java.util.Map;

/**
 * Validation data in {@link PostCreateExhibitionCommand#execute}
 */
public class ExhibitionValidatorManager extends ValidatorManager {

    @Override
    protected void validateObject(Map<String, Boolean> errors, Serializable object) {
        if (!(object instanceof Exhibition)) {
            throw new IllegalArgumentException("The object is not a type of Exhibition");
        }
        Exhibition exhibition = (Exhibition) object;
        validateField(new TitleValidator(),
                exhibition.getExhibitionName(),
                Attributes.ERROR_EXHIBITION_NAME,
                errors);
        validateField(new DescriptionValidator(),
                exhibition.getDescription(),
                Attributes.ERROR_EXHIBITION_DESCRIPTION,
                errors);
        validateField(new ExhibitionPriceValidator(),
                exhibition.getPrice(),
                Attributes.ERROR_EXHIBITION_PRICE,
                errors);
    }
}