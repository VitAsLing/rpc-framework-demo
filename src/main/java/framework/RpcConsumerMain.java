package framework;

import invoke.ConsumerProxy;
import service.HelloService;

/**
 * @author lingliqi
 * @date 2018-03-26 15:49
 */
public class RpcConsumerMain {

    public static void main(String[] args) throws Exception {
        HelloService service = ConsumerProxy.consume(HelloService.class, "127.0.0.1", 8083);
        for (int i = 0; i < 100; i++) {
            String hello = service.sayHello("Hello, word counts => " + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
