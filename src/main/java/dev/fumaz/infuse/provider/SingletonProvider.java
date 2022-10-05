package dev.fumaz.infuse.provider;

import dev.fumaz.infuse.context.Context;

/**
 * A {@link SingletonProvider} is a {@link Provider} that provides a singleton instance.
 *
 * @param <T> the type of the class
 */
public class SingletonProvider<T> implements Provider<T> {

    private final Class<T> type;
    private final boolean eager;
    private T instance;

    public SingletonProvider(Class<T> type, boolean eager) {
        this.type = type;
        this.eager = eager;
    }

    @Override
    public T provide(Context<?> context) {
        if (instance == null) {
            instance = context.getInjector().construct(type, context);
            validate();
        }

        return instance;
    }

    public boolean isEager() {
        return eager;
    }

    private void validate() {
        if (instance != null) {
            return;
        }

        throw new IllegalStateException("Singleton cannot be null");
    }

}
