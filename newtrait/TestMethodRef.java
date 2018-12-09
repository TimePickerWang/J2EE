package newtrait;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.Test;

/*
 * һ���������ã��� Lambda ���еĹ��ܣ��Ѿ��з����ṩ��ʵ�֣�����ʹ�÷�������
 * 			  �����Խ������������Ϊ Lambda ���ʽ������һ�ֱ�����ʽ��
 * 1. ��������� :: ʵ��������
 * 2. ���� :: ��̬������
 * 3. ���� :: ʵ��������
 * 
 * ע��(important)��
 * 	 �ٷ������������õķ����Ĳ����б��뷵��ֵ���ͣ���Ҫ�뺯��ʽ�ӿ��г��󷽷��Ĳ����б�ͷ���ֵ���ͱ���һ�£�
 * 	 ����Lambda�Ĳ����б�ĵ�һ����������ʵ�������ĵ����ߣ��ڶ�������(���޲�)��ʵ�������Ĳ���ʱ����ʽ�� ClassName::MethodName
 * 
 * �������������� :�������Ĳ����б���Ҫ�뺯��ʽ�ӿ��в����б���һ�£�
 * 1. ���� :: new
 * 
 * ������������
 * 	����[] :: new;
 */

public class TestMethodRef {

	// ��������� :: ʵ��������
	@Test
	public void test() {
		Consumer<String> comsumer = System.out::println;
		comsumer.accept("hello world");
	}

	@Test
	public void test2() {
		Employee employee = new Employee(1, "hello", 21, 222.22);
		Supplier<Integer> supplier = employee::getAge;
		System.out.println(supplier.get());
	}

	// ���� :: ��̬������
	@Test
	public void test3() {
		Comparator<Integer> comparator = Integer::compareTo;
	}

	// ���������� ���� :: new
	@Test
	public void test4() {
		Supplier<Employee> supplier = () -> new Employee();

		// ���������÷�ʽ,�����޲ι��췽��
		Supplier<Employee> supplier2 = Employee::new;
		System.out.println(supplier2.get());
	}

	@Test
	public void test5() {
		Function<Integer, Employee> function = (x) -> new Employee(x);

		Function<Integer, Employee> function2 = Employee::new;
		System.out.println(function2.apply(10));
	}

	// ��������
	@Test
	public void test6() {
		Function<Integer, String[]> function = (x) -> new String[x];
		String[] strings = function.apply(10);
		System.out.println(strings.length);

		Function<Integer, String[]> function2 = String[]::new;
		String[] strings2 = function2.apply(20);
		System.out.println(strings2.length);
	}

}
