package cn.thinkingdata.netty.sixthdemo;

import cn.thinkingdata.netty.sixthdemo.MyDataInfo.MyMessage;
import cn.thinkingdata.netty.sixthdemo.MyDataInfo.MyMessage.DataType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {


//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, Person msg) throws Exception {
//        System.out.println(msg.getName());
//        System.out.println(msg.getAge());
//        System.out.println(msg.getAddress());
//      //  ctx.channel().writeAndFlush(msg);
//    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {

        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        if (dataType == DataType.PersonType) {
            MyDataInfo.Person person = msg.getPerson();
            System.out.println(person.getName()+" "+person.getAddress()+" "+person.getAge());

        } else if (dataType == DataType.DogType) {
            MyDataInfo.Dog dog = msg.getDog();
            System.out.println(dog.getName()+" "+dog.getAge());
        } else {
            MyDataInfo.Cat cat = msg.getCat();
            System.out.println(cat.getName()+" "+cat.getCity());
        }

    }
}
