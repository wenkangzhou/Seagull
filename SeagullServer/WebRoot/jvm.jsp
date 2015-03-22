
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	Runtime lRuntime = Runtime.getRuntime();

	out.println("*** BEGIN MEMORY STATISTICS ***<br/>");

	out.println("Free Memory(空闲内存): "
			+ (lRuntime.freeMemory() / 1024 / 1024) + "<br/>");

	out.println("Max   Memory(JVM能从OS系统中获取的最大内存): "
			+ (lRuntime.maxMemory() / 1024 / 1024) + "<br/>");

	out.println("Total Memory(目前JVA从OS系统中获取的内存): "
			+ (lRuntime.totalMemory() / 1024 / 1024) + "<br/>");

	out.println("Available Processors服务器的CPU个数 : "
			+ lRuntime.availableProcessors() + "<br/>");

	out.println("*** END MEMORY STATISTICS ***");
%>