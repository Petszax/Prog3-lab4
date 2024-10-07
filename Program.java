import java.util.*;
import java.io.*;

public class Program {
    static ArrayList<Beer> sorok=new ArrayList<>();

    protected static void add(String[] cmd){
        if(cmd.length==4){
            sorok.add(new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3])));
        }else{
            System.out.println("Nem  megfelelő számú argumentum a parancshoz.");
        }
    }

    protected static void sorokKiir(ArrayList<Beer> sorok){
        for(Beer b: sorok){
            System.out.println(b.toString());
        }
    }

    protected static void list(String[] cmd){
        if(cmd.length==1){
            sorokKiir(sorok);
        }
        else if(cmd.length==2){
            switch(cmd[1]){
                case "name": {
                    Collections.sort(sorok, new NameComparator());
                    sorokKiir(sorok);
                    break;
                }
                case "style": {
                    Collections.sort(sorok, new StyleComparator());
                    sorokKiir(sorok);
                    break;
                }
                case "strength": {
                    Collections.sort(sorok, new StrengthComparator());
                    sorokKiir(sorok);
                    break;
                }
                default: {
                    System.out.println("Helytelen paraméter a parancshoz.");
                    break;
                }
            }
        }
        else{
            System.out.println("Nem  megfelelő számú argumentum a parancshoz.");
        }
    }

    protected static void save(String[] cmd){
        if(cmd.length!=2){
            System.out.println("Nem  megfelelő számú argumentum a parancshoz.");
        }
        else{
            try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream((cmd[1])));){     
                out.writeObject(sorok);
            }
            catch(IOException e){
                System.out.println("Hiba a fájlbamentés során.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected static void load(String[] cmd){
        if(cmd.length!=2){
            System.out.println("Nem  megfelelő számú argumentum a parancshoz.");
        }
        else{
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(cmd[1]));){
                sorok = (ArrayList<Beer>)in.readObject();
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println("Hiba a fájlból olvasás során.");
            }
        }
    }

    protected static void search(String[] cmd){
        if(cmd.length!=2){
            System.out.println("Nem  megfelelő számú argumentum a parancshoz.");
        }
        else{
            for(Beer b: sorok){
                if(cmd[1].equals(b.getName())){
                    System.out.println(b.toString());
                }
            }
        }
    }

    protected static void find(String[] cmd){
        if(cmd.length!=2){
            System.out.println("Nem  megfelelő számú argumentum a parancshoz.");
        }
        else{
            for(Beer b: sorok){
                if(b.getName().contains(cmd[1])){
                    System.out.println(b.toString());
                }
            }
        }
    }

    protected static void delete(String[] cmd){
        if(cmd.length!=2){
            System.out.println("Nem  megfelelő számú argumentum a parancshoz.");
        }
        else{
            boolean vanIlyen=false;
            ListIterator<Beer> iter = sorok.listIterator();
            while(iter.hasNext()){
                Beer b=iter.next();
                if(b.getName().equals(cmd[1])){
                    iter.remove();
                    vanIlyen=true;
                }
            }
            if(!vanIlyen){
                System.out.println("Nincs ilyen nevű sör.");
            }
        }
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        while(true){
            String sor=sc.nextLine();
            String[] cmd=sor.split(" ");
            if(cmd[0].equals("exit")){
                break;
            }
            else if(cmd[0].equals("add")){
                add(cmd);
                System.out.println();
            }
            else if(cmd[0].equals("list")){
                list(cmd);
                System.out.println();
            }
            else if(cmd[0].equals("save")){
                save(cmd);
                System.out.println();
            }
            else if(cmd[0].equals("load")){
                load(cmd);
                System.out.println();
            }
            else if(cmd[0].equals("search")){
                search(cmd);
                System.out.println();
            }
            else if(cmd[0].equals("find")){
                find(cmd);
                System.out.println();
            }
            else if(cmd[0].equals("delete")){
                delete(cmd);
                System.out.println();
            }
            else{
                System.out.println("Nincs ilyen parancs.");
                System.out.println();
            }
        }

        sc.close();
    }
}
