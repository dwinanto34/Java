package generic;

import java.math.BigDecimal;

public class GenericApp {
    public static void main(String[] args) {
        basic();
        genericMethod();
        invariant();
        covariant();
        contravariant();
        boundedGenericData();

    }

    private static void basic() {
        GenericData<String> stringGenericData = new GenericData<>("This is string");
        String string = stringGenericData.getData();

        GenericData<Long> longGenericData = new GenericData<>(1000L);
        Long number = longGenericData.getData();
    }

    private static void genericMethod() {
        String[] names = {"one", "two", "three"};
        int length = GenericHelper.count(names);
        System.out.println(length);
    }

    private static void invariant() {
        GenericData<Object> objectGenericData = new GenericData<>("This is string");
        GenericData<String> stringGenericData1 = new GenericData<>("This is string");
        GenericData<String> stringGenericData2 = stringGenericData1;

        // Invariant
        // Cannot do this because generics cannot be substituted with
        // a subtype (child) directly. To achieve that, we need covariance.
        // GenericData<Object> invariantGenericData = stringGenericData1;
    }

    private static void covariant() {
        GenericData<Object> objectGenericData = new GenericData<>("This is string");
        GenericData<String> stringGenericData1 = new GenericData<>("This is string");

        // covariant uses ? extends
        // allow all child types that extend Object
        GenericData<? extends Object> covariantGenericData;

        covariantGenericData = objectGenericData;
        processCovariant(covariantGenericData);

        covariantGenericData = stringGenericData1;
        processCovariant(covariantGenericData);
    }

    private static void processCovariant(GenericData<? extends Object> genericData) {
        System.out.println(genericData.getData());
    }

    private static void contravariant() {
        // allow all parent types that has the child
        GenericData<Object> objectGenericData = new GenericData<>("This is string");
        GenericData<String> stringGenericData1 = new GenericData<>("This is string");

        // covariant uses ? super
        // Allows all supertypes of the specified type
        GenericData<? super String> covariantGenericData;

        covariantGenericData = objectGenericData;
        processContravariant(covariantGenericData);

        covariantGenericData = stringGenericData1;
        processContravariant(covariantGenericData);
    }

    private static void processContravariant(GenericData<? super String> genericData) {
        System.out.println(genericData.getData());
    }

    private static void boundedGenericData() {
        // OK
        // Because integer and bigdecimal both extend Number
        BoundedGenericData<Integer> integerData = new BoundedGenericData<>(1);
        BoundedGenericData<BigDecimal> bigDecimalData = new BoundedGenericData<>(new BigDecimal(100));

        // NOT OK
        // Because String does not extend Number
        // BoundedGenericData<String> stringData = new BoundedGenericData<>("String");
    }
}
