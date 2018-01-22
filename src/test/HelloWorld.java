package test;

public class HelloWorld {
    private String message;

    public void setMessage(String message){
        this.message  = message;
    }
    public void getMessage(){
        System.out.println("Your Message : " + message);
    }
    public void init() {
        System.out.println("I'm getting ready");
    }
    public void destroy() {
        System.out.println("Oh fuck there I go");
    }
}
