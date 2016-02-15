package slidenerd.vivz.d2demo.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import slidenerd.vivz.d2demo.FragmentA;
import slidenerd.vivz.d2demo.FragmentB;
import slidenerd.vivz.d2demo.dagger.modules.StorageModule;

/**
 * Created by Hurman on 15/02/2016.
 *
 * Step 2: Create a component interface called StorageComponent that has all the methods which
 * simply specify where to inject the reference. Dagger 2 will generate the concrete implementation
 * for your interface and fill the necessary code using your module that you created earlier in
 * Step 1. Notice that we plan to use @Inject annotations inside Fragment A and Fragment B and
 * therefore we have 2 methods inside our Component interface that exactly tell Dagger the list
 * of all places where we need a SharedPreferences object. Also notice the modules annotation that
 * tells Dagger to use our module declared in the earlier step to actually construct a
 * SharedPreferences object when needed.
 */
@Singleton
@Component(modules = StorageModule.class)
public interface StorageComponent {

    void inject(FragmentA fragmentA);
    void inject(FragmentB fragmentB);
}
