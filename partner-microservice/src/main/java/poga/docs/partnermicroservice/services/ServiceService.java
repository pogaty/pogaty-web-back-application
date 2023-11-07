package poga.docs.partnermicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.partnermicroservice.models.Services;
import poga.docs.partnermicroservice.repositories.ServiceRepository;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Services> findAllService() {
        return this.serviceRepository.findAll();
    }

    public List<Services> findByNameServiceStartingWith(String prefix) {
        return this.serviceRepository.findByNameStartingWith(prefix);
    }

    public List<Services> findByServiceType(String type) {
        return this.serviceRepository.findByServiceType(type);
    }

    public List<Services> findByServiceTypeAndCategory(String type, String category) {
        return this.serviceRepository.findByServiceTypeAndCategory(type, category);
    }
}
