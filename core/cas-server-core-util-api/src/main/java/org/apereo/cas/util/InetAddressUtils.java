package org.apereo.cas.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.URL;

/**
 * This is {@link InetAddressUtils}.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
@Slf4j
public final class InetAddressUtils {


    private InetAddressUtils() {
    }

    /**
     * Gets by name.
     *
     * @param urlAddr the host
     * @return the by name
     */
    public static String getByName(final String urlAddr) {
        try {
            final URL url = new URL(urlAddr);
            return InetAddress.getByName(url.getHost()).getHostAddress();
        } catch (final Exception e) {
            LOGGER.debug("Host name could not be determined automatically.", e);
        }
        return null;
    }

    /**
     * Gets cas server host name.
     *
     * @return the cas server host name
     */
    public static String getCasServerHostName() {
        try {
            final String hostName = InetAddress.getLocalHost().getHostName();
            final int index = hostName.indexOf('.');
            if (index > 0) {
                return hostName.substring(0, index);
            }
            return hostName;
        } catch (final Exception e) {
            throw new IllegalArgumentException("Host name could not be determined automatically.", e);
        }
    }
}
