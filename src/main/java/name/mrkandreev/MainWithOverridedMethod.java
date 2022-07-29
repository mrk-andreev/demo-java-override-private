package name.mrkandreev;

import name.mrkandreev.app.MainService;
import name.mrkandreev.app.StubService;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.dynamic.loading.ClassReloadingStrategy.fromInstalledAgent;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class MainWithOverridedMethod {
    public static void main(String[] args) throws Throwable {
        ByteBuddyAgent.install();

        Class<? extends MainService> cls = new ByteBuddy()
                .redefine(MainService.class)
                .method(named("privateMethod"))
                .intercept(MethodDelegation.to(StubService.class))
                .make()
                .load(StubService.class.getClassLoader(), fromInstalledAgent())
                .getLoaded();
        MainService mainService = cls.newInstance();
        mainService.publicMethod();
    }
}
