
package xyz.tanagelee;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * LogProperties
 *
 * @author liyunjun
 * @date 2021/4/15 11:21
 */
@Component
@ConfigurationProperties(prefix = "log")
public class LogProperties {
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}