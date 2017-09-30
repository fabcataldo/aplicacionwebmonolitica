/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service;

/**
 *
 * @author fabio
 */
import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.service.IGenericService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
import java.util.List;

public interface IUsuarioService extends IGenericService<Usuario, Integer>{
    public List<Usuario> list(String parteDelNombre) throws ServiceException; 
}
