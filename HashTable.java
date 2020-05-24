package lab7;

import java.util.*;

public class HashTable {
	
	static long[] table = new long[10000000];
	static int c = 150;// константа можно установить любую
	
	static int hashFunction(int value) {
		int key = 0;
		
		while(value!=0)
		{
			key += (value%10) * (value%10);
			value /= 10;
		}
		return key;
	}
	
	static void insertData(int value) 
	{
		int adress = hashFunction(value);///изначальный адрес найденного элемента по хэшфункции
		int temp = adress;// переменная для нового вычисления адреса

		int i = 0;/// попытка разрешить коллизию
		
		while(true)
		{
			if(table[temp]==0)
			{
				table[temp]=value;
				break;
			}
			i++;
			temp = adress + c * i;// присваиваем новый адресс если возникла коллизия
		}
	}
	
	static int findData(int value,int n) {
		
		int adress = hashFunction(value);
		int temp = adress;
		int i = 0;
		
		while(true)
		{
			if(table[temp]==value)
			{
				return temp;
			}
			i++;
			temp = adress + c * i;
			if(temp>10000000) {
				return -1;
			}
		}
	} 
	
	public static void main(String[]args) 
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Введите N");
		int n = in.nextInt();
		
		HashSet<Integer> mas = new HashSet<Integer>();
		
		for(int i=0;i<n;)
		{
			int temp = 1 + (int)(Math.random()*10000000);
			if(!mas.contains(temp))
			{
				mas.add(temp);
				i++;
			}
		}
		System.out.println("Массив сформирован!");
		
		Iterator<Integer> iterator = mas.iterator();
		
		long begin1 = System.nanoTime();
		for(int i = 0; i < 100; i++)
			insertData(iterator.next());
		long end1 = System.nanoTime();
	
		
		for(int i = 100; i < n-100; i++) 
			insertData(iterator.next());
		
		long begin2 = System.nanoTime();
		
		for(int i = n-100; i < n; i++)
			insertData(iterator.next());
		
		long end2 = System.nanoTime();
		
		System.out.println("Введите искомое значение");
		int key = in.nextInt();
		
		long search1 = System.nanoTime();
		int find = findData(key,n);
		long search2 = System.nanoTime();
		
		if(find==-1) 
		{
			System.out.println("Искомое значениене найдено!");
		}else {
			System.out.println("Искомое значение "+find);
			System.out.println("проверяем действительно ли подэтим ключом находится искомое значени "+table[find]);
		}
		
		System.out.println("Время потраченное на 100 первых " + (end1-begin1)+" наносекунд");
		System.out.println("Время потраченное на 100 последних "+ (end2-begin2)+" наносекунд");
		System.out.println("Время потраченное на поиск "+ (search2-search1)+" наносекунд");
		in.close();
	}
}