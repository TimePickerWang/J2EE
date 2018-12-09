package newtrait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

/*
 * һ��Stream API �Ĳ������裺
 * 
 * 1. ���� Stream
 * 
 * 2. �м����
 * 
 * 3. ��ֹ����(�ն˲���)
 */
public class TestStreamaAPI {
	List<Employee> emps = Arrays.asList(new Employee(102, "����", 59, 6666.66), 
										new Employee(101, "����", 18, 9999.99),
										new Employee(103, "����", 28, 3333.33), 
										new Employee(104, "����", 8, 7777.77),
										new Employee(104, "����", 8, 8888.88), 
										new Employee(104, "����", 8, 8888.88),
										new Employee(105, "����", 28, 5555.55));

	
	// 1. ���� Stream
	
	
	@Test
	public void test1() {
		// 1. Collection �ṩ���������� 1.��������stream() ��2.�������� parallelStream()
		List<String> strList = new ArrayList<String>();
		Stream<String> stream = strList.stream();

		// 2. ͨ�� Arrays �е� stream() ��ȡһ��������
		Employee[] employees = new Employee[10];
		Stream<Employee> stream2 = Arrays.stream(employees);

		// 3. ͨ�� Stream ���о�̬���� of()
		Stream<String> stream3 = Stream.of("aa", "bb", "cc");

		// 4. ����������
		// ����
		Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
		iterate.limit(10).forEach(System.out::println);

		// ����
		Stream<Integer> generate = Stream.generate(() -> (int) (Math.random() * 10));
		generate.limit(10).forEach(System.out::println);
	}


	/*-----------------------------------------------------------------------------*/

	
	// 2. �м����
	
	
	/*
	 * ɸѡ����Ƭ 
	 * filter�������� Lambda �� �������ų�ĳЩԪ�ء� 
	 * limit�����ض�����ʹ��Ԫ�ز��������������� 
	 * skip ���� ����Ԫ�أ�����һ���ӵ���ǰ n ��Ԫ�ص�����������Ԫ�ز��� n �����򷵻�һ���������� limit(n) ����
	 * distinct ���� ɸѡ��ͨ����������Ԫ�ص� hashCode() �� equals() ȥ���ظ�Ԫ��
	 */
	@Test
	public void test2() {
		Stream<Employee> stream = emps.stream().filter((e) -> {
			System.out.println("�����м����");// ���е��м�����������κεĴ���
			return e.getAge() <= 35;
		});
		// ֻ�е�����ֹ����ʱ�����е��м������һ���Ե�ȫ��ִ�У���Ϊ��������ֵ��
		stream.forEach(System.out::println);
	}

	// ��test2�Ļ���������ǰ2��
	@Test
	public void test3() {
		emps.stream().filter((e) -> e.getAge() <= 35)
					 .skip(2)
					 .forEach(System.out::println);
	}
	
	// ��test3�Ļ�����ȥ��
	@Test
	public void test4(){
		emps.stream().filter((e) -> e.getAge() <= 35)
		 .skip(2)
		 .distinct()
		 .forEach(System.out::println);
	}
	
	/*
	 * ӳ��
	 * map�������� Lambda, ��Ԫ��ת����������ʽ����ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿ��Ԫ���ϣ�������ӳ���һ���µ�Ԫ�ء�
	 * flatMap��������һ��������Ϊ�����������е�ÿ��ֵ��������һ������Ȼ������������ӳ�һ����
	 */
	@Test
	public void test5(){
		// ������Ԫ��תΪ��д
		List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "ddd");
		list.stream()
			.map((str) -> str.toUpperCase())
			.forEach(System.out::println);
		
		System.out.println("-------------------------------------------");
		
