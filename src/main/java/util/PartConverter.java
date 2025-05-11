package util;

import exception.PartConverterException;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PartConverter {
    private Part part;

    public PartConverter(Part part) {
        this.part = part;
    }

    public byte[] partToByte() throws PartConverterException {
        ByteArrayOutputStream buffer = null;
        try {
            InputStream inputStream = part.getInputStream();
               buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[16384];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
        } catch (IOException e) {
            System.out.println("Error! Class: " + PartConverter.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
           throw new PartConverterException("Can't get input stream");
        }
        return buffer.toByteArray();
    }
}
