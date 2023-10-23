package poga.docs.partnermicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.partnermicroservice.models.Page;
import poga.docs.partnermicroservice.repositories.PageRepository;

@Service
public class PageService {
    private final PageRepository pageRepository;

    @Autowired
    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public List<Page> findAll() {
        return this.pageRepository.findAll();
    }

    public List<Page> findByServiceStartingWith(String prefix){
        return this.pageRepository.findByServiceStartingWith(prefix);
    }
}
