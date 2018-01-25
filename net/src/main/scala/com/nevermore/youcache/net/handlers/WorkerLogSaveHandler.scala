package com.nevermore.youcache.net.handlers

import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}
import org.slf4j.LoggerFactory

/**
  * @author suncheng@kuaishou.com
  */
class WorkerLogSaveHandler extends ChannelInboundHandlerAdapter {
  private val LOGGER = LoggerFactory.getLogger(classOf[WorkerLogSaveHandler])

  override def channelRead(ctx: ChannelHandlerContext, msg: scala.Any): Unit = {
    LOGGER.info("{}", msg)
    ctx.write("received! \n")
  }

  override def channelReadComplete(ctx: ChannelHandlerContext): Unit = {
    ctx.flush()
  }
}
