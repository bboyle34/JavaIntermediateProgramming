
package ch10;

public class Insurance 
{
    // Data Fields
    private String policy;
    public Owner holder;
    public int expiration;
    
    // Constructors
    public Insurance()
    {
        this.policy = "";
        this.holder = null;
        this.expiration = 0;
    }
    public Insurance(String policy, Owner theOwner, int expiration)
    {
        this.policy = policy;
        this.holder = theOwner;
        this.expiration = expiration;
    }
    
    // Methods
    public void setPolicy(String policy)
    {
        this.policy = policy;
    }
    public String getPolicy()
    {
        return this.policy;
    }
    public void renewPolicy(double renew)
    {
        this.expiration += renew;
    }
    public String displayInformation()
    {
        String answer = "";
        answer += ("\tPolicy: " + this.policy + "\n\tHolder\n\t\t" 
                + holder.displayInformation() + "\n\tExpiration: " 
                + this.expiration);
        return answer;
    }
    
    
}
