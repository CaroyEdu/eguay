package eguay.entity;

import eguay.entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-15T00:50:10")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, Long> rolid;
    public static volatile ListAttribute<Rol, Users> usersList;
    public static volatile SingularAttribute<Rol, String> name;
    public static volatile SingularAttribute<Rol, Integer> type;

}