/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.Modelo_Usuario;
import vista.CuentaUsuario;
import vista.FormularioAcceso;
import vista.RegistroUsuario;

/**
 *
 * @author ASUS
 */
public class Controlador_Usuario {

    private CuentaUsuario vista_cuenta_usuario;
    private FormularioAcceso vista_formulario_acceso;
    private RegistroUsuario vista_registro_usuario;
    private Modelo_Usuario modelousu;

    public Controlador_Usuario(CuentaUsuario vista_cuenta_usuario, FormularioAcceso vista_formulario_acceso, RegistroUsuario vista_registro_usuario, Modelo_Usuario modelousu) {
        this.vista_cuenta_usuario = vista_cuenta_usuario;
        this.vista_formulario_acceso = vista_formulario_acceso;
        this.vista_registro_usuario = vista_registro_usuario;
        this.modelousu = modelousu;
        vista_formulario_acceso.setVisible(true);
    }

    //Metodo para botones
    public void ControlBoton() {
        vista_formulario_acceso.getBtningresar().addActionListener(l -> Login());
        vista_formulario_acceso.getBtnregistrar().addActionListener(l -> ver_registro());
        vista_registro_usuario.getBtnregistroprincipal().addActionListener(l -> registrousuario());
        vista_registro_usuario.getBtnregresarformulario().addActionListener(l -> volver_Acceso());
        vista_cuenta_usuario.getBtnregistrocuenta().addActionListener(l -> CuentaUsuario());
        vista_cuenta_usuario.getBtnregresarcuenta().addActionListener(l -> volver_Acceso());
    }

    //Metodo para Ingreso Login
    private void Login() {
        String user = vista_formulario_acceso.getTxtusuario().getText();
        String password = vista_formulario_acceso.getPasscontrasena().getText();

        String compararusu = modelousu.Login(user, password);
        if (compararusu.equals("Usuario incorrecto")) {
            JOptionPane.showMessageDialog(null, "Usuario no existente");
        } else {
            vista_cuenta_usuario.setVisible(true);
            vista_formulario_acceso.setVisible(false);
            String[] usuariorecorrido = compararusu.split("/");
            String nombreusuario = usuariorecorrido[0];
            String contrasenarecivida = usuariorecorrido[1];
            int saldo_recivido = Integer.parseInt(usuariorecorrido[2]);

            vista_cuenta_usuario.getLblusuario().setText(nombreusuario);
            vista_cuenta_usuario.getTxtsaldocuenta().setText("" + saldo_recivido);
            limpiarFormulario();
        }

    }

    //Metodo Registro usuario
    private void registrousuario() {
        String user = vista_registro_usuario.getTxtreusuario().getText();
        String clave = vista_registro_usuario.getPassclave().getText();
        String repe_clave = vista_registro_usuario.getPassrepeclave().getText();
        String saldo_ini = vista_registro_usuario.getTxtsaldo().getText();
        if (clave.equals(repe_clave)) {
            if (modelousu.Registro(user, clave, Integer.parseInt(saldo_ini))) {
                limpiarRegistrou();
                JOptionPane.showMessageDialog(vista_registro_usuario, "Usuario creado exitosamente");
            } else {
                JOptionPane.showMessageDialog(vista_registro_usuario, "Error the user exit");
            }

        } else {
            JOptionPane.showMessageDialog(vista_registro_usuario, "La contrasena no es la misma.");

        }
    }

    private void ver_registro() {
        //vista_formulario_acceso.setVisible(false);
        vista_formulario_acceso.dispose();
//        vista_registro_usuario.setSize(429, 374);
        vista_registro_usuario.setVisible(true);
    }

    private void volver_Acceso() {
        vista_registro_usuario.setVisible(false);
        vista_cuenta_usuario.setVisible(false);
        vista_formulario_acceso.setVisible(true);
        vista_registro_usuario.setSize(429, 374);

    }

    private void limpiarRegistrou() {
        vista_registro_usuario.getPassclave().setText("");
        vista_registro_usuario.getPassrepeclave().setText("");
        vista_registro_usuario.getTxtreusuario().setText("");
        vista_registro_usuario.getTxtsaldo().setText("");
    }

    private void limpiarFormulario() {
        vista_formulario_acceso.getTxtusuario().setText("");
        vista_formulario_acceso.getPasscontrasena().setText("");
    }

    private void limpiarCuenta() {
        vista_cuenta_usuario.getTxtsaldocuenta().setText("");
        vista_cuenta_usuario.getTxtsaldodescuento().setText("");
        vista_cuenta_usuario.getButtonGroup1().clearSelection();
    }

    //Metodo Ingresar cuenta.
    private void CuentaUsuario() {
        String usuario_ingre = vista_cuenta_usuario.getLblusuario().getText();
        int saldo_cuenta = Integer.parseInt(vista_cuenta_usuario.getTxtsaldocuenta().getText());

        if (!vista_cuenta_usuario.getRadioRetiro().isSelected() && !vista_cuenta_usuario.getRadioDeposito().isSelected()) {
            JOptionPane.showMessageDialog(vista_cuenta_usuario, "Seleccione el tipo de retiro");
        } else {
            if (vista_cuenta_usuario.getRadioDeposito().isSelected()) {
                int monto_ingresado = Integer.parseInt(vista_cuenta_usuario.getTxtsaldodescuento().getText());

                int nuevosaldo_cuenta = saldo_cuenta + monto_ingresado;

                if (modelousu.Cuenta(usuario_ingre, nuevosaldo_cuenta)) {
                    vista_cuenta_usuario.getTxtsaldocuenta().setText("" + nuevosaldo_cuenta);
                    JOptionPane.showMessageDialog(vista_cuenta_usuario, "Saldo actual en cuenta: " + nuevosaldo_cuenta);
                    limpiarCuenta();
                } else {
                    JOptionPane.showMessageDialog(vista_cuenta_usuario, "Error de cuenta");
                }

            } else if (vista_cuenta_usuario.getRadioRetiro().isSelected()) {
                int valor_arestar = Integer.parseInt(vista_cuenta_usuario.getTxtsaldodescuento().getText());

                if (valor_arestar > saldo_cuenta) {
                    JOptionPane.showMessageDialog(vista_cuenta_usuario, "No posee el suficiente dinero para hacer el retiro.");
                } else {
                    int nuevosaldo_cuenta = saldo_cuenta - valor_arestar;

                    if (modelousu.Cuenta(usuario_ingre, nuevosaldo_cuenta)) {
                        vista_cuenta_usuario.getTxtsaldocuenta().setText("" + nuevosaldo_cuenta);
                        JOptionPane.showMessageDialog(vista_cuenta_usuario, "Saldo actual en cuenta: " + nuevosaldo_cuenta);
                        limpiarCuenta();

                    } else {
                        JOptionPane.showMessageDialog(vista_cuenta_usuario, "Error de cuenta");
                    }

                }
            }
        }
    }

}
