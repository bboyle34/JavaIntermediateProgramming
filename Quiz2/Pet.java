/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz2;

/**
 *
 * @author labpatron
 */
public class Pet{
        //data fields
        public String name;
        public String type;
        public int age;
        public String shots;
        
        //Constructor
        public Pet(String name, String type, int age)
        {
            this.name = name;
            this.type = type;
            this.age = age;
        }
        
        //methods
        public void giveShot(String shot)
        {
            this.shots += (shot + " ");
        }
        public String status()
        {
            String answer = ("Name: " + this.name + "\nSpecies: " + this.type
                                + "\nAge: " + this.age + "\nImmunization: " + 
                                this.shots);
            return answer;
        }
}

