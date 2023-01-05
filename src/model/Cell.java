/*
 * Nom de classe : Cell
 *
 * Description   : Classe regroupant les attributs et fonctions spécifiques à la représentation du plateau de jeu dans la console.
 *
 * Version       : 1.0
 *
 * Date          : 02/01/2023
 *
 * Copyright     : moi
 */
package src.model;

public class Cell {
    private final String leftPart;
    private final String rightPart;
    public String representation;
    public Cell(){
        this.leftPart = "| ";
        this.representation = " ";
        this.rightPart = " ";
    }
    public String getCell(){
        return this.leftPart + this.representation + this.rightPart;
    }

    public String getRepresentation(){
        return this.representation;
    }
    protected void setRepresentation(String pRepresentation){
        this.representation = pRepresentation;
    }
}
