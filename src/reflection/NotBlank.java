package reflection;

import java.lang.annotation.*;

@Documented
@Target({
    ElementType.FIELD
})
// Runtime retention make the code available during runtime
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlank {
    boolean allowEmptyString() default false;
}
