package JavaInAction.chap18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Summary {

  public static void main(String[] args) {

  }

  static List<List<Integer>> subsets(List<Integer> list) {
    if (list.isEmpty()) {
      List<List<Integer>> ans = new ArrayList<>();
      ans.add(Collections.emptyList());
      return ans;
    }
    Integer first = list.get(0);
    List<Integer> rest = list.subList(1, list.size());

    List<List<Integer>> subans = subsets(rest);
    List<List<Integer>> subans2 = insertAll(first, subans);
    return concat(subans, subans2);
  }

  public static <T> List<List<T>> insertAll(T first, List<List<T>> lists) {
    List<List<T>> result = new ArrayList<>();
    for (List<T> l : lists) {
      List<T> copyList = new ArrayList<>();
      copyList.add(first);
      copyList.addAll(l);
      result.add(copyList);
    }
    return result;
  }

  static <T> List<List<T>> concat(List<List<T>> a, List<List<T>> b) {
    List<List<T>> r = new ArrayList<>(a);
    r.addAll(b);
    return r;
  }
}
