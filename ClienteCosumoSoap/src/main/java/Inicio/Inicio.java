/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import controlador.Controlador_Usuario;
import modelo.Modelo_Usuario;
import vista.CuentaUsuario;
import vista.FormularioAcceso;
import vista.RegistroUsuario;

/**
 *
 * @author ASUS
 */
public class Inicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Modelo_Usuario modelousu = new Modelo_Usuario();
        RegistroUsuario vista_registro_usuario = new RegistroUsuario();
        CuentaUsuario vista_cuenta_usuario = new CuentaUsuario();
        FormularioAcceso  vista_formulario_acceso = new FormularioAcceso ();
        Controlador_Usuario controlmp = new Controlador_Usuario(vista_cuenta_usuario, vista_formulario_acceso, vista_registro_usuario, modelousu);
        controlmp.ControlBoton();
        
 
    }
    
}
