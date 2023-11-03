package poga.docs.partnermicroservice.controllers;

import java.util.List;
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
import poga.docs.partnermicroservice.models.Service;
import poga.docs.partnermicroservice.models.ServiceDTO;
import poga.docs.partnermicroservice.repositories.ServiceRepository;
import poga.docs.partnermicroservice.services.ServiceService;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceRepository serviceRepository;
    private final ServiceService serviceService;
    private final ServiceMapper serviceMapper;

    @Autowired
    public ServiceController(ServiceRepository serviceRepository, ServiceService serviceService,
            ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceService = serviceService;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping()
    public ResponseEntity<?> getAllPage() {
        return ResponseEntity.ok(serviceRepository.findAll());
    }

    @GetMapping("/{service_id}")
    public ResponseEntity getAllServiceByservice_id(@PathVariable long service_id) {
        Optional<Service> optService = serviceRepository.findById(service_id);

        // check if id exists in db
        if (!optService.isPresent()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service Not Found");

        }
        Service services = optService.get();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getNameStartingWithService(@PathVariable String name) {
        if (name.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service Not Found");
        }

        List<Service> services = serviceService.findByNameServiceStartingWith(name);
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<String> createService(@RequestBody Service service) {
        serviceRepository.save(service);
        return ResponseEntity.ok("Service created");
    }

    @PatchMapping("/{service_id}")
    public ResponseEntity<String> partialUpdateService(@PathVariable Long service_id,
            @RequestBody ServiceDTO serviceDTO) {
        Optional<Service> optPage = serviceRepository.findById(service_id);
        if (!optPage.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found");
        }

        Service services = optPage.get();
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
