package poga.docs.partnermicroservice;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import poga.docs.partnermicroservice.models.Campaign;
import poga.docs.partnermicroservice.models.CampaignDTO;
import poga.docs.partnermicroservice.models.Collaborator;
import poga.docs.partnermicroservice.models.CollaboratorDTO;
import poga.docs.partnermicroservice.models.Page;
import poga.docs.partnermicroservice.models.PageDTO;
import poga.docs.partnermicroservice.models.Service;
import poga.docs.partnermicroservice.models.ServiceDTO;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCampaignFromDto(CampaignDTO dto, @MappingTarget Campaign client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCollaboratorFromDto(CollaboratorDTO dto , @MappingTarget Collaborator problem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePageFromDto(PageDTO dto , @MappingTarget Page idea);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateServiceFromDto(ServiceDTO dto , @MappingTarget Service participant);
}