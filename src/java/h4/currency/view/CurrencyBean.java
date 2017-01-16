/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package h4.currency.view;

import h4.currency.controller.CurrencyFacade;
import h4.currency.model.CurrencyDTO;
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
public class CurrencyBean implements Serializable {

    @EJB
    CurrencyFacade currencyFacade;
    private String homeCurrency;
    private String forignCurrency;
    private float rate=0.0f;
   private Float Result=0.0f; 
    private Float money=0.0f;
    
    private CurrencyDTO homeCurrencyRate;
    private CurrencyDTO forignCurrencyRate;
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }
    
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }
   
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
    
    public Float getResult() {
        return Result;
    }

    public void Convert() {
        
         startConversation();
         transactionFailure = null;

        
        homeCurrencyRate = currencyFacade.findCurrency(homeCurrency);
        forignCurrencyRate = currencyFacade.findCurrency(forignCurrency);
        if(homeCurrency.equals("USD"))
        {
           rate = homeCurrencyRate.getRate()*forignCurrencyRate.getRate();
        }
        if(homeCurrency.equals(forignCurrency))
        {
            rate = homeCurrencyRate.getRate()/forignCurrencyRate.getRate();
        }
        
      if(money<0)
        {
                     handleException(new Exception("The money must be a positive float number."));
        }
        
        else
        {
            rate = forignCurrencyRate.getRate()/homeCurrencyRate.getRate();
             Result=rate*money;
        }
          
    }
    
   
    

}
