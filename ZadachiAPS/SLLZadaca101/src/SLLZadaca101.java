import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;

	public SLLNode(E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return element.toString();
	}
}

class SLL<E> {
	private SLLNode<E> first;

	public SLL() {
		// Construct an empty SLL
		this.first = null;
	}

	public void deleteList() {
		first = null;
	}

	public int length() {
		int ret;
		if (first != null) {
			SLLNode<E> tmp = first;
			ret = 1;
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret++;
			}
			return ret;
		} else
			return 0;

	}

	@Override
	public String toString() {
		String ret = new String();
		if (first != null) {
			SLLNode<E> tmp = first;
			ret += tmp + "->";
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret += tmp + "->";
			}
		} else
			ret = "Prazna lista!!!";
		return ret;
	}

	public void insertFirst(E o) {
		SLLNode<E> ins = new SLLNode<E>(o, first);
		first = ins;
	}

	public void insertAfter(E o, SLLNode<E> node) {
		if (node != null) {
			SLLNode<E> ins = new SLLNode<E>(o, node.succ);
			node.succ = ins;
		} else {
			System.out.println("Dadenot jazol e null");
		}
	}

	public void insertBefore(E o, SLLNode<E> before) {

		if (first != null) {
			SLLNode<E> tmp = first;
			if (first == before) {
				this.insertFirst(o);
				return;
			}
			// ako first!=before
			while (tmp.succ != before)
				tmp = tmp.succ;
			if (tmp.succ == before) {
				SLLNode<E> ins = new SLLNode<E>(o, before);
				tmp.succ = ins;
			} else {
				System.out.println("Elementot ne postoi vo listata");
			}
		} else {
			System.out.println("Listata e prazna");
		}
	}

	public void insertLast(E o) {
		if (first != null) {
			SLLNode<E> tmp = first;
			while (tmp.succ != null)
				tmp = tmp.succ;
			SLLNode<E> ins = new SLLNode<E>(o, null);
			tmp.succ = ins;
		} else {
			insertFirst(o);
		}
	}

	public E deleteFirst() {
		if (first != null) {
			SLLNode<E> tmp = first;
			first = first.succ;
			return tmp.element;
		} else {
			System.out.println("Listata e prazna");
			return null;
		}
	}

	public E delete(SLLNode<E> node) {
		if (first != null) {
			SLLNode<E> tmp = first;
			if (first == node) {
				return this.deleteFirst();
			}
			while (tmp.succ != node && tmp.succ.succ != null)
				tmp = tmp.succ;
			if (tmp.succ == node) {
				tmp.succ = tmp.succ.succ;
				return node.element;
			} else {
				System.out.println("Elementot ne postoi vo listata");
				return null;
			}
		} else {
			System.out.println("Listata e prazna");
			return null;
		}

	}

	public SLLNode<E> getFirst() {
		return first;
	}

	public SLLNode<E> find(E o) {
		if (first != null) {
			SLLNode<E> tmp = first;
			while (tmp.element != o && tmp.succ != null)
				tmp = tmp.succ;
			if (tmp.element == o) {
				return tmp;
			} else {
				System.out.println("Elementot ne postoi vo listata");
			}
		} else {
			System.out.println("Listata e prazna");
		}
		return first;
	}

	public Iterator<E> iterator() {
		// Return an iterator that visits all elements of this list, in left-to-right
		// order.
		return new LRIterator<E>();
	}

	// //////////Inner class ////////////

	private class LRIterator<E> implements Iterator<E> {

		private SLLNode<E> place, curr;

		private LRIterator() {
			place = (SLLNode<E>) first;
			curr = null;
		}

		public boolean hasNext() {
			return (place != null);
		}

		public E next() {
			if (place == null)
				throw new NoSuchElementException();
			E nextElem = place.element;
			curr = place;
			place = place.succ;
			return nextElem;
		}

		public void remove() {
			// Not implemented
		}
	}

	public void mirror() {
		if (first != null) {
			// m=nextsucc, p=tmp,q=next
			SLLNode<E> tmp = first;
			SLLNode<E> newsucc = null;
			SLLNode<E> next;

			while (tmp != null) {
				next = tmp.succ;
				tmp.succ = newsucc;
				newsucc = tmp;
				tmp = next;
			}
			first = newsucc;
		}

	}

	public void merge(SLL<E> in) {
		if (first != null) {
			SLLNode<E> tmp = first;
			while (tmp.succ != null)
				tmp = tmp.succ;
			tmp.succ = in.getFirst();
		} else {
			first = in.getFirst();
		}
	}
}

public class SLLZadaca101 {
	
	public static int Zadacha101(SLL<Integer> lista, int x)
	{
		SLLNode<Integer> find = lista.find(x);
		if(find != null) {
			int brojach = 1;
			SLLNode<Integer> tmp = lista.getFirst();
			while(tmp != null && tmp.element != x) {
				brojach++;
				tmp = tmp.succ;
			}
			
			lista.delete(find);
			lista.insertFirst(x);
			return brojach;
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int i,n,broj;
		// float broj;
		//double broj;
		String[] pom;
		
		SLL<Integer> lista1=new SLL<Integer>();
		
		
		s= br.readLine();	//s="10"
		n=Integer.parseInt(s);	//n=10
		s=br.readLine();	//s="1 2 3 4 5 6 7 8 9 10"
		pom=s.split(" ");	//pom[0]="1" pom[1]="2" ... pom[9]="10"
		for(i=0;i<n;i++) {
			broj=Integer.parseInt(pom[i]);
			//broj=Float.parseInt(pom[i]);
			//broj=Double.parseInt(pom[i]);
			lista1.insertLast(broj);
		}
		
		
		s=br.readLine();
		int x=Integer.parseInt(s);
		
		int pozicija=Zadacha101(lista1, x);
		
		System.out.println(lista1);
		System.out.println(pozicija);
		

	}

}