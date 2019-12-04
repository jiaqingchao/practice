package com.jqc.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class Client {
    public static void main(String[] args) {
        new Client().clientStart();
        }

    private void clientStart() {
        //线程池
        EventLoopGroup workers = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workers)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("channel initialized!");
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        try {
            System.out.println("start to connect...");
            ChannelFuture f = b.connect("127.0.0.1",8888).sync();

            f.addListener((ChannelFuture channelFuture) -> {
                if(!channelFuture.isSuccess()){
                    System.out.println("not connected");
                }else {
                    System.out.println("connected");
                }
            });
            f.sync();
            f.channel().closeFuture().sync(); //close() -> ChannelFuture,  //ChannelFuture调用close()时执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workers.shutdownGracefully();
        }


    }
}
class ClientHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //channle 第一次连上可用，写一个字符串
        System.out.println("channel is activated.");
        ByteBuf byteBuf = Unpooled.copiedBuffer("HelloNetty".getBytes());
        final ChannelFuture f = ctx.writeAndFlush(byteBuf);
        f.addListener((ChannelFuture channelFuture)-> {
            System.out.println("msg send!");
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            ByteBuf buf = (ByteBuf)msg;
            System.out.println(buf.toString(CharsetUtil.UTF_8));
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }
}