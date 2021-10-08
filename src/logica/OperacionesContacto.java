package logica;

import java.util.ArrayList;
import java.util.List;

public class OperacionesContacto {
    
    public List<Contacto> guardados = 
            new ArrayList<>(); 
    
    public void agregarContacto(Contacto contacto){
        int cons = guardados.size() +1; 
        contacto.num = cons; 
        guardados.add(contacto); 
    }
    
    public String[][] obtenerContactos(){
        String[][] listaContactos = new 
                 String[guardados.size()][3];
        for(int i=0;i<guardados.size();i++){
            Contacto c = guardados.get(i); 
            listaContactos[i][0] = "" + c.num;
            listaContactos[i][1] = c.nombre;
            listaContactos[i][2] = c.telefono;
        }
        return listaContactos;
    }
    
    public String[][] obtenerContactos(String filtro){
        String[][] listaContactos = new 
                 String[guardados.size()][3];
        int indice = 0; 
        for(int i=0;i<guardados.size();i++){
            Contacto c = guardados.get(i); 
            if(c.nombre.contains(filtro)){
                listaContactos[indice][0] = "" + c.num;
                listaContactos[indice][1] = c.nombre;
                listaContactos[indice][2] = c.telefono;
                indice ++; 
            }
        }
        return listaContactos;
    }
}
