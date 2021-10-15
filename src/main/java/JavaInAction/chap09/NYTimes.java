package JavaInAction.chap09;

public class NYTimes implements Observer {

    @Override
    public void notify(String tweet) {
        if (tweet.contains("money")) {
            System.out.println("Breaking news in NY Times about " + tweet);
        }
    }
}
