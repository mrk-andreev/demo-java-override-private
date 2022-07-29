package name.mrkandreev.app;

public class MainService {
    public void publicMethod() {
        privateMethod();
    }

    private void privateMethod() {
        throw new IllegalStateException();
    }
}
