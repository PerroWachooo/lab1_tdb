package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbd.lab1.entities.CategoriaEntity;
import tbd.lab1.entities.ClienteEntity;
import tbd.lab1.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public CategoriaEntity saveCategoria(CategoriaEntity categoria){
        return categoriaRepository.saveCategoria(categoria);
    }

    public CategoriaEntity getCategoryById(int id) {
        return categoriaRepository.findByIdCategoria(id);
    }
}
