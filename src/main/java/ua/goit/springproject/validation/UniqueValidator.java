package ua.goit.springproject.validation;


import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.UnexpectedTypeException;
import java.lang.reflect.Field;

public class UniqueValidator implements ConstraintValidator<IsUnique, Object> {

    private String table;
    private String field;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(IsUnique constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.table = constraintAnnotation.table();

        if (!StringUtils.hasText(table)) {
            Class<?> model = constraintAnnotation.model();
            Table tableAnnotation = model.getDeclaredAnnotation(Table.class);
            if (tableAnnotation != null) {
                this.table = tableAnnotation.name();
            } else {
                throw new UnexpectedTypeException("Model or table name not declared.");
            }
        }
    }

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
        Object fieldValue = null;

        try {
            Field field = dto.getClass().getDeclaredField(this.field);
            field.setAccessible(true);
            fieldValue = field.get(dto);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (fieldValue != null) {
            Query query = em.createNativeQuery(
                    String.format("select count(*) > 0 from %s where %s = :param", this.table, this.field)
            );
            query.setParameter("param", fieldValue);

            Boolean isPresentInDb = (Boolean) query.getSingleResult();

            if (isPresentInDb) {
                context.buildConstraintViolationWithTemplate("This field not unique.")
                        .addPropertyNode(this.field)
                        .addConstraintViolation();
            }

            return !isPresentInDb;
        }

        return true;
    }
}