		// ��ȡ����Ա��������
		emps.stream()
			.map(Employee::getName)
			.forEach(System.out::println);
	}
	
	/*
	 * sorted()������Ȼ����
	 * sorted(Comparator com)������������
	 */
	@Test
	public void test6(){
		// ������Ԫ�ذ���Ȼ˳������
		List<String> list = Arrays.asList("ccc","aaa","ddd","eee","bbb");
		list.stream()
			.sorted()
			.forEach(System.out::println);
		
		System.out.println("-------------------------------------------");
		
		// �Ȱ������ţ�������ͬ��нˮ��
		emps.stream()
			.sorted((e1,e2)->{
				if(e1.getAge() == e2.getAge()){
					return Double.compare(e1.getSalary(), e2.getSalary());
				}else{
					return e1.getAge() - e2.getAge();
				}
			}).forEach(System.out::println);
	}
	
	
	/*-----------------------------------------------------------------------------*/
	
	
	//3. ��ֹ����
	
	
	/*
	 * allMatch��������Ƿ�ƥ������Ԫ��
	 * anyMatch��������Ƿ�����ƥ��һ��Ԫ��
	 * noneMatch��������Ƿ�û��ƥ���Ԫ��
	 * findFirst�������ص�һ��Ԫ��
	 * findAny�������ص�ǰ���е�����Ԫ��
	 * count������������Ԫ�ص��ܸ���
	 * max���������������ֵ
	 * min��������������Сֵ
	 */
	@Test
	public void test7(){
		boolean anyMatch = emps.stream().anyMatch((e) -> e.getAge()==8);
		System.out.println(anyMatch);
		
		// ��нˮ���򣬷��ص�һ��
		Optional<Employee> findFirst = emps.stream()
										   .sorted((e1,e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
										   .findFirst();
		System.out.println(findFirst.get());
		
		// ��ȡ������ߵ���Ϣ
		Optional<Employee> max = emps.stream()
									 .max((e1,e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(max.get());
		
		// ��ȡ�������ٵ�ֵ�Ƕ���
		Optional<Double> min = emps.stream()
								   .map(Employee::getSalary)
								   .min(Double::compare);
		System.out.println(min.get());
	}
	
	
	/*
	 * ��Լ  reduce(T identity, BinaryOperator) / reduce(BinaryOperator) �������Խ�����Ԫ�ط�������������õ�һ��ֵ��
	 */
	@Test
	public void test8(){
		// ����Ԫ��֮��
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Integer reduce = list.stream()
							 .reduce(0,(x,y) -> x+y);
		System.out.println(reduce);
		
		System.out.println("-------------------------------------------");
		
		//��������Ա�������ܺ�
		Optional<Double> reduce2 = emps.stream()
									   .map(Employee::getSalary)
									   .reduce(Double::sum);
		System.out.println(reduce2.get());
	}
	
	
	/*
	 * �ռ� collect��������ת��Ϊ������ʽ������һ�� Collector�ӿڵ�ʵ�֣����ڸ�Stream��Ԫ�������ܵķ���
	 */
	@Test
	public void test9(){
		// �ռ�����Ա��������
		emps.stream()
			.map(Employee::getName)
			.collect(Collectors.toSet())
			.forEach(System.out::println);
		
		System.out.println("-------------------------------------------");
		
		// ���㹤��ƽ��ֵ
		Double avg = emps.stream()
						 .collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println(avg);
		
		// ���㹤���ܺ�
		Double sum = emps.stream()
						 .collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(sum);
		
		System.out.println("-------------------------------------------");
		
		// ����Ա�����ʷ���
		Map<Double, List<Employee>> map = emps.stream()
											.collect(Collectors.groupingBy(Employee::getSalary));
		System.out.println(map);
	}
	
	@Test
	public void test10(){
		DoubleSummaryStatistics collect = emps.stream()
										.collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(collect.getAverage());
		System.out.println(collect.getSum());
		System.out.println(collect.getCount());
		
		String str = emps.stream()
						 .map(Employee::getName)
						 .collect(Collectors.joining(","));
		System.out.println(str);
	}
	
}
