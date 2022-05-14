/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package eguay.service;

import eguay.dto.MailDTO;
import eguay.entity.Mail;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author pedro
 */
@Stateless
public class MailService {

    public static List<MailDTO> toDTO(List<Mail> mails){
        List<MailDTO> dtos = new ArrayList<MailDTO>(mails.size());
        
        for(Mail mail : mails){
            dtos.add(mail.toDTO());
        }
        
        return dtos;
    }
}
