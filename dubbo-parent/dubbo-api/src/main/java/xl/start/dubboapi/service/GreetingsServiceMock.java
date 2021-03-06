package xl.start.dubboapi.service;

/**
 * 这个类的作用就是：当远程调用dubbo服务失败时，此方法生效。默认的命名时：接口名 + Mock
 */
public class GreetingsServiceMock implements GreetingsService {

  @Override
  public String sayHello(String name) {
    return "远程接口调用失败时，返回的数据";
  }
}
