package framework;

import invoke.ProviderReflect;
import service.HelloService;
import service.HelloServiceImpl;

/**
 * @author lingliqi
 * @date 2018-03-26 15:47
 */
public class RpcProviderMain {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service, 8083);
    }
}
