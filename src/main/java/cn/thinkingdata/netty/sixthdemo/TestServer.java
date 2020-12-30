package cn.thinkingdata.netty.sixthdemo;

import java.net.InetSocketAddress;

import cn.thinkingdata.netty.fifthdemoo.WebSocketChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {


    public static void main(String[] args) throws InterruptedException {
        //事件循环组
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(workerGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
