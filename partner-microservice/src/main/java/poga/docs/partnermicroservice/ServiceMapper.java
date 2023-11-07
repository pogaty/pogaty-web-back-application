package poga.docs.partnermicroservice;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import poga.docs.partnermicroservice.models.Collaborator;
import poga.docs.partnermicroservice.models.CollaboratorDTO;

import poga.docs.partnermicroservice.models.Services;
import poga.docs.partnermicroservice.models.ServiceDTO;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCollaboratorFromDto(CollaboratorDTO dto , @MappingTarget Collaborator collaborator);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateServiceFromDto(ServiceDTO dto , @MappingTarget Services service);
}