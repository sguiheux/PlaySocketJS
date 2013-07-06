package fr.soart.engine.business;

import fr.soart.impl.business.simple.SimpleBusiness1;

/**
 * Liste des services.
 */
public enum EnumBusinessService {
    BUSINESS_1(1, SimpleBusiness1.class, "simpleBusiness1", true);
    public int id;
    public Class c;
    public String name;
    public boolean isSynchronous;

    private EnumBusinessService(int i, Class c, String name, boolean isSynchronous) {
        this.id = i;
        this.c = c;
        this.name = name;
        this.isSynchronous = isSynchronous;
    }
}
