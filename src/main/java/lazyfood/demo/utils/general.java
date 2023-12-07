package lazyfood.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class general {
    public static String convertBlobToBase64(Blob bytes) {
        InputStream inputStream = null;
        try {
            inputStream = bytes.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            inputStream.close();
            outputStream.close();
            return base64Image;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
