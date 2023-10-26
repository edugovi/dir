package dev.edugovi.dir.model;


import dev.edugovi.dir.util.Utilities;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PasswordConverter implements AttributeConverter<String,String> {
    @Override
    public String convertToDatabaseColumn(String rawPassword) {
        if (rawPassword == null)
            return null;
        return Utilities.getPasswordEncoded(rawPassword);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
