package io.github.haappi.BoardGame;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.net.URI;

public class CustomPool extends JedisPool {
    @Override
    public CustomJedis getResource() {
        return (CustomJedis) super.getResource();
    }

    public CustomPool(final String url) {
        super(URI.create(url));
    }

    public CustomPool() {
        super(Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT);
    }
}
