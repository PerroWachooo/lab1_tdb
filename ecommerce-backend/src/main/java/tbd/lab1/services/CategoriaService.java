package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbd.lab1.entities.CategoriaEntity;
import tbd.lab1.repositories.CategoriaRepository;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public CategoriaEntity getCategoriaById(int id) {
        return categoriaRepository.findByIdCategoria(id);
    }  
     public List<CategoriaEntity> getAllCategorias() {
        return categoriaRepository.findAll(); 
    }
    public CategoriaEntity createCategoria(CategoriaEntity categoria) {
        return categoriaRepository.save(categoria); // Guarda la categoría en la base de datos
    }
}
