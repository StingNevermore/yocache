package com.nevermore.youcache.net.handlers

import com.nevermore.yocache.log.Logging
import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}

/**
  * @author Damon Sun
  */
class YocacheLogHandler extends ChannelInboundHandlerAdapter with Logging {
  this.logIdent = "[worker]"

  override def channelRead(ctx: ChannelHandlerContext, msg: scala.Any): Unit = {
    info(s"received a msg $msg")
    ctx.write("received! \n")
  }

  override def channelReadComplete(ctx: ChannelHandlerContext): Unit = {
    info(s"flushing")
    ctx.flush()
  }
}


