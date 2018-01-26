package com.nevermore.youcache.server;

import static io.netty.channel.ChannelOption.SO_BACKLOG;
import static io.netty.channel.ChannelOption.TCP_NODELAY;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nevermore.youcache.net.channel.YocacheChannelInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Damon Sun
 * @date 26/01/2018
 */
public class Yocache {
    private static final Logger logger = LoggerFactory.getLogger(Yocache.class);

    public static void main(String[] args) {
        Options options = new Options()
                .addOption(new Option("p", "port", true, "bind port"));

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            CommandLine commandLine = new DefaultParser().parse(options, args);

            int port = 18080;
            if (commandLine.hasOption("p")) {
                port = toInt(commandLine.getOptionValue("p"));
            }


            ChannelFuture future = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(SO_BACKLOG, 1024)
                    .option(TCP_NODELAY, true)
                    .childHandler(new YocacheChannelInitializer())
                    .bind(port).sync();


            future.channel().closeFuture().sync();
        } catch (ParseException | InterruptedException e) {
            logger.error("", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
