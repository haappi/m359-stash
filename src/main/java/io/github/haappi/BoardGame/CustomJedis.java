package io.github.haappi.BoardGame;

import redis.clients.jedis.Jedis;

import static redis.clients.jedis.Protocol.Command.PUBLISH;

public class CustomJedis extends Jedis {
    @Override
    public long publish(final String channel, final String message) {
        checkIsInMultiOrPipeline();
        connection.sendCommand(PUBLISH, channel, "message");
        return connection.getIntegerReply();
    }
}
