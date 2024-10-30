package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbd.lab1.entities.CategoriaEntity;
import tbd.lab1.repositories.CategoriaRepository;

import java.util.ArrayList;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public CategoriaEntity saveCategoria(CategoriaEntity categoria){
        return categoriaRepository.saveCategoria(categoria);
    }

    public ArrayList<CategoriaEntity> getCategorias(){
        return (ArrayList<CategoriaEntity>) categoriaRepository.getCategorias();
    }

    public CategoriaEntity getCategoryById(Long id) {
        return categoriaRepository.findByIdCategoria(id);
    }

    public boolean deleteCategoria(Long id) throws Exception {
        try{
            categoriaRepository.deleteCategoria(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean updateCategoria(CategoriaEntity categoria) {
        // vemos si categoria existe en la base de datos
        if (categoriaRepository.findByIdCategoria(categoria.getIdCategoria()) != null) {
            // actualizamos el categoria usando el método del repositorio
            return categoriaRepository.updateCategoria(categoria);
        }
        return false;
    }
}
