package invoke;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author lingliqi
 * @date 2018-03-26 14:47
 */
public class ConsumerProxy {

    /**
     * 服务消费代理接口
     *
     * @param interfaceClass
     * @param host
     * @param port
     * @param <T>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T consume(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
                (proxy, method, args) -> {
                    try (Socket socket = new Socket(host, port)) {
                        try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
                            output.writeUTF(method.getName());
                            output.writeObject(args);
                            try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                                Object result = input.readObject();
                                if (result instanceof Throwable) {
                                    throw (Throwable) result;
                                }
                                return result;
                            }
                        }
                    }
                });

    }
}