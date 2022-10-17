import java.util.Scanner;

class SLLNode {
    String name;
    int price;
    SLLNode succ;

    public SLLNode(String name, int price, SLLNode succ) {
        this.name = name;
        this.price = price;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return name;
    }
}

class SLL {
    SLLNode first;

    public SLL() {
        this.first = null;
    }

    public void insertFirst(String name, int price) {
        SLLNode ins = new SLLNode(name, price, first);
        first = ins;
    }
    
    public void insertAfter(String name, int price, SLLNode node) {
		if (node == null)
			System.out.println("Elementot ne postoi vo listata");
		else {
			SLLNode nov = new SLLNode(name, price, node.succ);
			node.succ = nov;
		}
	}
    
    public void insertBefore(String name, int price, SLLNode before) {
		if(first!=null) {
			SLLNode tmp = first;
			if(before == first) {
				SLLNode nov = new SLLNode(name, price, first);
				first = nov;
				return;
			}
			while(tmp.succ != null && tmp.succ != before) {
				tmp = tmp.succ;
			}
			if(tmp.succ == before) {
				SLLNode nov = new SLLNode(name, price, before);
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

    public void insertLast(String name, int price) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(name, price, null);
            tmp.succ = ins;
        } else {
            insertFirst(name, price);
        }
    }
    
    public SLLNode deleteFirst() {
		if(first == null) {
			System.out.println("Listata e prazna");
			return null;
		}
		else {
			SLLNode tmp = first;
			first = first.succ;
			return tmp;
		}
	}
    
    public SLLNode delete(SLLNode node) {
		if(first != null) {
			SLLNode tmp = first;
			if(node == first) {
				first = first.succ;
				return node;
			}
			while(tmp.succ != null && tmp.succ != node)
				tmp = tmp.succ;
			if(tmp.succ == null) {
				System.out.println("Elementot ne posto vo listata");
				return null;
			}
			else {
				tmp.succ = node.succ; //tmp.succ = tmp.succ.succ;
				return node;
			}
		}
		else {
			System.out.println("Listata e prazna");
			return null;
		}
	}
    
	public String deleteFirst1() {
		if (first != null) {
			SLLNode tmp = first;
			first = first.succ;
			return tmp.name;
		} else {
			System.out.println("Listata e prazna");
			return null;
		}
	}

	public String delete1(SLLNode node) {
		if (first != null) {
			SLLNode tmp = first;
			if(first ==node){
				return this.deleteFirst1();
			}
			while (tmp.succ != node && tmp.succ.succ != null)
				tmp = tmp.succ;
			if (tmp.succ == node) {
				tmp.succ = tmp.succ.succ;
				return node.name;
			} else {
				System.out.println("Elementot ne postoi vo listata");
				return null;
			}
		} else {
			System.out.println("Listata e prazna");
			return null;
		}

	}
    
 
    
    public SLLNode getFirst() {
		return first;
	}
    
    
    public SLLNode findByNamePrice(String name, int price) {
		if (first != null) {
			SLLNode tmp = first;
			while ((!tmp.name.equals(name) || tmp.price!=price) && tmp.succ != null)
				tmp = tmp.succ;
			if (tmp.name.equals(name) && tmp.price == price) {
				return tmp;
			} else {
				System.out.println("Elementot ne postoi vo listata");
			}
		} else {
			System.out.println("Listata e prazna");
		}
		return first;
	}
    
    public SLLNode findByName(String name) {
		if (first != null) {
			SLLNode tmp = first;
			while (!tmp.name.equals(name)  && tmp.succ != null)
				tmp = tmp.succ;
			if (tmp.name.equals(name)) {
				return tmp;
			} else {
				System.out.println("Elementot ne postoi vo listata");
			}
		} else {
			System.out.println("Listata e prazna");
		}
		return first;
	}
    
   
    
    public SLLNode findLast(String name, int price){
		SLLNode find=null;
		if(first!=null) {
			SLLNode tmp=first;
			while(tmp!=null) {
				if(tmp.name.equals(name) && tmp.price == price)
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

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (first != null) {
            SLLNode tmp = first;
            ret.append(tmp).append("\n");
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret.append(tmp).append("\n");
            }
        } else
            ret = new StringBuilder("NO ELEMENTS");
        return ret.toString();
    }
}
public class CakeShop {

    public static void removeCakes(SLL cakes) {
    	SLLNode tmp = cakes.first;
    	float prosek = 0;
    	int suma = 0, brojac = 0;
    	while(tmp != null) {
    		suma += tmp.price;
    		brojac++;
    		tmp = tmp.succ;
    	}
    	
    	prosek = (float) suma/brojac;
    	tmp = cakes.first;
    	while(tmp != null) {
    		if(tmp.price > prosek)
    			cakes.delete(tmp);
    		tmp = tmp.succ;
    	}
    }

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        SLL cakes =  new SLL();

        for(int i=0; i<n; i++){
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            cakes.insertLast(parts[0], Integer.parseInt(parts[1]));
        }

        removeCakes(cakes);
        System.out.println(cakes.toString());
    }
}



