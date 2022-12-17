/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import sv.Servicios;
import sv.Servicios_Service;

/**
 *
 * @author ASUS
 */
public class Modelo_Usuario {

    Servicios_Service servicio = new Servicios_Service();
    Servicios cliente = servicio.getServiciosPort();

    public String Login(String user, String password) {
        String userResponse = cliente.login(user, password);
        return userResponse;
    }

    public Boolean Registro(String user, String password, int saldo) {
        boolean successful = cliente.registro(user, password, saldo);
        return true;
    }

    public Boolean Cuenta(String user, int monto_ingre) {
        int saldo_ingre = cliente.cuentaUsuario(user, monto_ingre);
        return true;
    }

}
