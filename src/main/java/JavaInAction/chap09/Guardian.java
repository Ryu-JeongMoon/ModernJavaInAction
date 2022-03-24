package JavaInAction.chap09;

public class Guardian implements Observer {

  @Override
  public void notify(String tweet) {
    if (tweet.contains("queen")) {
      System.out.println("Breaking news about " + tweet);
    }
  }
}
