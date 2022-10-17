import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
			ret += tmp + "\n";
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret += tmp + "\n";
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
			if(first==before){
				this.insertFirst(o);
				return;
			}
			//ako first!=before
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
			if(first ==node){
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
	
    public Iterator<E> iterator () {
    // Return an iterator that visits all elements of this list, in left-to-right order.
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
			//Not implemented
		}
	}
	
	public void mirror(){
		if (first != null) {
			//m=nextsucc, p=tmp,q=next
			SLLNode<E> tmp = first;
			SLLNode<E> newsucc = null;
			SLLNode<E> next;
			
			while(tmp != null){
				next = tmp.succ;
				tmp.succ = newsucc;
				newsucc = tmp;
				tmp = next;
			}
			first = newsucc;
		}
		
	}
	
	public void merge (SLL<E> in){
		if (first != null) {
			SLLNode<E> tmp = first;
			while(tmp.succ != null)
				tmp = tmp.succ;
			tmp.succ = in.getFirst();
		}
		else{
			first = in.getFirst();
		}
	}
}
 
 
 class Student {
	 String index;
	 String name;
	 int points;
	 
	 public Student(String index, String name, int points) {
		 this.index = index;
		 this.name = name;
		 this.points = points;
	 }
	 
	 public String toString() {
		 return  name +" "+ points;
	 }
	 
	 public int getPoints() {
			return points;
		}
 }


public class APS20202021PrvKolokviumTermin3Class {
	public static void SortirajStudent(SLL<Student> lista) {
		SLLNode<Student> p, q;
		Student tmp;
		
		for(p = lista.getFirst(); p!= null; p = p.succ) {
			for(q = p.succ; q != null; q = q.succ) {
				if(p.element.points > q.element.points) {
					tmp = p.element;
					p.element = q.element;
					q.element = tmp;
				}
			}
		}
	}
	
	
	public static void removeStudent(SLL<Student> students) {
		SLLNode<Student> tmp = students.getFirst();
		int min = 999;
		SLLNode<Student> minIndex = students.getFirst();
		
		while(tmp != null) {
			if(tmp.element.points < min) {
				minIndex = tmp;
			}
			tmp = tmp.succ;
		}
		
		students.delete(minIndex);
			
			
	}
	
	public static void removeLast(SLL<Student> students) {
		SLLNode<Student> tmp = students.getFirst();
		SLLNode<Student> minIndex = students.getFirst();
		
		while(tmp != null) {
			if(tmp.element.points <= minIndex.element.points) {
				minIndex = tmp;
			}
			tmp = tmp.succ;
			}
		
	}
	
	public static void removeFirst(SLL<Student> students) {//site so min poeni{
		SLLNode<Student> tmp = students.getFirst();
		SLLNode<Student> minIndex = students.getFirst();
		
		while(tmp != null) {
			if(tmp.element.points < minIndex.element.points) {
				minIndex = tmp;
			}
			tmp = tmp.succ;
			}
		
		while(tmp != null) {
			if(minIndex.element.points == tmp.element.points)
				students.delete(tmp);
			tmp = tmp.succ;
			}
		}
	
		
		public static void removeLast1(SLL<Student> students) {
			SLLNode<Student> tmp = students.getFirst();
			SLLNode<Student> minIndex = students.getFirst();
			
			while(tmp != null) {
				if(tmp.element.points < minIndex.element.points) {
					minIndex = tmp;
				}
				tmp = tmp.succ;
				}
			
			tmp = null;
			while(tmp != students.getFirst()) {
				if(tmp.element.points == minIndex.element.points) {
					students.delete(tmp);
					return;
			}
		}//posledniot  
		}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		SLL<Student> students = new SLL<Student>();

		for (int i = 0; i < n; i++) {
			String line = scanner.nextLine();
			String[] parts = line.split("\\s+");
			Student s = new Student(parts[0], parts[1], Integer.parseInt(parts[2]));
			students.insertLast(s);
		}
		SortirajStudent(students);
		removeStudent(students);
		System.out.println(students.toString());
	}

}
