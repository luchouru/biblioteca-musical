package com.mycompany.biblioteca.musical.logica;

import com.mycompany.biblioteca.musical.logica.Disco;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-12-16T10:37:22", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Banda.class)
public class Banda_ { 

    public static volatile ListAttribute<Banda, Disco> discografia;
    public static volatile SingularAttribute<Banda, Integer> id;
    public static volatile SingularAttribute<Banda, String> nombre;
    public static volatile SingularAttribute<Banda, String> pais;

}