/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package h4.currency.view;

import h4.currency.controller.CurrencyFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Nizam
 */
@Named(value = "currencyBean")
@ConversationScoped
public class CurrencyBean implements Serializable{

    @EJB
    CurrencyFacade currencyFacade;
    private String homeCurrency;
     private String forignCurrency;
    private float rate;
    @Inject
    private Conversation conversation;
    /**
     * Creates a new instance of CurrencyBean
     */
    
    public CurrencyBean() {
    }

    public String getHomeCurrency() {
        return homeCurrency;
    }

    public void setHomeCurrency(String Currency) {
        this.homeCurrency = Currency;
    }

    public String getForignCurrency() {
        return forignCurrency;
    }

    public void setForignCurrency(String Currency) {
        this.forignCurrency = Currency;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
   
}
