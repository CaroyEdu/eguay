package eguay.entity;

import eguay.entity.Auction;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-11T10:49:11")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile ListAttribute<Category, Auction> auctionList;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile SingularAttribute<Category, Long> categoryid;

}