package org.apereo.cas.configuration.model.support.redis

import org.apereo.cas.configuration.support.RequiredProperty
import java.io.Serializable

/**
 * This is [BaseRedisProperties].
 *
 * @author Misagh Moayyed
 * @since 5.2.0
 */
open class BaseRedisProperties : Serializable {

    /**
     * Database index used by the connection factory.
     */
    @RequiredProperty
    var database: Int = 0

    /**
     * Redis server host.
     */
    @RequiredProperty
    var host = "localhost"

    /**
     * Login password of the redis server.
     */
    @RequiredProperty
    var password: String? = null

    /**
     * Redis server port.
     */
    @RequiredProperty
    var port = 6379

    /**
     * Connection timeout in milliseconds.
     */
    var timeout = 2000

    /**
     * Radius connection pool settings.
     */
    var pool: Pool? = null

    /**
     * Redis Sentinel settings.
     */
    var sentinel: Sentinel? = null

    /**
     * Whether or not to activate the pool configuration.
     */
    var isUsePool = true

    /**
     * Whether or not to use SSL for connection factory.
     */
    var isUseSsl: Boolean = false

    /**
     * Pool properties.
     */
    class Pool : Serializable {

        /**
         * Sets the maximum number of objects to examine during each run (if any) of the
         * idle object evictor thread. When positive, the number of tests performed for
         * a run will be the minimum of the configured value and the number of idle
         * instances in the pool. When negative, the number of tests performed will be
         * ceil(getNumIdle()/ abs(getNumTestsPerEvictionRun())) which means that when
         * the value is -n roughly one nth of the idle objects will be tested per run.
         */
        var numTestsPerEvictionRun: Int = 0

        /**
         * Sets the minimum amount of time an object may sit idle in the pool before it
         * is eligible for eviction by the idle object evictor (if any - see
         * setTimeBetweenEvictionRunsMillis(long)), with the extra condition that at
         * least minIdle object instances remain in the pool. This setting is overridden
         * by getMinEvictableIdleTimeMillis() (that is, if
         * getMinEvictableIdleTimeMillis() is positive, then
         * getSoftMinEvictableIdleTimeMillis() is ignored).
         */
        var softMinEvictableIdleTimeMillis: Long = 0

        /**
         * Sets the minimum amount of time an object may sit idle in the pool before it
         * is eligible for eviction by the idle object evictor (if any - see
         * setTimeBetweenEvictionRunsMillis(long)). When non-positive, no objects will
         * be evicted from the pool due to idle time alone.
         */
        var minEvictableIdleTimeMillis: Long = 0

        /**
         * Returns whether the pool has LIFO (last in, first out) behaviour with respect
         * to idle objects - always returning the most recently used object from the
         * pool, or as a FIFO (first in, first out) queue, where the pool always returns
         * the oldest object in the idle object pool.
         */
        var isLifo = true

        /**
         * Returns whether or not the pool serves threads waiting to borrow objects
         * fairly. True means that waiting threads are served as if waiting in a FIFO
         * queue.
         */
        var isFairness: Boolean = false

        /**
         * Returns whether objects created for the pool will be validated before being
         * returned from the borrowObject() method. Validation is performed by the
         * validateObject() method of the factory associated with the pool. If the
         * object fails to validate, then borrowObject() will fail.
         */
        var isTestOnCreate: Boolean = false

        /**
         * Returns whether objects borrowed from the pool will be validated before being
         * returned from the borrowObject() method. Validation is performed by the
         * validateObject() method of the factory associated with the pool. If the
         * object fails to validate, it will be removed from the pool and destroyed, and
         * a new attempt will be made to borrow an object from the pool.
         */
        var isTestOnBorrow: Boolean = false

        /**
         * Returns whether objects borrowed from the pool will be validated when they
         * are returned to the pool via the returnObject() method. Validation is
         * performed by the validateObject() method of the factory associated with the
         * pool. Returning objects that fail validation are destroyed rather then being
         * returned the pool.
         */
        var isTestOnReturn: Boolean = false

        /**
         * Returns whether objects sitting idle in the pool will be validated by the
         * idle object evictor ( if any - see setTimeBetweenEvictionRunsMillis(long)).
         * Validation is performed by the validateObject() method of the factory
         * associated with the pool. If the object fails to validate, it will be removed
         * from the pool and destroyed.
         */
        var isTestWhileIdle: Boolean = false

        /**
         * Max number of "idle" connections in the pool. Use a negative value to
         * indicate an unlimited number of idle connections.
         */
        var maxIdle = 8

        /**
         * Target for the minimum number of idle connections to maintain in the pool.
         * This setting only has an effect if it is positive.
         */
        var minIdle: Int = 0

        /**
         * Max number of connections that can be allocated by the pool at a given time.
         * Use a negative value for no limit.
         */
        var maxActive = 8

        /**
         * Maximum amount of time (in milliseconds) a connection allocation should block
         * before throwing an exception when the pool is exhausted. Use a negative value
         * to block indefinitely.
         */
        var maxWait = -1

        companion object {

            private const val serialVersionUID = 8534823157764550894L
        }
    }

    /**
     * Redis sentinel properties.
     */
    class Sentinel : Serializable {

        /**
         * Name of Redis server.
         */
        var master: String? = null

        /**
         * list of host:port pairs.
         */
        var node: List<String>? = null

        companion object {

            private const val serialVersionUID = 5434823157764550831L
        }
    }

    companion object {
        private const val serialVersionUID = -2600996981339638782L
    }

}
