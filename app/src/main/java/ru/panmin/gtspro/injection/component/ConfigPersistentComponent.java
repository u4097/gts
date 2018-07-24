package ru.panmin.gtspro.injection.component;

import dagger.Component;
import ru.panmin.gtspro.injection.ConfigPersistent;
import ru.panmin.gtspro.injection.module.ActivityModule;

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}