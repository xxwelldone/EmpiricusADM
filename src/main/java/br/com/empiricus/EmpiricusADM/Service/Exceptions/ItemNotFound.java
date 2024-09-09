package br.com.empiricus.EmpiricusADM.Service.Exceptions;

public class ItemNotFound extends RuntimeException {
    public ItemNotFound(Object id){
        super("Resource Not Found: ID - " + id);
    }
}
