import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class DLLNode<E> {
	protected E element;
	protected DLLNode<E> pred, succ;
	
	public DLLNode(E element, DLLNode<E> pred, DLLNode<E> succ) {
		this.element = element;
		this.pred = pred;
		this.succ = succ;
	}
	
	public String toString() {
		return element.toString();
	}
}

class DLL<E> {
	private DLLNode<E> first, last;
	
	public DLL() {
		first = null;
		last = null;
	}
	
	public DLLNode<E> getFirst() {
		return first;
	}
	
	public DLLNode<E> getLast() {
		return last;
	}
	
	public void deleteList() {
		first = null;
		last = null; //garbage collector
	}
	
	public int length() {
		int brojach = 0;
		if(first != null) {
			DLLNode<E> tmp = first;
			while(tmp != null) {
				brojach++;
				tmp = tmp.succ;
			}
		}
		return brojach;
	}
	
	public void insertFirst(E o) {
		DLLNode<E> nov = new DLLNode<E>(o, null, first);
		
		if(first == null)
			first = last = nov;
		else {
			first.pred = nov;
			first = nov;
		}
	}
	
	public void insertLast(E o) {
		DLLNode<E> nov = new DLLNode<E>(o, last, null);
		
		if(first == null) 
			first = last = nov;
		else {
			last.succ = nov;
			last = nov;
		}
	}
	
	
	public void insertAfrer(E o, DLLNode<E> after) {
		if(after == last) {
			insertLast(o);
			return;
		}
		
		DLLNode<E> nov = new DLLNode<E>(o, after, after.succ);
		after.succ.pred = nov;
		after.succ = nov;
	}
	
	public void insertBefore(E o, DLLNode<E> before) {
		if(before == first) {
			insertFirst(o);
			return;
		}
		
		DLLNode<E> nov = new DLLNode<E>(o, before.pred, before);
		before.pred.succ = nov;
		before.pred = nov;
	}
	
	public E deleteFirst() {
		if(first != null) {
			DLLNode<E> tmp = first;
			first = first.succ;
			if(first == null) 
				last = null;
			else first.pred = null;
			
			return tmp.element;
		}
		return null;
	}
	
	public E deleteLast() {
		if(first != null) {
			DLLNode<E> tmp = last;
			last = last.pred;
			
			if(last == null) 
				first = null;
			else last.succ = null;
			return tmp.element; 
		}
		else return null;
	}
	
	public E delete(DLLNode<E> node) 
	{
		if(node == first) {
			deleteFirst();
			return node.element;
		}
		if(node == last) {
			deleteLast();
			return node.element;
		}
		node.pred.succ=node.succ;
		node.succ.pred=node.pred;
		return node.element;
	}

	public DLLNode<E> find(E o){
		if(first!=null) {
			DLLNode<E> tmp=first;
			while(tmp!=null&&tmp.element!=o)
			{
				tmp=tmp.succ;
			}
			if(tmp==null) {
				System.out.println("Elementot ne postoi vo listata");
				return null;
			}
			else return tmp;
		}
		else {
			System.out.print("Listata e prazna");
			return null;
		}
	}
	
	public DLLNode<E> findLast(E o){
		if(first!=null) {
			DLLNode<E> tmp = last;
			while(tmp!=null&&tmp.element!=o)
			{
				tmp=tmp.pred;
			}
			if(tmp==null) {
				System.out.println("Elementot ne postoi vo listata");
				return null;
			}
			else return tmp;
		}
		else {
			System.out.println("Listata e prazna");
			return null;
		}
	}
	
	public String toString() {
		String s="";
//		String s=new String();
		if(first!=null) {
			DLLNode<E> tmp=first;
			while(tmp!=null) {
				s+= tmp.toString() + "<->";
						tmp=tmp.succ;
			}
		}
		else s="Prazna lista!!!";
		return s;
	}
	
	public String toStringR() {
		String s="";
//		String s=new String();
		if(first!=null) {
			DLLNode<E> tmp=last;
			while (tmp!=null) {
				s+=tmp.toString() + "<->";
				tmp=tmp.pred;
			}
		}
		else s="Prazna lista!!!";
		return s;
	}
}



public class DLLDvojnoPovrzanaLista  {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		int i, n, broj;
		String[] pom;
		
		DLL<Integer> lista1 = new DLL<Integer>();
		DLL<Integer> lista2 = new DLL<Integer>();
		DLL<Integer> lista3 = new DLL<Integer>();
		
		s = br.readLine();
		n = Integer.parseInt(s);
		s = br.readLine();
		pom = s.split(" ");
		for(i = 0; i<n; i++) {
			broj = Integer.parseInt(pom[i]);
			lista1.insertLast(broj);
		}
		
		s = br.readLine();
		n = Integer.parseInt(s);
		s = br.readLine();
		pom = s.split(" ");
		for(i = 0; i<n; i++) {
			broj = Integer.parseInt(pom[i]);
			lista2.insertLast(broj);
		}
		
		s = br.readLine();
		n = Integer.parseInt(s);
		s = br.readLine();
		pom = s.split(" ");
		for(i = 0; i<n; i++) {
			broj = Integer.parseInt(pom[i]);
			lista3.insertLast(broj);
		}
		
		
		System.out.println(lista1.toString());
		System.out.println(lista2);
		System.out.println(lista3);
		
		//Funkcijata tuka
		
		char c;
		
		DLL<Character> lista4 = new DLL<Character>();
		DLL<Character> lista5 = new DLL<Character>();
		DLL<Character> lista6 = new DLL<Character>();
		
		s = br.readLine();
		for(i = 0; i<s.length(); i++) {
			 c = s.charAt(i);
			lista4.insertLast(c);
		}
		
		s = br.readLine();
		for(i = 0; i<s.length(); i++) {
			 c = s.charAt(i);
			lista5.insertLast(c);
		}
		
		s = br.readLine();
		for(i = 0; i<s.length(); i++) {
			 c = s.charAt(i);
			lista6.insertLast(c);
		}
		
		System.out.println(lista4.toString());
		System.out.println(lista5);
		System.out.println(lista6);
		
		//Funkcijata tuka
		
		DLL<String> lista7 = new DLL<String>();
		DLL<String> lista8 = new DLL<String>();
		DLL<String> lista9 = new DLL<String>();
		
		s = br.readLine();
		n = Integer.parseInt(s);
		s = br.readLine();
		pom = s.split(" ");
		for(i = 0; i<n; i++) {
			lista7.insertLast(pom[i]);
		}
		
		s = br.readLine();
		n = Integer.parseInt(s);
		s = br.readLine();
		pom = s.split(" ");
		for(i = 0; i<n; i++) {
			lista8.insertLast(pom[i]);
		}
		
		s = br.readLine();
		n = Integer.parseInt(s);
		s = br.readLine();
		pom = s.split(" ");
		for(i = 0; i<n; i++) {
			lista9.insertLast(pom[i]);
		}
		//Funkcijata tuka
		
		System.out.println(lista7.toString());
		System.out.println(lista8);
		System.out.println(lista9);
		

	}

}
