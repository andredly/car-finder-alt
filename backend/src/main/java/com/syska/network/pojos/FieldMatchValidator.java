package com.syska.network.pojos;
import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * The Class FieldMatchValidator.
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    /** The first field name. */
    private String firstFieldName;

    /** The second field name. */
    private String secondFieldName;

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
     * Annotation)
     */
    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
     * javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean match = false;
        try {
            Field firstField = value.getClass().getDeclaredField(firstFieldName);
            firstField.setAccessible(true);
            final Object firstObj = firstField.get(value);
            Field secondField = value.getClass().getDeclaredField(secondFieldName);
            secondField.setAccessible(true);
            final Object secondObj = secondField.get(value);

            match = (firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj));
        } catch (final Exception ignore) {
            // ignore
        }
        return match;
    }
}