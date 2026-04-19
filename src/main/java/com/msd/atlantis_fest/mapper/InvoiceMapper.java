package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.InvoiceInputDTO;
import com.msd.atlantis_fest.dto.output.InvoiceOutputDTO;
import com.msd.atlantis_fest.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(source = "paymentId", target = "payment.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "numeroFactura", ignore = true)
    @Mapping(target = "fechaEmision", ignore = true)
    Invoice toEntity(InvoiceInputDTO inputDTO);

    @Mapping(source = "payment.id", target = "paymentId")
    InvoiceOutputDTO toOutputDTO(Invoice entity);
}
