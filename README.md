# rpc-framework-demo
 
> 通过java socket实现简单的本地RPC调用服务框架，在本示例中，简单的将RPC框架分为四个部分：
> - Service API: 定义对外服务的接口规范 
> - Consumer Proxy: Service接口的代理类，内部逻辑通过Socket与服务的提供方通信，包括写入调用参数与获取调用返回的结果对象，通过代理使通信及获取返回结果等复杂逻辑对接口调用方透明 
> - Provider Reflect： 服务的提供方，通过接受Consumer Proxy借助Socket写入的参数，定位到具体的服务实现，并通过Java反射技术实现服务的调用，然后将参数写入Socket，返回到Comsumer Proxy
> - Service Impl： 远程服务的具体实现类

### 技术原理

1. java socket通信与BIO
2. jdk动态代理
3. java反射调用
