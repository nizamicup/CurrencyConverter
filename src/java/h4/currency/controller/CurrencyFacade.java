/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package h4.currency.controller;

import h4.currency.model.*;
import h4.currency.model.Currency;
import h4.currency.model.CurrencyDTO;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nizam
 */
@Stateless
@LocalBean
public class CurrencyFacade{
    
    @PersistenceContext(unitName = "CurrencyConverterPU")
    private EntityManager em;
    
    public CurrencyDTO findCurrency(String currency)
    {
        CurrencyDTO found = em.find(Currency.class, currency);
        
        //if(found==null)
           //throw Exception;
        return found;
    }
}
