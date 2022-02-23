package br.com.daniel.ramos.learningdaggercwd.di;

import br.com.daniel.ramos.learningdaggercwd.AuthActivity;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();
}
