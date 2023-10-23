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
import poga.docs.partnermicroservice.models.Page;
import poga.docs.partnermicroservice.models.PageDTO;
import poga.docs.partnermicroservice.repositories.PageRepository;
import poga.docs.partnermicroservice.services.PageService;

@RestController
@RequestMapping("/pages")
public class PageController {
    private final PageRepository pageRepository;
    private final PageService pageService;
    private final ServiceMapper serviceMapper;

    @Autowired
    public PageController(PageRepository pageRepository, PageService pageService, ServiceMapper serviceMapper) {
        this.pageRepository = pageRepository;
        this.pageService = pageService;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping()
    public ResponseEntity<?> getAllPage() {
        return ResponseEntity.ok(pageRepository.findAll());
    }

    @GetMapping("/{page_id}")
    public ResponseEntity getAllPageByPage_id(@PathVariable long page_id) {
        Optional<Page> optPage = pageRepository.findById(page_id);
        
        // check if id exists in db
        if (!optPage.isPresent()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Page Not Found");
        
        }
        Page pages = optPage.get();
        return ResponseEntity.ok(pages);
    }

     @GetMapping("/search/{service}")
    public ResponseEntity<?> getServiceStartingWithPage(@PathVariable String service) {
        if (service.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Page Not Found");
        }

        List<Page> services = pageService.findByServiceStartingWith(service);
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<String> createPage(@RequestBody Page page) {
        pageRepository.save(page);
        return ResponseEntity.ok("Page created");
    }

    @PatchMapping("/{page_id}")
    public ResponseEntity<String> partialUpdatePage(@PathVariable Long page_id, @RequestBody PageDTO pageDTO) {
        Optional<Page> optPage = pageRepository.findById(page_id);
        if (!optPage.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Page not found");
        }

        Page pages = optPage.get();
        serviceMapper.updatePageFromDto(pageDTO, pages);
        pageRepository.save(pages);
        return ResponseEntity.ok("Page updated");
    }

    @DeleteMapping("/{page_id}")
    public ResponseEntity<String> deletePage(@PathVariable Long page_id) {
        if (!pageRepository.existsById(page_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Page not found");
        }

        pageRepository.deleteById(page_id);
        return ResponseEntity.ok("Page deleted");
    }
}
