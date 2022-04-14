package eguay.entity;

import eguay.entity.Mail;
import eguay.entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-15T00:50:10")
@StaticMetamodel(Groups.class)
public class Groups_ { 

    public static volatile ListAttribute<Groups, Users> usersList;
    public static volatile SingularAttribute<Groups, Long> groupid;
    public static volatile SingularAttribute<Groups, String> name;
    public static volatile ListAttribute<Groups, Mail> mailList;

}