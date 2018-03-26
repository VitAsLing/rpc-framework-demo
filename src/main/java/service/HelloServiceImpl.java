package service;

/**
 * @author lingliqi
 * @date 2018-03-26 14:45
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String content) {
        return "hello, " + content;
    }
}
