package br.com.daniel.ramos.learningdaggercwd;

import br.com.daniel.ramos.learningdaggercwd.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build(); // Este 'application' é o que definimos na interface de AppComponent
    }

}
