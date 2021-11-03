package JavaInAction.chap19;

public class PersistentDataStructure {

    public static void main(String[] args) {

    }

    static class TrainJourney {

        public int price;
        public TrainJourney onward;

        public TrainJourney(int p, TrainJourney t) {
            price = p;
            onward = t;
        }

        // 파괴적 갱신
        static TrainJourney link(TrainJourney a, TrainJourney b) {
            if (a == null) {
                return b;
            }

            TrainJourney t = a;

            while (t.onward != null) {
                t = t.onward;
            }

            t.onward = b;
            return a;
        }

        static TrainJourney append(TrainJourney a, TrainJourney b) {
            return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
        }
    }
}
