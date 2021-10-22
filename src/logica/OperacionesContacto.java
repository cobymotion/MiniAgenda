package logica;

import basedatos.ManejadorBaseDatos;
import files.ManejadorArchivos;
import java.util.ArrayList;
import java.util.List;

public class OperacionesContacto {
    
    public List<Contacto> guardados = 
            new ArrayList<>(); 
    
    public void agregarContacto(Contacto contacto){
        int cons = guardados.size() +1; 
        contacto.num = cons; 
        guardados.add(contacto);
        //ManejadorArchivos archivo = new ManejadorArchivos();
        ManejadorBaseDatos archivo = new ManejadorBaseDatos();
        boolean res = archivo.agregarDato(contacto); 
        if(res)
            System.out.println("Se grabo correctamente");
        else 
            System.out.println("No se grabo");
    }
    
    public void inicializarLista(){
        // ManejadorArchivos archivo = new ManejadorArchivos();
        ManejadorBaseDatos archivo = new ManejadorBaseDatos();
        guardados = archivo.leerDatos();         
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
    
    public Contacto buscarContacto(int num){         
        Contacto contacto = null; 
        for(int i=0;i<guardados.size();i++){
            Contacto c = guardados.get(i); 
            if(c.num==num){
                contacto=c; 
                break; 
            }
        }
        return contacto;
    }

    public void modificarContacto(Contacto contacto) {
        boolean update = false; 
        for(int i=0;i<guardados.size();i++){
            Contacto c = guardados.get(i); 
            if(c.num==contacto.num){
                guardados.set(i, contacto); 
                update = true; 
                break; 
            }
        }
        if(update)
        {
            //ManejadorArchivos archivo = new ManejadorArchivos();
            ManejadorBaseDatos archivo = new ManejadorBaseDatos();
            archivo.agregarDatos(guardados);
        }
    }
}
