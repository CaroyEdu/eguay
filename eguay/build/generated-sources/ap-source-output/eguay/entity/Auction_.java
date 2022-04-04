package eguay.entity;

import eguay.entity.Bid;
import eguay.entity.Category;
import eguay.entity.Mail;
import eguay.entity.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-04T18:06:36")
@StaticMetamodel(Auction.class)
public class Auction_ { 

    public static volatile ListAttribute<Auction, Users> usersList;
    public static volatile SingularAttribute<Auction, Date> closedate;
    public static volatile SingularAttribute<Auction, String> description;
    public static volatile ListAttribute<Auction, Mail> mailList;
    public static volatile SingularAttribute<Auction, Date> startdate;
    public static volatile SingularAttribute<Auction, String> title;
    public static volatile SingularAttribute<Auction, Float> startprice;
    public static volatile ListAttribute<Auction, Bid> bidList;
    public static volatile SingularAttribute<Auction, Long> auctionid;
    public static volatile SingularAttribute<Auction, Users> sellerid;
    public static volatile SingularAttribute<Auction, Float> maxbid;
    public static volatile SingularAttribute<Auction, String> fotourl;
    public static volatile ListAttribute<Auction, Category> categoryList;
    public static volatile SingularAttribute<Auction, Integer> closenumberofbids;
    public static volatile SingularAttribute<Auction, Float> closeprice;

}