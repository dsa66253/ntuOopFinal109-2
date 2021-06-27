package backend;
//just Practice Singleton way to write code

public final class Singleton {

    private static Singleton INSTANCE;
    private String info = "Initial info class";
    
    public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	private Singleton() {        
    }
    
    public static Singleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        
        return INSTANCE;
    }
    public static void main(String[] args) {
    	Singleton s1 = Singleton.getInstance();
    	System.out.println(s1);
    	System.out.println(s1.getInfo());
    	s1.setInfo("setinfo by s1");
    	System.out.println(s1.getInfo());
    	Singleton s2 = Singleton.getInstance();
    	System.out.println(s2);
    	System.out.println(s2.getInfo());
    	
    } 
}