package com.mycompany.biblioteca.musical.logica;

import com.mycompany.biblioteca.musical.logica.Banda;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-12-16T10:37:22", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Disco.class)
public class Disco_ { 

    public static volatile SingularAttribute<Disco, Integer> calificacion;
    public static volatile SingularAttribute<Disco, String> genero;
    public static volatile SingularAttribute<Disco, Integer> id;
    public static volatile SingularAttribute<Disco, String> nombre;
    public static volatile SingularAttribute<Disco, Banda> autor;

}