package reflection;

import java.lang.reflect.Field;

public class Validator {
    public static void validateNotBlank(Object object) throws IllegalAccessException {
        // get the class
        Class<?> aClass = object.getClass();

        // get all the fields
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            NotBlank notBlank = field.getAnnotation(NotBlank.class);
            // check if the field implements the annotation
            if (notBlank == null) {
                continue;
            }

            if (notBlank.allowEmptyString()) {
                continue;
            }

            field.setAccessible(true);
            String value = (String) field.get(object);

            if (value == null || value == "") {
                throw new RuntimeException(field.getName() + " must not blank");
            }
        }
    }
}
