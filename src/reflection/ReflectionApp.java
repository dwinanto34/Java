package reflection;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionApp {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        initiateClassDemo();
        fieldsDemo();
        methodsDemo();
        parametersDemo();
        constructorsDemo();
        superClassDemo();
        modifiersDemo();
        packagesDemo();
    }

    private static void initiateClassDemo() {
        // How to get class of Person
        // 1. Using forName plain string
        // Class<?> personClass = Class.forName("reflection.Person");

        // 2. Using object.class
        Class<Person> personClass = Person.class;

        // get some details about the class
        System.out.println(Arrays.toString(personClass.getInterfaces()));
        System.out.println(Arrays.toString(personClass.getDeclaredConstructors()));
        System.out.println(Arrays.toString(personClass.getDeclaredFields()));
        System.out.println(Arrays.toString(personClass.getDeclaredMethods()));
        // and so on..
    }

    private static void fieldsDemo() throws NoSuchFieldException, IllegalAccessException {
        Class<Person> personClass = Person.class;

        // print all declared fields within person class
        for (Field field : personClass.getDeclaredFields()) {
            System.out.println(field.getName() + " " + field.getType().getName());
        }

        Field firstNameField = personClass.getDeclaredField("firstName");
        // because firstName field is private, so we need to set it to accessible
        firstNameField.setAccessible(true);

        Person newPerson = new Person("John", "Wick");

        // to get the value of the field
        String firstName = (String) firstNameField.get(newPerson);
        System.out.println(firstName);

        // to set value to the field
        firstNameField.set(newPerson, "Cena");
        System.out.println(newPerson.getFirstName());
    }

    private static void methodsDemo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Person> personClass = Person.class;

        // print all declared methods within person class
        for (Method method : personClass.getDeclaredMethods()) {
            System.out.println(method.getName());
            System.out.println(method.getReturnType());
            System.out.println(Arrays.toString(method.getParameterTypes()));
        }

        Person newPerson = new Person("John", "Wick");

        // to get the value using getter method
        Method firstNameGetterMethod = personClass.getDeclaredMethod("getFirstName");
        String firstName = (String) firstNameGetterMethod.invoke(newPerson);
        System.out.println(firstName);

        // to set value to the field using setter method
        Method firstNameSetterMethod = personClass.getDeclaredMethod("setFirstName", String.class);
        firstNameSetterMethod.invoke(newPerson, "new value");
        System.out.println(newPerson.getFirstName());
    }

    private static void parametersDemo() {
        Class<Person> personClass = Person.class;

        for (Method method : personClass.getDeclaredMethods()) {
            for (Parameter parameter : method.getParameters()) {
                System.out.println(parameter.getName());
                System.out.println(parameter.getType());
            }
        }
    }

    private static void constructorsDemo() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;

        for (Constructor constructor : personClass.getConstructors()) {
            System.out.println(constructor.getName());
            System.out.println(Arrays.toString(constructor.getParameterTypes()));
        }

        Constructor<Person> defaultConstructor = personClass.getConstructor();
        Constructor<Person> allArgsConstructor = personClass.getConstructor(String.class, String.class);

        Person person1 = defaultConstructor.newInstance();
        Person person2 = allArgsConstructor.newInstance("John", "Wick");

        System.out.println(person1);
        System.out.println(person2);
    }

    private static void superClassDemo() {
        Class<Person> personClass = Person.class;

        Class<? super Person> superClass = personClass.getSuperclass();
        // the result is object
        System.out.println(superClass);
    }

    private static void modifiersDemo() {
        Class<Person> personClass = Person.class;

        // check if the person class is public
        System.out.println(Modifier.isPublic(personClass.getModifiers()));
        // check if the person class is final
        System.out.println(Modifier.isFinal(personClass.getModifiers()));
        // check if the person class is static
        System.out.println(Modifier.isStatic(personClass.getModifiers()));
    }

    private static void packagesDemo() {
        Class<Person> personClass = Person.class;

        Package aPackage = personClass.getPackage();

        System.out.println(aPackage.getName());
        System.out.println(Arrays.toString(aPackage.getAnnotations()));
    }
}
