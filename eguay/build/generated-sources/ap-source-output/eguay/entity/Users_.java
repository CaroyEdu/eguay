package eguay.entity;

import eguay.entity.Auction;
import eguay.entity.Bid;
import eguay.entity.Groups;
import eguay.entity.Mail;
import eguay.entity.Rol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-29T10:10:44")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> country;
    public static volatile SingularAttribute<Users, String> address;
    public static volatile ListAttribute<Users, Auction> auctionList;
    public static volatile SingularAttribute<Users, Date> birthyear;
    public static volatile SingularAttribute<Users, String> city;
    public static volatile SingularAttribute<Users, Integer> sex;
    public static volatile ListAttribute<Users, Mail> mailList;
    public static volatile ListAttribute<Users, Groups> groupsList;
    public static volatile ListAttribute<Users, Rol> rolList;
    public static volatile SingularAttribute<Users, Long> userid;
    public static volatile ListAttribute<Users, Bid> bidList;
    public static volatile SingularAttribute<Users, Date> joineddate;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile ListAttribute<Users, Mail> mailList1;
    public static volatile ListAttribute<Users, Auction> auctionList1;
    public static volatile SingularAttribute<Users, String> surname;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> username;

}