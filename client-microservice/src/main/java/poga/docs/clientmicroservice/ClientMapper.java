package poga.docs.clientmicroservice;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.models.ClientDTO;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClientFromDto(ClientDTO dto, @MappingTarget Client entity);

    // Add other mapping methods if needed
}

