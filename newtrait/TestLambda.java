package newtrait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

/*
 * һ��Lambda ���ʽ�Ļ����﷨��Java8��������һ���µĲ����� "->" �ò�������Ϊ��ͷ�������� Lambda ������
 * 						    ��ͷ�������� Lambda ���ʽ��ֳ������֣�
 * ��ࣺLambda ���ʽ�Ĳ����б�
 * �ҲࣺLambda ���ʽ������ִ�еĹ��ܣ� �� Lambda ��
 * 
 * �﷨��ʽһ���޲������޷���ֵ
 * 		() -> System.out.println("Hello Lambda!");
 * 
 * �﷨��ʽ������һ�������������޷���ֵ
 * 		(x) -> System.out.println(x)
 * 
 * �﷨��ʽ������ֻ��һ��������С���ſ���ʡ�Բ�д
 * 		x -> System.out.println(x)
 * 
 * �﷨��ʽ�ģ����������ϵĲ������з���ֵ������ Lambda �����ж������
 *		Comparator<Integer> com = (x, y) -> {
 *			System.out.println("����ʽ�ӿ�");
 *			return Integer.compare(x, y);
 *		};
 *
 * �﷨��ʽ�壺�� Lambda ����ֻ��һ����䣬 return �� �����Ŷ�����ʡ�Բ�д
 * 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 * 
 * �﷨��ʽ����Lambda ���ʽ�Ĳ����б���������Ϳ���ʡ�Բ�д����ΪJVM������ͨ���������ƶϳ����������ͣ����������ƶϡ�
 * 		(Integer x, Integer y) -> Integer.compare(x, y);
 * 
 * ������������һ����ʡ
 * ����������ƶ�����ʡ
 * ��������ʡ��ʡ
 * 
 * ����Lambda ���ʽ��Ҫ������ʽ�ӿڡ���֧��
 * ����ʽ�ӿڣ��ӿ���ֻ��һ�����󷽷��Ľӿڣ���Ϊ����ʽ�ӿڡ� ����ʹ��ע�� @FunctionalInterface ����
 * 			 ���Լ���Ƿ��Ǻ���ʽ�ӿ�
 */

public class TestLambda {
	List<Employee> emps = Arrays.asList(new Employee(100, "����", 18, 9999.99), 
										new Employee(101, "����", 18, 6666.66),
										new Employee(102, "����", 28, 3333.33), 
										new Employee(103, "����", 8, 7777.77),
										new Employee(104, "����", 38, 5555.55));

	@Test
	public void test1() {
		Runnable runnable = () -> System.out.println("hello");
		runnable.run();
	}

	@Test
	public void test2() {
		Comparator<String> comparator = (x, y) -> Integer.parseInt(y + x) - Integer.parseInt(x + y);
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("456");
		list.sort(comparator);
		list.forEach(System.out::println);
	}

	@Test
	public void compareByAgeAndName() {
		Collections.sort(emps, (e1, e2) -> {// �Ȱ���������������ͬ��нˮ����
			if (e1.getAge() == e2.getAge()) {
				return -Double.compare(e1.getSalary(), e2.getSalary());
			} else {
				return Integer.compare(e1.getAge(), e2.getAge());
			}
		});
		emps.forEach(System.out::println);
	}

	@Test
	public void test3() {
		op(100L, 200L, (l1, l2) -> l1 + l2);

		op(100L, 200L, (l1, l2) -> l1 * l2);
	}

	public void op(Long l1, Long l2, MyFunction<Long> mf) {
		System.out.println(mf.getValut(l1, l2));
	}
}
