import java.util.Scanner;

class Card {

	private int id;
	private int rank;

	public Card(int id, int rank) {
		this.id = id;
		this.rank = rank;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}

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
			ret += tmp;
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret += " " + tmp;
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
}

public class Tarot {
	
	public static void Mesanje(SLL<Card> lista1, SLL<Card> lista2) {
		SLLNode<Card> prvDelPrva = lista1.getFirst();
		SLLNode<Card> vtorDelPrva = lista2.getFirst();
		
		lista2.insertLast(prvDelPrva.element);
		lista1.delete(prvDelPrva);
		
		lista1.insertLast(vtorDelPrva.element);
		lista2.delete(vtorDelPrva);
		
		
		SLLNode<Card> tmp1 = lista1.getFirst();
		SLLNode<Card> tmp2 = lista2.getFirst();
		
		while(tmp1.succ.succ!=null) {
			tmp1 =tmp1.succ;
		}
			
		
		int n= lista2.length();
		
		for (int i=1; i <= n/2; i++) {
				tmp2 = tmp2.succ;
		}
		
		lista2.insertAfter(tmp1.element, tmp2);
		lista1.delete(tmp1);

	}

	// todo: implement function
	public static void tarotCards(SLL<Card> firstPart, SLL<Card> secondPart) {

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SLL<Card> firstPart = new SLL<Card>();
		SLL<Card> secondPart = new SLL<Card>();

		for (int i = 0; i < 6; i++) {
			String line = scanner.nextLine();
			String[] parts = line.split("\\s+");
			firstPart.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
		}

		for (int i = 0; i < 6; i++) {
			String line = scanner.nextLine();
			String[] parts = line.split("\\s+");
			secondPart.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
		}

		Mesanje(firstPart, secondPart);
		System.out.println(firstPart.toString());
		System.out.println(secondPart.toString());
	}
}