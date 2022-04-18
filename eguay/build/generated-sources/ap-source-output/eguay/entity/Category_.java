package eguay.entity;

import eguay.entity.Auction;
import eguay.entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-18T13:38:53")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile ListAttribute<Category, Users> usersList;
    public static volatile ListAttribute<Category, Auction> auctionList;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile SingularAttribute<Category, Long> categoryid;

}