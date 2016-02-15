package slidenerd.vivz.d2demo.dagger.modules;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import slidenerd.vivz.d2demo.MyApplication;

/**
 * Created by Hurman on 15/02/2016.
 *
 * Step 1: Construct a module that knows how to create SharedPreferences object which will be used
 * by Dagger. Since we need a context which should be Application Context as SharedPreferences tend
 * to be used everywhere, make a constructor that accepts an object of your custom Application
 * class. Notice the @Singleton annotation which indicates that only a single object of
 * SharedPreferences will be supplied to everyone once its created.
 */
@Module
public class StorageModule {

    private final MyApplication myApplication;

    public StorageModule(MyApplication application){
        this.myApplication = application;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(myApplication);
    }


}
