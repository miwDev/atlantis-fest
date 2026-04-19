package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.InvoiceInputDTO;
import com.msd.atlantis_fest.dto.output.InvoiceOutputDTO;
import com.msd.atlantis_fest.entity.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice toEntity(InvoiceInputDTO inputDTO);
    InvoiceOutputDTO toOutputDTO(Invoice entity);
}
