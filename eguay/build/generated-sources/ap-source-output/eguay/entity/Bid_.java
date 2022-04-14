package eguay.entity;

import eguay.entity.Auction;
import eguay.entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-15T00:50:10")
@StaticMetamodel(Bid.class)
public class Bid_ { 

    public static volatile SingularAttribute<Bid, Auction> auctionid;
    public static volatile SingularAttribute<Bid, Users> biderid;
    public static volatile SingularAttribute<Bid, Integer> bid;
    public static volatile SingularAttribute<Bid, Long> bidid;

}