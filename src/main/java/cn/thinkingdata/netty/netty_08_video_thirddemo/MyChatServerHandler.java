package cn.thinkingdata.netty.netty_08_video_thirddemo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);

        Channel channel = ctx.channel();

        CHANNEL_GROUP.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(channel.remoteAddress() + "发送的消息：" + msg + "\n");
            } else {
                ch.writeAndFlush("【自己】发送的消息：" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        CHANNEL_GROUP.writeAndFlush("[服务器] - " + channel.remoteAddress() + "离开\n");
        System.out.println("CHANNEL_GROUP SIZE : " + CHANNEL_GROUP.size());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        CHANNEL_GROUP.writeAndFlush("[服务器] - " + channel.remoteAddress() + "加入\n");


        CHANNEL_GROUP.add(channel);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线");
    }
}
