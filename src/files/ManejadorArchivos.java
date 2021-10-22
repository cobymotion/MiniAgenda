package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import logica.Contacto;
import vistas.VentanaPrincipal;


public class ManejadorArchivos {
    
    File archivo; 
    
    public ManejadorArchivos(){
        archivo = new File(VentanaPrincipal.nameFile); 
    }
    
    public List<Contacto> leerDatos(){
        List<Contacto> contactos = new ArrayList<>(); 
        if(archivo.exists()){
            try {
                FileInputStream fis = new FileInputStream(archivo); 
                ObjectInputStream ois ; 
                ois = new ObjectInputStream(fis); 
                while(fis.available()>0){                    
                    Contacto persona = (Contacto) ois.readObject(); 
                    contactos.add(persona);                    
                }                    
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        }
        return contactos;
    }
    
    
    public boolean agregarDato(Contacto contacto)
    {
        boolean res = false; 
        try {
            FileOutputStream fos = new FileOutputStream(archivo,true); 
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contacto);
            oos.close();
            fos.close();
            res = true; 
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return res; 
    }
    
    public void agregarDatos(List<Contacto> contactos){
        
        try {
            FileOutputStream fos = new FileOutputStream(archivo); 
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Contacto contacto : contactos) {
                oos.writeObject(contacto);            
            }
            oos.close();
            fos.close();            
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        
    }
}
