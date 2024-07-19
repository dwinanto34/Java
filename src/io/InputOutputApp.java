package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class InputOutputApp {
    public static void main(String[] args) throws IOException {
        // Can use both file and path
        // But the recommendation is to use path instead of file
        file();
        path();

        // Manipulation
        fileManipulation();
        directoryManipulation();
        closeResource();

        // Read and write file
        readWriteFile();
        inputOutputStream();
        readerWriter();

        // Open option
        openOption();

        // Object stream
        objectStream();

        // Scanner
        scanner();

        // Clean all created files
        cleanAll();
    }

    private static void file() {
        // File
        System.out.println("---FILE---");

        // Relative path
        File relative= new File("./src/io/Test File.txt");
        System.out.println("Exist: " + relative.exists());

        // Absolute path
        File absolute = new File("/Users/dwinanto/Projects/Java/src/io/Test File.txt");
        System.out.println("Exist: " + absolute.exists());
    }

    private static void path() {
        // Path
        System.out.println("---PATH---");

        // Relative path
        Path relativePath = Path.of("./src/io/Test File.txt");
        System.out.println("Exist: " + Files.exists(relativePath));
        System.out.println("Is Absolute: " + relativePath.isAbsolute());

        // Absolute path
        Path absolutePath = Path.of("/Users/dwinanto/Projects/Java/src/io/Test File.txt");
        System.out.println("Exist: " + Files.exists(absolutePath));
        System.out.println("Is Absolute: " + absolutePath.isAbsolute());
    }

    private static void fileManipulation() throws IOException {
        // File manipulation
        System.out.println("---FILE MANIPULATION---");

        Path createFilePath = Path.of("./src/io/New File Test.txt");
        Path copyDestinationFilePath = Path.of("./src/io/New File Test - Copy.txt");
        Path moveDestinationFilePath = Path.of("./src/io/New File Test - Move.txt");

        Files.createFile(createFilePath);

        Files.copy(createFilePath, copyDestinationFilePath);
        Files.move(createFilePath, moveDestinationFilePath);

        Files.delete(copyDestinationFilePath);
        Files.deleteIfExists(moveDestinationFilePath);
    }

    private static void directoryManipulation() throws IOException {
        // Folder manipulation
        System.out.println("---FOLDER MANIPULATION---");

        // Create folder
        Path createFolderPath = Path.of("./src/io/New Folder");
        Files.createDirectory(createFolderPath);
        System.out.println("Is directory: " + Files.isDirectory(createFolderPath));

        // Create file inside the folder
        Path createFilePath = Path.of("./src/io/New Folder/File inside folder.txt");
        Files.createFile(createFilePath);

        // Stream all the files inside particular folder
        Files.newDirectoryStream(createFolderPath).forEach(filename -> {
            System.out.println(filename);
        });
    }

    private static void closeResource() {
        // Close the resource after using
        // To prevent memory leak

        Path createFolderPath = Path.of("./src/io/New Folder/File inside folder.txt");

        // by defining inputStream in try method parameter, the close would be triggered automatically.
        try (InputStream inputStream = Files.newInputStream(createFolderPath)) {
            // do something
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readWriteFile() throws IOException {
        // Read Write file
        // Not recommended for big file since it will load the file into our memory
        // But note that it will overwrite the file and not appending the file

        Path path = Path.of("./src/io/New Folder/File inside folder.txt");

        // Read write using bytes
        byte[] bytes = "Hello world in bytes".getBytes();
        Files.write(path, bytes);

        byte[] readFileInBytes = Files.readAllBytes(path);
        System.out.println(new String(readFileInBytes));

        // Read write using string
        Files.writeString(path, "Hello world in string");

        String value = Files.readString(path);
        System.out.println(value);
    }

    private static void inputOutputStream() {
        Path path = Path.of("./src/io/New Folder/File inside folder.txt");
        Integer endOfFile = -1;

        // Read using input stream
        try (
            InputStream is1 = Files.newInputStream(path);
            InputStream is2 = Files.newInputStream(path)
        ) {
            // Read non-text file, like Image, Video, etc is really encouraged to use InputStream
            StringBuilder sb = new StringBuilder();

            // do not use readAllBytes for big files
            // since it will load the file to memory
            byte[] b = is1.readAllBytes();
            System.out.println("Read all bytes: " + new String(b));

            // read the data, a char by a char at a time
            int data;
            while ((data = is2.read()) != endOfFile) {
                sb.append((char) data);
            }
            System.out.println("Input stream: " + sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Write using output stream
        try(OutputStream os = Files.newOutputStream(path)) {
            for (int i = 0; i < 100; i++) {
                os.write("Hello world\n".getBytes());
                // Flush to save the data into actual file instead of hold it in stream
                os.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readerWriter() {
        Path path = Path.of("./src/io/New Folder/File inside folder.txt");
        Integer endOfFile = -1;
        Integer counter = 0;

        // reader
        try (Reader reader = Files.newBufferedReader(path)) {
            StringBuilder sb = new StringBuilder();
            int character;
            while ((character = reader.read()) != endOfFile) {
                // read character by character
                sb.append((char) character);
                // the counter is 1200
                counter++;
            }

            System.out.println(sb);
            System.out.println(counter);
        } catch (IOException exception) {

        }

        counter = 0;
        try (Reader reader = Files.newBufferedReader(path)) {
            StringBuilder sb = new StringBuilder();
            int length;
            char[] chars = new char[100];
            while ((length = reader.read(chars)) != endOfFile) {
                // read 100 characters for each iteration
                sb.append(chars, 0 , length);
                // the counter is 12
                counter++;
            }

            System.out.println(sb);
            System.out.println(counter);
        } catch (IOException exception) {

        }

        // writer
        try (Writer writer = Files.newBufferedWriter(path)) {
            for (int i = 0; i < 100; i++) {
                writer.write("Hello world using Writer\n");
                writer.flush();
            }
        } catch (IOException exception) {

        }
    }

    private static void openOption() {
        // previous code that we did will replace the old contents with the newest one
        // open option gives us more flexibility whether to append or replace

        Path path = Path.of("./src/io/New Folder/Open option.txt");
        // create new file if the file does not exist
        StandardOpenOption create = StandardOpenOption.CREATE;
        // append the content if the file exists.
        StandardOpenOption append = StandardOpenOption.APPEND;

        // writer
        try (Writer writer = Files.newBufferedWriter(path, create, append)) {
            for (int i = 0; i < 100; i++) {
                writer.write("Hello world using Writer\n");
                writer.flush();
            }
        } catch (IOException exception) {

        }
    }

    private static void objectStream() {
        Path path = Path.of("./src/io/New Folder/Product.txt");

        // Write object to a file
        try (
            OutputStream outputStream = Files.newOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
        ) {
            Product product = new Product();
            product.setName("NAME");
            product.setDescription("DESCRIPTION");

            objectOutputStream.writeObject(product);
            objectOutputStream.flush();
        } catch (IOException exception) {

        }

        // Read file to an object
        try (
            InputStream inputStream = Files.newInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
        ) {
            Product product = (Product) objectInputStream.readObject();

            System.out.println(product.getName());
            System.out.println(product.getDescription());
        } catch (IOException | ClassNotFoundException exception) {

        }
    }

    private static void scanner() {
        Path path = Path.of("./src/io/New Folder/File inside folder.txt");

        try (InputStream inputStream = Files.newInputStream(path)) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (IOException exception) {

        }
    }

    private static void cleanAll() throws IOException {
        Files.delete(Path.of("./src/io/New Folder/File inside folder.txt"));
        Files.delete(Path.of("./src/io/New Folder/Open option.txt"));
        Files.delete(Path.of("./src/io/New Folder/Product.txt"));
        Files.delete(Path.of("./src/io/New Folder"));
    }
}
