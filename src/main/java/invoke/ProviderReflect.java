package invoke;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lingliqi
 * @date 2018-03-26 15:29
 */
public class ProviderReflect {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 服务发布
     * @param service
     * @param port
     * @throws Exception
     */
    public static void provider(final Object service, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(() -> {
                try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                    // 方法名称
                    String methodName = input.readUTF();
                    // 方法参数
                    Object[] arguments = (Object[]) input.readObject();
                    try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
                        try {
                            Object result = MethodUtils.invokeExactMethod(service, methodName, arguments);
                            output.writeObject(result);
                        } catch (Throwable t) {
                            output.writeObject(t);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
