package com.nevermore.youcache.net.channel

import com.nevermore.youcache.net.handlers.WorkerLogSaveHandler
import io.netty.channel.{Channel, ChannelInitializer}
import io.netty.handler.codec.string.{StringDecoder, StringEncoder}
import io.netty.handler.logging.{LogLevel, LoggingHandler}

/**
  * @author suncheng@kuaishou.com
  */
class YocacheChannelInitializer extends ChannelInitializer[Channel] {
  override def initChannel(ch: Channel): Unit = {
    ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG))
      .addLast(new StringDecoder)
      .addLast(new StringEncoder)
      .addLast(new WorkerLogSaveHandler)
  }
}
