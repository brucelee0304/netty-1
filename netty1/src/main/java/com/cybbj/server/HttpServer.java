/**   
 * 类名：HttpServer
 *
 */
package com.cybbj.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * HttpServer: TODO请填写类描述
 * 
 * @version 1.0
 * @author 15989
 * @modified 2016-7-4 v1.0 15989 新建 
 */
public class HttpServer {  
	 
	   private static Log log = LogFactory.getLog(HttpServer.class);  
	     
	   public void start(int port) throws Exception {  
	       EventLoopGroup bossGroup = new NioEventLoopGroup();  
	       EventLoopGroup workerGroup = new NioEventLoopGroup();  
	       try {  
	           ServerBootstrap b = new ServerBootstrap();  
	           b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)  
	                   .childHandler(new ChannelInitializer<SocketChannel>() {  
	                               public void initChannel(SocketChannel ch) throws Exception {  
	                                   ch.pipeline().addLast(new HttpResponseEncoder());  
	                                   ch.pipeline().addLast(new HttpRequestDecoder());  
	                                   ch.pipeline().addLast(new HttpServerInboundHandler());  
	                               }  
	                           }).option(ChannelOption.SO_BACKLOG, 128)   
	                   .childOption(ChannelOption.SO_KEEPALIVE, true);  
	 
	           ChannelFuture f = b.bind(port).sync();  
	 
	           f.channel().closeFuture().sync();  
	       } finally {  
	           workerGroup.shutdownGracefully();  
	           bossGroup.shutdownGracefully();  
	       }  
	   }  
}
