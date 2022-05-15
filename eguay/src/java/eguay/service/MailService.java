/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package eguay.service;

import eguay.dao.MailFacade;
import eguay.dto.MailDTO;
import eguay.entity.Mail;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author pedro
 */
@Stateless
public class MailService {
    @EJB MailFacade mailFacade;
    
    public List<MailDTO> getAllMails(Integer userId){
        return Mail.toDTO(mailFacade.findAllMailsToUser(userId));
    }
}
