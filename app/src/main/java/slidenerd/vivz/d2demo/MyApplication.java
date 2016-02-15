package slidenerd.vivz.d2demo;

import android.app.Application;

import slidenerd.vivz.d2demo.dagger.components.DaggerStorageComponent;
import slidenerd.vivz.d2demo.dagger.components.StorageComponent;
import slidenerd.vivz.d2demo.dagger.modules.StorageModule;

/**
 * Step 4: In this step, Dagger has compiled our modules and components and generated an
 * implementation of our component interface. Its name is DaggerStorageComponent in our case.
 * Call its builder() method, supply an object of our StorageModule and finally call its build()
 * method.
 */
public class MyApplication extends Application{

    StorageComponent storageComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        storageComponent = DaggerStorageComponent
                .builder()
                .storageModule(new StorageModule(this))
                .build();
    }

    public StorageComponent getStorageComponent(){return storageComponent;}
}
