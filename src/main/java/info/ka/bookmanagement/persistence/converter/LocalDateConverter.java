/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.ka.bookmanagement.persistence.converter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author ka
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date>{

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return Objects.nonNull(localDate) ? Date.valueOf(localDate) : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return Objects.nonNull(date) ? date.toLocalDate() : null;
    }
    
}
