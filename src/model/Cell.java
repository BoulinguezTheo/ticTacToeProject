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
    private final String LeftPart;
    private final String RightPart;
    public String representation;
    public Cell(){
        this.LeftPart = "| ";
        this.representation = " ";
        this.RightPart = " ";
    }
    public String getCell(){
        return this.LeftPart + this.representation + this.RightPart;
    }
}
