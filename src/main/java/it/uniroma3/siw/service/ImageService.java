package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.ImageRepository;

@Service
public class ImageService {
	@Autowired
    private ImageRepository imageRepository;

    public void salvaImmagine(Image image){
        this.imageRepository.save(image);
    }

    public Image findImmagineById (Long id){
        return this.imageRepository.findById(id).get();
    }

}
