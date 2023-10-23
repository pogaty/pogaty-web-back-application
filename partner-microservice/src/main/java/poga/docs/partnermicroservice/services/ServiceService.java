package poga.docs.partnermicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.partnermicroservice.repositories.ServiceRepository;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<poga.docs.partnermicroservice.models.Service> findAllService() {
        return this.serviceRepository.findAll();
    }

    public List<poga.docs.partnermicroservice.models.Service> findByNameServiceStartingWith(String prefix){
        return this.serviceRepository.findByNameStartingWith(prefix);
    }
}
