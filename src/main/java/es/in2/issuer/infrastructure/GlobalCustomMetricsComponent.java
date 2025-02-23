package es.in2.issuer.infrastructure;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class GlobalCustomMetricsComponent {

    // Counter to track the total number of requests processed by the system
    private final Counter totalRequestCounter;

    // Counter to track the total number of errors encountered across the system
    private final Counter totalErrorCounter;

    // Timer to track the latencies of HTTP requests
    private final Timer requestLatencyTimer;

    public GlobalCustomMetricsComponent(MeterRegistry meterRegistry) {
        String module = "module";
        String global = "global";

        this.totalRequestCounter = Counter.builder("global_total_requests")
                .description("Total number of HTTP requests processed by the system")
                .tags(module, global)
                .register(meterRegistry);

        this.totalErrorCounter = Counter.builder("global_total_errors")
                .description("Total number of errors encountered across the system")
                .tags(module, global)
                .register(meterRegistry);

        this.requestLatencyTimer = Timer.builder("global_request_latency")
                .description("Timer to track HTTP request latencies")
                .tags(module, global)
                .register(meterRegistry);
    }

    // Increment the request counter
    public void incrementRequestCounter() {
        totalRequestCounter.increment();
    }

    // Increment the error counter
    public void incrementErrorCounter() {
        totalErrorCounter.increment();
    }

    // Register the latency of an operation with a return value
    public <T> T recordLatency(Callable<T> callable) throws Exception {
        return requestLatencyTimer.recordCallable(callable);
    }

    // Register the latency of an operation without a return value
    public void recordLatency(Runnable runnable) {
        requestLatencyTimer.record(runnable);
    }

}
