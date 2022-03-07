package br.com.daniel.ramos.learningdaggercwd.di;

import android.app.Application;

import javax.inject.Singleton;

import br.com.daniel.ramos.learningdaggercwd.BaseApplication;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component //Informa que anotamos como uma classe de component para o dagger. */
        (
                modules = {
                        AndroidSupportInjectionModule.class, //
                        ActivityBuildersModule.class,
                        AppModule.class,

                }
        )
public interface AppComponent extends AndroidInjector<BaseApplication> /* Dizemos para o Dagger que esse o BaseApplication vai ser um cliente do AppComponent que seria um serviço, como EXEMPLO. */ {

    @Component.Builder
    interface Builder {
        @BindsInstance // Usamos para fazer um co.
        Builder application(Application application);

        AppComponent build();
    }
}
