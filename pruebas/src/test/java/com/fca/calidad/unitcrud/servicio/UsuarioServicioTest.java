package com.fca.calidad.unitcrud.servicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.fca.calidad.unitcrud.dao.IDAOUsuario;
import com.fca.calidad.unitcrud.modelo.Usuario;

@ExtendWith(MockitoExtension.class)
public class UsuarioServicioTest {

    @Mock
    private IDAOUsuario dao;

    private UsuarioServicio usuarioServicio;

    // HashMap que simula la base de datos
    private Map<Integer, Usuario> baseDatos;

    @BeforeEach
    public void setUp() {
        usuarioServicio = new UsuarioServicio(dao);
        baseDatos = new HashMap<>();
    }

    @Test
    public void testCreateUserCaminoFeliz() {
        // Arrange: Datos de entrada válidos para el camino feliz
        String nombre = "Juan Perez";
        String email = "juan@correo.com";
        String password = "password123";

        // Simulación: findUserByEmail busca en nuestro HashMap simulado
        when(dao.findUserByEmail(anyString())).thenAnswer(new Answer<Usuario>() {
            @Override
            public Usuario answer(InvocationOnMock invocation) throws Throwable {
                String emailBuscado = invocation.getArgument(0);
                for (Usuario u : baseDatos.values()) {
                    if (u.getEmail().equals(emailBuscado)) {
                        return u;
                    }
                }
                return null; // No existe previamente
            }
        });

        // Simulación: save guarda el usuario en el HashMap asignándole un ID autoincremental
        when(dao.save(any(Usuario.class))).thenAnswer(new Answer<Integer>() {
            int idContador = 1;

            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Usuario usuarioAGuardar = invocation.getArgument(0);
                int nuevoId = idContador++;
                usuarioAGuardar.setId(nuevoId);
                baseDatos.put(nuevoId, usuarioAGuardar);
                return nuevoId;
            }
        });

        // Act: Ejecutar el método a probar
        Usuario usuarioCreado = usuarioServicio.createUser(nombre, email, password);

        // Assert: Verificaciones con AssertJ
        // 1. El objeto retornado no es nulo y contiene los valores esperados
        assertThat(usuarioCreado).isNotNull();
        assertThat(usuarioCreado.getId()).isEqualTo(1);
        assertThat(usuarioCreado.getNombre()).isEqualTo(nombre);
        assertThat(usuarioCreado.getEmail()).isEqualTo(email);
        assertThat(usuarioCreado.getPassword()).isEqualTo(password);

        // 2. Verificar que el usuario se guardó en el HashMap (base de datos simulada)
        assertThat(baseDatos).hasSize(1);
        assertThat(baseDatos).containsKey(usuarioCreado.getId());
        assertThat(baseDatos.get(usuarioCreado.getId())).isEqualTo(usuarioCreado);

        // 3. Verificar que se invocaron los métodos correspondientes en el DAO
        verify(dao).findUserByEmail(email);
        verify(dao).save(any(Usuario.class));
    }

    @Test
    public void testCreateUserEmailYaExiste() {
        // Arrange: Pre-cargamos un usuario existente en la base de datos simulada (HashMap)
        String email = "juan@correo.com";
        Usuario usuarioExistente = new Usuario("Juan Original", email, "password123", 10);
        baseDatos.put(10, usuarioExistente);

        // Simulación: findUserByEmail busca en el HashMap y encuentra a usuarioExistente
        when(dao.findUserByEmail(anyString())).thenAnswer(new Answer<Usuario>() {
            @Override
            public Usuario answer(InvocationOnMock invocation) throws Throwable {
                String emailBuscado = invocation.getArgument(0);
                for (Usuario u : baseDatos.values()) {
                    if (u.getEmail().equals(emailBuscado)) {
                        return u;
                    }
                }
                return null;
            }
        });

        // Act: Intentamos crear un usuario con el mismo email ya registrado
        Usuario resultado = usuarioServicio.createUser("Juan Nuevo", email, "otraPassword123");

        // Assert: Verificaciones con AssertJ
        // 1. Debe retornar el usuario que ya existía (sin sobreescribir sus datos)
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(10);
        assertThat(resultado.getNombre()).isEqualTo("Juan Original");
        assertThat(resultado.getEmail()).isEqualTo(email);

        // 2. La base de datos simulada no debe haber crecido ni duplicado el registro
        assertThat(baseDatos).hasSize(1);
        assertThat(baseDatos.get(10)).isEqualTo(usuarioExistente);

        // 3. Verificar que se buscó por correo pero NUNCA se llamó a save()
        verify(dao).findUserByEmail(email);
        verify(dao, never()).save(any(Usuario.class));
    }

    @Test
    public void testDeleteUserCaminoFeliz() {
        // Arrange: Pre-cargamos dos usuarios en la base de datos simulada (HashMap)
        Usuario u1 = new Usuario("Juan Perez", "juan@correo.com", "password123", 1);
        Usuario u2 = new Usuario("Maria Lopez", "maria@correo.com", "password456", 2);
        baseDatos.put(1, u1);
        baseDatos.put(2, u2);
        int tamanoInicial = baseDatos.size(); // 2

        // Simulación: findById busca y retorna el usuario del HashMap
        when(dao.findById(anyInt())).thenAnswer(new Answer<Usuario>() {
            @Override
            public Usuario answer(InvocationOnMock invocation) throws Throwable {
                int idBuscado = invocation.getArgument(0);
                return baseDatos.get(idBuscado);
            }
        });

        // Simulación: deleteById elimina el usuario del HashMap y retorna true si se eliminó
        when(dao.deleteById(anyInt())).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                int idAEliminar = invocation.getArgument(0);
                Usuario eliminado = baseDatos.remove(idAEliminar);
                return eliminado != null;
            }
        });

        // Act: Eliminamos al usuario con id 1
        boolean resultado = usuarioServicio.deleteUser(1);

        // Assert: Verificaciones con AssertJ
        // 1. Comprobar que el método retornó true
        assertThat(resultado).isTrue();

        // 2. Comprobar que el tamaño de la base de datos se redujo en 1
        assertThat(baseDatos).hasSize(tamanoInicial - 1);
        assertThat(baseDatos).doesNotContainKey(1);
        assertThat(baseDatos).containsKey(2);

        // 3. Verificar que se invocaron los métodos en el DAO
        verify(dao).findById(1);
        verify(dao).deleteById(1);
    }
}
