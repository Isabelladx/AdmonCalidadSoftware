package com.fca.calidad;

import com.fca.calidad.unitcrud.dao.DAOUsuarioMySQL;
import com.fca.calidad.unitcrud.dao.IDAOUsuario;
import com.fca.calidad.unitcrud.modelo.Usuario;

public class App {
    public static void main(String[] args) {
        System.out.println("Iniciando inserción en la base de datos...");

        try {
            // Establecer / registrar el conector al driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Instanciar el DAO conectado a MySQL
            IDAOUsuario dao = new DAOUsuarioMySQL();

            // 2. Crear el objeto Usuario con los datos a insertar
            Usuario nuevoUsuario = new Usuario("Isabella Duran", "isabella@correo.com", "password123");

            // 3. Ejecutar el insert a través del método save()
            int idGenerado = dao.save(nuevoUsuario);
            nuevoUsuario.setId(idGenerado);

            System.out.println("¡Usuario insertado exitosamente!");
            System.out.println("ID generado: " + nuevoUsuario.getId());
            System.out.println("Nombre: " + nuevoUsuario.getNombre());
            System.out.println("Email: " + nuevoUsuario.getEmail());
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el driver conector de MySQL: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ocurrió un error al insertar en la base de datos:");
            e.printStackTrace();
        }
    }
}
