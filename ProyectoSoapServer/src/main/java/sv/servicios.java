/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import sv.servicios.usuario;

/**
 *
 * @author ASUS
 */
@WebService(serviceName = "servicios")
public class servicios {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "potencia")
    public Double potencia(@WebParam(name = "base") double base, @WebParam(name = "exponente") double exponente) {
        //TODO write your implementation code here:
        double resultado = Math.pow(base, exponente);
        return resultado;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "par")
    public String par(@WebParam(name = "numero") int numero) {
        //TODO write your implementation code here:

        if (numero % 2 == 0) {
            return "El numero es " + numero + " es par";
        } else {
            return "El numero es " + numero + " es impar";
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "buscarpalabra")
    public String buscarpalabra(@WebParam(name = "palabra") String palabra) {
        //TODO write your implementation code here:
        String salida = null;
        if (palabra == null) {
            salida = "Palabra nula";
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("java", "Lenguaje de progrmacion");
            map.put("procesador", "Decodifica y ejecuta las instrucciones de los programas cargados en la memoria principal del ordenador.");
            map.put("case", "Unidad o Cabina del Sistema es la caja que contiene los componentes internos del computador.");
            map.put("ram", "Memoria de acceso aleatorio, y es uno de los elementos más fundamentales de la informática.");
            map.put("led", "Diodo emisor de luz");
            map.put("placa", "Pieza plana y delgada, generalmente de metal, en la que se graba o escribe algo.");
            map.put("web", "Conjunto de información que se encuentra en una dirección determinada de internet.");
            map.put("angular", "Complemento framework Javascript con el que desarrollar aplicaciones frontend modernas.");
            map.put("regleta", "Pequeña plancha de metal utilizada en imprenta para espaciar la composición de los textos.");
            map.put("algoritmo", "Conjunto ordenado de operaciones sistemáticas que permite hacer un cálculo y hallar la solución de un tipo de problemas.");

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                if (palabra.equalsIgnoreCase(key)) {
                    return val;
                } else {
                    salida = "Palabra no encontrada en el diccionario";
                }

            }

        }
        return salida;

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Resta")
    public int Resta(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2) {
        //TODO write your implementation code here:
        return num1 - num2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Multiplicacion")
    public int Multiplicacion(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2) {
        //TODO write your implementation code here:
        return num1 * num2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Division")
    public int Division(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2) {
        //TODO write your implementation code here:
        return num1 / num2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Seno")
    public Double Seno(@WebParam(name = "num") double num) {
        //TODO write your implementation code here:
        return Math.sin(num);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Coseno")
    public Double Coseno(@WebParam(name = "num") double num) {
        //TODO write your implementation code here:
        return Math.cos(num);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Tangente")
    public Double Tangente(@WebParam(name = "num") double num) {
        //TODO write your implementation code here:
        return Math.tan(num);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Login")
    public String Login(@WebParam(name = "usuario") String usuario, @WebParam(name = "contrasena") String contrasena) {
        //TODO write your implementation code here:
        String usu = "", contra = "";
        int sal = 0;

        boolean salida = false;
        for (int i = 0; i < listau.size(); i++) {
            if (usuario.equalsIgnoreCase(listau.get(i).getUsuarios()) && contrasena.equalsIgnoreCase(listau.get(i).getContrasena())) {
                usu = listau.get(i).getUsuarios();
                contra = listau.get(i).getContrasena();
                sal = (int) listau.get(i).getSaldo_inicial();
                salida = true;

            } else {
                salida = false;
            }
        }
        if (salida == true) {
            return "" + usu + "/" + contra + "/" + sal;
        } else {
            return "Usuario incorrecto";
        }

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Registro")
    public Boolean Registro(@WebParam(name = "user") String user, @WebParam(name = "contrasena") String contrasena, @WebParam(name = "saldo_ini") int saldo_ini) {
        //TODO write your implementation code here:
        Boolean dato_encontrado = false;

        for (int i = 0; i < listau.size(); i++) {

            if (user.equals(listau.get(i).getUsuarios())) {
                dato_encontrado = true;
            }
        }
        if (dato_encontrado == true) {
            return false;
        } else {
            usuario usu = new usuario();
            usu.setUsuarios(user);
            usu.setContrasena(contrasena);
            usu.setSaldo_inicial(saldo_ini);
            listau.add(usu);
            return true;
        }

    }
    
    

    ArrayList<usuario> listau = new ArrayList<>();

    class usuario {

        private String usuarios;
        private String contrasena;
        private int saldo_inicial;

        public usuario(String usuario, String contrasena, int saldo_inicial) {
            this.usuarios = usuarios;
            this.contrasena = contrasena;
            this.saldo_inicial = saldo_inicial;
        }

        public usuario(String usuarios) {
            this.usuarios = usuarios;
        }

        public usuario() {
        }

        public String getUsuarios() {
            return usuarios;
        }

        public void setUsuarios(String usuarios) {
            this.usuarios = usuarios;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }

        public int getSaldo_inicial() {
            return saldo_inicial;
        }

        public void setSaldo_inicial(int saldo_inicial) {
            this.saldo_inicial = saldo_inicial;
        }

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "CuentaUsuario")
    public int CuentaUsuario(@WebParam(name = "usuario") String usuario, @WebParam(name = "saldo_final") int saldo_final) {
        //TODO write your implementation code here:
        
        boolean sal_dato = true;
        for (int i = 0; i < listau.size(); i++) {
            if(usuario.equals(listau.get(i).getUsuarios())){
                  listau.get(i).setSaldo_inicial(saldo_final);
                  sal_dato = true;
            }
        }
        if(sal_dato){
            return saldo_final;
        }else{
            return 0;
        }
        
    }


}
