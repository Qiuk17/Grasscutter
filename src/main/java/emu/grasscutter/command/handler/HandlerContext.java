package emu.grasscutter.command.handler;

import emu.grasscutter.command.BaseContext;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static emu.grasscutter.utils.Language.translate;
@Getter
public final class HandlerContext extends BaseContext {
    private final  Map<Class<?>, Consumer<Object>> messageConsumers;
    @Nullable
    private final Consumer<Object> resultConsumer;
    @Nullable
    private final Consumer<Throwable> errorConsumer;

    @Builder(toBuilder = true)
    public HandlerContext(
            @Singular Map<String, Object> contents,
            @Singular Map<Class<?>, Consumer<Object>> messageConsumers,
            @Nullable Consumer<Object> resultConsumer,
            @Nullable Consumer<Throwable> errorConsumer
    ) {
        this.contents = new ConcurrentHashMap<>(contents);
        this.messageConsumers = messageConsumers;
        this.resultConsumer = resultConsumer;
        this.errorConsumer = errorConsumer;
    }

    /**
     * <p>Report an error to the callback. The following code will be executed.</p>
     * <p>You do <b>NOT</b> need this before an exception is thrown. It will be called automatically.</p>
     * @param throwable a throwable
     */
    public void onError(Throwable throwable) {
        if (errorConsumer != null) {
            errorConsumer.accept(throwable);
        }
    }

    /**
     * Send a message to messageConsumer.
     */
    public void onMessage(Object message) {
        Consumer<Object> consumer = messageConsumers.get(message.getClass());
        if (consumer != null) {
            consumer.accept(message);
        }
    }

    public void onResult(Object result) {
        if (resultConsumer != null) {
            resultConsumer.accept(result);
        }
    }
}
