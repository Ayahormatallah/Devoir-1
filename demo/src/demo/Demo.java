/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;
import beans.*;
import static beans.Test.load;
import static beans.Test.save;
/**
 *
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
//insertion des données
save(new Site("SAFI"));
save(new Site("MARRAKECH"));
save(new Site ("EL JADIDA"));
load();
    }
    

}
