/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service;

import java.util.List;

/**
 *
 * @author fabio
 */
import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.service.IGenericService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public interface ICabeceraticketService extends IGenericService<Cabeceraticket, Integer>{
    public String usuarioQueMasCompro() throws ServiceException;
}
