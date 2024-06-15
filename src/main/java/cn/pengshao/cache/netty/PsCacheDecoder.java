package cn.pengshao.cache.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ps cache decoder.
 *
 * @Author: yezp
 * @date 2024/6/15 10:31
 */
@Slf4j
public class PsCacheDecoder extends ByteToMessageDecoder {

    AtomicLong counter = new AtomicLong(0);

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in, List<Object> out) throws Exception {
        // in -> out
        log.debug("decode count:{}", counter.incrementAndGet());
        if (in.readableBytes() <= 0) {
            return;
        }

        int count = in.readableBytes();
        int index = in.readerIndex();
        log.debug("readableBytes:{}, readerIndex:{}", count, index);

        byte[] bytes = new byte[count];
        in.readBytes(bytes);
        String msg = new String(bytes);
        log.debug("msg:{}", msg);
        out.add(msg);
    }
}
