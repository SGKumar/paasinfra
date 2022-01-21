package bandwidth.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class FileUtil {

    public static File getFile(ClassLoader classLoader, String fileName) throws IOException, URISyntaxException {

        URL fileResource = classLoader.getResource(fileName);
        if (fileResource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }

        return new File(fileResource.toURI());
    }

}