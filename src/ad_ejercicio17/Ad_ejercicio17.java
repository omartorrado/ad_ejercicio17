/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_ejercicio17;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author otorradomiguez
 */
public class Ad_ejercicio17 {

    static String rutaXML = "/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/ad_ejercicio16/produtos.xml";
    static XMLStreamReader xmlsr;
    static XMLInputFactory xmlif = XMLInputFactory.newInstance();
    static ArrayList listaProdutos = new ArrayList();

    public static void main(String[] args) {
        conectar(rutaXML);
        leerXML();
    }

    public static void conectar(String ruta) {
        try {
            xmlsr = xmlif.createXMLStreamReader(new FileReader(ruta));
        } catch (XMLStreamException ex) {
            Logger.getLogger(Ad_ejercicio17.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ad_ejercicio17.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void leerXML() {
        try {
            Product tempProduct=null;
            while(xmlsr.hasNext()) {
                xmlsr.next();
                if(xmlsr.isStartElement()){
                    if(xmlsr.getLocalName().equals("Product")){
                        tempProduct=new Product();
                    }else if(xmlsr.getLocalName().equals("codigo")){
                        tempProduct.setCodigo(xmlsr.getElementText());
                    }else if(xmlsr.getLocalName().equals("descricion")){
                        tempProduct.setDescricion(xmlsr.getElementText());
                    }else if(xmlsr.getLocalName().equals("prezo")){
                        tempProduct.setPrezo(Double.parseDouble(xmlsr.getElementText()));                        
                        listaProdutos.add(tempProduct);
                    }                
                }                
            }
            for(Object b:listaProdutos){
                System.out.println(b.toString());
            }
        } catch (XMLStreamException ex) {
            Logger.getLogger(Ad_ejercicio17.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
