import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


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
				s+= tmp.toString() + " ";
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
				s+=tmp.toString() + " ";
				tmp=tmp.pred;
			}
		}
		else s="Prazna lista!!!";
		return s;
	}
}


public class APSKolokvium2020Temin1 {
	
	public static void SortiraniListi(DLL<Integer> lista1, DLL<Integer> lista2, DLL<Integer> lista3) {
		DLLNode<Integer> p = lista1.getLast();
		DLLNode<Integer> q = lista2.getFirst();
		
		while(p != null && q != null) {
			if(p.element > q.element) {
				lista3.insertLast(p.element);
				p = p.pred;
			} else {
				lista3.insertLast(q.element);
				q = q.succ;
			}
		}
		
		while(p != null) {
			lista3.insertLast(p.element);
			p = p.pred;
		}
		while(q != null) {
			lista3.insertLast(q.element);
			q = q.succ;
		}
		
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Scanner input = new Scanner (System.in); 
			
			int m=input.nextInt(); 
			int n = input.nextInt(); 
			
			
			DLL<Integer> lista1 = new DLL<Integer>(); 
			DLL<Integer> lista2 = new DLL<Integer>(); 
			DLL<Integer> lista3 = new DLL<Integer>(); 
			
			
			for (int i=0; i<m; i++) { 
				int el =input.nextInt(); 
				lista1.insertLast(el); 
			} 
			
			for (int i=0; i<n; i++) { 
				int el = input.nextInt(); 
				lista2.insertLast(el); 
			} 
			
			//vasiot kod tuka...  
			
			SortiraniListi(lista1, lista2, lista3);
			//pecatenje 
			
			System.out.println(lista3.toString()); 
			System.out.println(lista3.toStringR());
			

	}

}
