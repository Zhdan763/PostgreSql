package util;

import Strategy.ExportDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.JsonByteConverterException;

import java.io.IOException;

public class JsonByteConverter {
    private ObjectMapper objectMapper;
    private ExportDTO exportDTO;

    public JsonByteConverter() {
        this.objectMapper = new ObjectMapper();
        this.exportDTO = new ExportDTO();
    }

    public ExportDTO byteToExportDto(byte[] bytes) throws JsonByteConverterException {

        try {
            exportDTO = objectMapper.readValue(bytes, ExportDTO.class);
        } catch (IOException e) {
            System.out.println("Error! Class: " + JsonByteConverter.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new JsonByteConverterException("Can't read bytes");
        }
        return exportDTO;
    }

    public byte[] exportDtoToByte(ExportDTO exportDTO) throws JsonByteConverterException {
        byte[] byteArray = new byte[0];
        try {
            byteArray = objectMapper.writeValueAsBytes(exportDTO);
        } catch (JsonProcessingException e) {
            System.out.println("Error! Class: " + JsonByteConverter.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new JsonByteConverterException("Can't write bytes");
        }
        return byteArray;
    }

}
