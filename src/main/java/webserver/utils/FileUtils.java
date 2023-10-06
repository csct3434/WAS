package webserver.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileUtils {
    private static final String TEMPLATES_DIRECTORY = "src/main/resources/templates";
    private static final String STATIC_DIRECTORY = "src/main/resources/static";

    private FileUtils() {
    }

    public static byte[] readFileBytes(String path) throws IOException {
        if (FileUtils.isStaticFile(path)) {
            return Files.readAllBytes(Paths.get(STATIC_DIRECTORY + path));
        }
        return Files.readAllBytes(Paths.get(TEMPLATES_DIRECTORY + path));
    }

    public static String checkFilePath(String path) {
        if (isFileExist(path)) {
            return path;
        }
        return "/";
    }

    private static boolean isFileExist(String URI) {
        return isTemplateFile(URI) || isStaticFile(URI);
    }

    private static boolean isTemplateFile(String URI) {
        Path path = Paths.get(TEMPLATES_DIRECTORY + URI);
        return Files.exists(path) && !Files.isDirectory(path);
    }

    private static boolean isStaticFile(String URI) {
        Path path = Paths.get(STATIC_DIRECTORY + URI);
        return Files.exists(path) && !Files.isDirectory(path);
    }
}
