package lombok;

@Getter
@Setter
@NoArgsConstructor(force = true) // Generates a default constructor with forced initialization of final fields
@AllArgsConstructor // Generates a constructor with all fields as arguments
@ToString(exclude = {"name"}) // Generates a toString method excluding the 'name' field
@EqualsAndHashCode(exclude = {"name"}) // Generates equals and hashCode methods excluding the 'name' field
@Data // Generates getter, setter, requiredArgsConstructor, toString, and equalsAndHashCode
@Builder // Generates a builder for the class
// Generates a constructor with required fields, determined by @NonNull or final declarations.
// In this class, the 'description' field marked with @NonNull is a required field.
@RequiredArgsConstructor
public class Product {
    @Getter(value = AccessLevel.PUBLIC) // Modify the access level of the getter to public
    @With // Generates a wither method for the 'name' field
    private String name;

    @Setter(value = AccessLevel.PUBLIC) // Modify the access level of the setter to public
    @NonNull // Indicates that this field is non-null and required
    private String description;
}
