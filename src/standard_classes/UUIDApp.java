package standard_classes;

import java.util.UUID;

public class UUIDApp {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        String key = uuid.toString();
        System.out.println(key);
    }
}
