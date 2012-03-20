package spring.profiles;

public class DevDatabase implements Database {

    @Override
    public String getName() {
        return "dev";
    }
}
