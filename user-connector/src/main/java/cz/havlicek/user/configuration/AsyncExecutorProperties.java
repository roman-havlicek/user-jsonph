package cz.havlicek.user.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties of the asynchronous executor.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
@Component
@ConfigurationProperties(prefix = "connector.async-executor")
public class AsyncExecutorProperties {

    /**
     * See ThreadPoolTaskExecutor.corePoolSize
     */
    private int corePoolSize = 5;
    /**
     * See ThreadPoolTaskExecutor.maxPoolSize
     */
    private int maxPoolSize = 20;
    /**
     * See ThreadPoolTaskExecutor.queueCapacity
     */
    private int queueCapacity = 40;

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(final int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(final int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(final int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
