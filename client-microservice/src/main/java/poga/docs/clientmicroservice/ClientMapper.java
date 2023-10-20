package poga.docs.clientmicroservice;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.models.ClientDTO;
import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.models.IdeaDTO;
import poga.docs.clientmicroservice.models.Problem;
import poga.docs.clientmicroservice.models.ProblemDTO;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClientFromDto(ClientDTO dto, @MappingTarget Client entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProblemFromDto(ProblemDTO dto , @MappingTarget Problem problem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateIdeaFromDto(IdeaDTO dto , @MappingTarget Idea problem);
}

