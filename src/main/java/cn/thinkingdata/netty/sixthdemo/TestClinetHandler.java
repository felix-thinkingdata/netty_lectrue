package cn.thinkingdata.netty.sixthdemo;

import java.util.Random;

import cn.thinkingdata.netty.sixthdemo.MyDataInfo.MyMessage.DataType;
import cn.thinkingdata.netty.sixthdemo.MyDataInfo.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestClinetHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        if (randomInt == 0) {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder().setName("向三").setAddress("北京").setAge(20).build())
                    .build();

        } else if (randomInt == 1) {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setName("小黄").setAge(20).build())
                    .build();

        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder().setName("小花").setCity("上海").build())
                    .build();


        }

        ctx.channel().writeAndFlush(myMessage);
    }
}
