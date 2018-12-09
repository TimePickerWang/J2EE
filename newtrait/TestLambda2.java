package newtrait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.junit.Test;

/*
 * Java8 ���õ��Ĵ���ĺ���ʽ�ӿ�
 * 
 * Consumer<T> : �����ͽӿ�
 * 		void accept(T t);
 * 
 * Supplier<T> : �����ͽӿ�
 * 		T get(); 
 * 
 * Function<T, R> : �����ͽӿ�
 * 		R apply(T t);
 * 
 * Predicate<T> : �����ͽӿ�
 * 		boolean test(T t);
 * 
 */
public class TestLambda2 {

	@Test
	public void test1() {// �����ͽӿ�
		// ��ʽһ
		Consumer<Double> consumer = m -> System.out.println("ÿ�����" + m + "Ԫ");
		consumer.accept(500D);

		// ��ʽ��
		consumer(1000, m -> System.out.print("ÿ�����" + m + "Ԫ"));
	}

	public void consumer(double money, Consumer<Double> con) {
		con.accept(money);
	}

	/*--------------------------------------------------------*/

	@Test
	public void test2() {// �����ͽӿ�
		// ��ʽһ
		Supplier<List<Integer>> supplier = () -> {
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < 10; i++) {
				list.add((int) (Math.random() * 100));
			}
			return list;
		};
		List<Integer> list = (List<Integer>) supplier.get();
		list.forEach(System.out::println);

		System.out.println("----------------------------");

		// ��ʽ��
		List<Integer> list2 = getList(10, () -> (int) (Math.random() * 100));
		list2.forEach(System.out::println);
	}

	public List<Integer> getList(int num, Supplier<Integer> supplier) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			list.add(supplier.get());
		}
		return list;
	}

	/*--------------------------------------------------------*/

	@Test
	public void test3() { // �����ͽӿ�
		// ��ʽһ
		Function<String, String> fun = str -> str.trim();
		System.out.println(fun.apply("\t\t\t IGţ��   "));

		// ��ʽ��
		System.out.println(strHandle("\t\t\t IGţ��   ", str -> str.trim()));
	}

	public String strHandle(String str, Function<String, String> function) {
		return function.apply(str);
	}

	/*--------------------------------------------------------*/

	@Test
	public void test4() { // �����ͽӿ�
		List<String> strList = new ArrayList<>(Arrays.asList("aaaaa","bbb","cc","ddddd","eeeee"));
		filterString(strList, str -> str.length() > 3);
		strList.forEach(System.out::println);
	}
	
	public void filterString(List<String> strList, Predicate<String> predicate){
		Iterator<String> iter = strList.iterator();
		String str;
		while(iter.hasNext()){
			if(predicate.test(str = iter.next())){
				iter.remove();
			}
		}
	}

}
