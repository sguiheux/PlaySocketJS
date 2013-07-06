package fr.soart.engine.service;

/**
 * Service
 */
public abstract class AbstractService {

    public void preCall(){
      // Nothing
    }

    public abstract void call();

    public void postCall(){
          // Nothing
    }
}
