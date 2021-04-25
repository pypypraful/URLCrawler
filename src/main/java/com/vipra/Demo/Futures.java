package com.vipra.Demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Futures {
    private Futures() {}

    public static <T> T get(final Future<T> future, final long timeout, final TimeUnit unit) {
        try {
            return future.get(timeout, unit);
        } catch (final InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
