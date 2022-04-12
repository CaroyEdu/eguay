package eguay.entity;

import eguay.entity.Mail;
import eguay.entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-13T00:11:57")
@StaticMetamodel(Groups.class)
public class Groups_ { 

    public static volatile ListAttribute<Groups, Users> usersList;
    public static volatile SingularAttribute<Groups, Long> groupid;
    public static volatile SingularAttribute<Groups, String> name;
    public static volatile ListAttribute<Groups, Mail> mailList;

}