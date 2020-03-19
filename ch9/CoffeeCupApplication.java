
package ch9;

public class CoffeeCupApplication 
{
    public static void main(String[] args) 
    {
        CoffeeCup myRedCup = new CoffeeCup(12, "Red");
        CoffeeCup myPurpleCup = new CoffeeCup();
        
        myRedCup.fill();
        //now myRedCup is completely filled
        myPurpleCup.fill();
        //now myPurpleCup is completely filled
        myRedCup.empty();
        //now myRedCup is completely empty
        myPurpleCup.empty(60);
        //now myPurpleCup is emptied 60%
        System.out.println("My first cup has " + myRedCup.currentAmount + ""
                + "fl. oz. left \nAnd my second has "
                + "" + myPurpleCup.currentAmount + " fl. oz. left!");
        
        CoffeeCup[] cupArray = new CoffeeCup[10];
        for (int i = 0; i < cupArray.length; i++)
        {
            cupArray[i] = new CoffeeCup();
            //this creates 10 coffee cups named cupArray1, cupArray2, ...
        }
    }
    
}
