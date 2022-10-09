<html>
<head>
    <meta charset="utf-8">
    <title>Freemarker入门小DEMO </title>
</head>
<body>
<#--注释内容  -->
${hero.username},你好。



<br>
<#--定义变量-->
<#assign myname="李白">
${myname},你好。



<br>
<#--定义对象-->
<#assign myObj={"name":"对象","age":"12"}>
这是个对象:${myObj.name} 年龄:${myObj.age}



<br>
<#--list指令-->
<#list list as item>
    ${item_index}-----${item}
</#list>



<br>
<#--if指令-->
<#if myname="白">
    <h1>李白</h1>
<#else>
    <h1>杜甫</h1>
</#if>



<br>
<#--引入页面-->
<#include "head.ftl">
</body>



<br>
<#--获取集合大小-->
list集合大小为：${list?size}个元素



<br>
<#--转换JSON字符串为对象-->
<#assign jsonString="{'name':'zs','gender':'man'}"/>
<#assign data=jsonString?eval/>
${data.name}--------${data.gender}



<br>
<#--日期-->
${time?date}



<br>
<#--时间-->
${time?time}



<br>
<#--格式化时间-->
${time?datetime}
<br>
${time?string("yyyy-MM-dd HH:ss:mm")}



<br>
<#--数字直接显示,会出现逗号-->
${price?c}



<br>
<#--判断变量是否存在-->
<#if price??>
    价格存在=${price}
<#else>
    价格不存在
</#if>



<br>
<#--不存在变量则给个默认值-->
${asd!'不存在改变量 已经自动设置默认值'}
</html>
