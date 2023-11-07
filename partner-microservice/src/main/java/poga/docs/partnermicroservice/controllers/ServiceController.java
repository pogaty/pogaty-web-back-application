package poga.docs.partnermicroservice.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poga.docs.partnermicroservice.ServiceMapper;
import poga.docs.partnermicroservice.models.Collaborator;
import poga.docs.partnermicroservice.models.Services;
import poga.docs.partnermicroservice.models.ServiceDTO;
import poga.docs.partnermicroservice.repositories.CollaboratorRepository;
import poga.docs.partnermicroservice.repositories.ServiceRepository;
import poga.docs.partnermicroservice.services.CollaboratorService;
import poga.docs.partnermicroservice.services.ServiceService;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceRepository serviceRepository;
    private final CollaboratorService collaboratorService;
    private final CollaboratorRepository collaboratorRepository;
    private final ServiceMapper serviceMapper;

    @Autowired
    public ServiceController(ServiceRepository serviceRepository, ServiceService serviceService,
            ServiceMapper serviceMapper, CollaboratorService collaboratorService, CollaboratorRepository collaboratorRepository) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
        this.collaboratorService = collaboratorService;
        this.collaboratorRepository =collaboratorRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getAllPage() {
        return ResponseEntity.ok(serviceRepository.findAll());
    }

    @GetMapping("/{service_id}")
    public ResponseEntity<?> getAllServiceByservice_id(@PathVariable long service_id) {
        Optional<Services> optService = serviceRepository.findById(service_id);

        // check if id exists in db
        if (!optService.isPresent()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service Not Found");

        }
        Services services = optService.get();
        return ResponseEntity.ok(services);
    }


    @PostMapping("/by/{collab_id}")
    public ResponseEntity<String> createService(@PathVariable Long collab_id, @RequestBody Services service) {
        Optional<Collaborator> collabOpt = collaboratorService.findById(collab_id);

        if (!collabOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("collab not found.");
        }
        Collaborator collab = collabOpt.get();
        service.setCollaborator(collab);
        serviceRepository.save(service);

        collab.getService().add(service);
        collaboratorRepository.save(collab);
        return ResponseEntity.ok("Service created");
    }

    @PatchMapping("/{service_id}")
    public ResponseEntity<String> partialUpdateService(@PathVariable Long service_id,
            @RequestBody ServiceDTO serviceDTO) {
        Optional<Services> optPage = serviceRepository.findById(service_id);
        if (!optPage.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found");
        }

        Services services = optPage.get();
        serviceMapper.updateServiceFromDto(serviceDTO, services);
        serviceRepository.save(services);
        return ResponseEntity.ok("Service updated");
    }

    @DeleteMapping("/{service_id}")
    public ResponseEntity<String> deleteService(@PathVariable Long service_id) {
        if (!serviceRepository.existsById(service_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found");
        }

        serviceRepository.deleteById(service_id);
        return ResponseEntity.ok("Service deleted");
    }
}
