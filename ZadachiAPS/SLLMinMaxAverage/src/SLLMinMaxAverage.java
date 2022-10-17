import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;

	public SLLNode(E element, SLLNode<E> succ) {
		this.element = element;
		this.succ = succ;
	}

	public String toString() {
		return element.toString();
	}
}

class SLL<E> {
	private SLLNode<E> first;

	public SLL() {
		first = null;
	}

	public SLLNode<E> getFirst() {
		return first;
	}

	public void deleteList() {
		first = null;
	}

	public int length() {
		int brojac = 0;
		if (first != null) {
			SLLNode<E> tmp = first;
			while (tmp != null) {
				brojac++;
				tmp = tmp.succ;
			}
		}
		return brojac;
	}

	public void insertFirst(E o) {
		SLLNode<E> nov = new SLLNode<E>(o, first);
		first = nov;
	}

	public void insertAfter(E o, SLLNode<E> node) {
		if (node == null)
			System.out.println("Elementot ne postoi vo listata");
		else {
			SLLNode<E> nov = new SLLNode<E>(o, node.succ);
			node.succ = nov;
		}
	}
	
	public void insertBefore(E o, SLLNode<E> before) {
		if(first!=null) {
			SLLNode<E> tmp = first;
			if(before == first) {
				SLLNode<E> nov = new SLLNode<E>(o, first);
				first = nov;
				return;
			}
			
			while(tmp.succ != null && tmp.succ != before) {
				tmp = tmp.succ;
			}
			if(tmp.succ == before) {
				SLLNode<E> nov = new SLLNode<E>(o, before);
				tmp.succ = nov;
			}
			else {
				System.out.println("Elementot ne postoi vo listata");
			}
		}
		else {
			System.out.println("Listata e prazna");
		}
	}
	
	public void insertLast(E o) {
		if(first == null) {
			SLLNode<E> nov = new SLLNode<E>(o, null);
			first = nov;
		}
		else {
			SLLNode<E> tmp = first;
			while(tmp.succ != null)
				tmp = tmp.succ;
			SLLNode<E> nov = new SLLNode<E>(o, null);
			tmp.succ = nov;
		}
	}
	
	public E deleteFirst() {
		if(first == null) {
			System.out.println("Listata e prazna");
			return null;
		}
		else {
			SLLNode<E> tmp = first;
			first = first.succ;
			return tmp.element;
		}
	}
	
	
	public E delete(SLLNode<E> node) {
		if(first != null) {
			SLLNode<E> tmp = first;
			if(node == first) {
				first = first.succ;
				return node.element;
			}
			while(tmp.succ != null && tmp.succ != node)
				tmp = tmp.succ;
			if(tmp.succ == null) {
				System.out.println("Elementot ne posto vo listata");
				return null;
			}
			else {
				tmp.succ = node.succ; //tmp.succ = tmp.succ.succ;
				return node.element;
			}
		}
		else {
			System.out.println("Listata e prazna");
			return null;
		}
	}
	
	public SLLNode<E> find (E o){
		if(first!=null) {
			SLLNode<E> tmp=first;
			while(tmp!=null&&tmp.element!=o)
			{
//				if(tmp.element==o) {return tmp;}
				tmp=tmp.succ;
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
	
	public SLLNode<E> findLast(E o){
		SLLNode<E> find=null;
		if(first!=null) {
			SLLNode<E> tmp=first;
			while(tmp!=null) {
				if(tmp.element==o)
					find=tmp;
				tmp=tmp.succ;
			}
			if(find==null) {
				System.out.println("Elementot ne postoi vo listata");
				return null;
			}
			else return find;
		}
		else {
			System.out.println("Listata ne postoi");
			return null;
		}
	}
	
	public String toString() {
		String s="";
//		String s=new String();
		if(first!=null) {
			SLLNode<E> tmp=first;
			while(tmp!=null) {
				s+=tmp.toString()+"->";
				tmp=tmp.succ;
			}
		}
		else {
			s="Prazna lista!!!";
		}
		return s;
	}
	
}




public class SLLMinMaxAverage {
	
	public static void MinMaxAverage(SLL<Integer> lista) {
		SLLNode<Integer> tmp = lista.getFirst();
		if(tmp == null) return;
			
		SLLNode<Integer> maxT = lista.getFirst();
		SLLNode<Integer> minT = lista.getFirst();
		
		int suma = 0, brojac = 0;
		int prosek = 0;
		
		while(tmp != null) {
			//MAX
			if(tmp.element > maxT.element) 
				maxT = tmp;
			//MIN
			if(tmp.element < minT.element)
				minT = tmp;
			//Average
			suma += tmp.element;
			brojac++;
			
			tmp = tmp.succ;
		}
		
		//akcija so maxT
		lista.delete(maxT);
		lista.insertFirst(maxT.element);
		
		//akcija so minT
		lista.delete(minT);
		lista.insertLast(minT.element);
		
		//akcija so average
		prosek = suma/brojac;
		tmp = lista.getFirst();
		while(tmp != null) {
			lista.insertAfter(prosek, tmp);
			tmp = tmp.succ.succ;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		int i, n, broj;
		String[] pom;
		
		SLL<Integer> lista1 = new SLL<Integer>();
		
		

		s = br.readLine();
		n = Integer.parseInt(s);
		s = br.readLine();
		pom = s.split(" ");
		for(i = 0; i<n; i++) {
			broj = Integer.parseInt(pom[i]);
			lista1.insertLast(broj);
		}
		
		MinMaxAverage(lista1);
		System.out.println(lista1);
	}

}
