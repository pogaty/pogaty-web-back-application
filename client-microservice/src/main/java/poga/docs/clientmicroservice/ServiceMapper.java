package poga.docs.clientmicroservice;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.models.ClientDTO;
import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.models.IdeaDTO;
import poga.docs.clientmicroservice.models.Participant;
import poga.docs.clientmicroservice.models.ParticipantDTO;
import poga.docs.clientmicroservice.models.Problem;
import poga.docs.clientmicroservice.models.ProblemDTO;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClientFromDto(ClientDTO dto, @MappingTarget Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProblemFromDto(ProblemDTO dto , @MappingTarget Problem problem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateIdeaFromDto(IdeaDTO dto , @MappingTarget Idea idea);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateParticipatFromDto(ParticipantDTO dto , @MappingTarget Participant participant);
}

