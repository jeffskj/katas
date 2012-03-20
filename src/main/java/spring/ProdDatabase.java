package spring;

public class ProdDatabase implements Database {

    @Override
    public String getName() {
        return "prod";
    }
}
