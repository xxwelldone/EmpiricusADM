package br.com.empiricus.EmpiricusADM.Model.Exceptions;

public class ItemNotFound extends RuntimeException {
    public ItemNotFound(Object id){
        super("Resource Not Found: ID - " + id);
    }
}
